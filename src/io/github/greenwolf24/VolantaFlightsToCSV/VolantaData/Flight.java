package io.github.greenwolf24.VolantaFlightsToCSV.VolantaData;

import java.sql.Time;
import java.util.ArrayList;
import java.util.UUID;

public class Flight
{
	public UUID Id;
	public String Callsign;
	public String FlightNumber;
	public Airport Origin;
	public UUID OriginId;
	public String OriginIcao;
	public Airport Destination;
	public UUID DestinationId;
	public String DestinationIcao;
	public Airport Alternate;
	public UUID AlternateId;
	public String AlternateIcao;
	public boolean hasDiverted;
	// there is a data field here called AtcRoute, but I do not know what it is
	public UUID SessionId;
	public String AircraftTitle;
	public String AircraftDirectory;
	public String AircraftRegistration;
	public String AircraftIcao;
	// There is a data field called Events here, but I do not know what it is
	public ArrayList<Position> Positions;
	public String Network;
	public String NetworkUserId;
	public String State;
	public String OffBlocksTime; // There should be a way to parse this, but Gson doesn't like it
	public String OnBlocksTime;
	public double RealBlockTime;
	public double EffectiveBlockTime;
	public double RealFlightTime;
	public double EffectiveFlightTime;
	public double FuelBurn;
	public double PlannedFuelBurn;
	public double PlannedBlockTime;
	public double PlannedFlightTime;
	public int PlannedPax; // may be a double, I am going by count
	public double PlannedCargo;
	public double PlannedAltitude;
	public String CreatedAt;
	public UUID ImportedFlightId; // assuming this is a UUID
	public UUID OriginCountryId;
	public UUID DestinationCountryId;
	public String Note;
	public String NetworkUserName; // assuming this is a string
	// Here there is a List Screenshot field, I do not know what it is
	// Here there is a List SavedFlightFiles field, I do not know what it is
	
	public String toString()
	{
		String ret = "";
		ret += "Id: " + Id + "\n";
		ret += "Callsign: " + Callsign + "\n";
		ret += "FlightNumber: " + FlightNumber + "\n";
		ret += "Origin: " + Origin + "\n";
		ret += "OriginId: " + OriginId + "\n";
		ret += "OriginIcao: " + OriginIcao + "\n";
		ret += "Destination: " + Destination + "\n";
		ret += "DestinationId: " + DestinationId + "\n";
		ret += "DestinationIcao: " + DestinationIcao + "\n";
		ret += "Alternate: " + Alternate + "\n";
		ret += "AlternateId: " + AlternateId + "\n";
		ret += "AlternateIcao: " + AlternateIcao + "\n";
		ret += "hasDiverted: " + hasDiverted + "\n";
		ret += "SessionId: " + SessionId + "\n";
		ret += "AircraftTitle: " + AircraftTitle + "\n";
		ret += "AircraftDirectory: " + AircraftDirectory + "\n";
		ret += "AircraftRegistration: " + AircraftRegistration + "\n";
		ret += "AircraftIcao: " + AircraftIcao + "\n";
		ret += "Positions: " + Positions + "\n";
		ret += "Network: " + Network + "\n";
		ret += "NetworkUserId: " + NetworkUserId + "\n";
		ret += "State: " + State + "\n";
		ret += "OffBlocksTime: " + OffBlocksTime + "\n";
		ret += "OnBlocksTime: " + OnBlocksTime + "\n";
		ret += "RealBlockTime: " + RealBlockTime + "\n";
		ret += "EffectiveBlockTime: " + EffectiveBlockTime + "\n";
		ret += "RealFlightTime: " + RealFlightTime + "\n";
		ret += "EffectiveFlightTime: " + EffectiveFlightTime + "\n";
		ret += "FuelBurn: " + FuelBurn + "\n";
		ret += "PlannedFuelBurn: " + PlannedFuelBurn + "\n";
		ret += "PlannedBlockTime: " + PlannedBlockTime + "\n";
		ret += "PlannedFlightTime: " + PlannedFlightTime + "\n";
		ret += "PlannedPax: " + PlannedPax + "\n";
		ret += "PlannedCargo: " + PlannedCargo + "\n";
		ret += "PlannedAltitude: " + PlannedAltitude + "\n";
		ret += "CreatedAt: " + CreatedAt + "\n";
		ret += "ImportedFlightId: " + ImportedFlightId + "\n";
		ret += "OriginCountryId: " + OriginCountryId + "\n";
		ret += "DestinationCountryId: " + DestinationCountryId + "\n";
		ret += "Note: " + Note + "\n";
		ret += "NetworkUserName: " + NetworkUserName + "\n";
		return ret;
	}
}
