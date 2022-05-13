# VolantaFlightsToCSV
A project to take Volanta Flight Sim Recorder data and turn it's recorded flights into CSV for easy use of other programs

~~The method of downloading flight data is strange, as it appears Volanta only allows one full extraction of the data.
This may be an oversight of Volanta, but I found another method of extracting the data.
The downside is that it is a lot more hands-on.
In the Volanta Web App, open the browser Dev Tools, and open the network tab.
Click through to a flight that you want to extract in the web app.
The network tab will find a json file that contains the flight data.
Copy the json file to the clipboard.
Paste the json file into a text editor, and save it as a .json file.~~
Turns out this is not the case, it just takes some time for the button to re-appear.

This project makes use of the GSON library to parse the json file.
I am using GSON version 2.8.6 because it has the function to parse the json file.
The main reason for this being noted is that it appears the functions I am using are deprecated, so I do not know if it is supported by newer versions.
