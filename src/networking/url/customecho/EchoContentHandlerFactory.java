package networking.url.customecho;

import java.net.ContentHandler;
import java.net.ContentHandlerFactory;

public class EchoContentHandlerFactory implements ContentHandlerFactory {

	@Override
	public ContentHandler createContentHandler(String mimetype) {
		if ("text/plain".equals(mimetype)) {
			return new EchoContentHandler();
		}

		return null;
	}

}
