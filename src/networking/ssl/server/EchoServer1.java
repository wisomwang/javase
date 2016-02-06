package networking.ssl.server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class EchoServer1 {
	private static final String PASSWORD = "123456";
	private static final String KEY_PATH = "D:\\studyspace\\javase\\src\\networking\\ssl\\server\\server.keys";
	private SSLServerSocketFactory serverSocketFactory;
	SSLServerSocket serverSocket;

	public static void main(String[] args) {
		EchoServer1 server = new EchoServer1();
		server.service();
	}

	public EchoServer1() {
		SSLContext context = createSSLContext();
		serverSocketFactory = context.getServerSocketFactory();
		try {
			serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("服务器已经启动：");
		System.out.println(serverSocket.getUseClientMode() ? "客户模式" : "服务模式");
		System.out.println(serverSocket.getNeedClientAuth() ? "需要客户端验证身份" : "不需要客户端验证身份");

		// System.out.println(serverSocket.getSupportedCipherSuites());
		// System.out.println(serverSocket.getSupportedProtocols());

		serverSocket.setEnabledCipherSuites(serverSocket.getSupportedCipherSuites());
	}

	private SSLContext createSSLContext() {
		SSLContext sslContext = null;
		try {
			char[] password = PASSWORD.toCharArray();

			// JKS，JDK指定的
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(KEY_PATH), password);

			// 创建一个算法是SunX509的KeyManagerFactory
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
			keyManagerFactory.init(ks, password);

			KeyManager[] km = keyManagerFactory.getKeyManagers();

			// 创建一个SSL协议的上下文
			sslContext = SSLContext.getInstance("SSL");
			// 第一个参数表示需要认证自己的身份，第二个参数为null表示不需要认证客户端身份
			sslContext.init(km, null, null);
			
			//km为空的话，会创建一个默认的KeyMananger对象，及与这关联的KeyStore对象，KeyStore对象会从系统属性javax.net.ssl.keyStore中获取安全证书
			//tm为空的话，会创建一个默认的TrustManager对象，及与这关联的KeyStore对象，KeyStore对象按如下步骤获取安全证书
			//1.先尝试从系统属性javax.net.ssl.trustStore
			//(	D:\studyspace\javase\bin>java -Djavax.net.ssl.trustStore=D:\studyspace\javase\src\networking\ssl\server\server.keys networking.ssl.client.EchoClient2)
			//可以执行
			//2.失败的话，会尝试 jdk1.7.0\jre\lib\security\jssecacerts文件作为安全证书 
			//3.失败的话，会尝试 jdk1.7.0\jre\lib\security\cacerts文件作为安全证书
			//4.如果失败的话，那么KeyStore对象为空
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sslContext;
	}

	private void service() {
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				System.out.println("New connection accepted:" + socket.getInetAddress() + ":" + socket.getPort());
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				System.out.println("Server received: " + br.readLine());
				
				PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);
				pw.println("hello client");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
