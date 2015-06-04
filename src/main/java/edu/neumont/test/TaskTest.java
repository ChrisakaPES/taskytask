package edu.neumont.test;

import edu.neumont.dal.SlateServiceImpl;

public class TaskTest {
	public static void main(String[] args) {
//		TaskDALImpl tdi = new TaskDALImpl();
//		tdi.delete(5);
//		tdi.delete(6);
//		tdi.create(5, "Test task", "this is a test", LocalDateTime.now());
//		List<Task> tasks = tdi.retrieveTasks(5);
//		Task t = tasks.get(0);
//		t.toString();
		SlateServiceImpl sh = new SlateServiceImpl();
		System.out.println(sh.retrieveUserSlates(1).size());
	}
}
