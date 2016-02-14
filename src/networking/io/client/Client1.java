package networking.io.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import networking.io.Util;

/**
 * 客户端通过PrintWriter输出数据 服务端通过BufferedReader读数据
 * 
 * 这时需要知道，BufferedReader读数据时需要换行符的，所以客户端输出数据时必须输出换行符，即 writer.print(line +
 * "\r\n") or writer.println(line),随后要跟writer.flush()
 * 
 * @author smwang
 * 
 */
public class Client1 {
	private static final int PORT = 8088;
	private static final String LOCALHOST = "localhost";
	private Socket socket;

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client1().communicate();
	}

	public Client1() throws UnknownHostException, IOException {
		socket = new Socket();

		// 默认的缓冲大小是8192个字节
		System.out.println("Default send buffer size is " + socket.getSendBufferSize());

		// 通过如下设置缓冲区没有什么作用
		// socket.setSendBufferSize(20*1024);
		// 设置连接超时时间，当主机名是存在的情况下，如果连接超时，就会报SocketTimeOutException
		// 如果主机名找不到，就会报UnknownHostException
		// 如果指定的端口没有在监听或者客户端的连接数超过了服务端设置的最大连接数，会报ConnectionException
		socket.connect(new InetSocketAddress(LOCALHOST, PORT), 5000);
		System.out.println("Connected to remote host:" + socket.getRemoteSocketAddress());

	}

	private void communicate() throws IOException {
		// PrintWriter里面有一个BufferedWriter缓冲是8192
		// BufferedWriter里面有一个OutputStreamWriter缓冲是1024
		PrintWriter writer = Util.getWriter(socket);

		BufferedReader readerLocal = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		// 控制台输入，按回车返回一行的值给line
		while ((line = readerLocal.readLine()) != null && line.length() != 0) {
			if ("bye".equals(line)) {
				break;
			}

			writer.println(line);
			// writer.print(line + "\r\n");

			// 会最终把socketOutputStream中的流刷出去,服务端才能接收到
			writer.flush();
		}

		socket.close();
	}

	/**
	 * 超出发送缓冲区的大小后自动刷新流从而可以让服务器端接收到
	 * 
	 * @throws IOException
	 */
	private void communicate2() throws IOException {
		// PrintWriter里面持有一个BufferedWriter缓冲是8192,当输出的数据大小超过这个数时，会自动刷新输出流，意味着服务端
		// 可以收到了，但这个大小貌似修改不了,socket.setSendBufferSize(20*1024);不会影响这个值

		// BufferedWriter里面有一个OutputStreamWriter缓冲是1024
		PrintWriter writer = Util.getWriter(socket);

		BufferedReader readerLocal = new BufferedReader(new InputStreamReader(
				new FileInputStream("D:\\hm-webapp.log.2")));
		String line = null;

		// 控制台输入，按回车返回一行的值给line
		while ((line = readerLocal.readLine()) != null && line.length() != 0) {

			System.out.println("line=" + line);
			writer.println(line);
			writer.flush();
		}
		readerLocal.close();
		socket.close();
	}
	
	/**
	 * 超出发送缓冲区的大小后自动刷新流从而可以让服务器端接收到
	 * 
	 * @throws IOException
	 */
	private void communicate3() throws IOException {
		BufferedWriter writer = Util.getBufferedWriter(socket);

		BufferedReader readerLocal = new BufferedReader(new InputStreamReader(
				new FileInputStream("D:\\hm-webapp.log.2")));
		String line = null;

		// 控制台输入，按回车返回一行的值给line
		while ((line = readerLocal.readLine()) != null && line.length() != 0) {

			System.out.println("line=" + line);
			writer.write(line);
			writer.newLine();
//			writer.write(line + "\r\n");
			
			//不用flush，超出缓冲区大小时，缓冲区会自动刷新,刷到操作系统用于写到网络中 
			writer.flush();
		}
		readerLocal.close();
		socket.close();
	}
}
