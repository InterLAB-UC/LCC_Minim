
//probando paneo con uGens
/* 
 * desarrollado por Adrian Segovia Nessen ## tio necio ##
 * (http://segovianessen.cc)
 *
 *##  felices compilaciones ##
 */

import ddf.minim.*;
import ddf.minim.ugens.*;


Minim minim;
AudioOutput out;

Oscil sineOsc;
Pan pan;

void setup(){
  size(300,300);

  frameRate(30);

  minim = new Minim(this);

  out = minim.getLineOut(Minim.STEREO, 1024);

  sineOsc = new Oscil(300,0.5,Waves.SINE);

  pan = new Pan (0);

  sineOsc.patch(pan); 

  pan.patch(out);
}

void draw(){

  background(0);

  if (choice()==-1){
    fill(0,0,255);
    rect(0,0,width*.5,height);
  }
 if (choice()==1){
   fill(255,0,0);
   rect(width*.5,0,width*.5,height);
  }

 
  pan.setPan(choice());
}

int choice(){
  int step = int (random(2));
  int panRet = 0;
if (step == 0){
  panRet = 1;
}

if (step == 1){
  panRet = -1;
}


  return panRet;
  
}


void stop(){
  out.close();
  minim.stop();
  super.stop();
}
