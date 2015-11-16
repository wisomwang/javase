package container;

import java.util.HashMap;
import java.util.IdentityHashMap;

/**
 * 键以地址去比较，而不是比较equals和hashcode
 * 
 * @author smwang
 * 
 */
public class TestIdentityHashMap {

	public static void main(String[] args) {
		IdentityHashMap<String, String> map = new IdentityHashMap<String, String>();
		map.put("a", "11");
		map.put("a", "22");
		map.put(new String("a"), "33");
		map.put(new String("a"), "44");

		//尽管key都是a，但地址不同
		System.out.println(map);
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("a", "11");
		hashMap.put("a", "22");
		hashMap.put(new String("a"), "33");
		hashMap.put(new String("a"), "44");

		System.out.println(hashMap);
	}

}
