package osbcookbook.util.checksum;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class ChecksumUtil {
	public static long calculateChecksum(String data) {
		Checksum checksum = new CRC32();
		checksum.update(data.getBytes(), 0, data.getBytes().length);
		return checksum.getValue();
	}
}
