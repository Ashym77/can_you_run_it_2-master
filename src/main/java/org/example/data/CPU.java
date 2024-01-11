package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.Buffer;

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
		int cores = 0;
		String tempText = "";

		Process cpuCores = Runtime.getRuntime().exec("wmic cpu get numberofcores");
		BufferedReader readCPUspecs = new BufferedReader(new InputStreamReader(cpuCores.getInputStream()));

		while((line = readCPUspecs.readLine()) != null){
			tempText += line.replaceAll("\\n", "");
		}

		cores = Integer.parseInt(tempText.replaceAll("[^0-9]", ""));

		Process cpuFrequency = Runtime.getRuntime().exec("wmic cpu get maxclockspeed");
		readCPUspecs = new BufferedReader(new InputStreamReader(cpuFrequency.getInputStream()));

		tempText = "";

		while ((line = readCPUspecs.readLine()) != null){
			tempText += line.replaceAll("\\n", "");
		}
		double frequency = Double.parseDouble(tempText.replaceAll("[^0-9]", ""));
		frequency = frequency / 1000;

		// Skip the header line
		reader.readLine();



		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		reader.close();
		return new CPU(frequency, cores);
	}
}
