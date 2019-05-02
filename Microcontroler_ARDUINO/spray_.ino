//newLight is the most recent reading from the photoelectric diode
//oldLight is the previous reading from the photoelectric diode
//diff is the absolute value of the difference between oldLight and newLight
//isSignificant is a boolean variable. It is set to true when diff variable's value is greater than 50
//spray is a boolean variable. It basically serves the same purpose as the isSignificant variable

#include <Servo.h>
const int sensorPin = 0;
int newLight, oldLight, diff;
bool isSignificant, spray;

Servo servo1; //instantiating servo object

void setup() {
  servo1.attach(9);
  servo1.write(0); //making sure servo's initial position is 0
  oldLight = analogRead(sensorPin); 
  Serial.begin(9600);
  delay(5000);  
}
void loop() {
  //all functions are called from within this loop function
  newLight = analogRead(sensorPin);
  isSignificant = compare();
  if(isSignificant == true){
    turnServo();
  }
  oldLight = newLight; 
  showData(); //prints data to serial monitor
  delay(2500); 
}
bool compare() { 
  //this function determines if there has been a significant change in the lightLevel 
  diff = abs(oldLight - newLight);
  return (diff > 50);
}

void turnServo(){ 
  //this function turns the servo 
  servo1.write(50);
  delay(1000);
  servo1.write(0);
}

void showData(){ 
  //this function prints the data to serial monitor.
  Serial.print("new light: ");
  Serial.println(newLight);
  Serial.println("_________________");
  Serial.print("old light: ");
  Serial.println(oldLight);
}
