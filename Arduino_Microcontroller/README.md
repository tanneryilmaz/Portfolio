# Arduino

My design group was assigned the task of designing a device which sprays air freshener when someone enters or exits the bathroom. 
The design consisted of an Arduino which was connected to a servo motor and a photoelectric diode. The arduino detects the amount of light in the room using the photoelectric diode. If the amount of light in the room changes drastically from one sampling period to the next, the arduino turns the servo motor, which presses down on an aerosol can. This causes air freshener to be sprayed into the bathroom. 

A sudden change in the amount of light in the room is used to determine when to spray the aerosol because it is assumed that a sudden 
change in light means that someone has either entered the room and turned the lights on, or exited the room and turned the lights off.

