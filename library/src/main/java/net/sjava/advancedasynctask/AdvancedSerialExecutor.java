package net.sjava.advancedasynctask;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/**
 *
 * Created by mcsong@gmail.com on 2015-09-09.
 */
class AdvancedSerialExecutor implements Executor {

	final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();
	Runnable mActive;

	public synchronized void execute(final Runnable r) {
		mTasks.offer(new Runnable() {
			public void run() {
				try {
					r.run();
				} finally {
					scheduleNext();
				}
			}
		});

		if (mActive == null) {
			scheduleNext();
		}
	}

	protected synchronized void scheduleNext() {
		if((mActive = mTasks.poll()) == null)
			return;

		AdvancedAsyncTask.getThreadPoolExecutor().execute(mActive);
	}

}
