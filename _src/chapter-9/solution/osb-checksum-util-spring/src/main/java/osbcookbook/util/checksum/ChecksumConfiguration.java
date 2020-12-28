package osbcookbook.util.checksum;


public class ChecksumConfiguration {
	private String checksumImplementation = "crc32";

	public String getChecksumImplementation() {
		return checksumImplementation;
	}

	public void setChecksumImplementation(String checksumImplementation) {
		this.checksumImplementation = checksumImplementation;
	}
	
	
}
