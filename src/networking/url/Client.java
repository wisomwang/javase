package networking.url;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 启动服务Server3
 * 
 * @author smwang
 * 
 */
public class Client {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://localhost:8088/hello.html");

		InputStream in = url.openStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		byte[] buff = new byte[1024];
		int len = -1;
		while ((len = in.read(buff)) != -1) {
			buffer.write(buff, 0, len);
		}
		System.out.println(buffer.toString());
	}
}
