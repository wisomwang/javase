package io.endecoder;

import java.nio.charset.Charset;

public class Demo01 {

	public static void main(String[] args) {
		test01();
		test02();
	}

	private static void test02() {
		/** 使用平台默认字符集进行编码,长度为6个字节 */
		byte[] china = "中国".getBytes();

		/** 使用平台默认字符集进行解码，但长度不够，utf-8时，一个中文字符占有三个字节 */
		String charChina = new String(china, 0, 4);
		System.out.println(charChina);
	}

	private static void test01() {
		/** 平台默认字符集是UTF-8 */
		Charset defaultCharset = Charset.defaultCharset();
		System.out.println("default charset is " + defaultCharset);

		/** 使用平台默认字符集进行编码 */
		byte[] china = "中国".getBytes();
		/** 使用平台默认字符集进行解码 */
		String charChina = new String(china);
		System.out.println(charChina);

		/** 使用GBK字符集进行编码 */
		china = "中国".getBytes(Charset.forName("GBK"));
		/** 使用平台默认字符集进行解码,编码与解码不一致，出现乱码 */
		charChina = new String(china);
		System.out.println(charChina);

		/** 使用GBK字符集进行编码 */
		china = "中国".getBytes(Charset.forName("GBK"));
		/** 使用GBK字符集进行解码,编码与解码一致，没有乱码 */
		charChina = new String(china, Charset.forName("GBK"));
		System.out.println(charChina);
	}

}
