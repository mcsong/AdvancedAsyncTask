package net.sjava.advancedasynctask;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Created by mcsong@gmail.com on 2015-09-09.
 */
public class AdvancedThreadPoolExecutorFactory {
	private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

	private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
	private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
	private static final int KEEP_ALIVE_SECONDS = 30;

	private int DEFAULT_QUEUE_SIZE = 64;
	private final ReentrantLock lock =  new ReentrantLock();

	private static final ThreadFactory sThreadFactory = new ThreadFactory() {
		private final AtomicInteger mCount = new AtomicInteger(1);
		public Thread newThread(Runnable r) {
			return new Thread(r, "AdvacnedAsyncTask #" + mCount.getAndIncrement());
		}
	};

	private BlockingQueue<Runnable> sTaskQueue;

	/**
	 * An {@link Executor} that can be used to execute tasks in parallel.
	 */
	private ThreadPoolExecutor THREAD_POOL_EXECUTOR;

	Executor getThreadPoolExecutor() {
		if(THREAD_POOL_EXECUTOR != null) {
			return THREAD_POOL_EXECUTOR;
		}

		return getThreadPoolExecutor(0);
	}

	Executor getThreadPoolExecutor(int queueSize) {
		return getThreadPoolExecutor(queueSize, null);
	}

	Executor getThreadPoolExecutor(int queueSize, RejectedExecutionHandler rejectedExecutionHandler) {
		return getThreadPoolExecutor(queueSize, null, rejectedExecutionHandler);
	}

	Executor getThreadPoolExecutor(int queueSize, BlockingQueue<Runnable> queue, RejectedExecutionHandler rejectedExecutionHandler) {
		if(queueSize == 0) {
			if(CPU_COUNT >= 8) {
				DEFAULT_QUEUE_SIZE = 256;
			} else if(CPU_COUNT >= 4) {
				DEFAULT_QUEUE_SIZE = 128;
			} else {
				DEFAULT_QUEUE_SIZE = 64;
			}
		} else if(queueSize > 0 && queueSize < 64) {
			DEFAULT_QUEUE_SIZE = 64;
		} else {
			DEFAULT_QUEUE_SIZE = queueSize;
		}

		if(queue == null) {
			sTaskQueue = new PriorityBlockingQueue<>(DEFAULT_QUEUE_SIZE);
		} else {
			sTaskQueue = queue;
		}

		RejectedExecutionHandler rejectHandler = rejectedExecutionHandler;
		if(rejectHandler == null) {
			rejectHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
		}

		THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS,
				TimeUnit.SECONDS, sTaskQueue, sThreadFactory, rejectHandler);

		THREAD_POOL_EXECUTOR.allowCoreThreadTimeOut(true);
		return THREAD_POOL_EXECUTOR;
	}

	private static AdvancedThreadPoolExecutorFactory instance = new AdvancedThreadPoolExecutorFactory();
	public static AdvancedThreadPoolExecutorFactory getInstance() {
		if(instance == null) {
			instance = new AdvancedThreadPoolExecutorFactory();
		}

		return instance;
	}

	private AdvancedThreadPoolExecutorFactory() { }

	public int getQueueCount() {
		if(sTaskQueue == null) {
			return 0;
		}

		return sTaskQueue.size();
	}

	public boolean setExecutorToAdvancedAsyncTask(int queueSize)
			throws InterruptedException {

		return setExecutorToAdvancedAsyncTask(queueSize, null);
	}

	public boolean setExecutorToAdvancedAsyncTask(int queueSize, BlockingQueue<Runnable> queue)
			throws InterruptedException {

		return setExecutorToAdvancedAsyncTask(queueSize, queue, null);
	}

	public boolean setExecutorToAdvancedAsyncTask(int queueSize, BlockingQueue<Runnable> queue,
	                                              RejectedExecutionHandler rejectedExecutionHandler) throws InterruptedException {
		if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
			try {
				return AdvancedAsyncTask.setThreadPoolExecutor(getThreadPoolExecutor(queueSize, queue, rejectedExecutionHandler));
			} finally {
				lock.unlock();
			}
		}

		return false;
	}

}
