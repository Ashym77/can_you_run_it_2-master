package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class CPU {
	
	private double frequency;
	private int cores;
	
	public CPU(double frequency, int cores) {
		this.frequency = frequency;
		this.cores = cores;
	}
	
	
	
	public double getFrequency() {
		return frequency;
	}



	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}



	public int getCores() {
		return cores;
	}



	public void setCores(int cores) {
		this.cores = cores;
	}



	public static CPU getCPUinfo() throws IOException, InterruptedException {

			 

			 /*
			  * Lägg scriptet i exec argumentet för att se cpu info
			  * Ni kan prova dessa i era terminaler
			  * Sök gärna upp om de kanske finns något annat script ni kan nytja.
			  * cmd(windows) terminal(mac) 
			  * 
			  * Windows
			  * wmic cpu get caption, deviceid, name, numberofcores, maxclockspeed, status 
			  * 
			  * Mac
			  * sysctl machdep.cpu
			  * 
			  * Linux
			  * lscpu
			  * 
			  * */

		        Process process = Runtime.getRuntime().exec("wmic cpu get caption, deviceid, name, numberofcores, maxclockspeed, status");
		        process.waitFor(); 

		        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		        String line;

				OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

				int availableProcessors = osBean.getAvailableProcessors();
		        double frequency = 0.0;
				int cores = 0;



		        // Skip the header line
		        reader.readLine();

		        while ((line = reader.readLine()) != null) {
		        	System.out.println(line);
		        }

		        reader.close();
		        return new CPU(frequency, cores);
	}
}
