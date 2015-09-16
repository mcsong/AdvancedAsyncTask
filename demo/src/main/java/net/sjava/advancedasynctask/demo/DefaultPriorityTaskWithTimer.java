package net.sjava.advancedasynctask.demo;

import android.util.Log;
import net.sjava.advancedasynctask.*;

/**
 *
 * Created by mcsong@gmail.com on 2015-09-14.
 */

class DefaultPriorityTaskWithTimer extends AdvancedAsyncTask<String, String, String> {
	private String TAG_NAME = DefaultPriorityTaskWithTimer.class.getSimpleName();

	private int number;
	public DefaultPriorityTaskWithTimer(int number, AdvancedAsyncTaskCancelTimer timer) {
		super(QueuePriority.MEDIUM, ThreadPriority.MEDIUM, timer);

		this.number = number;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			//Log.i(NAME, number + " doInBackground run");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.number + " : finished";
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();

		Log.i(TAG_NAME, number + " cancelled");
	}

	@Override
	protected void onCancelled(String result) {
		super.onCancelled(result);

		Log.i(TAG_NAME, number + " cancelled result : " + result);
	}

	@Override
	protected void onPostExecute(String result) {
		Log.i(TAG_NAME, result + " : default : size : " + AdvancedThreadPoolExecutorFactory.getInstance().getQueueCount());
	}
}