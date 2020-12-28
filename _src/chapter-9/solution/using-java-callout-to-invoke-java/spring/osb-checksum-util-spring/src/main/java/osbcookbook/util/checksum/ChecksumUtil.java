package osbcookbook.util.checksum;

import java.util.zip.Checksum;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChecksumUtil {
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	
	public static long calculateChecksum(String data, String strategy) {
		Checksum checksum = null;
		
		ChecksumConfiguration configuredImplementation = (ChecksumConfiguration)ctx.getBean("configuration");
		if (strategy == null || strategy.isEmpty()) {
			checksum = (Checksum)ctx.getBean(configuredImplementation.getChecksumImplementation());
		} else {
			checksum = (Checksum)ctx.getBean(strategy);
		}
		checksum.update(data.getBytes(), 0, data.getBytes().length);
		return checksum.getValue();
	}
}
