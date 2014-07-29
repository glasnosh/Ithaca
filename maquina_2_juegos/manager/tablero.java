/*
 *  "tablero.java"
 *      El resultado es un JPanel sobre el que se dibuja un tablero con los elementos de la malla. Además permite actuar sobre
 *       los elementos introducidos mediante eventos de ratón:
 *		Cambiar los elementos de casilla
 *		Insertarlos  (desde capas superiores)
 * 		Eliminarlos
 *		Darles nombre	(desde capas superiores)
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

 
 /*
	Glasnosh: Para que todo funcione bien según mis pruebas, lo ideal para los dibujos es hacerlo 
		   como se ve aquí. La clase que dibuja hereda de JPanel y en el método "paint" declarar
		   el "Graphics2D" y dibujar sobre él. Finalmente no hace falta incluirlo en el JPanel ni 
		   nada. Regla de oro: "1 JPanel para cada Graphics2D.		
	
	
	public tablero(malla mimalla)			//Constructor
	public void actualizar_malla(malla mimalla)	//Sincroniza la malla interna del nucleo con la dibujada (no necesario)
	public malla devolver_malla()			// IDEM
	public void paint(Graphics g)			
	public Image getImage(String name)
	public int insertar_aleatorio(int forma, int color, int tamanho)	//Busca hueco libre en la malla e inserta el objeto
	public void dar_nombre(int x, int y,char nombre)			//Da nombre a un objeto marcado (a su casilla)
	
	Glasnosh: Aviso. Los ajustes de posición de las imágenes son correctos pero se han sacado en plan prueba y error. Para cambiarlos
		   mucho ojo porque seguramente hay que volver a recalcularlo todo.
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

import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;
import java.net.URL;
import java.util.Random;

public class tablero extends JPanel implements MouseListener {

	int _x0;			//coordenadas del punto (0,0)
	int _y0;
	int pixx;			//ancho de casilla
	int pixy;			//alto de casilla
	Polygon[][] mapa_casillas;	//area poligonal que ocupa cada casilla
	Polygon mapa_papelera;		//area poligonal que ocupa la "papelera"
	malla mimalla;			// la malla que se va a representar
	Boolean flag_marcado;		//indica si hay alguna casilla iluminada (para cambiar su contenido....)
	int x_marcado;			//coordenadas de la casilla qu está iluminada
	int y_marcado;
	
	boolean[] nombres_cogidos;
	

    public tablero(malla mimalla){
    	addMouseListener(this);
	this.mimalla = mimalla;
	
	flag_marcado = false;		//inicialmente ninguna casilla está marcada/iluminada
	x_marcado = 300;
	y_marcado = 300;
	nombres_cogidos = new boolean[5];
	for (int i=0; i<5; i++) nombres_cogidos[i] = false;
		
    }
    
    public void dar_nombre(char nombre){	//si hay una casilla iluminada que tenga un objeto, esta función le añade el "nombre"
    	if ( 	(flag_marcado == true) 
		&& (mimalla.devolverCasilla(x_marcado,y_marcado).ocupada() == true)
		&& (mimalla.existeCasilla(nombre) == false) )
		 {
//(new blanquear_sentencias(elementos_tabla)).run();
			casilla temp = mimalla.devolverCasilla(x_marcado,y_marcado);
			int forma = temp.forma();
			int color = temp.color();
			int tamanho = temp.tamanho();
			mimalla.eliminar(x_marcado,y_marcado);
			mimalla.insertar(x_marcado,y_marcado,nombre,forma,tamanho,color);
			flag_marcado = false;
			repaint();
	}//if
    }
    
    public void actualizar_malla(malla mimalla){
    	this.mimalla = mimalla;
    }//actualizar_malla()
    
    public malla devolver_malla(){		//estas dos funciones son prescindibles totalmente. Las dejo por pena.... jeje
    	return mimalla;
    }//devolver_malla()
    
    public int insertar_aleatorio(int forma, int color, int tamanho){	//Lo aleatorio es la posición donde se inserta el elemento
    	int result = 0;
	if (mimalla.numElementos() >= 5) result = 1;	//máximo 15 elementos en la malla
	else{
	
		
		//buscar hueco libre
		boolean hueco_encontrado = false;
		int x = 0;
		int y = 0;
		char nombre = ' ';
		int pos_nombre = 100;
		int ii = 0;
		Random aleatorio1 = new Random();	
		while (hueco_encontrado != true){
			x = aleatorio1.nextInt(6);		//sacamos una posición aleatoria
			y = aleatorio1.nextInt(9);
			//for (ii=0; ii<=4; ii++){
			for (ii=4; ii>=0; ii--){
				if (nombres_cogidos[ii]	 == false) pos_nombre = ii;
			}
			if ( mimalla.devolverCasilla(x,y).ocupada() == false ) {
				if (pos_nombre == 0) nombre = 'a';
				else if (pos_nombre == 1) nombre = 'b';
				else if (pos_nombre == 2) nombre = 'c';
				else if (pos_nombre == 3) nombre = 'd';
				else if (pos_nombre == 4) nombre = 'e';
				
				mimalla.insertar(x,y,nombre,forma,tamanho,color);
				nombres_cogidos[pos_nombre] = true;
				hueco_encontrado = true;
				repaint();

			}
		}//while
	}//else
	return result;
    } //insertar_aleatorio()
	
    public void paint(Graphics g) {		// la más importante
        Graphics2D g2 = (Graphics2D) g;
        Dimension d = getSize();	//pilla lo que mide la ventana. Al redimensionar se readapta
	int x_size = (d.width-10);
	int y_size = (d.height-10);

	boolean medidas_ok = false;
	while (medidas_ok != true){
		if ((x_size / y_size) == 3) medidas_ok = true;		//Esto calcula cual debe ser la medida del dibujo en sí en función
		else if (( x_size/y_size) < 3) {			// de cual es el tamaño de la ventana. Se debe conservar la relación
			x_size = x_size;				// de ( ancho = 3 * largo). De modo que se toma la medida límite
			y_size = (x_size /3);				// de entre las dos en función del tamaño de la ventana y se
									// calcula la otra
		}//else
		else if ((x_size/y_size) > 3){
			x_size = (y_size * 3);
			y_size = y_size;
		}//else
	}//while
	_x0 = (((d.width-10) /2) - (x_size/2));		//punto (0,0) que usaremos como referencia para comenzar a dibujar
	_y0 = (((d.height-10) /2) - (y_size/2));

	
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);		//para que las lineas se dibujen con alisado. Mooooola esta función
	
	g2.setBackground(Color.black);

        g2.clearRect(0, 0, d.width, d.height);				//Un fondo negro para "limpiar" lo que haya debajo
	g2.setColor(Color.orange);
	pixx = x_size /12;
	pixy = y_size /6;
	
	for (int i=0; i<10; i++){	//lineas cuasi-verticales
		g2.drawLine( ((pixx*i)+_x0) , _y0, ((3+i)*pixx)+_x0, (6*pixy)+_y0);	
	}//for
	
	int poquito = ((3*pixx)/6);	//lo que se desplaza el comienzo/final de cada linea horizontal al ir bajando para dar perspectiva
	for (int i=0; i<7; i++){	//lineas cuasi-horizontales
		g2.drawLine( _x0+(i*pixx)-(i*poquito) , _y0+(pixy*i), _x0+((9+i)*pixx)-(poquito*i) , _y0+(pixy*i) );
	}
	
	//imagen de la papelera
	 Image img_papelera = getImage("png/trash2.png");
	g2.drawImage(img_papelera,_x0,(pixy*4)+_y0,(pixx+(pixx/2)),(pixx+(pixx/2)),null);

	mapa_casillas = new Polygon[6][9];		//delimitamos las areas poligonales que ocupa cada casilla para saber donde se hace click
	for (int i=0; i<6; i++){		//i filas
		for (int j=0; j<9; j++){	//j columnas
			mapa_casillas[i][j] = new Polygon();
			
			mapa_casillas[i][j].addPoint( _x0+(j*pixx)+(i*poquito) , _y0+(i*pixy));
			mapa_casillas[i][j].addPoint( _x0+((j+1)*pixx)+(i*poquito)  , _y0+(i*pixy) );
			mapa_casillas[i][j].addPoint( _x0+((j+1)*pixx)+((i+1)*poquito)  , _y0+((i+1)*pixy)     );
			mapa_casillas[i][j].addPoint( _x0+(j*pixx)+((i+1)*poquito) , _y0+((i+1)*pixy) );
			
			
		}//for
	}//for
	
	//rellenar los cubos iluminados
	if (flag_marcado == true){
		g2.setColor(new Color(255,244,122));
		g2.fillPolygon(mapa_casillas[x_marcado][y_marcado]);
	}
	
	mapa_papelera = new Polygon();			//area que ocupara la papelera
	mapa_papelera.addPoint( _x0, (pixy*4)+_y0);
	mapa_papelera.addPoint( _x0+ (pixx+(pixx/2)) , (pixy*4)+_y0);
	mapa_papelera.addPoint( _x0+ (pixx+(pixx/2)) , (pixy*4)+_y0+(pixx+(pixx/2)) );
	mapa_papelera.addPoint( _x0, (pixy*4)+_y0+(pixx+(pixx/2)) );


	int _pos_x=_x0+(pixx/4);	//posición que va a ocupar la leyenda de número de elementos que hay 
	int _pos_y = _y0+(pixy*3);

	Integer ne = mimalla.numElementos();

	g2.setColor(Color.orange);
	g2.setFont(new Font("Dialog",Font.BOLD,poquito-3));
	if (ne != 0) g2.drawString(ne.toString(),_pos_x,_pos_y);	//Texto con el número de elementos de la malla
	else if (ne == 0) g2.drawString("*",_pos_x, _pos_y);		//Se pone un '*' en vez de cero porque a efectos teóricos en la
									// malla nunca hay cero elementos. Según la lógica clásica, que es la
									// que se aplica aquí, siempre hay algo. De hecho cuando no hay nada
									// aparece el fantasta y analizamos con al menos 1 objeto abstracto
	
	//dibujar las figuras en sus casillas
	Image img_cubo_rojo =		 getImage("png/cubo_rojo.png");	//Cogemos una imagen base de cada y según sea "grande" o 
	Image img_cubo_azul =		 getImage("png/cubo_azul.png");	// "pequeña" la redimensionamos al dibujarla
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
				if (mimalla.devolverCasilla(i,j).tamanho() == 1){	//objeto grande
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
				else if (mimalla.devolverCasilla(i,j).tamanho() == 2){	//objeto pequeño
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
	
	//dibujar el objeto nulo si no hay nada (el fantasmilla flotante)
	//Image img_objeto_nulo =  getImage("png/objeto_nulo.png");
	//if (mimalla.numElementos() == 0) g2.drawImage(img_objeto_nulo,_x0+x_size/2,_y0+y_size/4,x_size/6, x_size/6,null);
	
    }//paint
    
	
    
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


	public void mousePressed(MouseEvent e){			//Escuchando los eventos del usuario a través del ratón
		for (int i=0; i<6; i++){
			for (int j=0; j<9; j++){
				if (mapa_casillas[i][j].contains(e.getX(),e.getY())) {	// Si el click ha sido sobre una casilla
					if (flag_marcado == false){			// marcarla si no lo está ninguna
						flag_marcado = true;
						x_marcado = i;
						y_marcado = j;
						repaint();
					}
					else if (flag_marcado == true){			//si alguna lo está intercambiarlas y desiluminar
//(new blanquear_sentencias(elementos_tabla)).run();
						flag_marcado = false;
						mimalla.intercambiar(i,j,x_marcado,y_marcado);
						repaint();
					}
				}
			}//for
		}//for
			//si se hace click sobre la papelera
		if ( (mapa_papelera.contains(e.getX(),e.getY()) ) && (mimalla.devolverCasilla(x_marcado,y_marcado).ocupada() == true) ) {
mimalla.imprimirmalla();
			if (flag_marcado == true){	
//(new blanquear_sentencias(elementos_tabla)).run();
				flag_marcado = false;
				
				
				
				casilla casilla_temp = mimalla.devolverCasilla(x_marcado, y_marcado);
				char nombre_a_eliminar = casilla_temp.nombre();
				if (nombre_a_eliminar == 'a') nombres_cogidos[0] = false;
				else if (nombre_a_eliminar == 'b') nombres_cogidos[1] = false;
				else if (nombre_a_eliminar == 'c') nombres_cogidos[2] = false;
				else if (nombre_a_eliminar == 'd') nombres_cogidos[3] = false;
				else if (nombre_a_eliminar == 'e') nombres_cogidos[4] = false;
				
				
				mimalla.eliminar(x_marcado,y_marcado);
				repaint();	
			}//if
		}//if
		
		
	}
	public void mouseExited(MouseEvent e){}		//Solamente nos interesa el click. El resto es necesario redefinirlos para 
	public void mouseMoved(MouseEvent e) {}		// que el compilador no de problemas, pero como se ve no hacen nada.
	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	
   
}
