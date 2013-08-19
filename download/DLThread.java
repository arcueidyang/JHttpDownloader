package download;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DLThread extends Thread {

	public static final int BUFFER_SIZE = 32;
	
	
	private DLTask myDLTask;
	private int myThreadId;
	private long myStartPos;
	private long myEndPos;
	private long myCurrentPos;
	
	private long byteHasRead;
	
	private boolean isNewTask;
	private boolean isFinished;
	private boolean isExecuting;
	
	private String myURLString;
	private URL myURL;
	private HttpURLConnection myConnection;
	
	
	public DLThread(DLTask dlTask, int threadId, long startPos, long endPos) {
		myDLTask = dlTask;
		myThreadId =  threadId;
		myStartPos = startPos;
		myEndPos = endPos;
		byteHasRead = 0;
		
		myCurrentPos = myStartPos;
	 
		isNewTask = true;
		isFinished = false;
		isExecuting = true;
		
		myURLString = dlTask.getURLString();
		try {
			myURL = new URL(myURLString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		BufferedInputStream inputStream = null;
		RandomAccessFile randomAccess = null;
		
		byte[] buffer = new byte[BUFFER_SIZE];
		
		try {
			myConnection = (HttpURLConnection)myURL.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		myConnection.disconnect();
		
		System.out.println("Thread" + myThreadId + " start to run.");
		
		try{
			if(isNewTask) {
				myConnection.setRequestProperty("Range", "bytes=" + myStartPos + "-" + myEndPos);
			
			}else {
				myConnection.setRequestProperty("Range", "bytes=" + myCurrentPos + "-" + myEndPos);
			}
				randomAccess = new RandomAccessFile(myDLTask.getFile(), "rw");
			    randomAccess.seek(isNewTask ? myStartPos : myCurrentPos);
			
			inputStream = new BufferedInputStream(myConnection.getInputStream());
			
			while(myCurrentPos < myEndPos && isExecuting) {
				int size = inputStream.read(buffer, 0 , BUFFER_SIZE);
				if(size == -1) {
					break;
				}
				randomAccess.write(buffer, 0, size);
				myCurrentPos += size;
				if(myCurrentPos > myEndPos) {
					byteHasRead += size - (myCurrentPos - myEndPos) + 1;
				}
			}
			
			System.out.println("Thread " + myThreadId + " has been finished.");
			
			isFinished = true;
			
			inputStream.close();
			randomAccess.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	public void pauseThread() {
		this.isExecuting = false;
	}
	
	public void resumeThread() {
		this.isExecuting = true;
	}
	
	public boolean isExecuting() { 
		return isExecuting;
	}
	
	public long getByteHasRead() {
		return byteHasRead;
	}
}
