package mianshi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class ThreadTest {
	private volatile static int printNum = 0;
	final static int cycleTime = 3;
	private static Lock lock = new ReentrantLock();

	@Test
	public void useSingleThreadPool() {
		final Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < cycleTime; i++) {
					if (printNum < 100) {
						printNum++;
						System.out.println("t1打印数字:" + printNum);
					}
				}
			}
		}, "T1");

		final Thread t2 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < cycleTime; i++) {
					if (printNum < 100) {
						printNum++;
						System.out.println("t2打印数字:" + printNum);
					}
				}
			}
		}, "T2");

		final Thread t3 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < cycleTime; i++) {
					if (printNum < 100) {
						printNum++;
						System.out.println("t3打印数字:" + printNum);
					}
				}
			}
		}, "T3");

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		while (printNum < 100) {
			executorService.submit(t1);
			executorService.submit(t2);
			executorService.submit(t3);
		}

	}

	@Test
	public void useThreads() {
		
		final Thread t1 = new Thread(new Runnable() {
			public void run() {
				lock.lock();
				for (int i = 0; i < cycleTime; i++) {
					if (printNum < 100) {
						printNum++;
						System.out.println("t1打印数字:" + printNum);
					}
				}
				lock.unlock();
			}
		}, "T1");

		final Thread t2 = new Thread(new Runnable() {
			public void run() {
				lock.lock();
				for (int i = 0; i < cycleTime; i++) {
					if (printNum < 100) {
						printNum++;
						System.out.println("t2打印数字:" + printNum);
					}
				}
				lock.unlock();
			}
		}, "T2");

		final Thread t3 = new Thread(new Runnable() {
			public void run() {
				lock.lock();
				for (int i = 0; i < cycleTime; i++) {
					if (printNum < 100) {
						printNum++;
						System.out.println("t3打印数字:" + printNum);
					}
				}
				lock.unlock();
			}
		}, "T3");
		while (printNum < 100) {
			t1.start();
			t2.start();
			t3.start();
		}
	}
}
