package networking.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Client1 {

	private SocketChannel channel;
	private Selector selector;

	public static void main(String[] args) {
		new Client1().communicate();
	}

	public Client1() {
		try {
			selector = Selector.open();

			channel = SocketChannel.open();
			// channel.socket().connect(new InetSocketAddress("localhost",
			// 8088));
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("localhost", 8088));
			System.out.println("Client build connection with server:" + channel.getRemoteAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void communicate() {
		try {
			channel.register(selector, SelectionKey.OP_CONNECT);

			//selector.select() > 0会一直满足条件，一直打印connected
//			while (selector.select() > 0) {
//				Set<SelectionKey> selectedKeys = selector.selectedKeys();
//				Iterator<SelectionKey> iterator = selectedKeys.iterator();
//
//				while (iterator.hasNext()) {
//					SelectionKey selectionKey = iterator.next();
//					iterator.remove();
//
//					if (selectionKey.isConnectable()) {
//						if (channel.isConnectionPending()) {
//							System.out.println("connected");
//						}
//					} else if (selectionKey.isReadable()) {
//
//					} else if (selectionKey.isWritable()) {
//						System.out.println("writing");
//					}
//				}
//			}

			//如果注册了OP_WRITE事件，第二次循环直接结束
//			while (selector.select() > 0) {
//				Set<SelectionKey> selectedKeys = selector.selectedKeys();
//				Iterator<SelectionKey> iterator = selectedKeys.iterator();
//
//				while (iterator.hasNext()) {
//					SelectionKey selectionKey = iterator.next();
//					iterator.remove();
//
//					if (selectionKey.isConnectable()) {
//						if (channel.isConnectionPending()) {
//						}
//						channel.register(selector, SelectionKey.OP_WRITE);
//					} else if (selectionKey.isReadable()) {
//
//					} else if (selectionKey.isWritable()) {
//						System.out.println("writing");
//					}
//				}
//			}
			
			//一直输出writing
//			while (selector.select() > 0) {
//				Set<SelectionKey> selectedKeys = selector.selectedKeys();
//				Iterator<SelectionKey> iterator = selectedKeys.iterator();
//
//				while (iterator.hasNext()) {
//					SelectionKey selectionKey = iterator.next();
//					iterator.remove();
//
//					if (selectionKey.isConnectable()) {
//						if (channel.isConnectionPending()) {
//							channel.finishConnect();
//						}
//						channel.register(selector, SelectionKey.OP_WRITE);
//					} else if (selectionKey.isReadable()) {
//
//					} else if (selectionKey.isWritable()) {
//						System.out.println("writing");
//					}
//				}
//			}
			
			//第二次循环阻塞等待读
			while (selector.select() > 0) {
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();

				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					iterator.remove();

					if (selectionKey.isConnectable()) {
						if (channel.isConnectionPending()) {
							channel.finishConnect();
						}
						channel.register(selector, SelectionKey.OP_READ);
					} else if (selectionKey.isReadable()) {
						System.out.println("reading");
					} else if (selectionKey.isWritable()) {
						System.out.println("writing");
					}
				}
			}

		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		System.out.println("end");
	}
}
