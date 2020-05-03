package net.sjava.advancedasynctask.demo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import net.sjava.advancedasynctask.AdvancedAsyncTask;
import net.sjava.advancedasynctask.AdvancedAsyncTaskCancelTimer;
import net.sjava.advancedasynctask.AdvancedAsyncTaskCompat;

/**
 *
 * Created by mcsong@gmail.com on 2015-09-14.
 */
public class MainActivity extends AppCompatActivity {
	static String TAG_NAME = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn01 = (Button) findViewById(R.id.button01);
		btn01.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG_NAME, "Priority task test");

				Toast.makeText(MainActivity.this, "Priority task test clicked", Toast.LENGTH_SHORT).show();

				for (int i = 1; i < 200; i++) {
					int vv = i % 4;

					if (vv == 0) {
						AdvancedAsyncTaskCompat.executeParallel(new HighPriorityTask(i), "");
					} else {
						AdvancedAsyncTaskCompat.executeParallel(new DefaultPriorityTask(i), "");
					}
				}
			}
		});

		Button btn02 = (Button)findViewById(R.id.button02);
		btn02.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i(TAG_NAME, "TimerTask test");

				Toast.makeText(MainActivity.this, "TimerTask test clicked", Toast.LENGTH_SHORT).show();

				for (int i = 2; i < 200; i++) {
					new DefaultPriorityTaskWithTimer(i, new AdvancedAsyncTaskCancelTimer(1000, 500)).executeOnExecutor(AdvancedAsyncTask.getThreadPoolExecutor(), "");
				}

			}
		});



	}

}

