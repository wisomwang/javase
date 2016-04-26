package networking.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
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
			channel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server is starting to accetp the request...");
	}

	private void communicate() {

		while (true) {
			SelectionKey selectionKey = null;
			try {
				selector.select();

				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();

				while (iterator.hasNext()) {
					selectionKey = iterator.next();
					iterator.remove();

					if (selectionKey.isAcceptable()) {
						ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
						SocketChannel socketChannel = (SocketChannel) ssc.accept();

						System.out.println("Receive client connection : " + socketChannel.socket().getInetAddress()
								+ " " + socketChannel.socket().getPort());

						socketChannel.configureBlocking(false);
						// 阻塞模式不能register
						socketChannel.register(selector, SelectionKey.OP_READ);
					} else if (selectionKey.isReadable()) {
						SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
						ByteBuffer readBuffer = ByteBuffer.allocate(1024);
						while (socketChannel.read(readBuffer) > 0) {
							readBuffer.flip();
							byte[] readBytes = new byte[readBuffer.remaining()];
							readBuffer.get(readBytes);
							System.out.println("Server receive request:" + new String(readBytes));

							doWriter(socketChannel, new String(readBytes));
						}
					} else if (selectionKey.isWritable()) {
						// SocketChannel socketChannel = (SocketChannel)
						// selectionKey.channel();
						// ByteBuffer src = ByteBuffer.allocate(128);
						// src.put("hello client".getBytes());
						// src.flip();
						//
						// socketChannel.write(src);
						// System.out.println("server write");
						// selectionKey.cancel();

					}
				}
			} catch (Exception e) {
				if (selectionKey != null) {
					selectionKey.cancel();
					if (selectionKey.channel() != null) {
						try {
							selectionKey.channel().close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			} finally {
			}
		}

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
