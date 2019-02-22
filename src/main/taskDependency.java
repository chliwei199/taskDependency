package main;

import java.util.ArrayList;
import java.util.Map;

import utility.verifyDependency;
import utility.getKeyByValue;

public class taskDependency {
	/**
	 * 
	 * determine the order to execute dependent tasks 
	 * 
	 */
	public static void main(String[] args) {
		// case1
		ArrayList<String> task1 = genFakeTask(1);
		ArrayList<String> dependency1 = genFakeDependency(1);
		System.out.println(taskDependencyImp(task1, dependency1));

		// case2
		ArrayList<String> task2 = genFakeTask(2);
		ArrayList<String> dependency2 = genFakeDependency(2);
		System.out.println(taskDependencyImp(task2, dependency2));

		// case3
		ArrayList<String> task3 = genFakeTask(3);
		ArrayList<String> dependency3 = genFakeDependency(3);
		System.out.println(taskDependencyImp(task3, dependency3));

		// case4
		ArrayList<String> task4 = genFakeTask(4);
		ArrayList<String> dependency4 = genFakeDependency(4);
		System.out.println(taskDependencyImp(task4, dependency4));

		// case5
		ArrayList<String> task5 = genFakeTask(5);
		ArrayList<String> dependency5 = genFakeDependency(5);
		System.out.println(taskDependencyImp(task5, dependency5));

		// case6
		ArrayList<String> task6 = genFakeTask(6);
		ArrayList<String> dependency6 = genFakeDependency(6);
		System.out.println(taskDependencyImp(task6, dependency6));
	}

	/**
	 * @name taskDependencyImp
	 * 
	 * @tasks  ex:[], [a,b]
	 * @dependencies  ex:[], []
	 * 
	 * @return ArrayList<String>  ex:[], [a,b]
	 * @throws RuntimeException
	 * 
	 */
	public static ArrayList<String> taskDependencyImp(ArrayList<String> tasks, ArrayList<String> dependencies) {
		Map<String, String> dependencyResult = verifyDependency.dependenciesToMap(dependencies);
		if (dependencyResult == null) {
			return tasks;
		} else if (tasks.get(0).equals(dependencyResult.get(tasks.get(0)))) {
			throw new RuntimeException("Error - this is a cyclic dependency");
		} else {
			for (int i = 0; i < tasks.size(); i++) {
				if (dependencyResult.containsKey(tasks.get(i))) {
					tasks.set(i, dependencyResult.get(tasks.get(i)));
				} else if (dependencyResult.containsValue((tasks.get(i)))) {
					tasks.set(i, getKeyByValue.get(dependencyResult, tasks.get(i)));
				}
			}
		}
		return tasks;
	}

	/**
	 * @name genFakeTask
	 * 
	 * @num fake task number
	 * 
	 * @return ArrayList<String> ex:[], [a,b]
	 * 
	 */
	private static ArrayList<String> genFakeTask(int num) {
		ArrayList<String> task = new ArrayList<String>();
		switch (num) {
		case 1:
			task.add("");
			break;
		case 2:
			task.add("a");
			task.add("b");
			break;
		case 3:
			task.add("a");
			task.add("b");
			break;
		case 4:
			task.add("a");
			task.add("b");
			task.add("c");
			task.add("d");
			break;
		case 5:
			task.add("a");
			task.add("b");
			task.add("c");
			break;
		case 6:
			task.add("a");
			task.add("b");
			task.add("c");
			task.add("d");
			break;
		default:
			task.add("");
			break;
		}
		return task;
	}

	/**
	 * @name genFakeDependency
	 * 
	 * @num fake dependency number
	 * 
	 * @return ArrayList<String> ex:[], [a,b]
	 * 
	 */
	private static ArrayList<String> genFakeDependency(int num) {
		ArrayList<String> dependency = new ArrayList<String>();
		switch (num) {
		case 1:
			dependency.add("");
			break;
		case 2:
			dependency.add("");
			break;
		case 3:
			dependency.add("a => b");
			break;
		case 4:
			dependency.add("a => b");
			dependency.add("c => d");
			break;
		case 5:
			dependency.add("a => b");
			dependency.add("b => c");
			break;
		case 6:
			dependency.add("a => b");
			dependency.add("b => c");
			dependency.add("c => a");
			break;
		default:
			dependency.add("");
			break;
		}
		return dependency;
	}
}
