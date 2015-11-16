package container;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 1. HashTable键和值不能为空,线程安全 
 * 2. HashMap键最多一个null,值可以为多个null 
 * 3. 父类不同，HashTable是Dictionary, HashMap是AbstractMap
 * 4. Properties是HashTable的子类，读取资源配置文件,键与值只能是String
 * 
 * @author smwang
 * 
 */
public class TestProperties {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		/**
		 * 常用方法 setProperty() getProperty() 存储，后缀为.properties, store 存储，后缀为.xml,
		 * storeToXml 读取, load
		 */

		Properties prop = new Properties();
		prop.setProperty("name", "Alexander");
		prop.setProperty("age", "34");

		/** 源文件相对路径， test.properties在D:目录下 */
		prop.store(new FileOutputStream("/test.properties"),
				"person infomation");
		/** 源文件相对路径，相对当前工程下 */
		prop.store(new FileOutputStream("test1.properties"),
				"person infomation");
		/** 源文件相对路径，相对src下 */
		prop.store(new FileOutputStream("src/test2.properties"),
				"person infomation");

		Properties propLoad = new Properties();
		/** 源文件相对路径， test.properties在D:目录下 */
		propLoad.load(new FileInputStream("/test.properties"));
		System.out.println(propLoad.get("name"));
		
		/** 类相对路径，/表示相对bin */
		propLoad.load(TestProperties.class.getResourceAsStream("/container/test2.properties"));
		System.out.println(propLoad.get("name"));
		
		/** 类相对路径，空表示相对bin */
		propLoad.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("container/test2.properties"));
		System.out.println(propLoad.get("name"));
	}
}
