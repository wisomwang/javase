package networking.io.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 测试服务端能够接收客户端的连接数目
 * 
 * @author smwang
 * 
 */
public class Server4 {
	private static final int PORT = 8088;
	private ServerSocket serverSocket;

	public static void main(String[] args) throws IOException {
		new Server4().service();

	}

	public Server4() throws IOException {
		serverSocket = new ServerSocket(PORT, 1);
		//服务器等待连接6秒钟，超时抛出异常
//		serverSocket.setSoTimeout(6000);
		System.out.println("Server is starting to accetp the request...");
	}

	private void service() throws IOException {
		while (true) {
			Socket socketClient = serverSocket.accept();
			System.out.println("Accept connection from client:" + socketClient.getInetAddress() + ", port:"
					+ socketClient.getPort());

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			InputStream inputStream = socketClient.getInputStream();
			byte[] b = new byte[10*1024];
			int n = -1;
			do {
				n = inputStream.read(b);
				if(n!=-1)
					System.out.println(new String(b, 0, n));
			} while (n != -1);
			
		}
	}
}