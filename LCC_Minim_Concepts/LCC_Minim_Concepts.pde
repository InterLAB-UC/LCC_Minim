/*
 * Este sketch trata de los conceptos fundamentales en
 * la librería Minim (http://code.compartmental.net/tools/minim)
 * 
 * desarrollado por Adrian Segovia Nessen ## tio necio ##
 * (http://segovianessen.cc)
 *
 *##  felices compilaciones ##
 */

// Minim trabaja bajo el concepto estandar de UGen, el cual puede generar
// un tono, un filtro o una envolvente.
/*
  Oscil(freq,amp,waveform)//http://code.compartmental.net/minim-beta/javadoc/ddf/minim/ugens/Oscil.html

    IIRFilter(freq,sampleRate)//http://code.compartmental.net/minim/javadoc/ddf/minim/effects/IIRFilter.html

  ADSR( maxAmp,attTime,decTime,susLvl,relTime)// http://code.compartmental.net/minim-beta/javadoc/ddf/minim/ugens/ADSR.html
 
  //otro concepto fundamental es el de las cadenas de síntesis, donde la idea es conectar una serie de UGens para complejizar el sonido

  oscillator.patch(filter).patch(effect).patch(out)

  //las cadenas pueden ser tan complejas como deseemos, y se pueden concatenar con el UGen Summer, el cual nos permite sumar las salidas de varios UGens

    noise.patch(filter).patch(summer)
    oscillator(filter1).patch(summer)
    summer.patch(effect).patch(out)
   
  //para que los UGens se implementen es necesario importar:

    import ddf.minim.ugens.*;

//hagamos unos sonidos

*/

//un filtro

import ddf.minim.*;
import ddf.minim.ugens.*;
  import ddf.minim.effects.*; // acá estan los filtros

Minim minim;
AudioOutput out;

//creamos las variables

IIRFilter filtro;
Oscil oscilador;
Oscil oscCorte;
Constant cutoff; //la constante del filtro


void setup(){
  size(600,600);
  
  minim = new Minim(this);
  out = minim.getLineOut();

  oscilador = new Oscil(700,0.5,Waves.SAW);
  //existen 5 tipos de filtro en minim :LowPassSP,LowPassFS,BandPass, HighPassSP,ChebFilter, NotchFilter.
  filtro = new BandPass(400,50,out.sampleRate());

    // vamos a crear un summer
    Summer sum = new Summer();
  cutoff = new Constant(1200);
  oscCorte = new Oscil(2,800,Waves.SQUARE);
  cutoff.patch(sum);
  oscCorte.patch(sum);

  sum.patch(filtro.cutoff);
  oscilador.patch(filtro).patch(out);
}

void draw(){
  background(0);

  float freq = map(mouseX,0,width,2,10);
  float freq1 = map(mouseX,0,width,700,60);
  float cutFreq = map(mouseY,0,height,1200,30);
  oscCorte.setFrequency(freq);
  oscilador.setFrequency(freq1);
  cutoff.setConstant(cutFreq);
 
 
  fill(255);
  ellipse(mouseX,height*.5,freq*5, freq*5);
  stroke(255);
  fill(0,15,255,25);
  ellipse(mouseX,height*.5,(freq1*5)/2, (freq1*5)/2);
  fill(255);
  text(freq1+"Oscil 1",mouseX,(height*0.5)+(5+freq*5));


  text(freq+"OscilCorte",mouseX-freq*5,(height*0.5)+(5+freq*5)-50);
  text(cutFreq+"cutoff",width*.5,mouseY);

}

void stop(){
  out.close();
  minim.stop();
  super.stop();
}
