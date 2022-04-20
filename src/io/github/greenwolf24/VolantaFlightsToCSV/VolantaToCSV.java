package io.github.greenwolf24.VolantaFlightsToCSV;

import com.google.gson.Gson;
import io.github.greenwolf24.VolantaFlightsToCSV.VolantaData.Flight;

import java.io.File;
import java.io.FileReader;

public class VolantaToCSV
{
	public static void main(String[] args)
	{
		try
		{
			File file = new File("data/JSON-in/2dc71ed9-f8ec-4d9e-870b-a9bcbc8bec3c.json");
			Flight flight = new Gson().fromJson(new FileReader(file), Flight.class);
			System.out.println(flight);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
