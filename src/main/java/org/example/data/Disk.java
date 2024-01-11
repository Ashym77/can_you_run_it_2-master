package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Disk {

	private int total;
	private int used;
	private int free;
	
	public Disk(int total, int used, int free) {
		this.total = total;
		this.used = used;
		this.free = free;
	}
	
	
	
	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public int getUsed() {
		return used;
	}



	public void setUsed(int used) {
		this.used = used;
	}



	public int getFree() {
		return free;
	}



	public void setFree(int free) {
		this.free = free;
	}



		public static Disk getDiskInfo() throws IOException, InterruptedException {

			 

			 /*
			  * Lägg scriptet i exec argumentet för att se disk info.
			  *  
			  * 
			  * Ni kan prova dessa i era terminaler		  * 
			  * Sök gärna upp om de kanske finns något annat script ni kan nytja.
			  * cmd(windows) terminal(mac) 
			  * 
			  * Windows
			  * wmic logicaldisk get deviceid, freespace, size, volumename
			  * 
			  * 
			  * Mac/linux
			  * df -h
			  * 
			  * 
			  * */

		        Process process = Runtime.getRuntime().exec("wmic logicaldisk get deviceid, freespace, size, volumename");
		        process.waitFor(); 

		        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		        String line;

		        int free = 0, used = 0, total = 0;

		        // Skip the header line
		        reader.readLine();

		        while ((line = reader.readLine()) != null) {
		        	System.out.println(line);
		        }

		        reader.close();
		        return new Disk(total, used, free);
	}
}
