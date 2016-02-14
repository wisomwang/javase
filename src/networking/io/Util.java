package networking.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public final class Util {
	private Util() {

	}

	public static BufferedReader getReader(Socket socket) {
		try {
			return new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PrintWriter getWriter(Socket socket) {
		try {
			// PrintWriter构造函数会用BufferedWriter进行包装,默认缓冲大小是8192
			return new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedWriter getBufferedWriter(Socket socket) {
		try {
			// 指定缓冲大小是1024
			return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()),50);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public static PrintWriter getBufferedWriter(Socket socket) {
//		try {
//			SocketOutputStream socket.getOutputStream();
//			return new PrintWriter(socket.getOutputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
