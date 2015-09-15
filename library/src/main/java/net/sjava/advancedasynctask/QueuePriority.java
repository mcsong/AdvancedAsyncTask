package net.sjava.advancedasynctask;

/**
 *
 * Created by mcsong@gmail.com on 2015-09-09.
 */
public enum QueuePriority {
	LOWEST(0), LOW(1), MEDIUM(2), HIGH(3), HIGHEST(4);

	private final int code;

	private QueuePriority(int code) {
		this.code = code;
	}

	public int toInt() {
		return code;
	}
}
