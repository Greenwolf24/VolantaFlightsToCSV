package io.github.greenwolf24.VolantaFlightsToCSV.VolantaData;

import java.sql.Time;
import java.sql.Timestamp;

public class Position
{
	public double Latitude;
	public double Longitude;
	public double Altitude;
	public double AltitudeAgl;
	public double Pitch;
	public double Bank;
	public double HeadingTrue;
	public double GroundSpeed;
	public boolean OnGround;
	public String Time; // There should be a way to convert this to a timestamp. Gson doesn't like the formatting in the file
	public String Network;
	public Double FrameRate;
	public Double MemoryUsedMB;
	public Double FuelKg;
	public int Transponder;
	public double VerticalSpeed;
}
