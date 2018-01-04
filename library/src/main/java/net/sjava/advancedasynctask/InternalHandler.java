package net.sjava.advancedasynctask;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 *
 * Created by mcsong@gmail.com on 2015-09-08.
 */
class InternalHandler extends Handler implements Messages {
	public InternalHandler(Looper looper) {
		super(looper);
	}

	@SuppressWarnings({"unchecked", "RawUseOfParameterizedType"})
	@Override
	public void handleMessage(Message msg) {
		AdvancedAsyncTaskResult result = (AdvancedAsyncTaskResult) msg.obj;

		switch (msg.what) {
			case MESSAGE_POST_RESULT:
				result.mTask.finish(result.mData[0]);

				break;
			case MESSAGE_POST_PROGRESS:
				result.mTask.onProgressUpdate(result.mData);

				break;
		}
	}
}
