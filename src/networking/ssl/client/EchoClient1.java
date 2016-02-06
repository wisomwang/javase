package networking.ssl.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient1 {

	private Socket socket;
	
	public static void main(String[] args) {
		EchoClient1 client = new EchoClient1();
		client.communicate();
	}

	public EchoClient1() {
		try {
			socket = new Socket("localhost", 8000);
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println("hello server");
			pw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Client received: " + br.readLine());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void communicate() {
	}
}
