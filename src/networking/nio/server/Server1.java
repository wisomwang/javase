package networking.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server1 {

	private ServerSocketChannel channel;
	private Selector selector;

	public static void main(String[] args) {
		new Server1().communicate();
	}

	public Server1() {
		try {
			selector = Selector.open();

			channel = ServerSocketChannel.open();
			channel.configureBlocking(false);
			channel.socket().bind(new InetSocketAddress(8088));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server is starting to accetp the request...");
	}

	private void communicate() {
		try {
			channel.register(selector, SelectionKey.OP_ACCEPT);

			while (selector.select() > 0) {
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();

				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();

					if (selectionKey.isAcceptable()) {
						ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
						SocketChannel socketChannel = (SocketChannel) ssc.accept();

						System.out.println("Receive client connection : " + socketChannel.socket().getInetAddress() + " " +socketChannel.socket().getPort());
						
						socketChannel.configureBlocking(false);
						//阻塞模式不能register
						socketChannel.register(selector, SelectionKey.OP_WRITE);
					} else if (selectionKey.isReadable()) {

					} else if (selectionKey.isWritable()) {

					}
				}
			}

		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
