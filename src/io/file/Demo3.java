package io.file;

import java.io.File;

public class Demo3 {

	public static void main(String[] args) throws Exception {

		/** 创建临时文件 */
		File tempFile = File.createTempFile("test", ".tmp", new File("D:/studyspace/javase/src/io/file"));
		Thread.sleep(5 * 1000);
		tempFile.deleteOnExit();
	}

}
