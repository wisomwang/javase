package io.file;

import java.io.File;

public class Demo2 {

	public static void main(String[] args) {
		/** 相对路径 */
		String currentPaht = "D:/studyspace/javase/src/io/file";
		String fileName = "Test.java";
		File file1 = new File(currentPaht, fileName);
		System.out.println(file1.getName());
		System.out.println(file1.getPath());
		System.out.println(file1.getAbsolutePath());

		/** 绝对路径 */
		File file2 = new File("D:/studyspace/javase/src/io/file/Test.java");
		System.out.println(file2.getName());
		System.out.println(file2.getPath());
		System.out.println(file2.getAbsolutePath());

		/**
		 * 没有盘符，相对路径是user.dir, 默认相对路径是用户当前工程，这里是D:/studyspace/javase
		 * File只是建立联系，不会判断实际是否存在，下面可以输出, File只包含文件本身的信息，右键点击文件，打开属性对话框里面的信息
		 */
		File file3 = new File("test.txt");
		System.out.println(file3.getName());
		System.out.println(file3.getPath());
		System.out.println(file3.getAbsolutePath());
	}

}
