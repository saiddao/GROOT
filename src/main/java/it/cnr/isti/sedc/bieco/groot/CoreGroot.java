package it.cnr.isti.sedc.bieco.groot;

import it.cnr.isti.sedc.bieco.groot.utils.Sub;

public class CoreGroot extends Thread {

	private static CoreGroot INSTANCE;

	public static Thread getInstance() throws InterruptedException {
		if (INSTANCE == null) {
			INSTANCE = new CoreGroot();
			INSTANCE.run();
		}
		return INSTANCE;
	}

	public static boolean isRunning() {
		if (INSTANCE == null)
			return false;
		return true;
	}

	public static void killInstance() {
		INSTANCE.interrupt();
		INSTANCE = null;
	}

	public static void DemoStart() {

		System.out.println("------------------------------------------------------");
		System.out.println("-------Starting GROOT session-------");
		System.out.println("------------------------------------------------------");
	}

	public static void main(String[] args) {

	}

	public static String getLoggerData() {
		
		//System.out.println(System.getProperty("user.dir"));
		
		return Sub.readFile(System.getProperty("user.dir") + "/logs/groot-debug.log");
	}
}
