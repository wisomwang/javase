package networking.io.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author smwang
 * 
 */
public class Client4 {
	private static final int PORT = 8088;
	private static final String LOCALHOST = "localhost";
	private Socket socket;

	public static void main(String[] args) throws UnknownHostException, IOException {
		new Client4().communicate();

	}

	public Client4() throws UnknownHostException, IOException {
		socket = new Socket();
		socket.connect(new InetSocketAddress(LOCALHOST, PORT), 5000);
		System.out.println("Connected to remote host:" + socket.getRemoteSocketAddress());

	}

	private void communicate() throws IOException {
		
		socket.getOutputStream().write("hello client".getBytes());
		socket.getOutputStream().write("you are the worst person".getBytes());
		
		//设置读数据的等待 超时时间
//		socket.setSoTimeout(5000);
//		BufferedReader reader = Util.getReader(socket);
//		reader.readLine();
		
		//设置socket关闭后，底层socket继续可以发送数据的时间，如果设置0秒的话，socket.close()后，底层socket也立即关闭，默认是-1
		System.out.println(socket.getSoLinger());
		socket.setSoLinger(true, 10);
		socket.close();
	}
}
