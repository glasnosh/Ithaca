/*
 *  "cubo.java"	
 *	Crea un JPanel que contiene la forma cúbica de la malla con los objetos encima y girando de forma "cuasialeatoria" sobre los 3 ejes
 *	
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 *
 *  Este código, aunque original, está basado en las ideas de otro encontrado en la Red. Gracias a su autor (desconocido...  :-(  ).
 */

/*
 *	COMO SE ORDENA LA malla ...
 *	
 *	||====|====|====||====|====|====||====|====|====||
 *	|| 78 | 79 | 80 || 60 | 61 | 62 || 51 | 52 | 53 ||
 *	||----|----|----||----|----|----||----|----|----||
 *	|| 81 | 82 | 83 || 63 | 64 | 65 || 54 | 55 | 56 ||
 *	||----|----|----||----|----|----||----|----|----||	
 *	|| 84 | 85 | 86 || 66 | 67 | 68 || 57 | 58 | 59 ||
 *	||====|====|====||====|====|====||====|====|====||
 *	|| 69 | 70 | 71 || 35 | 34 | 33 || 42 | 43 | 44 ||
 *	||----|----|----||----|----|----||----|----|----||
 *	|| 72 | 73 | 74 || 38 | 37 | 36 || 45 | 46 | 47 ||
 *	||----|----|----||----|----|----||----|----|----||
 *	|| 75 | 76 | 77 || 41 | 40 | 39 || 48 | 49 | 50 ||
 *	||====|====|====||====|====|====||====|====|====||
 *	 
 *	 
 *	...SOBRE LAS CARAS DEL CUBO. Los números representan los puntos centrales de cada cara en el dibujo
 *	
 *	                                ||====|====|====||
 *				        || 60 | 61 | 62 ||
 *				        ||----|----|----||
 *				        || 63 | 64 | 65 ||
 *				        ||----|----|----||
 *	       			        || 66 | 67 | 68 ||
 *	||====|====|====||====|====|====||====|====|====||====|====|====||
 *	|| 78 | 79 | 80 || 69 | 70 | 71 || 35 | 34 | 33 || 42 | 43 | 44 ||
 *	||----|----|----||----|----|----||----|----|----||----|----|----||
 *	|| 81 | 82 | 83 || 72 | 73 | 74 || 38 | 37 | 36 || 45 | 46 | 47 ||
 *	||----|----|----||----|----|----||----|----|----||----|----|----||
 *	|| 84 | 85 | 86 || 75 | 76 | 77 || 41 | 40 | 39 || 48 | 49 | 50 ||
 *	||====|====|====||====|====|====||====|====|====||====|====|====||
 *					|| 51 | 52 | 53 ||
 *					||----|----|----||
 *					|| 54 | 55 | 56 ||
 *					||----|----|----||
 *					|| 57 | 58 | 59 ||
 *					||==============||
 *	
 *								       
 *	 
 *	public cubo(malla mimalla)		//constructor
 *	public void paint(Graphics g)	
 *	public void run()
 *	public synchronized void stop()
 *	public Image getImage(String name)	//Crea un objeto Image a partir de su localización (local o remota)
 *	public int traducir(int x,int y)	//de coordenadas en la malla a la numeración de casillas mostrada
  
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

import java.awt.image.*;
import java.net.URL;


public class cubo extends JPanel implements Runnable {

	private Thread idx_Thread = null;
	private int threadpriority=idx_Thread.MIN_PRIORITY;

	private double rotatex=10;
	private double rotatey=0;
	private double rotatez=0;

	private Color bgcolor=new Color(0,0,0);
	private Color fgcolor=new Color(0,255,0);

	private double centerx;
	private double centery;

	private double x[]=new double[144];
	private double y[]=new double[144];
	private double z[]=new double[144];
	private int xx[]=new int[144];
	private int yy[]=new int[144];

	private double pi=3.14159265;
	
	Image img_cubo_rojo =		 getImage("png/cubo_rojo.png");	//Cogemos una imagen base de cada y según sea "grande" o 
	Image img_cubo_azul =		 getImage("png/cubo_azul.png");	// "pequeña" la redimensionamos al dibujarla
	Image img_cubo_amarillo =	 getImage("png/cubo_amarillo.png");
	Image img_esfera_roja = 	 getImage("png/esfera_roja.png");
	Image img_esfera_azul = 	 getImage("png/esfera_azul.png");
	Image img_esfera_amarilla = 	 getImage("png/esfera_amarilla.png");
	Image img_piramide_roja = 	 getImage("png/piramide_roja.png");
	Image img_piramide_azul = 	 getImage("png/piramide_azul.png");
	Image img_piramide_amarilla = 	 getImage("png/piramide_amarilla.png");
	
	malla mimalla;
	
	
    public cubo(malla mimalla){ //constructor
    
		this.mimalla = mimalla;
	
		rotatex=(double)(80)/2;
		rotatey=(double)(40)/2;
		rotatez=(double)(80)/2;
		
		bgcolor=new Color(0,0,0);
		fgcolor=new Color(255,255,255);

		rotatex=(rotatex/180*pi)/20;
		rotatey=(rotatey/180*pi)/20;
		rotatez=(rotatez/180*pi)/20;


		//Esquinas del cubo
    		x[1]=-1;
		y[1]=-1;
		z[1]=-1;
		
		x[2]= 1;
		y[2]=-1;
		z[2]=-1;
		
		x[3]= 1;
		y[3]=-1;
		z[3]= 1;
		
		x[4]=-1;
		y[4]=-1;
		z[4]= 1;
		
		x[5]=-1;
		y[5]= 1;
		z[5]=-1;
		
		x[6]= 1;
		y[6]= 1;
		z[6]=-1;
		
		x[7]= 1;
		y[7]= 1;
		z[7]= 1;
		
		x[8]=-1;
		y[8]= 1;
		z[8]= 1;

		//puntos de división de las caras
		x[9]= 0.333;
		y[9]= 1;
		z[9]= 1;
		
		x[10]= 0.333;
		y[10]=-1;
		z[10]= 1;
		
		x[11]= -0.333;
    		y[11]= 1;
		z[11]= 1;
		
		x[12]= -0.333;
		y[12]= -1;
		z[12]= 1;
		
		x[13]= -1;
		y[13]= 0.333;
		z[13]= 1;
		
		x[14]= -1;
		y[14]= -0.333;
		z[14]= 1;
		
		x[15]= 1;
		y[15]= 0.333;
		z[15]= 1;
		
		x[16]= 1;
		y[16]= -0.333;
		z[16]= 1;
		
		x[17]= 1;
		y[17]= 0.333;
		z[17]= -1;
		
		x[18]= 1;
		y[18]= -0.333;
		z[18]= -1;
		
		x[19]= 1;
		y[19]= -1;
		z[19]= 0.333;
		
		x[20]= 1;
		y[20]= -1;
		z[20]= -0.333;
		
		x[21]= 1;
		y[21]= 1;
		z[21]= 0.333;
		
		x[22]= 1;
		y[22]= 1;
		z[22]= -0.333;
		
		x[23]= 0.333;
		y[23]= 1;
		z[23]= -1;
		
		x[24]= -0.333;
		y[24]= 1;
		z[24]= -1;
		
		x[25]= -1;
		y[25]= 1;
		z[25]= -0.333;
		
		x[26]= -1;
		y[26]= 1;
		z[26]= 0.333;
		
		x[27]= -1;
		y[27]= 0.333;
		z[27]= -1;
		
		x[28]= -1;
		y[28]= -0.333;
		z[28]= -1;
		
		x[29]= -1;
		y[29]= -1;
		z[29]= -0.333;
		
		x[30]= -1;
		y[30]= -1;
		z[30]= 0.333;
		
		x[31]= -0.333;
		y[31]= -1;
		z[31]= -1;
		
		x[32]= 0.333;
		y[32]= -1;
		z[32]= -1;
		
		//puntos centrales de cada cuadrícula
		x[33]= 0.666;
		y[33]= 0.666;
		z[33]= 1;
		
		x[34]= 0 ;
		y[34]= 0.666;
		z[34]= 1;
		
		x[35]= -0.666;
		y[35]= 0.666;
		z[35]= 1;
		
		x[36]= 0.666;
		y[36]= 0;
		z[36]= 1;
		
		x[37]= 0;
		y[37]= 0;
		z[37]= 1;
		
		x[38]= -0.666;
		y[38]= 0;
		z[38]= 1;
		
		x[39]= 0.666;
		y[39]= -0.666;
		z[39]= 1;
		
		x[40]= 0;
		y[40]= -0.666;
		z[40]= 1;
		
		x[41]= -0.666;
		y[41]= -0.666;
		z[41]= 1;
		
		//
		x[42]= 1;
		y[42]= 0.666;
		z[42]= 0.666;
		
		x[43]= 1;
		y[43]= 0.666;
		z[43]= 0;
		

		x[44]= 1;
		y[44]= 0.666;
		z[44]= -0.666;
		
		x[45]= 1;
		y[45]= 0;
		z[45]= 0.666;
		
		x[46]= 1;
		y[46]= 0;
		z[46]= 0;
		
		x[47]= 1;
		y[47]= 0;
		z[47]= -0.666;
		
		x[48]= 1;
		y[48]= -0.666;
		z[48]= 0.666;
		
		x[49]= 1;
		y[49]= -0.666;
		z[49]= 0;
		
		x[50]= 1;
		y[50]= -0.666;
		z[50]= -0.666;
		
		//
		x[51]= -0.666;
		y[51]= -1;
		z[51]= 0.666;
		
		x[52]= 0;
		y[52]= -1;
		z[52]= 0.666;
		
		x[53]= 0.666;
		y[53]= -1;
		z[53]= 0.666;
		
		x[54]= -0.666;
		y[54]= -1;
		z[54]= 0;
		
		x[55]= 0;
		y[55]= -1;
		z[55]= 0;
		
		x[56]= 0.666;
		y[56]= -1;
		z[56]= 0;
		
		x[57]= -0.666;
		y[57]= -1;
		z[57]= -0.666;
		
		x[58]= 0;
		y[58]= -1;
		z[58]= -0.666;
		
		x[59]= 0.666;
		y[59]= -1;
		z[59]= -0.666;
		
		//
		x[60]= -0.666;
		y[60]= 1;
		z[60]= -0.666;
		
		x[61]= 0;
		y[61]= 1;
		z[61]= -0.666;
		
		x[62]= 0.666;
		y[62]= 1;
		z[62]= -0.666;
		
		x[63]= -0.666;
		y[63]= 1;
		z[63]= 0;
		
		x[64]= 0;
		y[64]= 1;
		z[64]= 0;
		
		x[65]= 0.666;
		y[65]= 1;
		z[65]= 0;
		
		x[66]= -0.666;
		y[66]= 1;
		z[66]= 0.666;
		
		x[67]= 0;
		y[67]= 1;
		z[67]= 0.666;
		
		x[68]= 0.666;
		y[68]= 1;
		z[68]= 0.666;
		
		//
		x[69]= -1;
		y[69]= 0.666;
		z[69]= -0.666;
		
		x[70]= -1;
		y[70]= 0.666;
		z[70]= 0;
		
		x[71]= -1;
		y[71]= 0.666;
		z[71]= 0.666;
		
		x[72]= -1;
		y[72]= 0;
		z[72]= -0.666;
		
		x[73]= -1;
		y[73]= 0;
		z[73]= 0;
		
		x[74]= -1;
		y[74]= 0;
		z[74]= 0.666;
		
		x[75]= -1;
		y[75]= -0.666;
		z[75]= -0.666;
		
		x[76]= -1;
		y[76]= -0.666;
		z[76]= 0;
		
		x[77]= -1;
		y[77]= -0.666;
		z[77]= 0.666;
		
		//
		x[78]= 0.666;
		y[78]= 0.666;
		z[78]= -1;

		x[79]= 0;
		y[79]= 0.666;
		z[79]= -1;
		
		x[80]= -0.666;
		y[80]= 0.666;
		z[80]= -1;
		
		x[81]= 0.666;
		y[81]= 0;
		z[81]= -1;
		
		x[82]= 0;
		y[82]= 0;
		z[82]= -1;
		
		x[83]= -0.666;
		y[83]= 0;
		z[83]= -1;
		
		x[84]= 0.666;
		y[84]= -0.666;
		z[84]= -1;
		
		x[85]= 0;
		y[85]= -0.666;
		z[85]= -1;
		
		x[86]= -0.666;
		y[86]= -0.666;
		z[86]= -1;

    }
	
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();	
	int x_size = (d.width-10);	
	int y_size = (d.height-10);	
	
	//La siguiente linea es mejor no añadirla porque podría perjudicar el rendimento en procesadores lentos.
       	//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
	
	g2.setBackground(Color.black);	

        g2.clearRect(0, 0, d.width, d.height);	
	g2.setColor(Color.orange);					//las lineas se pintan en naranja
	
	centerx=(double)(d.width)/2;
	centery=(double)(d.height)/2;
	
	double qx,qy,qz;

	for (int i=1;i<=86;i++)	//-1 que el de abajo
		{

		// Rotation arround x-axis
			qy=y[i];
			qz=z[i];
			y[i]= qy*Math.cos(rotatex) - qz*Math.sin(rotatex);
			z[i]= qz*Math.cos(rotatex) + qy*Math.sin(rotatex);

		// Rotation arround y-axis
			qz=z[i];
			qx=x[i];
			z[i]= qz*Math.cos(rotatey) - qx*Math.sin(rotatey);
			x[i]= qx*Math.cos(rotatey) + qz*Math.sin(rotatey);

		// Rotation arround z-axis
			qx=x[i];
			qy=y[i];
			x[i]= qx*Math.cos(rotatez) - qy*Math.sin(rotatez);
			y[i]= qy*Math.cos(rotatez) + qx*Math.sin(rotatez);
		}

	// Calculate Points (3d -> 2d)
	for (int i=1;i<87;i++)
		{
			xx[i]=(int)(x[i]*(5/(5+z[i]))*centerx/2+centerx);
			yy[i]=(int)(y[i]*(5/(5+z[i]))*centery/2+centery);
		}
	
	// Delete DoubleBuffer
		g2.setColor(Color.black);
		g2.fillRect(0,0,(int)(centerx*2),(int)(centery*2));

	// Draw Cube edges
		g2.setColor(Color.orange);
		for (int i=1;i<5;i++)
		{
				g2.drawLine(xx[i],yy[i],xx[i+4],yy[i+4]);
				g2.drawLine(xx[i],yy[i],xx[(i%4)+1],yy[(i%4)+1]);
				g2.drawLine(xx[i+4],yy[i+4],xx[(i%4)+5],yy[(i%4)+5]);				
		}
		g2.drawLine(xx[9],yy[9],xx[10],yy[10]);
		g2.drawLine(xx[11],yy[11],xx[12],yy[12]);
		g2.drawLine(xx[13],yy[13],xx[15],yy[15]);
		g2.drawLine(xx[14],yy[14],xx[16],yy[16]);
		
		g2.drawLine(xx[15],yy[15],xx[17],yy[17]);
		g2.drawLine(xx[16],yy[16],xx[18],yy[18]);
		g2.drawLine(xx[19],yy[19],xx[21],yy[21]);
		g2.drawLine(xx[20],yy[20],xx[22],yy[22]);
		
		g2.drawLine(xx[9],yy[9],xx[23],yy[23]);
		g2.drawLine(xx[11],yy[11],xx[24],yy[24]);
		g2.drawLine(xx[22],yy[22],xx[25],yy[25]);
		g2.drawLine(xx[21],yy[21],xx[26],yy[26]);
		
		g2.drawLine(xx[10],yy[10],xx[32],yy[32]);
		g2.drawLine(xx[12],yy[12],xx[31],yy[31]);
		g2.drawLine(xx[19],yy[19],xx[30],yy[30]);
		g2.drawLine(xx[20],yy[20],xx[29],yy[29]);
		
		g2.drawLine(xx[13],yy[13],xx[27],yy[27]);
		g2.drawLine(xx[14],yy[14],xx[28],yy[28]);
		g2.drawLine(xx[26],yy[26],xx[30],yy[30]);
		g2.drawLine(xx[25],yy[25],xx[29],yy[29]);
		
		g2.drawLine(xx[17],yy[17],xx[27],yy[27]);
		g2.drawLine(xx[18],yy[18],xx[28],yy[28]);
		g2.drawLine(xx[23],yy[23],xx[32],yy[32]);
		g2.drawLine(xx[24],yy[24],xx[31],yy[31]);
		
		for (int i=0; i<6; i++){		
			for (int j=0; j<9; j++){
	                   if (mimalla.devolverCasilla(i,j).ocupada() == true){
				int tamanio = 0; 
				int pos_x = 0;
				int pos_y = 0;
				if (mimalla.devolverCasilla(i,j).tamanho() == 1){	//objeto grande
					tamanio = 25;
					pos_x = xx[traducir(i,j)]-8;
					pos_y = yy[traducir(i,j)]-8;
					
					if (mimalla.devolverCasilla(i,j).forma() == 1){//esfera
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_esfera_roja,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_esfera_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_esfera_amarilla,pos_x,pos_y,tamanio,tamanio,null);
					}
					else if (mimalla.devolverCasilla(i,j).forma() == 2){//cubo
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_cubo_rojo,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_cubo_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_cubo_amarillo,pos_x,pos_y,tamanio,tamanio,null);
					}
					else if (mimalla.devolverCasilla(i,j).forma() == 3){//piramide
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_piramide_roja,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_piramide_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_piramide_amarilla,pos_x,pos_y,tamanio,tamanio,null);
					}//if
				}
				else if (mimalla.devolverCasilla(i,j).tamanho() == 2){	//objeto pequeño
					tamanio = 15;
					pos_x = xx[traducir(i,j)]-3;
					pos_y = yy[traducir(i,j)]-3;
					
					if (mimalla.devolverCasilla(i,j).forma() == 1){//esfera
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_esfera_roja,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_esfera_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_esfera_amarilla,pos_x,pos_y,tamanio,tamanio,null);
					}
					else if (mimalla.devolverCasilla(i,j).forma() == 2){//cubo
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_cubo_rojo,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_cubo_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_cubo_amarillo,pos_x,pos_y,tamanio,tamanio,null);
					}
					else if (mimalla.devolverCasilla(i,j).forma() == 3){//piramide
					  if (mimalla.devolverCasilla(i,j).color()==1) g2.drawImage(img_piramide_roja,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==2) g2.drawImage(img_piramide_azul,pos_x,pos_y,tamanio,tamanio,null);
					  if (mimalla.devolverCasilla(i,j).color()==3) g2.drawImage(img_piramide_amarilla,pos_x,pos_y,tamanio,tamanio,null);
					}//if
				}//else
			}//if
		}//for
	}//for
		
	//nombres de los elementos
	for (int i=0; i<6; i++){		
		for (int j=0; j<9; j++){
			if ( 	(mimalla.devolverCasilla(i,j).ocupada() == true) && 
				( (mimalla.devolverCasilla(i,j).nombre() == 'a' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'b' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'c' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'd' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'e' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'f' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'g' ) ||
				(mimalla.devolverCasilla(i,j).nombre() == 'h' ) )	 ){
					Character _nombre = mimalla.devolverCasilla(i,j).nombre();
					String nombre = _nombre.toString(_nombre);
					int pos_x = xx[traducir(i,j)]+4;
					int pos_y = yy[traducir(i,j)]+8;
					g2.setColor(Color.white);
					g2.setFont(new Font("Dialog",Font.BOLD,10));
					g2.drawString(nombre,pos_x,pos_y);
			}//if
		}//for
	}//for
	
}

	public void run(){
		while(true){
			repaint();
			try{
				idx_Thread.sleep(50);
			}
			catch (InterruptedException e){}
		}
	}
	
	public synchronized void stop() {
        	idx_Thread = null;
    	}
	
	public void start() {
        	idx_Thread = new Thread(this);
        	idx_Thread.setPriority(Thread.MIN_PRIORITY);
        	idx_Thread.start();
    }
	
	public Image getImage(String name) {			//Grácias Google por ayudarme a descubrir esta estupenda función
		URL url = tablero.class.getResource(name);	// simple, funcional....
		Image img = getToolkit().getImage(url);
		try {
			MediaTracker tracker = new MediaTracker(this);
			tracker.addImage(img, 0);
			tracker.waitForID(0);
		} catch (Exception e) {}
		return img;
	}//getImage()
	
	public int traducir(int x,int y){
		int result = 0;
		
		if ((x==0) && (y==0)) result= 78;	
		else if ((x==0) && (y==1)) result= 79;
		else if ((x==0) && (y==2)) result= 80;
		else if ((x==0) && (y==3)) result= 60;
		else if ((x==0) && (y==4)) result= 61;
		else if ((x==0) && (y==5)) result= 62;
		else if ((x==0) && (y==6)) result= 51;
		else if ((x==0) && (y==7)) result= 52;
		else if ((x==0) && (y==8)) result= 53;
		else if ((x==1) && (y==0)) result= 81;
		else if ((x==1) && (y==1)) result= 82;
		else if ((x==1) && (y==2)) result= 83;
		else if ((x==1) && (y==3)) result= 63;
		else if ((x==1) && (y==4)) result= 64;
		else if ((x==1) && (y==5)) result= 65;
		else if ((x==1) && (y==6)) result= 54;
		else if ((x==1) && (y==7)) result= 55;
		else if ((x==1) && (y==8)) result= 56;
		else if ((x==2) && (y==0)) result= 84;
		else if ((x==2) && (y==1)) result= 85;
		else if ((x==2) && (y==2)) result= 86;
		else if ((x==2) && (y==3)) result= 66;
		else if ((x==2) && (y==4)) result= 67;
		else if ((x==2) && (y==5)) result= 68;
		else if ((x==2) && (y==6)) result= 57;
		else if ((x==2) && (y==7)) result= 58;
		else if ((x==2) && (y==8)) result= 59;
		else if ((x==3) && (y==0)) result= 69;
		else if ((x==3) && (y==1)) result= 70;
		else if ((x==3) && (y==2)) result= 71;
		else if ((x==3) && (y==3)) result= 35;
		else if ((x==3) && (y==4)) result= 34;
		else if ((x==3) && (y==5)) result= 33;
		else if ((x==3) && (y==6)) result= 42;
		else if ((x==3) && (y==7)) result= 43;
		else if ((x==3) && (y==8)) result= 44;
		else if ((x==4) && (y==0)) result= 72;
		else if ((x==4) && (y==1)) result= 73;
		else if ((x==4) && (y==2)) result= 74;
		else if ((x==4) && (y==3)) result= 38;
		else if ((x==4) && (y==4)) result= 37;
		else if ((x==4) && (y==5)) result= 36;
		else if ((x==4) && (y==6)) result= 45;
		else if ((x==4) && (y==7)) result= 46;
		else if ((x==4) && (y==8)) result= 47;
		else if ((x==5) && (y==0)) result= 75;
		else if ((x==5) && (y==1)) result= 76;
		else if ((x==5) && (y==2)) result= 77;
		else if ((x==5) && (y==3)) result= 41;
		else if ((x==5) && (y==4)) result= 40;
		else if ((x==5) && (y==5)) result= 39;
		else if ((x==5) && (y==6)) result= 48;
		else if ((x==5) && (y==7)) result= 49;
		else if ((x==5) && (y==8)) result= 50;
		
		return result;
	}//traducir()
	
}//class
