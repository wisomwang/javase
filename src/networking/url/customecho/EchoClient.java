package networking.url.customecho;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class EchoClient {

	public static void main(String[] args) throws IOException {
		URL.setURLStreamHandlerFactory(new EchoURLStreamHandlerFactory());
		URLConnection.setContentHandlerFactory(new EchoContentHandlerFactory());

		URL url = new URL("echo:localhost:8088");
		EchoURLConnection urlc = (EchoURLConnection) url.openConnection();

		// 默认false,设置true，允许输出
		urlc.setDoOutput(true);
		PrintWriter writer = new PrintWriter(urlc.getOutputStream(), true);

		while (true) {
			//从控制台输入
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String msg = reader.readLine();
			writer.println(msg);

			//urlc.getContent()是返回server的响应
			String echoMsg = (String) urlc.getContent();
			System.out.println("echoMsg=" + echoMsg);
			if ("echo:bye".equals(echoMsg)) {
				urlc.disconnect();
				break;
			}
		}
	}

}
