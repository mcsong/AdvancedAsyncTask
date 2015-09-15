package net.sjava.advancedasynctask;

/**
 *
 *
 * Created by mcsong@gmail.com on 2015-09-09.
 */
public enum ThreadPriority {
	//Process.THREAD_PRIORITY_LOWEST
	//Process.THREAD_PRIORITY_BACKGROUND
	//Process.THREAD_PRIORITY_DEFAULT
	//Process.THREAD_PRIORITY_MORE_FAVORABLE
	LOW(19), MEDIUM(10), HIGH(0), HIGHEST(-1);

	private final int code;

	private ThreadPriority(int code) {
		this.code = code;
	}

	public int toInt() {
		return code;
	}
}
