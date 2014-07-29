/* 
 *	"tablero.java"
 *		El tablero visible en el juego. Debe responder a los clicks sobre los botones para indicar que objeto
 *		 se ha tocado. Está basado en la clase "tablero" de ITHACA
 *
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.FlatteningPathIterator;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import javax.swing.*;
import javax.swing.border.*;


import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;
import java.net.URL;
import java.util.Random;

public class tablero extends JPanel implements MouseListener {

	int _x0;
	int _y0;
	int pixx;
	int pixy;
	Polygon[][] mapa_casillas;
	//Polygon mapa_papelera;
	malla mimalla;
	Boolean flag_marcado;
	int x_marcado;
	int y_marcado;
	Object[][] elementos_tabla;
	boolean recuadrar;
	boolean sensible;
	char objeto_marcado;
	Graphics2D g2;
	
    public tablero(malla mimalla){
    
    	setBorder(new EmptyBorder(10,10,10,10));
    	addMouseListener(this);
	this.mimalla = mimalla;
	//this.elementos_tabla = elementos_tabla;
	
	flag_marcado = false;
	x_marcado = 300;
	y_marcado = 300;
	
	recuadrar = false;
	sensible = false;
		
    }
    
	public void parpadear(int i, int j){
		x_marcado = i;
		y_marcado = j;
		flag_marcado = true;
	}//parpadear
	
	public void desparpadear(int i, int j){
		flag_marcado = false;
	}//desparpadear
    
    public void actualizar_malla(malla mimalla){
    	this.mimalla = mimalla;
    }
    
    public malla devolver_malla(){
    	return mimalla;
    }
    
 
	
    public void paint(Graphics g) {
        g2 = (Graphics2D) g;
        Dimension d = getSize();	//pilla lo que mide la ventana
	int x_size = (d.width-10);
	int y_size = (d.height-10);

	boolean medidas_ok = false;
	while (medidas_ok != true){
		if ((x_size / y_size) == 3) medidas_ok = true;
		else if (( x_size/y_size) < 3) {
			x_size = x_size;
			y_size = (x_size /3);
		}
		else if ((x_size/y_size) > 3){
			x_size = (y_size * 3);
			y_size = y_size;
		}
	}//while
	_x0 = (((d.width-10) /2) - (x_size/2));
	_y0 = (((d.height-10) /2) - (y_size/2));

	
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
	
	g2.setBackground(Color.black);

        g2.clearRect(0, 0, d.width, d.height);
	g2.setColor(Color.orange);
	pixx = x_size /12;
	pixy = y_size /6;
	
	//marco 
	
	
	for (int i=0; i<10; i++){	//lineas cuasi-verticales
		g2.drawLine( ((pixx*i)+_x0) , _y0, ((3+i)*pixx)+_x0, (6*pixy)+_y0);
	}//for
	
	int poquito = ((3*pixx)/6);
	for (int i=0; i<7; i++){	//lineas cuasi-horizontales
		g2.drawLine( _x0+(i*pixx)-(i*poquito) , _y0+(pixy*i), _x0+((9+i)*pixx)-(poquito*i) , _y0+(pixy*i) );
	}


	mapa_casillas = new Polygon[6][9];
	for (int i=0; i<6; i++){		//i filas
		for (int j=0; j<9; j++){	//j columnas
			mapa_casillas[i][j] = new Polygon();
			
			mapa_casillas[i][j].addPoint( _x0+(j*pixx)+(i*poquito) , _y0+(i*pixy));
			mapa_casillas[i][j].addPoint( _x0+((j+1)*pixx)+(i*poquito)  , _y0+(i*pixy) );
			mapa_casillas[i][j].addPoint( _x0+((j+1)*pixx)+((i+1)*poquito)  , _y0+((i+1)*pixy)     );
			mapa_casillas[i][j].addPoint( _x0+(j*pixx)+((i+1)*poquito) , _y0+((i+1)*pixy) );
			
			
		}//for
	}//for
	
	
	
	
	//dibujar las figuras en sus casillas
	Image img_cubo_rojo =		 getImage("png/cubo_rojo.png");
	Image img_cubo_azul =		 getImage("png/cubo_azul.png");
	Image img_cubo_amarillo =	 getImage("png/cubo_amarillo.png");
	Image img_esfera_roja = 	 getImage("png/esfera_roja.png");
	Image img_esfera_azul = 	 getImage("png/esfera_azul.png");
	Image img_esfera_amarilla = 	 getImage("png/esfera_amarilla.png");
	Image img_piramide_roja = 	 getImage("png/piramide_roja.png");
	Image img_piramide_azul = 	 getImage("png/piramide_azul.png");
	Image img_piramide_amarilla = 	 getImage("png/piramide_amarilla.png");
	
	for (int i=0; i<6; i++){		
		for (int j=0; j<9; j++){
			if (mimalla.devolverCasilla(i,j).ocupada() == true){
				int tamanio = 0; 
				int pos_x = 0;
				int pos_y = 0;
				if (mimalla.devolverCasilla(i,j).tamanho() == 1){
					tamanio = pixx - (pixx/5);
					pos_x = _x0+(j*pixx)+(i*poquito)+ (pixy/2);
					pos_y = _y0+(i*pixy)- (pixy/2);
					
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
				else if (mimalla.devolverCasilla(i,j).tamanho() == 2){
					tamanio = pixx/2;
					pos_x = _x0+(j*pixx)+(i*poquito)+(pixx/2);
					pos_y = _y0+(i*pixy);
					
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
	
	//dibujar los nombres en las figuras que los tengan
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
					int pos_x = _x0+(j*pixx)+(i*poquito)+ (pixy);
					int pos_y = _y0+(i*pixy)+ (pixy/2);
					g2.setColor(Color.white);
					g2.setFont(new Font("Dialog",Font.BOLD,poquito-1));
					g2.drawString(nombre,pos_x,pos_y);
			}//if
		}//for
	}//for
	
	//dibujar el objeto nulo si no hay nada
	Image img_objeto_nulo =  getImage("png/objeto_nulo.png");
	if (mimalla.numElementos() == 0) g2.drawImage(img_objeto_nulo,_x0+x_size/2,_y0+y_size/4,x_size/6, x_size/6,null);
	
	if (flag_marcado == true){
		g2.setColor(new Color(255,244,122));
		g2.fillPolygon(mapa_casillas[x_marcado][y_marcado]);
	}
	
	
    }//paint
    
	
    
	public Image getImage(String name) {
            URL url = tablero.class.getResource(name);
            Image img = getToolkit().getImage(url);
            try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                tracker.waitForID(0);
            } catch (Exception e) {}
            return img;
        }
	
	public void recuadro_on(){	recuadrar = true;}
	
	public void recuadro_off(){	recuadrar = false;}
	
	public void sensible_on(){	sensible = true;}
	
	public void sensible_off(){	sensible = false;}
	
	public boolean get_sensible(){	return sensible;}
	
	

	public void mousePressed(MouseEvent e){		//Cuando se pulsa sobre él. Ver si es sobre un objeto y 
	    if (sensible == true){			// salvar el nombre del objeto en una variable
		for (int i=0; i<6; i++){
			for (int j=0; j<9; j++){
				if (mapa_casillas[i][j].contains(e.getX(),e.getY())) {
					int x_casilla = i;
					int y_casilla = j;
					if (mimalla.devolverCasilla(x_casilla, y_casilla).ocupada() == true){
						objeto_marcado = mimalla.devolverCasilla(x_casilla, y_casilla).nombre();
					}//if
					else objeto_marcado = '%';	// Si no se pone esto, pasa el anterior objeto clickado
				}//if
			}//for
		}//for
	    }//if	
	}//mousePressed
	
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	
	public char objeto_clickado(){ return objeto_marcado;}
	

	
   
}//class
