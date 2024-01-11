package org.example;
import java.io.IOException;

import org.example.data.CPU;
import org.example.data.Computer;
import org.example.data.Disk;
import org.example.data.RAM;
import org.example.programs.Programs;

public class Main {

	public static void main(String[] args) throws Exception
	{
		  try {

	            // Get CPU information
	            CPU cpu = CPU.getCPUinfo();

	            // Get RAM information
	            RAM ram = RAM.getRAMinfo();

	            // Get Disk information
	            Disk disk = Disk.getDiskInfo();

	            // Create an instance of Computer
	            Computer myComputer = new Computer(disk, cpu, ram);

	            // Access and print information
	            System.out.println("CPU Frequency: " + myComputer.getCpu().getFrequency() + " GHz, Cores: " + myComputer.getCpu().getCores());
	            System.out.println("RAM Memory: " + myComputer.getRam().getMemory() + " GB");
	            System.out.println("Disk Free Space: " + myComputer.getDisk().getFree() + " GB, Used Space: " + myComputer.getDisk().getUsed() + " GB, Total Space: " + myComputer.getDisk().getTotal()+ " GB");

				tester(myComputer);
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }

	}

	public static void tester(Computer myComputer)
	{
		Programs[] programlist = Programs.createSamplePrograms();

		for (Programs program : programlist){
			System.out.println(program.getName());
			System.out.println("requiremet for cores: " + program.getCpu().getCores() + " - your spec: " + myComputer.getCpu().getCores());
			System.out.println("requirement for clokcspeed: " + program.getCpu().getFrequency() + " - your spec: " + myComputer.getCpu().getFrequency());
			System.out.println("requirement for ram: " + program.getRam().getMemory() + " - your spec: " + myComputer.getRam().getMemory());
			System.out.println("requirement for: " + program.getCpu().getFrequency() + " - your spec: " + myComputer.getCpu().getFrequency());
			System.out.println("requiremed space: " + program.getDisk().getUsed() + " - your free space: " + myComputer.getDisk().getFree());
			System.out.println("-----------------------------------------------------------");
		}

	}

}


/*
 * 
*/
