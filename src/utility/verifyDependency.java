package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class verifyDependency {
	/**
	 * @name dependenciesToMap
	 * 
	 * @dependencies  ex:[a => b,c => d], [a => b,b => c]
	 * 
	 * @return Map<String, String>  ex:{a=b,c=d} ,{a=c}
	 * 
	 */
	public static Map<String, String> dependenciesToMap(ArrayList<String> dependencies) {
		Map<String, String> result = new HashMap<String, String>();
		if (dependencies == null || dependencies.get(0) == "" || dependencies.get(0) == " ") {
			return null;
		} else {
			ArrayList<String> dependenciesOrder = regex(dependencies);
			for (int i = 0; i < dependenciesOrder.size(); i++) {
				if (i % 2 == 0) {
					if (result.containsValue(dependenciesOrder.get(i))) {
						result.replace(getKeyByValue.get(result, dependenciesOrder.get(i)),	dependenciesOrder.get(i + 1));
					} else {
						result.put(dependenciesOrder.get(i), dependenciesOrder.get(i + 1));
					}
				}
			}
		}
		return result;
	}
	/**
	 * @name regex
	 * 
	 * @dependencies  ex:[a => b,c => d], [a => b,b => c]
	 * 
	 * @return ArrayList<String>  ex:[a,b,c,d], [a,b,b,c]
	 * 
	 */
	private static ArrayList<String> regex(ArrayList<String> dependencies) {
		Pattern pattern = Pattern.compile("\\w");
		Matcher matcher = pattern.matcher(dependencies.toString());
		ArrayList<String> regexResult = new ArrayList<String>();
		while (matcher.find()) {
			regexResult.add(matcher.group().trim());
		}
		return regexResult;
	}
}
