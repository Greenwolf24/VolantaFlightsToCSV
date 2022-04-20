package io.github.greenwolf24.VolantaFlightsToCSV.VolantaData;

import java.util.UUID;

public class Airport
{
	public UUID Id;
	public String Name;
	public String IcaoCode;
	public String IataCode;
	public double Latitude;
	public double Longitude;
	public UUID CountryId;
	public String Country;
	public String Metar;
	
	public String toString()
	{
		String ret = "";
		ret += "Id: " + Id + "\n";
		ret += "Name: " + Name + "\n";
		ret += "IcaoCode: " + IcaoCode + "\n";
		ret += "IataCode: " + IataCode + "\n";
		ret += "Latitude: " + Latitude + "\n";
		ret += "Longitude: " + Longitude + "\n";
		ret += "CountryId: " + CountryId + "\n";
		ret += "Country: " + Country + "\n";
		ret += "Metar: " + Metar + "\n";
		return ret;
	}
}
