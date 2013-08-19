package download;

import gui.panel.DownloadPanel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

	public static final String CURRENT_VERSION = "1.0";
	
	public static final String BUFFER_FORMAT = ".dlt";
	
	public static final int MAX_TASK_NUMBER = 10;
	
	private List<DLTask> myTasks;
	
	private int currentTaskId;
	
	public TaskManager() {
		currentTaskId = 1;
		myTasks = new ArrayList<DLTask>();
	}
	
	public DLTask addNewTask(String url, int threadNumber) {
		String desFileName = getTaskFileName(url);
		DLTask task = new DLTask(url, desFileName, threadNumber, currentTaskId); 
		myTasks.add(task);
		//myDownloadPanel.appendNewProgressBar(task);
		//task.startDownload();
		currentTaskId ++;
		return task;
	}
	
	public String getTaskFileName(String url) {
		return url.substring(url.lastIndexOf("/"));
	}
	
	public void serialize(int index) {
		
		DLTask buffer = myTasks.get(index);
		buffer.setTaskNotNew();
		FileOutputStream fos = null;
		ObjectOutputStream so = null;
		try {
			fos = new FileOutputStream(DLTask.DESTINATION_PATH + index + buffer.getFileName() + BUFFER_FORMAT);
		    so = new ObjectOutputStream(fos);
			so.writeObject(buffer);
			so.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				so.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void resumeTask(String serilizedFileName) {
		DLTask buffer = null;
		int index = Integer.parseInt(Character.toString(serilizedFileName.charAt(0)));
		FileInputStream fi = null;
		ObjectInputStream si = null;
		try {
			fi =  new FileInputStream(DLTask.DESTINATION_PATH + serilizedFileName);
	        si = new ObjectInputStream(fi);
	        buffer = (DLTask)si.readObject();
	        myTasks.add(index, buffer);
	        si.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				si.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
