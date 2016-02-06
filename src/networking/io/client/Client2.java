package networking.io.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import networking.io.Util;

/**
 * 服务端通过InputStream读数据
 * 客户端通过输出数据时不一定需要输出换行符
 * 
 * @author smwang
 * 
 */
public class Client2 {
	private static final int PORT = 8088;
	private static final String LOCALHOST = "localhost";
	private Socket socket;

	public static void main(String[] args) throws UnknownHostException, IOException {
		 new Client2().communicate();
	}

	public Client2() throws UnknownHostException, IOException {
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
		OutputStream output = socket.getOutputStream();

		// 服务端可以收到
		byte[] buffer = "hello server\r\n".getBytes();
		output.write(buffer);

		// 服务端可以收到，没有换行结束符
		output.write("hello server again".getBytes());

		//通过PrintWriter输出时，必须要flush，有没有换行符不要紧，换行符取决于接收端的方式
		PrintWriter writer = Util.getWriter(socket);
		writer.println("you are from south korea\r\n");
		writer.flush();
		
		socket.close();
	}

}
