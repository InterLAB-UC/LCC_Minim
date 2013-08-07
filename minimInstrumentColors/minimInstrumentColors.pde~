import ddf.minim.*;
import ddf.minim.ugens.*;

Minim minim;
AudioOutput out;

int  rand;
float frq;

void setup(){
  size( 600,600);
  background(0);
  minim = new Minim(this);
  out = minim.getLineOut(); 
}

void draw(){
 
  rand = int(random(50));
  
  frq = random(50,500);
  if (rand == 3){ 
    notes(frq);
    noFill();
    stroke(0,255,0,frq%255);
    rect(map(frq,50,500,0,width),random(0,height),50,50,5);  
}
}

void notes(float freq)
{
  out.playNote(0.0,0.5, new senoide(freq));

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

  void noteOn(float duration)
  {

    ampEnv.activate(duration,0.5f,0);
    wave.patch(out);
  }

  void noteOff()
  {
    wave.unpatch(out);
  }
}
