import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.signals.*; 
import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sintesisAditiva extends PApplet {





Minim minim;
AudioOutput out,out1;
SawWave sine;
SawWave sine1;

Paseo p;

float amp = 0.5f;
float freq = 20;

public void setup()
{
  size(700,600);
  frameRate(1);
  //noStroke();
  noFill();
  minim = new Minim(this);
  
  out = minim.getLineOut(Minim.STEREO,512);
  out1 = minim.getLineOut(Minim.STEREO,512);
  
  sine = new SawWave(70,amp,44100);
  sine1 = new SawWave(1,amp,44100);
  
  out.addSignal(sine);
  out.addSignal(sine1);

  
  p = new Paseo();
  
}

public void draw()
{
  background(0);
  
  p.step1();

  
 
  sine.setPan(p.step1());
  sine.setAmp(p.step());
  sine1.setPan(p.step1());
  sine1.setAmp(p.step());
  sine1.setFreq(p.step()*freq);
  
}

class Paseo{
  int pan; //tambien nos ayudara a la posicion en pantalla
  float amp; //este es para la s\u00edntesis aditiva
  float vol;
  Paseo(){
    pan = 0;
    amp = 0.0f;
    
  }
  
  
  public float step(){
    int choice = PApplet.parseInt (random(2));
        
   vol = noise(amp);
     println(vol*255+" "+vol);
    if (choice == 0){
      
      amp ++;
    }
    
    if (choice == 1){
      
      amp --;
    }
    
    return vol;

}
   
    public int step1(){
    int choice = PApplet.parseInt (random(2));
   
  
    
    if (choice == 0){
      pan = 1;
      stroke(0,vol*300,vol*300);
      strokeWeight(vol*10);
     
    
    }
    
    if (choice == 1){
      pan = -1;
      //fill(255,vol*255,0);
      
      
    }
    
//    if (choice == 2){
//      //fill(0,vol*255,255,vol*255);
//      
//      pan = 0;
//      
//    }
    line(map(pan,-1,1,200,500),0,map(pan,-1,1,200,500),height);
    
    return pan;
}
     
  
}//EOC


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sintesisAditiva" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
