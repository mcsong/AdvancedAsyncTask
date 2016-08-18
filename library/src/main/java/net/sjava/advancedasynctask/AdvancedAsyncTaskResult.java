package net.sjava.advancedasynctask;

/**
 *
 * Created by mcsong@gmail.com on 2015-09-08.
 *
 */
@SuppressWarnings({"RawUseOfParameterizedType"})
class AdvancedAsyncTaskResult<Data> {
	final AdvancedAsyncTask mTask;
	final Data[] mData;

	AdvancedAsyncTaskResult(AdvancedAsyncTask task, Data... data) {
		mTask = task;
		mData = data;
	}
}
