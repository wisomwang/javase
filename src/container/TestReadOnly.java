package container;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 只读设置 
 * emptyXxx() 空的不可变的集合(emptyList(),emptySet(), emptyMap())
 * singletonXxx() 一个元素不可变的集合(...)
 * unmodifiableXxx() 不可变容器(...)
 * 
 * @author smwang
 * 
 */
public class TestReadOnly {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("aa", "11");
		map.put("bb", "22");
		Map<String, String> unmodifiableMap = Collections.unmodifiableMap(map);
		unmodifiableMap.put("cc", "33");
		System.out.println("unmodifiableMap=" + unmodifiableMap);
	}

}
