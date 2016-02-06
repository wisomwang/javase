package networking.url.customecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import networking.io.Util;

/**
 * @author smwang
 * 
 */
public class EchoServer {
	private static final int PORT = 8088;
	private ServerSocket serverSocket;

	private ExecutorService executorService = Executors.newCachedThreadPool();

	public static void main(String[] args) throws IOException {
		new EchoServer().service();

	}

	public EchoServer() throws IOException {
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
			PrintWriter writer = Util.getWriter(socket);

			String line = null;
			try {
				while ((line = reader.readLine()) != null && line.length() != 0) {
					System.out.println("Received from client:" + line);
					writer.println("echo:" + line);
					writer.flush();
					
					if ("bye".equals(line)) {
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Handle this client over");
		}

	}
}
