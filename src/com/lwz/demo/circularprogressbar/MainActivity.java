package com.lwz.demo.circularprogressbar;

import com.lwz.demo.circularprogressbar.widget.CircularProgressBar;
import com.lwz.demo.circularprogressbar.widget.RateTextCircularProgressBar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private CircularProgressBar mCircularProgressBar;
	private RateTextCircularProgressBar mRateTextCircularProgressBar;
	
	private int progress = 0;
	
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			mCircularProgressBar.setProgress(msg.what);
			mRateTextCircularProgressBar.setProgress(msg.what);
			if( progress >= 100 ) {
				progress = 0;
			}
			mHandler.sendEmptyMessageDelayed(progress++, 100);
			super.handleMessage(msg);
		}
		
	};
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mCircularProgressBar = (CircularProgressBar)findViewById(R.id.circular_progress_bar);
		mCircularProgressBar.setMax(100);
		
		mRateTextCircularProgressBar = (RateTextCircularProgressBar)findViewById(R.id.rate_progress_bar);
		mRateTextCircularProgressBar.setMax(100);
		mRateTextCircularProgressBar.getCircularProgressBar().setCircleWidth(20);
		
		mHandler.sendEmptyMessageDelayed(progress++, 100);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
