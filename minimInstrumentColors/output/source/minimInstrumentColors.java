import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class minimInstrumentColors extends PApplet {




Minim minim;
AudioOutput out;

int  rand;
float frq;

public void setup(){
  size( 600,600);
  background(0);
  minim = new Minim(this);
  out = minim.getLineOut(); 
}

public void draw(){
 
  rand = PApplet.parseInt(random(50));
  
  frq = random(50,500);
  if (rand == 3){ 
    notes(frq);
    noFill();
    stroke(0,255,0,frq%255);
    rect(map(frq,50,500,0,width),random(0,height),50,50,5);  
}
}

public void notes(float freq)
{
  out.playNote(0.0f,0.5f, new senoide(freq));

}

class senoide implements Instrument
{
  
  Oscil wave;
  Line ampEnv;


   senoide (float frecuency) {
     wave = new Oscil( frecuency,0,Waves.SQUARE);
     ampEnv = new Line();
     ampEnv.patch(wave.amplitude);
  }

  public void noteOn(float duration)
  {

    ampEnv.activate(duration,0.5f,0);
    wave.patch(out);
  }

  public void noteOff()
  {
    wave.unpatch(out);
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "minimInstrumentColors" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
