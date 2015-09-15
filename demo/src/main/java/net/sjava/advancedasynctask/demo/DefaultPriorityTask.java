package net.sjava.advancedasynctask.demo;

import android.util.Log;
import net.sjava.advancedasynctask.AdvancedAsyncTask;
import net.sjava.advancedasynctask.AdvancedThreadPoolExecutorFactory;
import net.sjava.advancedasynctask.QueuePriority;
import net.sjava.advancedasynctask.ThreadPriority;

/**
 *
 *
 * Created by mcsong@gmail.com on 2015-09-14.
 */

class DefaultPriorityTask extends AdvancedAsyncTask<String, String, String> {
	private String TAG_NAME = DefaultPriorityTask.class.getSimpleName();

	private int number;

	public DefaultPriorityTask(int number) {
		super(QueuePriority.MEDIUM, ThreadPriority.MEDIUM);

		this.number = number;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			//Log.i(NAME, number + " doInBackground run");
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.number + " : finished";
	}

	@Override
	protected void onPostExecute(String result) {
		Log.i(TAG_NAME, result + " : default : size : " + AdvancedThreadPoolExecutorFactory.getInstance().getQueueCount());
	}
}