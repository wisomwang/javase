package io.file;

import java.io.File;

public class Demo1 {

	public static void main(String[] args) {
		/** 路径分隔符 */
		System.out.println(File.pathSeparator);

		/** 文件分隔符 */
		System.out.println(File.separator);

		/** 路径表示形式 */
		String path1 = "D:\\studyspace\\javase\\src\\io\\file\\Test.java";// 静态的绝对路径，双斜杠
		String path2 = "D:" + File.separator + "studyspace" + File.separator + "javase" + File.separator + "src"
				+ File.separator + "io" + File.separator + "file" + File.separator + "Test.java";// 动态构建路径用File.separator
		String path3 = "D:/studyspace/javase/src/io/file/Test.java"; // 推荐方式，单斜杠
		System.out.println("path1=" + path1);
		System.out.println("path2=" + path2);
		System.out.println("path3=" + path3);
	}

}
