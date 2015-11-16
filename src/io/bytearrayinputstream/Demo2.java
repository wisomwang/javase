package io.bytearrayinputstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Demo2 {

	public static void main(String[] args) throws Exception {
		byte[] bytes = getByteFromFile("D:/studyspace/javase/src/io/bytearrayinputstream/Demo2.java");
		System.out.println(new String(bytes));
		toFileFromByteArray("D:/studyspace/javase/src/io/bytearrayinputstream/TestCopy.java", bytes);
	}

	/**
	 * 字节数组-->程序-->文件
	 * 
	 * @param string
	 * @param bytes
	 * @throws Exception
	 */
	private static void toFileFromByteArray(String destFileName, byte[] bytes) throws Exception {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(destFileName)));

		ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
		// BufferedInputStream bis = new BufferedInputStream(new
		// ByteArrayInputStream(bytes));//这个也可以
		byte[] flush = new byte[10];
		int len = 0;
		while (-1 != (len = bai.read(flush, 0, flush.length))) {
			bos.write(flush, 0, len);
		}
		bos.close();
	}

	/**
	 * 文件-->程序-->字节数组
	 * 
	 * @param string
	 * @return
	 * @throws FileNotFoundException
	 */
	private static byte[] getByteFromFile(String fileName) throws Exception {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len = 0;
		byte[] flush = new byte[10];

		/**
		 * 从缓冲流中读取 flush.length长度字节到flush数组中，数组起始存放位置是0，如果缓冲输入流中内容长度超过数组长度，则多次读取
		 */
		while (-1 != (len = bis.read(flush, 0, flush.length))) {
			bos.write(flush, 0, len);
		}
		bis.close();
		return bos.toByteArray();
	}
}
