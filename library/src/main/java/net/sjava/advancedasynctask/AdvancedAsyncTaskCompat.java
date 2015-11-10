package net.sjava.advancedasynctask;

/**
 *
 * Created by mcsong@gmail.com on 2015-11-10.
 *
 */
public class AdvancedAsyncTaskCompat {

    public static <Params, Progress, Result> AdvancedAsyncTask<Params, Progress, Result> executeParallel(
            AdvancedAsyncTask<Params, Progress, Result> task, Params... params) {
        if (task == null)
            throw new IllegalArgumentException("task can not be null");

        task.executeOnExecutor(AdvancedAsyncTask.getThreadPoolExecutor(), params);
        return task;
    }

}
