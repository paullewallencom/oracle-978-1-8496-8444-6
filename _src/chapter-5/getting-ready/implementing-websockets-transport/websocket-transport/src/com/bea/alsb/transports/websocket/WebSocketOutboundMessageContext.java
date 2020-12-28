package com.bea.alsb.transports.websocket;

import com.bea.wli.sb.sources.PassThruSource;
import com.bea.wli.sb.sources.Source;
import com.bea.wli.sb.sources.StreamSource;
import com.bea.wli.sb.sources.XmlObjectSource;
import com.bea.wli.sb.transports.*;
import org.apache.xmlbeans.XmlObject;
import osb.cookbook.websocket.OsbCookbookServletContext;

import java.io.InputStream;
import java.net.Socket;
import java.net.URI;
import java.util.Random;

/**
 * This class provides transport level message context abstraction for an
 * outgoing message.
 */
public class WebSocketOutboundMessageContext implements OutboundTransportMessageContext
{
    private TransportSender sender;
    private TransportOptions options;
    private String msgId;
    private EndPointConfiguration config;
    private InputStream responseIS;
    private WebSocketResponseMetaData responseMetadata;

    /**
     * Initializes the field variables. sender should be either {@link
     * com.bea.wli.sb.transports.ServiceTransportSender} or {@link
     * com.bea.wli.sb.transports.NoServiceTransportSender}.
     *
     * @param sender
     * @param options
     * @throws com.bea.wli.sb.transports.TransportException
     *          if the given sender type is neither
     *          ServiceTransportSender nor NoServiceTransportSender.
     */
    public WebSocketOutboundMessageContext(TransportSender sender,
                                           TransportOptions options) throws TransportException
    {
        this.msgId = new Random().nextInt() + "." + System.nanoTime();
        this.sender = sender;
        this.options = options;
        if (sender instanceof ServiceTransportSender) {
            this.config = ((ServiceTransportSender) sender).getEndPoint().getConfiguration();
        } else if (sender instanceof NoServiceTransportSender) {
            this.config = ((NoServiceTransportSender) sender).getEndPointConfiguration();
        } else {
            throw new TransportException(WebSocketTransportMessagesLogger.illegalTransportSender());
        }
    }


    /**
     * @return the meta-data for the response part of the message, e.g. headers,
     *         etc. Returns null if there is no response meta-data
     */
    public ResponseMetaData getResponseMetaData() throws TransportException {
        return responseMetadata;
    }

    /**
     * Returns the Source of the response stream.
     *
     * @return
     * @throws TransportException
     */
    public Source getResponsePayload() throws TransportException {
        return responseIS == null ? null : new StreamSource(responseIS);
    }

    /**
     * @return the base uri for to which the message was sent for an outbound
     *         message or from which the message was sent on an inbound message
     */
    public URI getURI() {
        return options.getURI();
    }

    /**
     * @return returns transport provider-specific message identifier. Ideally it
     *         should uniquely identify the message among other messages going
     *         through the ALSB runtime, However, ALSB does not depend on the
     *         message Id being unique. The message Id will be added to the
     *         message context and thus visible in the pipeline.
     */
    public String getMessageId() {
        return msgId;
    }

    /**
     * Sends the message to the external service, schedules a Runnable which sets
     * the response metadata and reads the response from the external service.
     *
     * @param listener
     */
    public void send(final TransportSendListener listener) throws TransportException
    {
        try {
            // Send the message to the external service.
            Source source = sender.getPayload();
            if (source instanceof PassThruSource)
                source = ((PassThruSource) source).getSource();

            XmlObject x = ((XmlObjectSource)source).getXmlObject();

            WebSocketTransportEndPoint endPoint = (WebSocketTransportEndPoint) ((ServiceTransportSender) sender).getEndPoint();

            OsbCookbookServletContext.getInstance().update(x, endPoint.getServletName());

            WebSocketTransportMessagesLogger.flushed();

            WebSocketEndpointConfiguration wsEpConfig = WebSocketTransportUtil.getConfig(config);
            PipelineAcknowledgementTask task = new PipelineAcknowledgementTask(listener, null, wsEpConfig);
            TransportManagerHelper.schedule(task, wsEpConfig.getDispatchPolicy());

        } catch (TransportException e) {
            WebSocketTransportUtil.logger.error(e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            WebSocketTransportUtil.logger.error(e.getLocalizedMessage());
            throw new TransportException(e.getMessage(), e);
        }
    }

    /**
     * This task does the acknowledgment work of the outbound to the pipeline.
     */
    class PipelineAcknowledgementTask implements Runnable {
        private TransportSendListener listener;
        private Socket clientSocket;
        private WebSocketEndpointConfiguration epc;

        public PipelineAcknowledgementTask(TransportSendListener listener,
                                           Socket clientSocket,
                                           WebSocketEndpointConfiguration epc) {
            this.listener = listener;
            this.clientSocket = clientSocket;
            this.epc = epc;
        }

        /**
         * It reads the response sent from the external service, sets the headers
         * and invokes the pipeline.
         */
        public void run() {
            //try {
                // if the end-point is one-way, don't read the response.
              //  if (!epc.getRequestResponse()) {
                    WebSocketTransportMessagesLogger.oneWayEndpoint();
                    listener.onReceiveResponse(WebSocketOutboundMessageContext.this);
                    return;
                //}

                /*
                String resEnc = getResponseEncoding();
                responseMetadata = new WebSocketResponseMetaData(resEnc);
                InetAddress inetAddress = clientSocket.getInetAddress();
                responseMetadata.setEndPointHost(inetAddress.getHostName());
                responseMetadata.setEndPointIP(inetAddress.getHostAddress());

                // Reading the response from the external service.
                InputStream inputStream = clientSocket.getInputStream();
                InputStreamReader inputStreamReader =
                        new InputStreamReader(inputStream, resEnc);
                int i = -1;
                StringBuilder sb = new StringBuilder();
                char[] buff = new char[512];
                while (true) {
                    i = inputStreamReader.read(buff);
                    if (i == -1) {
                        break;
                    }
                    sb.append(buff, 0, i);
                    / * * if it ends with double CRLF, come out. We can read the content
                     * after  \r\n\r\n becuase we are expecting only one message per
                     * connection i.e we are closing the connection after processing a
                     * single message.
                     * /
                    if ((i = sb.indexOf(WebSocketTransportUtil.D_CRLF)) != -1) {
                        break;
                    }
                }
                if (i != -1) {
                    // strip \r\n\r\n from the message.
                    String msg = sb.substring(0, i);
                    responseIS = new ByteArrayInputStream(msg.getBytes(resEnc));
                    listener.onReceiveResponse(WebSocketOutboundMessageContext.this);
                } else {
                    // Message format is wrong, it should end with \r\n\r\n
                    listener.onError(WebSocketOutboundMessageContext.this,
                            TransportManager.TRANSPORT_ERROR_GENERIC,
                            WebSocketTransportMessagesLogger.invalidMessage());
                }
            } catch (IOException e) {
                WebSocketTransportUtil.logger.error(e.getLocalizedMessage(), e);
                listener.onError(WebSocketOutboundMessageContext.this,
                        TransportManager.TRANSPORT_ERROR_GENERIC, e.getLocalizedMessage());
            } catch (TransportException trex) {
                WebSocketTransportUtil.logger.error(trex.getLocalizedMessage(), trex);
                listener.onError(WebSocketOutboundMessageContext.this,
                        TransportManager.TRANSPORT_ERROR_GENERIC, trex.getLocalizedMessage());

            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    WebSocketTransportUtil.logger.error(e.getLocalizedMessage(), e);
                }
            }
            */
        }
    }
}
