package networking.ssl.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class EchoClient2 {

	private SSLSocket socket;
	
	public static void main(String[] args) {
		EchoClient2 client = new EchoClient2();
		client.communicate();
	}

	public EchoClient2() {
		SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		try {
			socket = (SSLSocket)sslSocketFactory.createSocket("localhost", 8000);
			socket.setEnableSessionCreation(true);
			socket.addHandshakeCompletedListener(new HandshakeCompletedListener() {
				
				@Override
				public void handshakeCompleted(HandshakeCompletedEvent event) {
					System.out.println("Handshake over");
					System.out.println("加密套件是: " + event.getCipherSuite());
					System.out.println(" " + event.getSession().getPeerHost());
				}
			});
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
