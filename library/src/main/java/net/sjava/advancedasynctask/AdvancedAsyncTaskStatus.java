package net.sjava.advancedasynctask;

/**
 * Indicates the current status of the task. Each status will be set only once
 * during the lifetime of a task.
 */
public enum AdvancedAsyncTaskStatus {
	/**
	 * Indicates that the task has not been executed yet.
	 */
	PENDING,
	/**
	 * Indicates that the task is running.
	 */
	RUNNING,
	/**
	 * Indicates that {@link AdvancedAsyncTask#onPostExecute} has finished.
	 */
	FINISHED,
}
