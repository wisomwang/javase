package networking.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import networking.io.Util;

/**
 * 对应Client1
 * 
 * @author smwang
 * 
 */
public class Server1 {
	private static final int PORT = 8088;
	private ServerSocket serverSocket;

	private ExecutorService executorService = Executors.newCachedThreadPool();

	public static void main(String[] args) throws IOException {
		new Server1().service();

	}

	public Server1() throws IOException {
		// 第二个参数，请求队列里最大的长度，1就是表示请求队列里只能有一个请求在等待,并不代表只能处理一个请求，如果请求的处理速度
		// 很快的话，是不会有请求在队列里等待的
		serverSocket = new ServerSocket(PORT, 1);
		System.out.println("Server is starting to accetp the request...");
	}

	private void service() throws IOException {
		while (true) {
			Socket socketClient = serverSocket.accept();
			System.out.println("Accept connection from client:" + socketClient.getInetAddress() + ", port:"
					+ socketClient.getPort());
			executorService.execute(new Request(socketClient));
		}
	}

	private class Request implements Runnable {
		private Socket socket;

		public Request(Socket socketClient) {
			this.socket = socketClient;
		}

		@Override
		public void run() {
			BufferedReader reader = Util.getReader(socket);
			String line = null;
			try {
				while ((line = reader.readLine()) != null && line.length() != 0) {
					System.out.println("Received from client:" + line);

					if ("bye".equals(line)) {
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Handle this client over");
		}

	}
}
