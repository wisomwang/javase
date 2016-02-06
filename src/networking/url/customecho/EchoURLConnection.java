package networking.url.customecho;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

/**
 * 自定义Echo协议，客户发过来message,回应echo:message
 * 
 * @author smwang
 * 
 */
public class EchoURLConnection extends URLConnection {

	private static final int DEFAULT_PORT = 8088;
	private Socket connection;

	protected EchoURLConnection(URL url) {
		super(url);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream getInputStream() throws IOException {
		if (!connected) {
			connect();
		}
		return connection.getInputStream();
	}

	/**
	 * 需要override
	 */
	@Override
	public String getContentType() {
		return "text/plain";
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		if (!connected) {
			connect();
		}
		return connection.getOutputStream();
	}

	@Override
	public void connect() throws IOException {
		if (!connected) {
			int port = url.getPort();
			if (port < 0 || port > 65535) {
				port = DEFAULT_PORT;
			}
			this.connection = new Socket(url.getHost(), port);
			this.connected = true;
		}
	}

	public void disconnect() throws IOException {
		if (connected) {
			this.connection.close();
			this.connected = false;
		}
	}
}
