/*
 *  "intercambiador_malla.java"
 *      Esta clase accesoria está pensada para el análisis de la malla espejo. Toma un objeto de la clase "malla" y recoloca
 * 	 los objetos en forma especular. Ver diagrama más abajo
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


/*   devuelve la malla "espejo"
 *	
 * 	public intercambiador_malla()		//CONSTRUCTOR
 *	public malla espejo(malla mimalla)		//MÉTODO QUE DA LA VUELTA A LA malla
 *
 *	 __________		 __________
 *	|A         |		|         A|
 *	|        B |   ----->   | B        |
 *	|  C       |            |       C  |
 *	|_________D|            |D_________|
 *
*/

class intercambiador_malla{

	static malla malla_espejo;

	public intercambiador_malla(){}//constructor
	
	
	public malla espejo(malla mimalla){
	
		malla_espejo = new malla();	//En realidad no sería necesario crear otro objeto. Se podría usar la original.
		malla_espejo =  mimalla;
		
		for (int i=0; i<6; i++){
			for (int j=0; j<4; j++){
				malla_espejo.intercambiar(i, j, i, 8-j);
			}//for
		}//for
		
		return malla_espejo;	
	}

}//class