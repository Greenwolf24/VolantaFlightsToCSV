package io.github.greenwolf24.VolantaFlightsToCSV;

import com.google.gson.Gson;
import io.github.greenwolf24.VolantaFlightsToCSV.VolantaData.Flight;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class VolantaToCSV
{
	public static void main(String[] args)
	{
		File folder = new File("data/JSON-in/");
		File[] files = folder.listFiles();
		for(File file : files)
		{
			try
			{
				//File file = new File("data/JSON-in/2dc71ed9-f8ec-4d9e-870b-a9bcbc8bec3c.json");
				Flight flight = new Gson().fromJson(new FileReader(file), Flight.class);
				//System.out.println(flight);
				
				// This small section is a bit of a hacky way of debugging something I found.
				// the error appears that there is a piece of flight data directly between two airports for no reason.
				// this is simple going to print out the filename of the flight if either the origin or destination matches either buggy airport.
				/*
				if(flight.OriginIcao.equals("KBRO") || flight.DestinationIcao.equals("MMMD"))
				{
					System.out.println(file.getName());
				}
				//*/
				
				// basic parse positions into csv
				String csvOutStr = "Time,Latitude,Longitude,Altitude" + "\n";
				for(int i = 0; i < flight.Positions.size(); i++)
				{
					String time = flight.Positions.get(i).Time;
					// we need to reformat the time
					// the input is in the format "2019-01-01T00:00:00Z"
					// the output is "2019-01-01 00:00:00"
					time = time.substring(0, 10) + " " + time.substring(11, 19);
					csvOutStr += time + "," + flight.Positions.get(i).Latitude + ", " + flight.Positions.get(i).Longitude + "," + flight.Positions.get(i).Altitude + "\n";
				}
				
				// write to file
				File csvOutFile = new File("data/CSV-out/" + file.getName().replace(".json", ".csv"));
				FileWriter csvOut = new FileWriter(csvOutFile);
				csvOut.write(csvOutStr);
				csvOut.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
