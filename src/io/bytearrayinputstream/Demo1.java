package io.bytearrayinputstream;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Demo1 {

	public static void main(String[] args) throws Exception {
		read();
		write();
	}

	private static void write() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffer = "我来自大中国\r\n,你来自USA".getBytes();

		/** 把字节数组写入到字节数组输出流 */
		bos.write(buffer, 0, buffer.length);

		// bos.write(buffer);//该 方法与上面的方法内部处理过程还是不一样的

		/** ByteArrayOutputStream新方法toByteArray(),把字节数组输出流中的内容输出到字节数组中 */
		byte[] dest = bos.toByteArray();
		System.out.println(new String(dest));
	}

	private static void read() throws IOException {
		String msg = "我来自大中国\r\n,你来自USA";
		byte[] buf = msg.getBytes();

		BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(buf, 0, buf.length));

		byte[] flush = new byte[1024];
		int len = 0;
		while (-1 != (len = bis.read(flush, 0, flush.length))) {
			System.out.println(new String(flush, 0, len));
		}
	}

}
