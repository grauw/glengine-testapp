package nl.grauw.glengine.test;

import android.os.SystemClock;

public class BenchmarkRunner {
	
	public static interface Benchmark {
		
		public Object benchmark();
		
	}
	
	private long startTime;
	private long stopTime;
	
	public BenchmarkRunner() {
	}
	
	public void run(int runs, Benchmark test) {
		for (int run = 0; run < runs; run++) {
			start();
			Object output = test.benchmark();
			stop();
			
			android.util.Log.d("GGG", "Benchmark took: " + (stopTime - startTime) + "ms. Output: " + output);
		}
	}
	
	public BenchmarkRunner start() {
		System.gc();
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			throw new RuntimeException("Sleep exception", e);
		}
		
		startTime = SystemClock.elapsedRealtime();
		
		return this;
	}
	
	public void stop() {
		stopTime = SystemClock.elapsedRealtime();
	}
	
}
