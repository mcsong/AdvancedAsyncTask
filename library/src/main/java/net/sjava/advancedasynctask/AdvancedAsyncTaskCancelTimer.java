package net.sjava.advancedasynctask;

import android.os.CountDownTimer;

/**
 *
 * Timer for cancelling registered AdvancedAsyncTask instance.
 *
 * Created by mcsong@gmail.com on 2015-09-08.
 */
@SuppressWarnings("rawtypes")
public class AdvancedAsyncTaskCancelTimer extends CountDownTimer {
	private AdvancedAsyncTask advancedAsyncTask;
	private boolean interrupt;

	public AdvancedAsyncTaskCancelTimer(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
	}

	public void setAdvancedAsyncTask(AdvancedAsyncTask asyncTask) {
		this.advancedAsyncTask = asyncTask;
	}

	public void setInterrupt(boolean interrupt) {
		this.interrupt = interrupt;
	}

	public AdvancedAsyncTask getAdvancedAsyncTask() {
		return advancedAsyncTask;
	}

	public boolean isInterrupt() {
		return interrupt;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		if(advancedAsyncTask == null) {
			this.cancel();
			return;
		}

		if(advancedAsyncTask.isCancelled())
			this.cancel();

		if(advancedAsyncTask.getStatus() == AdvancedAsyncTaskStatus.FINISHED)
			this.cancel();
	}

	@Override
	public void onFinish() {
		if(advancedAsyncTask == null || advancedAsyncTask.isCancelled() )
			return;

		if(advancedAsyncTask.getStatus() == AdvancedAsyncTaskStatus.FINISHED)
			return;

		advancedAsyncTask.cancel(interrupt);
	}

}