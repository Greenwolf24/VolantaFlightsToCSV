package io.github.greenwolf24.VolantaFlightsToCSV;

import com.google.gson.Gson;
import io.github.greenwolf24.VolantaFlightsToCSV.VolantaData.Flight;


import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class VolantaToKML
{
	public static void main(String[] args)
	{
		try
		{
			File file = new File("data/JSON-in/2dc71ed9-f8ec-4d9e-870b-a9bcbc8bec3c.json");
			Flight flight = new Gson().fromJson(new FileReader(file), Flight.class);
			
			String filePath = "data/KML-out/";
			String fileName = "2dc71ed9-f8ec-4d9e-870b-a9bcbc8bec3c";
			
			ArrayList<io.github.greenwolf24.KMLmanage.Util.Position> positions = new ArrayList<>();
			for(io.github.greenwolf24.VolantaFlightsToCSV.VolantaData.Position position : flight.Positions)
			{
				double altitude = position.Altitude;
				// convert from feet to meters
				altitude *= 0.3048;
				positions.add(new io.github.greenwolf24.KMLmanage.Util.Position(position.Latitude, position.Longitude, (int)altitude));
			}
			io.github.greenwolf24.KMLmanage.Maker.PathMaker pathMaker = new io.github.greenwolf24.KMLmanage.Maker.PathMaker(filePath);
			pathMaker.makePathLine( positions, fileName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
