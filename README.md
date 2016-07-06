# AdvancedAsyncTask
[![Release](https://img.shields.io/badge/jcenter-1.0.2-blue.svg)](https://bintray.com/mcsong/maven/advancedasynctask)  [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AdvancedAsyncTask-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/2506)

## Overview
This library is enhanced for using AsyncTask api.

## Features
- You can handle task's priority in task queue(from fifo queue to priority queue).
- You can handle running thread's priority for task.
- You can set a timer for task.

## Gradle & Maven
Gradle
```groovy
    dependencies {
    	compile 'net.sjava:advancedasynctask:1.0.2'
    }
```

Maven
```xml
    <dependency>
     <groupId>net.sjava</groupId>
     <artifactId>advancedasynctask</artifactId>
     <version>1.0.2</version>
    </dependency>
```

## Usage
### Example 1
- Queue priority : default
- Running thread priority : default
- Timout : not used
```java
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
```
### Example 2
- Queue priority : high
- Running thread priority : high
- Timeout : not used

```java
class HighPriorityTask extends AdvancedAsyncTask<String, String, String> {
	private String TAG_NAME = HighPriorityTask.class.getSimpleName();
	private int number;

	public HighPriorityTask(int number) {
		super(QueuePriority.HIGH, ThreadPriority.HIGH);
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
		Log.i(TAG_NAME, result + " : high : size : " + AdvancedThreadPoolExecutorFactory.getInstance().getQueueCount());

	}
}
```

### Example 3
- Queue priority : default
- Running thread priority : default
- Timout : used
```java
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
```

## License

Copyright 2015 Justin Song

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
