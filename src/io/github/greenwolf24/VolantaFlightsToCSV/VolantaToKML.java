package io.github.greenwolf24.VolantaFlightsToCSV;

import com.google.gson.Gson;
import io.github.greenwolf24.PolyTool.Color.Ranges.FlightRadar24AltitudeRange;
import io.github.greenwolf24.PolyTool.Color.Ranges.VolantaRange;
import io.github.greenwolf24.VolantaFlightsToCSV.VolantaData.Flight;
import io.github.greenwolf24.VolantaFlightsToCSV.VolantaData.Position;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

// TODO: The next challenge is getting the Volanta path to extend to the ground.
// This task is actually is something that will be done in the KMLManage library.


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
					System.out.println("Skipping likely buggy file " + file.getName() + ". This is flight " + flight.Callsign + "/" + flight.FlightNumber);
					continue;
				}
				
				String filePath = "data/KML-out/";
				// we need the filename without the leading path
				// we will also replace the extension with nothing
				String fileName = file.getName().replace(".json", "");
				
				String kml = colorLinePath(flight.Positions,flight.Callsign + "/" + flight.FlightNumber,false,true);
				
				File kmlFile = new File(filePath + fileName + ".kml");
				FileWriter fw = new FileWriter(kmlFile);
				fw.write(kml);
				fw.close();
				
				
				
				/* // This is the old way of doing it, with one single line as the path
				// I am keeping it here for reference and in case I need/want to revert
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
				//*/
				
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
	
	private static String onePlacemark(Position position1, Position position2,String name,boolean flightRadarWebFormat,boolean volantaFormat)
	{
		String base = "<Placemark>\n" +
				"        <Style>\n" +
				"          <LineStyle>\n" +
				"            <color>COLOR</color>\n" + // AA RR GG BB, no #
				"            <width>2</width>\n" +
				"          </LineStyle>\n" +
				"        </Style>\n" +
				"        <MultiGeometry>\n" +
				"          <LineString>\n" +
				"            <tessellate>1</tessellate>\n" +
				"            <altitudeMode>MODE</altitudeMode>\n" + // orig: absolute
				"            <coordinates>COORDS</coordinates>\n" + // orig: -80.269379,25.913589,38.1 -80.263947,25.913862,121.92    Lat,Lon,AltMeters
				"          </LineString>\n" +
				"        </MultiGeometry>\n" +
				"        <name>NAME</name>\n" + // Orig: P-50
				"      </Placemark>";
		
		if(position1.OnGround && position2.OnGround)
		{
			base = base.replace("MODE","clampToGround");
			base = base.replace("COLOR","ffffffff");
			// because we are on the ground, we can fill the altitude as 0
			base = base.replace("COORDS",position1.Longitude + "," + position1.Latitude + ",0 " + position2.Longitude + "," + position2.Latitude + ",0");
			base = base.replace("NAME",name);
		}
		else
		{
			base = base.replace("MODE","absolute");
			if(flightRadarWebFormat)
			{
				base = base.replace("COLOR", new FlightRadar24AltitudeRange().getColor((int) position1.Altitude).kmlFormat());
			}
			else if (volantaFormat)
			{
				base = base.replace("COLOR", VolantaRange.getColor((int) position1.Altitude).kmlFormat());
			}
			else
			{
				base = base.replace("COLOR", new FlightRadar24AltitudeRange().getColor((int) position1.Altitude).rawString(true));
			}
			// the Positions are in feet, convert to meters
			base = base.replace("COORDS",position1.Longitude + "," + position1.Latitude + "," + (position1.Altitude * 0.3048) + " " + position2.Longitude + "," + position2.Latitude + "," + (position2.Altitude * 0.3048));
			base = base.replace("NAME",name);
		}
		
		return base;
	}
	
	private static String colorLinePath(ArrayList<Position> positions, String name, boolean flightRadarWebFormat, boolean volantaFormat)
	{
		String top0 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
				"  <Document>\n" +
				"    <name>NAME</name>";
		
		String top1 = "<Folder>\n" +
				"      <name>Trail</name>";
		
		String bottom = " </Folder>\n" +
				"  </Document>\n" +
				"</kml>";
		
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(top0.replace("NAME",name));
		sb.append(top1);
		
		for(int i = 0; i < positions.size() - 1; i++)
		{
			Position position1 = positions.get(i);
			Position position2 = positions.get(i + 1);
			
			sb.append(onePlacemark(position1, position2, "P-" + i,flightRadarWebFormat,volantaFormat));
		}
		
		sb.append(bottom);
		
		return sb.toString();
	}
}
