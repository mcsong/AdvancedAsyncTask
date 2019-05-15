# AdvancedAsyncTask
[![Release](https://img.shields.io/badge/jcenter-1.0.6-blue.svg)](https://bintray.com/mcsong/maven/advancedasynctask)  [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AdvancedAsyncTask-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/2506)

<a href='https://ko-fi.com/mcsong' target='_blank'>
	<img height='34' style='border:0px;height:34px;' src='https://az743702.vo.msecnd.net/cdn/kofi4.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' />
</a>

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
    	implementation 'net.sjava:advancedasynctask:1.0.7'
    }
```

Maven
```xml
    <dependency>
     <groupId>net.sjava</groupId>
     <artifactId>advancedasynctask</artifactId>
     <version>1.0.7</version>
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

```
01-04 15:58:55.264 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 14 : finished : default : size : 46
01-04 15:58:55.265 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 55 : finished : default : size : 45
01-04 15:58:55.269 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 13 : finished : default : size : 44
01-04 15:58:55.270 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 53 : finished : default : size : 43
01-04 15:58:55.366 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 171 : finished : default : size : 42
01-04 15:58:55.366 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 90 : finished : default : size : 41
01-04 15:58:55.371 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 177 : finished : default : size : 39
01-04 15:58:55.372 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 179 : finished : default : size : 39
01-04 15:58:55.466 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 6 : finished : default : size : 38
01-04 15:58:55.467 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 183 : finished : default : size : 37
01-04 15:58:55.472 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 186 : finished : default : size : 36
01-04 15:58:55.473 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 94 : finished : default : size : 36
01-04 15:58:55.569 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 190 : finished : default : size : 33
01-04 15:58:55.570 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 25 : finished : default : size : 33
01-04 15:58:55.573 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 191 : finished : default : size : 32
01-04 15:58:55.575 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 193 : finished : default : size : 32
01-04 15:58:55.671 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 30 : finished : default : size : 30
01-04 15:58:55.671 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 27 : finished : default : size : 29
01-04 15:58:55.675 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 197 : finished : default : size : 29
01-04 15:58:55.677 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 199 : finished : default : size : 27
01-04 15:58:55.772 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 158 : finished : default : size : 26
01-04 15:58:55.773 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 15 : finished : default : size : 26
01-04 15:58:55.776 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 159 : finished : default : size : 25
01-04 15:58:55.778 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 161 : finished : default : size : 23
01-04 15:58:55.875 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 162 : finished : default : size : 21
01-04 15:58:55.876 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 166 : finished : default : size : 21
01-04 15:58:55.878 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 167 : finished : default : size : 20
01-04 15:58:55.879 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 169 : finished : default : size : 19
01-04 15:58:55.978 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 170 : finished : default : size : 18
01-04 15:58:55.978 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 175 : finished : default : size : 16
01-04 15:58:55.979 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 178 : finished : default : size : 16
01-04 15:58:55.980 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 182 : finished : default : size : 15
01-04 15:58:56.080 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 185 : finished : default : size : 12
01-04 15:58:56.080 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 189 : finished : default : size : 12
01-04 15:58:56.081 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 98 : finished : default : size : 11
01-04 15:58:56.081 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 195 : finished : default : size : 11
01-04 15:58:56.182 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 157 : finished : default : size : 11
01-04 15:58:56.182 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 82 : finished : default : size : 8
01-04 15:58:56.182 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 198 : finished : default : size : 7
01-04 15:58:56.183 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 165 : finished : default : size : 7
01-04 15:58:56.283 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 174 : finished : default : size : 5
01-04 15:58:56.283 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 86 : finished : default : size : 3
01-04 15:58:56.283 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 181 : finished : default : size : 3
01-04 15:58:56.283 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 50 : finished : default : size : 3
01-04 15:58:56.385 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 42 : finished : default : size : 2
01-04 15:58:56.385 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 194 : finished : default : size : 0
01-04 15:58:56.386 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 22 : finished : default : size : 0
01-04 15:58:56.386 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 173 : finished : default : size : 0
01-04 15:58:56.487 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 155 : finished : default : size : 0
01-04 15:58:56.487 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 46 : finished : default : size : 0
01-04 15:58:56.489 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTask: 187 : finished : default : size : 0
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

```
01-04 15:59:51.281 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 7 cancelled
01-04 15:59:51.281 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 7 cancelled result : null
01-04 15:59:51.294 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 17 cancelled
01-04 15:59:51.295 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 17 cancelled result : null
01-04 15:59:51.295 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 18 cancelled
01-04 15:59:51.295 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 18 cancelled result : null
01-04 15:59:51.303 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 19 cancelled
01-04 15:59:51.303 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 19 cancelled result : null
01-04 15:59:51.303 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 20 cancelled
01-04 15:59:51.303 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 20 cancelled result : null
01-04 15:59:51.309 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 34 cancelled
01-04 15:59:51.309 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 34 cancelled result : null
01-04 15:59:51.309 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 35 cancelled
01-04 15:59:51.309 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 35 cancelled result : null
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 6 cancelled
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 6 cancelled result : null
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 8 cancelled
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 8 cancelled result : null
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 9 cancelled
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 9 cancelled result : null
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 10 cancelled
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 10 cancelled result : null
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 11 cancelled
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 11 cancelled result : null
01-04 15:59:51.313 19847-19847/net.sjava.advancedasynctask.demo I/DefaultPriorityTaskWithTimer: 12 cancelled
```

## License

Copyright 2018 Justin Song

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
