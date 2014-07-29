/*
 *  "filtro_archivos.java"
 *      Esta clase está inspirada en un código recogido de Internet. Personaliza un filtrado de archivos para las ventanas
 * 	 de seleccion de archivos a cargar/guardar. Muy muy util. Gracias al autor original, aunque no recuerdo quien era.
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */


import javax.swing.filechooser.FileFilter;
import java.io.File;

public class filtro_archivos extends FileFilter {
	String[] tipo;
	
	filtro_archivos(String[] s) {	//CONSTRUCTOR
		tipo = s;
	};

	filtro_archivos(String s) {	//CONSTRUCTOR
		tipo = new String[1];
		tipo[0] = s;
	};
	
	public boolean accept(File f) {
		int num_tipos = tipo.length;
		String nombre_archivo = f.getName().toUpperCase();
		for(int i=0;i<num_tipos;i++)
			if(nombre_archivo.endsWith(tipo[i].toUpperCase()))
			return true;
			if (f.isDirectory()) return true;
			return false;
	}

	public String getDescription() {
		String temp ="";
		for(int i=0;i<tipo.length;i++)
		temp += (tipo[i]+(i!=tipo.length-1?", ":""));
		return temp;
	}
}//class