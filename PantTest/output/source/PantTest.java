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

public class PantTest extends PApplet {


//probando paneo con uGens
/* 
 * desarrollado por Adrian Segovia Nessen ## tio necio ##
 * (http://segovianessen.cc)
 *
 *##  felices compilaciones ##
 */





Minim minim;
AudioOutput out;

Oscil sineOsc;
Pan pan;

public void setup(){
  size(300,300);

  frameRate(30);

  minim = new Minim(this);

  out = minim.getLineOut(Minim.STEREO, 1024);

  sineOsc = new Oscil(300,0.5f,Waves.SINE);

  pan = new Pan (0);

  sineOsc.patch(pan); 

  pan.patch(out);
}

public void draw(){

  background(0);

  if (choice()==-1){
    fill(0,0,255);
    rect(0,0,width*.5f,height);
  }
 if (choice()==1){
   fill(255,0,0);
   rect(width*.5f,0,width*.5f,height);
  }

 
  pan.setPan(choice());
}

public int choice(){
  int step = PApplet.parseInt (random(2));
  int panRet = 0;
if (step == 0){
  panRet = 1;
}

if (step == 1){
  panRet = -1;
}


  return panRet;
  
}


public void stop(){
  out.close();
  minim.stop();
  super.stop();
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "PantTest" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
