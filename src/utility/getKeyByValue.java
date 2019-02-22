package utility;

import java.util.Map;

public class getKeyByValue {
	/**
	 * @name get
	 * @mapref  ex:{a=b,c=d}
	 * @value  
	 * 
	 * @return String return key
	 * 
	 */
	public static String get(Map<String, String> mapref, String value) {
		String key = "";
		for (Map.Entry<String, String> map : mapref.entrySet()) {
			if (map.getValue().toString().equals(value)) {
				key = map.getKey();
			}
		}
		return key;
	}
}
