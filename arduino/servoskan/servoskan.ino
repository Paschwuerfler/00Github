#include <NewPing.h>

#define TRIGGER_PIN  7  // Arduino pin tied to trigger pin on the ultrasonic sensor.
#define ECHO_PIN     8  // Arduino pin tied to echo pin on the ultrasonic sensor.
#define MAX_DISTANCE 300 // Maximum distance we want to ping for (in centimeters). Maximum sensor distance is rated at 400-500cm.

NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE); // NewPing setup of pins and maximum distance.


#include <Servo.h>

Servo sx, sy;
int posx, posy;
int Step = 5;
int xs, ys = 0; //start
int xe, ye = 100; //end
int dist;






void setup() {
  sx.attach(4);
  sy.attach(5);
  Serial.begin(9600); // Open serial monitor at 115200 baud to see ping results.
  sx.write(0);
  sy.write(0);

}

void loop() {
  for (posy = ys; posy < ye; posy += Step) {
    sy.write(posy);
    sx.write(0);
    delay(1000);

    for (posx = xs; posx < 180; posx += Step) {
      sx.write(posx);
      delay(Step * 20);

      for (int i = 0; i < 5; i ++) {
        dist = sonar.ping_cm();
        if (dist > 0) {
          i = 6;
        }
      }
      delay(100);
      Serial.println(":x=" + posx);
      Serial.println(":y=" + posy);
      Serial.println(":d=" + dist);
      delay(100);
    }


  }
}

