package net.sjava.advancedasynctask;

import java.util.concurrent.Callable;

/**
 *
 * Created by mcsong@gmail.com on 2015-09-08.
 */
abstract  class WorkerRunnable<Params, Result> implements Callable<Result> {
	Params[] mParams;
}
