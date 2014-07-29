/*
 *  "arbol.java"
 *	Estructura de datos que crea el arbol. Precisa de la clase "nodo" que son los elementos básicos de un árbol
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*
 *	public arbol()		//CONSTRUCTOR. No hace nada
 *
 *	public void hijos(nodo raiz, malla mimalla)	//Crea el arbol desde un nodo raiz y la malla que exista
 *	public void recorrer(nodo raiz)			//Recorre de las hojas hacia arriba. Solo para debug
 *	public void crear_estrategia(nodo raiz)		//Crea las rutas ganadoras del arbol
 *
 *
 */


import java.io.*;
import java.lang.*;



public class arbol{


 	malla mimalla;
	nodo raiz;
 
 	
	public arbol(){	}//constructor
	
	
	
	public void hijos(nodo raiz, malla mimalla){
		
		this.mimalla = mimalla;
		this.raiz = raiz;
		
		int estado = raiz._estado();
		boolean rol_maquina_inicial = raiz._rol_maquina();
		
		if (estado == 3){		//negación normal
			String cadena1 = raiz._contenido();
			eliminador_cosas ec1 = new eliminador_cosas(cadena1);
			nodo p1 = raiz._hijo1();
			p1 = new nodo(ec1.eliminar_negacion(), raiz, mimalla, 1, !rol_maquina_inicial);
			raiz.anhadir_hijo(p1,1);
		}
		
		if (estado == 4){		//negacion de cuanti
			String cadena1 = raiz._contenido();
			eliminador_cosas ec1 = new eliminador_cosas(cadena1);
			nodo p1 = raiz._hijo1();
			p1 = new nodo(ec1.eliminar_negacion(), raiz, mimalla, 1, !rol_maquina_inicial);
			raiz.anhadir_hijo(p1,1);
		}
		
		else if (estado == 2){		//derivacion
			String cadena1 = raiz._hijo_izquierdo();
			String cadena2 = raiz._hijo_derecho();
			nodo p1 = raiz._hijo1();
			p1 = new nodo(cadena1, raiz, mimalla,1, raiz._rol_maquina());
			raiz.anhadir_hijo(p1,1);
			
			nodo p2 = raiz._hijo2();
			p2 = new nodo(cadena2, raiz, mimalla,2, raiz._rol_maquina());
			raiz.anhadir_hijo(p2,2);
		}
		
		else if (estado == 1){		//cuantificador
			int numero_hijos = raiz._numero_hijos();
			String cadena = raiz._contenido();
			String cuantificador = raiz._cuantificador();
			char a_traducir = cuantificador.charAt(cuantificador.length()-1);	//variable del cuanti

			if (numero_hijos == 1){	
				String cadena1 = cadena;
				nodo p1 = raiz._hijo1();
				eliminador_cosas ec1 = new eliminador_cosas(cadena1);
				p1 = new nodo(ec1.eliminar_cuantificador(a_traducir,'a'), raiz, mimalla,1, raiz._rol_maquina());
				raiz.anhadir_hijo(p1,1);
			}//1 hijo
			else if (numero_hijos == 2){
				String cadena1 = cadena;
				String cadena2 = cadena;
				nodo p1 = raiz._hijo1();
				eliminador_cosas ec1 = new eliminador_cosas(cadena1);
				p1 = new nodo(ec1.eliminar_cuantificador(a_traducir,'a'), raiz, mimalla,1, raiz._rol_maquina());
				raiz.anhadir_hijo(p1,1);
				
				nodo p2 = raiz._hijo2();
				eliminador_cosas ec2 = new eliminador_cosas(cadena2);
				p2 = new nodo(ec2.eliminar_cuantificador(a_traducir,'b'), raiz, mimalla,2, raiz._rol_maquina());
				raiz.anhadir_hijo(p2,2);	
			}//2 hijos
			
			else if (numero_hijos == 3){
				String cadena1 = cadena;
				String cadena2 = cadena;
				String cadena3 = cadena;
				
				nodo p1 = raiz._hijo1();
				eliminador_cosas ec1 = new eliminador_cosas(cadena1);
				p1 = new nodo(ec1.eliminar_cuantificador(a_traducir,'a'), raiz, mimalla,1, raiz._rol_maquina());
				raiz.anhadir_hijo(p1,1);

				nodo p2 = raiz._hijo2();
				eliminador_cosas ec2 = new eliminador_cosas(cadena1);
				p2 = new nodo(ec2.eliminar_cuantificador(a_traducir,'b'), raiz, mimalla,2, raiz._rol_maquina());
				raiz.anhadir_hijo(p2,2);
				
				nodo p3 = raiz._hijo3();
				eliminador_cosas ec3 = new eliminador_cosas(cadena1);
				p3 = new nodo(ec3.eliminar_cuantificador(a_traducir,'c'), raiz, mimalla,3, raiz._rol_maquina());
				raiz.anhadir_hijo(p3,3);
			}// 3 hijos
			
			else if (numero_hijos == 4){
				String cadena1 = cadena;
				String cadena2 = cadena;
				String cadena3 = cadena;
				String cadena4 = cadena;
				nodo p1 = raiz._hijo1();
				eliminador_cosas ec1 = new eliminador_cosas(cadena1);
				p1 = new nodo(ec1.eliminar_cuantificador(a_traducir,'a'), raiz, mimalla,1, raiz._rol_maquina());
				raiz.anhadir_hijo(p1,1);
				
				nodo p2 = raiz._hijo2();
				eliminador_cosas ec2 = new eliminador_cosas(cadena2);
				p2 = new nodo(ec2.eliminar_cuantificador(a_traducir,'b'), raiz, mimalla,2, raiz._rol_maquina());
				raiz.anhadir_hijo(p2,2);
				
				nodo p3 = raiz._hijo3();
				eliminador_cosas ec3 = new eliminador_cosas(cadena3);
				p3 = new nodo(ec3.eliminar_cuantificador(a_traducir,'c'), raiz, mimalla,3, raiz._rol_maquina());
				raiz.anhadir_hijo(p3,3);
				
				nodo p4 = raiz._hijo4();
				eliminador_cosas ec4 = new eliminador_cosas(cadena4);
				p4 = new nodo(ec4.eliminar_cuantificador(a_traducir,'d'), raiz, mimalla,4, raiz._rol_maquina());
				raiz.anhadir_hijo(p4,4);
				
			}//4 hijos
			
			else if (numero_hijos == 5){
				String cadena1 = cadena;
				String cadena2 = cadena;
				String cadena3 = cadena;
				String cadena4 = cadena;
				String cadena5 = cadena;
				
				nodo p1 = raiz._hijo1();
				eliminador_cosas ec1 = new eliminador_cosas(cadena1);
				p1 = new nodo(ec1.eliminar_cuantificador(a_traducir,'a'), raiz, mimalla,1, raiz._rol_maquina());
				raiz.anhadir_hijo(p1,1);
				
				nodo p2 = raiz._hijo2();
				eliminador_cosas ec2 = new eliminador_cosas(cadena2);
				p2 = new nodo(ec2.eliminar_cuantificador(a_traducir,'b'), raiz, mimalla,2, raiz._rol_maquina());
				raiz.anhadir_hijo(p2,2);
				
				nodo p3 = raiz._hijo3();
				eliminador_cosas ec3 = new eliminador_cosas(cadena3);
				p3 = new nodo(ec3.eliminar_cuantificador(a_traducir,'c'), raiz, mimalla,3, raiz._rol_maquina());
				raiz.anhadir_hijo(p3,3);
				
				nodo p4 = raiz._hijo4();
				eliminador_cosas ec4 = new eliminador_cosas(cadena4);
				p4 = new nodo(ec4.eliminar_cuantificador(a_traducir,'d'), raiz, mimalla,4, raiz._rol_maquina());
				raiz.anhadir_hijo(p4,4);
				
				nodo p5 = raiz._hijo5();
				eliminador_cosas ec5 = new eliminador_cosas(cadena5);
				p5 = new nodo(ec5.eliminar_cuantificador(a_traducir,'e'), raiz, mimalla,5, raiz._rol_maquina());
				raiz.anhadir_hijo(p5,5);
				
			}//5 hijos
			
		}
		
		//nodo q1 = new nodo("Ex GRANDE(x)", null, mimalla);
		nodo q1 = raiz._hijo1();
		q1 = raiz._hijo1();
		if (q1 != null){
			if (q1._es_hoja() == false) hijos(q1, mimalla);
		}//if
		
		nodo q2 = raiz._hijo2();
		if (q2 != null){
			if (q2._es_hoja() == false) hijos(q2, mimalla);
		}//if
		
		nodo q3 = raiz._hijo3();
		if (q3 != null){
			if (q3._es_hoja() == false) hijos(q3, mimalla);
		}//if
		
		nodo q4 = raiz._hijo4();
		if (q4 != null){
			if (q4._es_hoja() == false) hijos(q4, mimalla);
		}//if
		
		nodo q5 = raiz._hijo5();
		if (q5 != null){
			if (q5._es_hoja() == false) hijos(q5, mimalla);
		}//if
		
		
	}//hijos
	


	public void recorrer(nodo raiz){
		if (raiz != null){	
			
			if (raiz._hijo1() != null) recorrer(raiz._hijo1());
			if (raiz._hijo2() != null) recorrer(raiz._hijo2());
			if (raiz._hijo3() != null) recorrer(raiz._hijo3());
			if (raiz._hijo4() != null) recorrer(raiz._hijo4());
			if (raiz._hijo5() != null) recorrer(raiz._hijo5());
			System.out.println();
if (raiz._es_hoja() == false) System.out.println(raiz._contenido()+" Est:"+raiz._estado()+"  N:"+raiz._rol_nodo()+"  M:"+raiz._rol_maquina()+" G:"+raiz._ganador());
else if (raiz._es_hoja() == true) System.out.println(raiz._contenido()+"= "+raiz._valor_hoja());
			System.out.println("V1 = "+raiz._return_v(1));
			System.out.println("V2 = "+raiz._return_v(2));
			System.out.println("V3 = "+raiz._return_v(3));
			System.out.println("V4 = "+raiz._return_v(4));
			System.out.println("V5 = "+raiz._return_v(5));
			
		}
		System.out.println();
	}//recorrer
	
	

	
	public void crear_estrategia(nodo raiz){
	
				//Nodo Ganador:
				// -nodos en los que se juega y algun hijo hoja coincide con el rol_maquina
				// -nodos en los que NO se juega y todos los hijos hoja coinciden con el rol_maquina
				// -nodos en los que se juega y algún hijo NO-hoja es nodo ganador
				// -nodos en los que NO se juega y todos los hijos NO-hoja son nodos ganadores
				
				//en un nodo se juega si ROL_MAQUINA coincide con ROL_NODO
	
		if (raiz != null){
			if (raiz._es_hoja() == false){
				if (raiz._hijo1() != null) crear_estrategia(raiz._hijo1());	//creamos de abajo hacia arriba
				if (raiz._hijo2() != null) crear_estrategia(raiz._hijo2());	//post-orden  :-)~~ (EDI POWA)
				if (raiz._hijo3() != null) crear_estrategia(raiz._hijo3());
				if (raiz._hijo4() != null) crear_estrategia(raiz._hijo4());
				if (raiz._hijo5() != null) crear_estrategia(raiz._hijo5());
				
				if ( ((raiz._estado()==1) || (raiz._estado()==2)) &&
				      (raiz._rol_maquina() == raiz._rol_nodo())	){

	if (	( (raiz._hijo1()!= null) && ((raiz._hijo1())._es_hoja()) && ((raiz._hijo1())._valor_hoja()==raiz._rol_maquina())) ||
		( (raiz._hijo1()!= null) && ((raiz._hijo1())._es_hoja()==false) && ((raiz._hijo1())._ganador() == true)) ||
		( (raiz._hijo2()!= null) && ((raiz._hijo2())._es_hoja()) && ((raiz._hijo2())._valor_hoja()==raiz._rol_maquina())) ||
		( (raiz._hijo2()!= null) && ((raiz._hijo2())._es_hoja()==false) && ((raiz._hijo2())._ganador() == true)) ||
		( (raiz._hijo3()!= null) && ((raiz._hijo3())._es_hoja()) && ((raiz._hijo3())._valor_hoja()==raiz._rol_maquina())) ||
		( (raiz._hijo3()!= null) && ((raiz._hijo3())._es_hoja()==false) && ((raiz._hijo3())._ganador() == true)) ||
		( (raiz._hijo4()!= null) && ((raiz._hijo4())._es_hoja()) && ((raiz._hijo4())._valor_hoja()==raiz._rol_maquina())) ||
		( (raiz._hijo4()!= null) && ((raiz._hijo4())._es_hoja()==false) && ((raiz._hijo4())._ganador() == true)) ||
		( (raiz._hijo5()!= null) && ((raiz._hijo5())._es_hoja()) && ((raiz._hijo5())._valor_hoja()==raiz._rol_maquina())) ||
		( (raiz._hijo5()!= null) && ((raiz._hijo5())._es_hoja()==false) && ((raiz._hijo5())._ganador() == true))  ){

						raiz._setGanador(true);
						nodo nodo_temp = raiz;
						while ( nodo_temp._padre() != null){
							int numero_hijo = nodo_temp._numero_de_hijo();
							(nodo_temp._padre())._v(numero_hijo);
							nodo_temp = nodo_temp._padre();	
						}//while
						
					}//if
				}//if 
				
				
				else if ( ((raiz._estado()==1) || (raiz._estado()==2)) &&
				      (raiz._rol_maquina() != raiz._rol_nodo())	){
	if (	
		((raiz._hijo1() == null) || (((raiz._hijo1()!= null) && ((raiz._hijo1())._es_hoja()) && ((raiz._hijo1())._valor_hoja()==raiz._rol_maquina())) ||
		((raiz._hijo1()!= null) && ((raiz._hijo1())._es_hoja()==false) && ((raiz._hijo1())._ganador() == true)))) &&
		((raiz._hijo2() == null) || (((raiz._hijo2()!= null) && ((raiz._hijo2())._es_hoja()) && ((raiz._hijo2())._valor_hoja()==raiz._rol_maquina())) ||
		((raiz._hijo2()!= null) && ((raiz._hijo2())._es_hoja()==false) && ((raiz._hijo2())._ganador() == true)))) &&
		((raiz._hijo3() == null) || (((raiz._hijo3()!= null) && ((raiz._hijo3())._es_hoja()) && ((raiz._hijo3())._valor_hoja()==raiz._rol_maquina())) ||
		((raiz._hijo3()!= null) && ((raiz._hijo3())._es_hoja()==false) && ((raiz._hijo3())._ganador() == true)))) &&
		((raiz._hijo4() == null) || (((raiz._hijo4()!= null) && ((raiz._hijo4())._es_hoja()) && ((raiz._hijo4())._valor_hoja()==raiz._rol_maquina())) ||
		((raiz._hijo4()!= null) && ((raiz._hijo4())._es_hoja()==false) && ((raiz._hijo4())._ganador() == true)))) &&
		((raiz._hijo5() == null) || (((raiz._hijo5()!= null) && ((raiz._hijo5())._es_hoja()) && ((raiz._hijo5())._valor_hoja()==raiz._rol_maquina())) ||
		((raiz._hijo5()!= null) && ((raiz._hijo5())._es_hoja()==false) && ((raiz._hijo5())._ganador() == true))))  ){
						raiz._setGanador(true);
						nodo nodo_temp = raiz;
						while ( nodo_temp._padre() != null){
							int numero_hijo = nodo_temp._numero_de_hijo();
							(nodo_temp._padre())._v(numero_hijo);
							nodo_temp = nodo_temp._padre();	
						}//while
						
					}//if
				}//else if
				
				else if (raiz._estado() == 3){		//nodo de negación. Será ganador si su hijo es ganador
					if ( (raiz._hijo1() != null) && ((raiz._hijo1())._ganador() == true) ) {
						raiz._setGanador(true);
						nodo nodo_temp = raiz;
						while ( nodo_temp._padre() != null){
							int numero_hijo = nodo_temp._numero_de_hijo();
							(nodo_temp._padre())._v(numero_hijo);
							nodo_temp = nodo_temp._padre();	
						}//while
					}//if
				}//else if
				
				else if (raiz._estado() == 4){		//nodo de negación cuanti. Será ganador si su hijo es ganador
					if ( (raiz._hijo1() != null) && ((raiz._hijo1())._ganador() == true) ) {
						raiz._setGanador(true);
						nodo nodo_temp = raiz;
						while ( nodo_temp._padre() != null){
							int numero_hijo = nodo_temp._numero_de_hijo();

							(nodo_temp._padre())._v(numero_hijo);
							nodo_temp = nodo_temp._padre();	
						}//while
					}//if
				}//else if
				
				
				

			}//if (raiz._es_hoja() == false)
		}//if (raiz != null)
	}//crear_estrategia
	

}//class