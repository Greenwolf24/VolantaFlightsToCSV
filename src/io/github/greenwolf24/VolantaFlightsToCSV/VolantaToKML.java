package io.github.greenwolf24.VolantaFlightsToCSV;

import com.google.gson.Gson;
import io.github.greenwolf24.VolantaFlightsToCSV.VolantaData.Flight;


import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;

public class VolantaToKML
{
	public static void main(String[] args)
	{
		File folder = new File("data/JSON-in/");
		File[] files = folder.listFiles();
		for(File file : files)
		{
			/*
			if(hardCodeBuggyFlight(file.getName()))
			{
				continue;
			}
			//*/
			
			try
			{
				Flight flight = new Gson().fromJson(new FileReader(file), Flight.class);
				
				if(likelyBuggyFlight(flight))
				{
					System.out.println("Skipping likely buggy file " + file.getName());
					continue;
				}
				
				String filePath = "data/KML-out/";
				// we need the filename without the leading path
				// we will also replace the extension with nothing
				String fileName = file.getName().replace(".json", "");
				
				ArrayList<io.github.greenwolf24.KMLmanage.Util.Position> positions = new ArrayList<>();
				for(io.github.greenwolf24.VolantaFlightsToCSV.VolantaData.Position position : flight.Positions)
				{
					double altitude = position.Altitude;
					// convert from feet to meters
					altitude *= 0.3048;
					positions.add(new io.github.greenwolf24.KMLmanage.Util.Position(position.Latitude, position.Longitude, (int) altitude));
				}
				io.github.greenwolf24.KMLmanage.Maker.PathMaker pathMaker = new io.github.greenwolf24.KMLmanage.Maker.PathMaker(filePath);
				pathMaker.makePathLine(positions, fileName);
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private static boolean likelyBuggyFlight(Flight flight)
	{
		// if the flight has less than 10 positions, it is probably a buggy flight
		if(flight.Positions.size() < 10)
		{
			return true;
		}
		return false;
	}
	
	private static boolean hardCodeBuggyFlight(String fileName)
	{
		String[] buggy = {"fcc8504c-3cbf-48d8-b1db-ae5b81827d3c"};
		for(String s : buggy)
		{
			if(s.equals(fileName.replace(".json", "")))
			{
				return true;
			}
		}
		return false;
	}
}
