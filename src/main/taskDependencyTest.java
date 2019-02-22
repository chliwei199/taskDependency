package main;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class taskDependencyTest {
	taskDependency taskDependency = new taskDependency();
	
	//test1 = tasks : [], dependencies: [], result: []
	@Test
	void test1() {
		ArrayList<String> task1 = new ArrayList<String>() { { add(""); } };
		ArrayList<String> dependency1 = new ArrayList<String>() { { 	add(""); } 	};
		assertEquals(task1, taskDependency.taskDependencyImp(task1, dependency1));
	}
	
	//test2 = tasks: [a,b], dependencies: [], result: [a,b]
	@Test
	void test2() { 
		ArrayList<String> task2 = new ArrayList<String>() { { add("a"); add("b"); } };
		ArrayList<String> dependency2 = new ArrayList<String>() { { add(""); } };
		assertEquals(task2, taskDependency.taskDependencyImp(task2, dependency2));
	}
	
	//test3 = tasks: [a,b], dependencies: [a => b], result: [b,a]
	@Test
	void test3() {
		ArrayList<String> task3 = new ArrayList<String>() { { add("a"); add("b"); } };
		ArrayList<String> dependency3 = new ArrayList<String>() { { add("a => b"); } };
		assertEquals(new ArrayList<String>() {{	add("b"); add("a");}}, taskDependency.taskDependencyImp(task3, dependency3));
	}
	
	//test4 = tasks: [a,b,c,d], dependencies: [a => b,c => d], result: [b,a,d,c]
	@Test
	void test4() {
		ArrayList<String> task4 = new ArrayList<String>() { { add("a");	add("b"); add("c"); add("d"); } };
		ArrayList<String> dependency4 = new ArrayList<String>() { { add("a => b"); add("c => d"); } };
		assertEquals(new ArrayList<String>() {{	add("b"); add("a");add("d"); add("c");}}, taskDependency.taskDependencyImp(task4, dependency4));
	}
	
	//test5 = tasks: [a,b,c], dependencies: [a => b,b => c], result: [c,b,a]
	@Test
	void test5() {
		ArrayList<String> task5 = new ArrayList<String>() {	{ add("a"); add("b"); add("c"); } };
		ArrayList<String> dependency5 = new ArrayList<String>() { { add("a => b"); add("b => c"); } };
		assertEquals(new ArrayList<String>() {{	add("c"); add("b");add("a");}}, taskDependency.taskDependencyImp(task5, dependency5));
	}
	
	//test6 = tasks: [a,b,c,d], dependencies: [a => b,b => c,c => a], result: error
	@Test 
	void test6() throws Exception{
		ArrayList<String> task6 = new ArrayList<String>() { { add("a"); add("b"); add("c"); add("d"); } };
		ArrayList<String> dependency6 = new ArrayList<String>() { { add("a => b"); add("b => c"); add("c => a"); } };
		 assertThrows(RuntimeException.class, ()->{taskDependency.taskDependencyImp(task6, dependency6); });
	}
	
	//test7 = tasks: [], dependencies: [a => b], result: []
	@Test 
	void test7() throws Exception{
		ArrayList<String> task7 = new ArrayList<String>() { { add(""); } };
		ArrayList<String> dependency7 = new ArrayList<String>() { { add("a => b"); } };
		assertEquals(task7, taskDependency.taskDependencyImp(task7, dependency7));
	}
}
