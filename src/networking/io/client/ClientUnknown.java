package networking.io.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import networking.io.Util;

public class ClientUnknown {
	private static final int PORT = 8088;
	private static final String HOST = "localhost";
	private Socket socket;

	public static void main(String[] args) throws UnknownHostException, IOException {
		new ClientUnknown().communicate();

	}

	public ClientUnknown() throws UnknownHostException, IOException {
		socket = new Socket();

		// 默认的缓冲大小是8192个字节
		System.out.println("Default send buffer size is " + socket.getSendBufferSize());
		socket.setSendBufferSize(5);

		// 设置连接超时时间，当主机名是存在的情况下，如果连接超时，就会报SocketTimeOutException
		// 如果主机名找不到，就会报UnknownHostException
		// 如果指定的端口没有在监听或者客户端的连接数超过了服务端设置的最大连接数，会报ConnectionException
		socket.connect(new InetSocketAddress(HOST, PORT));
		System.out.println("Connected to remote host:" + socket.getRemoteSocketAddress());

	}

	private void communicate() throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("GET" + " " + "/index.jsp" + " " + "HTTP/1.1\r\n");
		sb.append("Host: localhost:8088\r\n");
		sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n");
		sb.append("Accept-Encoding: gzip, deflate\r\n");
		sb.append("Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3\r\n");
		sb.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0\r\n");
		sb.append("Connection:keep-alive\r\n\r\n");

		PrintWriter writer = Util.getWriter(socket);
		writer.println(sb.toString());
		writer.flush();
		socket.shutdownOutput();

		InputStream socketIn = socket.getInputStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		byte[] buff = new byte[2014];
		int len = -1;
		while ((len = socketIn.read(buff)) != -1) {
			buffer.write(buff, 0, len);
		}
		System.out.println(buffer.toString());

		socket.close();
	}
}
