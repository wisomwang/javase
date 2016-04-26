package networking.aio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class Server1 {

	private AsynchronousServerSocketChannel channel;
	private Selector selector;

	public static void main(String[] args) {
		new Server1().communicate();
	}

	public Server1() {
		try {
			selector = Selector.open();

			channel = AsynchronousServerSocketChannel.open();
			channel.bind(new InetSocketAddress(8088));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server is starting to accetp the request...");
	}

	private void communicate() {
		channel.accept();
	}

	private void doWriter(SocketChannel socketChannel, String request) throws IOException {
		if ("QUERY TIME".equals(request)) {
			Date date = new Date();
			ByteBuffer writeBuffer = ByteBuffer.allocate(date.toString().getBytes().length);
			writeBuffer.put(date.toString().getBytes());
			writeBuffer.flip();
			while (writeBuffer.hasRemaining()) {
				socketChannel.write(writeBuffer);
			}
		}
	}
}
