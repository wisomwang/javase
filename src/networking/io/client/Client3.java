package networking.io.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import networking.io.Util;

/**
 * 介绍了连接时的一些异常情况;当server端中途关闭时，客户端socket的行为;
 * 
 * @author smwang
 * 
 */
public class Client3 {
	private static final int PORT = 8088;
	private static final String LOCALHOST = "localhost";
	private Socket socket;

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client3().communicate();

	}

	public Client3() throws UnknownHostException, IOException {
		socket = new Socket();

		// 默认的缓冲大小是8192个字节
		System.out.println("Default send buffer size is " + socket.getSendBufferSize());

		// 设置连接超时时间，当主机名是存在的情况下，如果连接超时，就会报SocketTimeOutException
		// 如果主机名找不到，就会报UnknownHostException
		// 如果指定的端口没有在监听或者客户端的连接数超过了服务端设置的最大连接数，会报ConnectionException
		socket.connect(new InetSocketAddress(LOCALHOST, PORT), 5000);
		System.out.println("Connected to remote host:" + socket.getRemoteSocketAddress());

	}

	private void communicate() throws IOException {
		PrintWriter writer = Util.getWriter(socket);
		try {
			writer.println("Hello Server");
			Thread.sleep(200);
			writer.println("Did you received");
			Thread.sleep(200);
			writer.println("I am leaving");
			// writer.flush();

			// 如果在等待的过程中，server强行关闭了，下面的数据还是会发送，因为底层的 socket并没有立即释放本地端口
			Thread.sleep(10000);
			writer.println("leaved");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		writer.flush();

		socket.close();
	}
}
