package networking.url.customecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ContentHandler;
import java.net.URLConnection;

public class EchoContentHandler extends ContentHandler {

	/**
	 * 返回是字符串
	 */
	@Override
	public Object getContent(URLConnection urlc) throws IOException {
		InputStream in = urlc.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		return reader.readLine();
	}

	@Override
	public Object getContent(URLConnection urlc, @SuppressWarnings("rawtypes") Class[] classes) throws IOException {
		InputStream in = urlc.getInputStream();
		for (int i = 0; i < classes.length; i++) {
			if (classes[i].isInstance(InputStream.class)) {
				return in;
			} else if (classes[i] == String.class) {
				return getContent(urlc);
			}
		}
		return null;
	}
}
