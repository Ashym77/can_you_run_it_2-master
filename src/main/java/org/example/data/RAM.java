package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RAM {

	private double memory;


	public double getMemory() {
		return memory;
	}

	public void setMemory(long memory) {
		this.memory = memory;
	}

	public RAM(long memory) {
		this.memory = memory;
	}

	public static RAM getRAMinfo() throws IOException, InterruptedException {

		/*
		 * Lägg scriptet i exec argumentet för att se ram/mem info.
		 *
		 * Ni kan prova dessa i era terminaler
		 * Sök gärna upp om de kanske finns något annat script ni kan nytja.
		 * cmd(windows) terminal(mac)
		 *
		 * Windows
		 * wmic memorychip get capacity
		 *
		 * Mac
		 * sysctl -n hw.memsize | awk '{print $0/1073741824" GB"}'
		 *
		 * Linux
		 * free -h
		 *
		 * */
		Process process = Runtime.getRuntime().exec("wmic memorychip get capacity");
		process.waitFor();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;

		long totalMemoryInKilobytes = 0;

		// Skip the header line
		reader.readLine();

		while ((line = reader.readLine()) != null) {
			// Check if the line is not empty
			if (!line.trim().isEmpty()) {
				// Extract numerical value from the line in kilobytes
				long capacityInKilobytes = Long.parseLong(line.trim());
				totalMemoryInKilobytes += capacityInKilobytes;
			}
		}

		reader.close();

		// Convert the total memory to gigabytes without rounding it
		double totalMemoryInGigabytes = (totalMemoryInKilobytes / (1024.0 * 1024.0)/1000);

		return new RAM((long) totalMemoryInGigabytes);
	}
}
