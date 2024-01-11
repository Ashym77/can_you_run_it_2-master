package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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



	public static CPU getRAMinfo() throws IOException, InterruptedException {

			 

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
		String oSystem = System.getProperty("os.name");
		String[] mainOS = oSystem.split("\\s+");
		String osFirstWord = mainOS[0];
		System.out.println(osFirstWord);

		Process process = switch (osFirstWord){
			case "Mac" -> Runtime.getRuntime().exec("sysctl machdep.cpu");
			case "Windows" -> Runtime.getRuntime().exec("wmic cpu get caption, deviceid, name, numberofcores, maxclockspeed, status");
			default -> Runtime.getRuntime().exec("lscpu");
		};

		process.waitFor();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;


		double frequency = 0.0; int cores = 0;

		// Skip the header line
		reader.readLine();

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		reader.close();
		return new CPU(frequency, cores);
	}


}
