package container;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * 1. 强引用，GC运行时不回收 2. 软引用，GC运行时可能回收（内存不够时）,适合做缓存 3. 弱引用，GC运行时立即回收 4.
 * 虚引用，类似于无引用，不能单独使用，必须与引用队列（ReferenceQueue）联合使用
 * 
 * WeakHashMap 键为弱引用，回收键后自动删除key-value对象
 * 
 * @author smwang
 * 
 */
public class TestWeakHashMap {

	public static void main(String[] args) {
		WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
		weakHashMap.put("aa", "11");
		weakHashMap.put("bb", "22");
		weakHashMap.put(new String("cc"), "33");
		weakHashMap.put(new String("dd"), "44");
		System.out.println("gc运行前：" + weakHashMap);

		// 通知回收
		System.gc();
		System.runFinalization();

		System.out.println("gc运行后：" + weakHashMap);
	}

	public static void testWeak() {
		// new 出来的str对象经过弱引用，GC马上被回收
		String str = new String("sxt is good");

		// 弱引用
		WeakReference<String> wr = new WeakReference<String>(str);
		System.out.println("gc运行前：" + wr.get());

		// 断开引用
		str = null;

		// 通知回收
		System.gc();
		System.runFinalization();

		System.out.println("gc运行后：" + wr.get());
	}

	public static void testStrong() {
		// 常量池的对象不能被回收
		String str = "sxt is good";

		// 弱引用
		WeakReference<String> wr = new WeakReference<String>(str);
		System.out.println("gc运行前：" + wr.get());

		// 断开引用
		str = null;

		// 通知回收
		System.gc();
		System.runFinalization();

		System.out.println("gc运行后：" + wr.get());
	}
}
