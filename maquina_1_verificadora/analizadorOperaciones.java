/*
 *  "analizadorOperaciones.java"	
 *	contiene métodos para devolver las tablas de verdad de las operaciones:    ^,v,-->, <->, ¬
 *	Se usará para propagar valores de no evaluabilidad
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


/*
	public analizadorOperaciones()

	public int Y(int a, int b)
	public int O(int a, int b)
	ublic int NO(int a)
	public int IMPLICA(int a, int b)
	public int BIMPLICA(int a, int b)
*/


import java.lang.*;
import java.io.*;
import java.util.*;


class analizadorOperaciones{

	public analizadorOperaciones(){	} 		//CONSTRUCTOR
	
	public int Y(int a, int b){
		int result = 100;	//(100 = no evaluable)
		
		if ( (a == 100) || (b == 100) ) result = 100;	// si alguna de las fórmulas es no evaluable, propagar ese valor
		else result = (a * b);
		
		return result;
	}//Y()
	
	public int O(int a, int b){
		int result = 100;
		
		if ( (a == 100) || (b == 100) ) result = 100;	// si alguna de las fórmulas es no evaluable, propagar ese valor
		else result = (a + b);
		
		if (result == 2) result = 1;
		
		return result;
	}//O()
	
	public int NO(int a){
		int result = 100;
		
		if (a == 100) result = 100;	// si alguna de las fórmulas es no evaluable, propagar ese valor
		else {
			if (a==0) result = 1;
			else if (a==1) result = 0;
		}//else
		
		return result;
	}//NO()
	
	public int IMPLICA(int a, int b){
		int result = 100;
		
		if ( (a==100) || (b==100) ) result = 100;	// si alguna de las fórmulas es no evaluable, propagar ese valor
		else{
			if ( (a==0) && (b==0) ) result = 1;
			else if ( (a==0) && (b==1) ) result = 1;
			else if ( (a==1) && (b==0) ) result = 0;
			else if ( (a==1) && (b==1) ) result = 1;
		}
		return result;
	}//IMPLICA()
	
	public int BIMPLICA(int a, int b){
		int result = 100;
		
		if ( (a==100) || (b==100) ) result = 100;	// si alguna de las fórmulas es no evaluable, propagar ese valor
		else{
			if ( (a==0) && (b==0) ) result = 1;
			else if ( (a==0) && (b==1) ) result = 0;
			else if ( (a==1) && (b==0) ) result = 0;
			else if ( (a==1) && (b==1) ) result = 1;
		}
		return result;
	}//BIMPLICA()
	



}//class analizadorOperaciones