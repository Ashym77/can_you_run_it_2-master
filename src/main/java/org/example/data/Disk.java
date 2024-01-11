package org.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Disk {

	private double total;
	private double used;
	private double free;

	public Disk(double total, double used, double free) {
		this.total = total;
		this.used = used;
		this.free = free;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getUsed() {
		return used;
	}

	public void setUsed(double used) {
		this.used = used;
	}

	public double getFree() {
		return free;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public static Disk getDiskInfo() throws IOException, InterruptedException {

		Process process = Runtime.getRuntime().exec("wmic logicaldisk get deviceid, freespace, size, volumename");
		process.waitFor();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;

		double free = 0, used = 0, total = 0;

		// Skip the header line
		reader.readLine();

		while ((line = reader.readLine()) != null) {
			if (line.trim().matches("[A-Z]:.*")) {
				// Adjust the regex to handle variations in spacing
				String[] columns = line.trim().split("\\s+");

				long freeSpace = Long.parseLong(columns[1]);
				long size = Long.parseLong(columns[2]);

				// Accumulate total, used, and free space as doubles
				total += Math.round((size / 1000000000.0) * 100.0 ) / 100.0;
				used += Math.round(((size - freeSpace) / 1000000000.0) * 100.0) / 100.0;
				free += Math.round((freeSpace / 1000000000.0) * 100.0) / 100.0;
			}
		}

		//total = Math.round(total * 100.0) / 100.0;

		System.out.println("Total Space: " + total);
		System.out.println("Used Space: " + used);
		System.out.println("Free Space: " + free);

		reader.close();
		return new Disk(total, used, free);
	}

	public static void main(String[] args) {
		try {
			Disk diskInfo = getDiskInfo();
			// You can access the values as doubles here
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}