package mianshi;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class QuickSort {

	@Test
	public void t1() {
		int[] array = new int[] { 8, 5, 1, 3, 6, 4, 9, 2, 7 };
		// sort(array, 0, array.length - 1);

		quickSort(array, 0, array.length - 1);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	public static void sort(int[] array, int low, int high) {

		int begin = low;
		int end = high;
		int key = array[low];
		while (begin < end) {
			while (begin < end && array[end] >= key) {
				end--;
			}
			array[begin] = array[end];
			while (begin < end && array[begin] <= key) {
				begin++;
			}
			array[end] = array[begin];
		}
		System.out.println("begin->" + begin);
		System.out.println("end->" + end);
		array[begin] = key;

		if (low < end) {
			sort(array, low, end - 1);
		}
		if (end < high) {
			sort(array, end + 1, high);
		}
	}

	public void quickSort(int[] array, int begin, int end) {
		int l1 = begin;
		int l2 = end;
		int key = array[l1];
		while (l1 < l2) {
			while (l1 < l2 && array[l2] >= key) {
				l2--;
			}
			array[l1] = array[l2];
			while (l1 < l2 && array[l1] <= key) {
				l1++;
			}
			array[l2] = array[l1];

		}
		array[l1] = key;
		if (l1 < end) {
			quickSort(array, l1 + 1, end);
		}
		if (l2 > begin) {
			quickSort(array, begin, l2 - 1);
		}

	}

	@Test
	public void threadpool() throws InterruptedException, ExecutionException {
		Callable<Integer> callable = new Callable<Integer>() {
			public Integer call() throws Exception {
				return new Random().nextInt(100);
			}
		};
		
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
		new Thread(futureTask).run();
		

		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		
		System.out.println(futureTask.get());

	}
}
