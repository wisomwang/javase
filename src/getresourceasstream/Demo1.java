package getresourceasstream;

import java.io.IOException;
import java.util.Properties;

/**
 * Test ClassLoader.getResourceAsStream()
 * @author smwang
 *
 */
public class Demo1 {

	static Properties pros = null; // 可以帮助读取和处理资源文件中的信息

	static { // 加载JDBCUtil类的时候调用
		pros = new Properties();
		try {
			/**
			 * ClassLoader.getResourceAsStream(String
			 * name)这里的name的路径是相对于类路径的，不能以
			 * /开头，db.properties所在的类路径是getresourceasstream
			 */
			pros.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("getresourceasstream/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(pros.get("mysqlDriver"));
	}

}
