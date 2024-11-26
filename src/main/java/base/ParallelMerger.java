package base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;

/**
 * <p>Your task in this lab is to implement a parallel string merger, which provides a method to merge strings in parallel (with threads).<p>
 * <p>An array of string segments will be given as input, e.g., `["hlo", "el!]`, the `merge` method should create a set of threads whose size is the same as the number of segments.
 * In this example, you should create `thread1` for `"hlo"` and `thread2` for `"el!"`.
 * Then, all threads take turns to merge the segments character by character.</p>
 * <p>A writer is provided for threads to write the character. <p>
 * <p>The expected behavior for the example is:<p>
 * <ul>
 * <li>`thread1` writes `h`</li>
 * <li>`thread2` writes `e`</li>
 * <li>`thread1` writes `l`</li>
 * <li>`thread2` writes `l`</li>
 * <li>`thread1` writes `o`</li>
 * <li>`thread2` writes `!`</li>
 * </ul>
 * <p>Eventually, the writer will get `h`, `e`, `l`, `l`, `o`, `!`, written by the two threads.</p>
 * <p>The last thing you need to make sure is that all threads should exit before `merge` method returns.</p>
 */
public class ParallelMerger {
	@FunctionalInterface
	public interface ThreadSafeCharacterWriter {
		void write(char ch);
	}

	/**
	 * You don't need to implement this interface by yourself.
	 * You can treat it as a list of char which should stores the string merge results of all threads after all threads exit.
	 * The content of this list will be cleared before the thread is started.
	 * So in your thread, what you need to do is to call {@link ThreadSafeCharacterWriter#write(char)} to write the character to the list.
	 */
	private static ThreadSafeCharacterWriter resultWriter;


	/*
	 * TODO: Add suitable attributes to this class to help your implementation.
	 * You can add any attributes you want to this class.
	 * You can refer to the tips in the lab slides.
	 */


	/**
	 * The worker class which is {@link Runnable}.
	 * All worker objects will work together to merge their own char array and put results in {@link ParallelMerger#resultWriter}.
	 * <p>
	 * The merge rule is at follows:
	 * <p>
	 * 1. each worker thread take turns to write one char into {@link ParallelMerger#resultWriter}
	 * <p>
	 * 2. For example if we have two workers, worker0, worker1, with char array "hlo" and "el!" respectively.
	 * Then after the merge, {@link ParallelMerger#resultWriter} should contain: "hello!".
	 * 'h' should be written by the first worker, 'e' should be written by the second worker and 'l' should be written by the first worker, so on and so forth.
	 * <p>
	 * TODO: complete this class to implement the above functionality.
	 */
	private static class Worker implements Runnable {
		private final String segment;
		private boolean completed = false;
		private final BooleanSupplier previousCompleted;
		private final Lock sharedLock;
		private final Condition currentCondition;
		private final Condition nextCondition;

		private Worker(String segment) {
			this.segment = segment;
			this.previousCompleted = () -> true;
			this.sharedLock = new ReentrantLock();
			this.currentCondition = sharedLock.newCondition();
			this.nextCondition = sharedLock.newCondition();
		}

		private Worker(String segment, Worker lastWorker) {
			this.segment = segment;
			this.previousCompleted = () -> lastWorker.completed;
			this.sharedLock = lastWorker.sharedLock;
			this.currentCondition = lastWorker.nextCondition;
			this.nextCondition = sharedLock.newCondition();
		}

		@Override
		public void run() {
			sharedLock.lock();
			try {
				while (!previousCompleted.getAsBoolean()) {
					try {
						currentCondition.await();
					} catch (InterruptedException ignored) {
					}
				}
				for (char character : segment.toCharArray())
					resultWriter.write(character);
				completed = true;
				nextCondition.notify();
			} finally {
				sharedLock.unlock();
			}
		}
	}

	/**
	 * This method does the merge in an array of string segments. Here is the desired procedure:
	 * <ol>
	 *     <li>Create workers and their threads, the number of threads should be the same as the length of array {@code segments}.</li>
	 *     <li>Start all threads to merge the {@code segments} in parallel.</li>
	 *     <li>Wait all threads to finish process and exit.</li>
	 * </ol>
	 *
	 * @param segments     An array of strings that are to be merged. Each segment should be corresponding to one thread.
	 * @param resultWriter A {@link ThreadSafeCharacterWriter} instance that the workers should write the results to.
	 *                     The {@code resultWriter} should be written with the characters of merged string.
	 *                     For example, suppose the merge result should be "hello", then the {@code resultWriter}
	 *                     should be called 5 times with each character in the order.
	 *                     <p>
	 *                     TODO: complete this method to implement the above functionality.
	 */
	public static void merge(String[] segments, ThreadSafeCharacterWriter resultWriter) throws InterruptedException {
		ParallelMerger.resultWriter = resultWriter;
		// Start from here
		// ...
		List<Thread> threads = new ArrayList<>();
		Worker lastWorker = null;
		for (String segment : segments) {
			if (lastWorker == null) {
				lastWorker = new Worker(segment);
				threads.add(new Thread(lastWorker));
				continue;
			}
			lastWorker = new Worker(segment, lastWorker);
			threads.add(new Thread(lastWorker));
		}
		threads.forEach(Thread::start);
		for (Thread thread : threads) {
			thread.join();
		}
	}
}
