package designpattern.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/***
 * 反映和序列化破坏单例模式
 * 
 * @author smwang
 * 
 */
public class BreakSingleton implements Serializable {

	private static final long serialVersionUID = 1L;

	/** static的，类加载的时候就会初始化变量（没有延时加载的优势），类加载的时候是天然的线程安全的，不涉及线程同步 安全问题，肯定是安全的 */
	private static BreakSingleton instance = new BreakSingleton();

	/** 构造器私有化，通过抛出异常来禁止反射创建实例 */
	private BreakSingleton() {
		if (instance != null) {
			throw new RuntimeException();
		}
	}

	/** 获取的时候不需要同步，所以性能高 */
	public static BreakSingleton getInstance() {
		return instance;
	}

	/** 如果反序列化时，定义了readResolve方法，则直接返回方法指定对象，而不会再单独创建对象 */
	public Object readResolve() throws ObjectStreamException {
		return instance;
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// testReflection();
		testSerialization();
	}

	private static void testSerialization() {
		BreakSingleton singleton1 = BreakSingleton.getInstance();
		BreakSingleton singleton2 = BreakSingleton.getInstance();

		System.out.println(singleton1);
		System.out.println(singleton2);

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/object.txt"));
			oos.writeObject(singleton1);
			oos.close();
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("d:/object.txt"));
			BreakSingleton singleton3 = (BreakSingleton) ois.readObject();

			System.out.println(singleton3);
			ois.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

	private static void testReflection() throws ClassNotFoundException, NoSuchMethodException, InstantiationException,
			IllegalAccessException, InvocationTargetException {
		BreakSingleton singleton1 = BreakSingleton.getInstance();
		BreakSingleton singleton2 = BreakSingleton.getInstance();

		System.out.println(singleton1);
		System.out.println(singleton2);

		Class<BreakSingleton> clazz = (Class<BreakSingleton>) Class.forName("designpattern.singleton.BreakSingleton");
		Constructor<BreakSingleton> constuctor = clazz.getDeclaredConstructor(null);
		BreakSingleton singleton3 = constuctor.newInstance();
		BreakSingleton singleton4 = constuctor.newInstance();

		/** 反映创建的两个实例不一样 */
		System.out.println(singleton3);
		System.out.println(singleton4);
	}

}
