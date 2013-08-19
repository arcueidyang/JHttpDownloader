package download;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLConnection;

public class DLTask implements Serializable{

	/**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 8400636398371562875L;

	public static final String DESTINATION_PATH = "./downloads/";
	
	public static final int MAX_THREAD_NUMBER = 20;
	
	private String myFileName;
	private File myFile;
	
	private int myThreadNumber;
	private int myTaskId;
	
	private boolean isNewTask;
	
	private Connector myConnector;
	private ThreadArranger myArranger;
	
	
	public DLTask(String url, String desFileName, int threadNumber, int taskId) {
		myThreadNumber = threadNumber;
		myConnector = new Connector(url);
		myArranger = new ThreadArranger(myThreadNumber);
		
		myFileName = desFileName;
		myFile = new File(DESTINATION_PATH + desFileName);
		try {
			myFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		isNewTask = true;
		myTaskId = taskId;
	}
	
	public void startDownload() {
		myArranger.arrangeThread(this);
	}
	
	public void pause() {
		myArranger.pause();
	}
	
	public void resume() {
		myArranger.resume();
	}
	
	public int getResourceLength() {
		return myConnector.getResourceLength();
	}
	
	public File getFile() {
		return myFile;
	}
	
	public URLConnection getConnection() {
		return myConnector.getConnection();
	}
	
	public int getTaskId() {
		return myTaskId;
	}
	
	public String getFileName() {
		return myFileName;
	}
	
	public void setTaskNotNew() {
	    isNewTask = false;
	}
	
	public String getURLString() {
		return myConnector.getURLString();
	}
	
	public boolean isDead() {
		return myArranger.isDead();
	}
	
	public int getCurrentByteDownloaded() {
		return myArranger.getTotalByteDownloaded();
	}
}
