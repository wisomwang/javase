package networking.io.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 用原始的读输入流方式来读取客户端的发送过来的数据，对应Client2
 * 
 * @author smwang
 * 
 */
public class Server2 {
	private static final int PORT = 8088;
	private ServerSocket serverSocket;

	public static void main(String[] args) throws IOException {
		new Server2().service();

	}

	public Server2() throws IOException {
		// 第二个参数，请求队列里最大的长度，1就是表示请求队列里只能有一个请求在等待,并不代表只能处理一个请求，如果请求的处理速度
		// 很快的话，是不会有请求在队列里等待的
		serverSocket = new ServerSocket(PORT, 1);
		
		//server端接收数据的缓冲区，设置了是有影响的
		serverSocket.setReceiveBufferSize(5);
		System.out.println("Server is starting to accetp the request...");
	}

	private void service() throws IOException {
		while (true) {
			Socket socketClient = serverSocket.accept();
			System.out.println("Accept connection from client:" + socketClient.getInetAddress() + ", port:"
					+ socketClient.getPort());

			InputStream inputStream = socketClient.getInputStream();

			byte[] buffer = new byte[20];
			int length = 0;
			//读取数据（如果输入流的字节长度超过了20），长度20循环一次
			//这里想要说明的是，如果客户端通过控制台输出时，控制台不管输入多长的字符，必须要按回车键，这里才会开始接收
			while ((length = inputStream.read(buffer)) != -1) {
				System.out.println("in");
				System.out.println(new String(buffer, 0, length));
			}
			System.out.println("end");
		}
	}
}


