package networking.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Client1 {

	private SocketChannel socketChannel;
	private Selector selector;
	
	private boolean running = true;

	public static void main(String[] args) {
		new Client1().communicate();
	}

	public Client1() {
		try {
			selector = Selector.open();

			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void communicate() {
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
			
		// 第二次循环阻塞等待读
		try {
			doConnect();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		
		while (running) {
			SelectionKey selectionKey = null;
			try {
				selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();

				while (iterator.hasNext()) {
					selectionKey = iterator.next();
					iterator.remove();

					SocketChannel sc = (SocketChannel) selectionKey.channel();

					if (selectionKey.isConnectable()) {
						if (socketChannel.finishConnect()) {
							socketChannel.register(selector, SelectionKey.OP_READ);
							doWrite(sc);
						} else {
							System.out.println("Connection failed");
							System.exit(1);
						}

					} else if (selectionKey.isReadable()) {
						SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
						ByteBuffer readBuffer = ByteBuffer.allocate(1024);
						while (socketChannel.read(readBuffer) > 0) {
							readBuffer.flip();
							byte[] readBytes = new byte[readBuffer.remaining()];
							readBuffer.get(readBytes);
							System.out.println("Client receive response:" + new String(readBytes));
						}
						running = false;
					} else if (selectionKey.isWritable()) {
						System.out.println("client writing");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				
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
				running = false;
			} finally {
			}
		}
		
		if(selector!=null)
		{
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("end");
	}

	private void doConnect() throws IOException {
		// 如果连接成功，注册读事件
		if (socketChannel.connect(new InetSocketAddress("localhost", 8088))) {
			socketChannel.register(selector, SelectionKey.OP_READ);
			System.out.println("Client build connection with server:" + socketChannel.getRemoteAddress());

			// 执行写，但这里的写可能写半包，不完整
			doWrite(socketChannel);
		} else {
			// 如果这时候连接不成功，注解连接事件
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
		}
	}

	private void doWrite(SocketChannel channel) throws IOException {
		byte[] request = "QUERY TIME".getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(request.length);
		writeBuffer.put(request);
		writeBuffer.flip();
		while (writeBuffer.hasRemaining()) {
			channel.write(writeBuffer);
		}
	}
}
