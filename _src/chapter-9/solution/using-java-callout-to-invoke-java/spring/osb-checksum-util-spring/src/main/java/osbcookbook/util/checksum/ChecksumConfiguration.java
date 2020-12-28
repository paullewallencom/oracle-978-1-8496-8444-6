package osbcookbook.util.checksum;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

public class ChecksumConfiguration {
	private String checksumImplementation = "crc32";

	@ManagedAttribute
	public String getChecksumImplementation() {
		return checksumImplementation;
	}

	@ManagedAttribute
	public void setChecksumImplementation(String checksumImplementation) {
		this.checksumImplementation = checksumImplementation;
	}
	
	
}
