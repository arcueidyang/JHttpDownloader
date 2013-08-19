package download;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadArranger {

	private Thread[] myDLThreads;
	private ExecutorService pool;
	private int[] byteForEachThread;
	
	public ThreadArranger(int threadNum) {
		myDLThreads = new DLThread[threadNum];
		pool = Executors.newFixedThreadPool(threadNum);
		byteForEachThread = new int[threadNum];
	}
	
	public ThreadArranger() {
		this(1);
	}
	
	public void arrangeThread(DLTask task) {
		int threadNumber = getThreadNumber();
		int resourceLength = task.getResourceLength();
		int threadLength = resourceLength / threadNumber;
		for(int i = 0 ; i < threadNumber; i++) {
			DLThread thread;
			if(i == threadNumber - 1) {
				thread = new DLThread(task, i + 1, threadLength * i, resourceLength);
				byteForEachThread[i] = resourceLength - threadLength * i;
			}else {
				thread = new DLThread(task, i + 1, threadLength * i, threadLength * (i + 1));	
			    byteForEachThread[i] = threadLength;
			}
			myDLThreads[i] = thread;
			pool.execute(myDLThreads[i]);
		}
		pool.shutdown();
	}
	
	public void pause() {
		for(int i = 0 ; i < getThreadNumber(); i++) {
			DLThread thread = (DLThread)myDLThreads[i];
			if(thread != null) {
				if(thread.isAlive()) {
					thread.pauseThread();
				}
			}
		}
	}
	
	public void resume() {
		for(int i = 0 ; i < getThreadNumber(); i++) {
			DLThread thread = (DLThread)myDLThreads[i];
			if(thread != null) {
				if(thread.isAlive()) {
					thread.resumeThread();
				}
			}
		}
	}
	
	public int getThreadNumber() {
		return myDLThreads.length;
	}
	
	public boolean isDead() {
		return pool.isShutdown();
	}
	
	public int getTotalByteDownloaded() {
		int totalBytes = 0;
		for(int i = 0; i < myDLThreads.length; i++) {
			DLThread cur = (DLThread)myDLThreads[i];
			if(cur != null) totalBytes += cur.getByteHasRead();
			else {
				totalBytes += byteForEachThread[i];
			}
			 
		}
		return totalBytes;
	}
	
	
}
