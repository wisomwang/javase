package networking.url.customecho;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class EchoURLStreamHandler extends URLStreamHandler {

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		return new EchoURLConnection(url);
	}

	@Override
	protected int getDefaultPort() {
		System.out.println("getDefaultPort() is called.");
		return 8088;
	}
}
