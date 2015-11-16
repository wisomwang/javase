package getresourceasstream;

import java.io.IOException;
import java.util.Properties;

/**
 * Test Class.getResourceAsStream()
 * 
 * Class.getResourceAsStream(String name)这里的name的路径有绝对路径和相对路径之分
 * 以/开头的是绝对路径,没有/开头是相对路径，相对路径是相对当前class的
 * 
 * @author smwang
 * 
 */
public class Demo2 {

	static Properties pros = new Properties(); // 可以帮助读取和处理资源文件中的信息

	public static void main(String[] args) throws IOException {
		pros.load(Demo2.class.getResourceAsStream("/getresourceasstream/db.properties"));
		System.out.println(pros.get("mysqlDriver"));

		/** 绝对路径错误 */
		// pros.load(Demo2.class.getResourceAsStream("/db.properties"));
		// System.out.println(pros.get("mysqlDriver"));

		/** 相对路径可以 */
		pros.load(Demo2.class.getResourceAsStream("db.properties"));
		System.out.println(pros.get("mysqlDriver"));
		
		/** 相对路径可以,Demo2类的类路径是getresourceasstream,他下面有demo2/db1.properties */
		pros.load(Demo2.class.getResourceAsStream("demo2/db1.properties"));
		System.out.println(pros.get("mysqlDriver"));
	}

}
