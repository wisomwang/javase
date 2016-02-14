package networking.io.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import networking.io.Util;

/**
 * 测试服务端能够接收客户端的连接数目
 * 
 * @author smwang
 * 
 */
public class Server3 {
	private static final int PORT = 8088;
	private ServerSocket serverSocket;

	private ExecutorService executorService = Executors.newCachedThreadPool();

	public static void main(String[] args) throws IOException {
		new Server3().service();

	}

	public Server3() throws IOException {
		// 第二个参数，请求队列里最大的长度，1就是表示请求队列里只能有一个请求在等待,并不代表只能处理一个请求，如果请求的处理速度
		// 很快的话，是不会有请求在队列里等待的
		serverSocket = new ServerSocket(PORT, 10);
		System.out.println("Server is starting to accetp the request...");
	}

	private void service() throws IOException {
		while (true) {
			Socket socketClient = serverSocket.accept();
			System.out.println("Accept connection from client:" + socketClient.getInetAddress() + ", port:"
					+ socketClient.getPort());
			executorService.execute(new Request2(socketClient));
		}
	}

	private class Request2 implements Runnable {
		private Socket socket;

		public Request2(Socket socketClient) {
			this.socket = socketClient;
		}

		@Override
		public void run() {
			BufferedReader reader = Util.getReader(socket);
			String line = null;
			try {
				// 当客户端强制关闭程序时，会报java.net.SocketException: Connection reset
				// 当客户端关闭socket.close()时，服务器不有接收完的数据还会继续发送，直到客户端发出的数据都被接收完为止
				while ((line = reader.readLine()) != null && line.length() != 0) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Received from client:" + line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				OutputStream outputStream = socket.getOutputStream();
				StringBuilder sb = new StringBuilder();
				sb.append("HTTP/1.1 200 OK\r\n");
				sb.append("Content-type: text/html;charset=GBK\r\n");
				sb.append("Content-length: 67\r\n");
				sb.append("\r\n");
				sb.append("<html><head><title>helloapp</title></head><body>hello</body></html>\r\n");
				outputStream.write(sb.toString().getBytes());
				//执行到这里，ClientUnknown可以接收到消息，但接收完消失后会在socketIn.read(buff)阻赛，直到
				//调用如下其中一行的代码后，才会接收到socketIn.read(buff)返回-1，从而程序结束
//				socket.shutdownOutput();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Handle this client over");
		}

	}
}
