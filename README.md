# VolantaFlightsToCSV
A project to take Volanta Flight Sim Recorder data and turn it's recorded flights into CSV for easy use of other programs

To get it working:
Export your volanta data.
find all the JSON files.
Place those JSONs into folder data/JSON-in
Also create folder data/KMl-out

Set the booleans to choose between FlightRadar24-WebFormat, FlightRadar-KMLFormat, and Volanta format
The FlightRadar KML format will be default if false is passed for both.
The Volanta format as of now only create the line, and does not extend to the ground. I plan to add this later.

I also plan to make the actual end-user running-bit a lot less complicated

This project makes use of the GSON library to parse the json file.
I am using GSON version 2.8.6 because it has the function to parse the json file.
The main reason for this being noted is that it appears the functions I am using are deprecated, so I do not know if it is supported by newer versions.
