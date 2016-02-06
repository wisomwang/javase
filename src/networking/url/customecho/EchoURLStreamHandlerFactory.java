package networking.url.customecho;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class EchoURLStreamHandlerFactory implements URLStreamHandlerFactory {

	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		if ("echo".equals(protocol)) {
			return new EchoURLStreamHandler();
		} else {
			return null;
		}
	}

}
