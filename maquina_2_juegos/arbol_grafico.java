/*
 *  "arbol_grafico.java"
 *	Crea una ventana de diálogo MODAL con un JTree con toda la derivación de la fórmula que se está jugando
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*
 *	public arbol_grafico(JFrame f)		//CONSTRUTOR
 *	public void sons (nodo _raiz, DefaultMutableTreeNode _raiz_grafica)	//Toma un arbol HECHO y lo recorre creando el 
 *										// árbol gráfico
 *	public void expandAll(JTree tree, boolean expand)
 *	private void expandAll(JTree tree, TreePath parent, boolean expand)	//Esta y la anterior expanden todos los nodos
 *	public void mostrar_arbol (DefaultMutableTreeNode  root)		//Crea la ventana y la muestra
 *
 */
 

import java.awt.*;
import java.awt.event.*;

import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.AWTKeyStroke;
import javax.swing.border.*;
import java.awt.GridBagLayout;

import javax.imageio.*;
import javax.imageio.stream.*;
import java.awt.image.*;
import java.net.URL;
import javax.swing.JFileChooser;

import java.util.Random;

import java.io.*;
import javax.swing.tree.*;
import java.lang.*;
import java.util.*;


public class arbol_grafico extends JDialog{

	miniconversor mn1;
	DefaultMutableTreeNode root;	//nodo gráfico raiz

	public arbol_grafico(JFrame f) {	 //constructor	
		super(f,"Árbol de derivación",false);
		
		addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e){
					setVisible(false);
		}});
		
		mn1 = new miniconversor();
		
	}
	
	public void sons (nodo _raiz, DefaultMutableTreeNode _raiz_grafica){	//crea el árbol gráfico
		
		nodo raiz = _raiz;
		DefaultMutableTreeNode raiz_grafica = _raiz_grafica;
		
		DefaultMutableTreeNode hijo1_grafico = null;
		DefaultMutableTreeNode hijo2_grafico = null;
		DefaultMutableTreeNode hijo3_grafico = null;
		DefaultMutableTreeNode hijo4_grafico = null;
		DefaultMutableTreeNode hijo5_grafico = null;
		
		if (raiz._hijo1() != null){						
			
			hijo1_grafico = new DefaultMutableTreeNode( mn1.convertir_interno_visual( (raiz._hijo1())._contenido()) );
			raiz_grafica.add(hijo1_grafico);
		}
		
		if (raiz._hijo2() != null){
			hijo2_grafico = new DefaultMutableTreeNode( mn1.convertir_interno_visual( (raiz._hijo2())._contenido()) );
			raiz_grafica.add(hijo2_grafico);
		}
		
		if (raiz._hijo3() != null){
			hijo3_grafico = new DefaultMutableTreeNode( mn1.convertir_interno_visual( (raiz._hijo1())._contenido()) );
			raiz_grafica.add(hijo3_grafico);
		}
		
		if (raiz._hijo4() != null){
			hijo4_grafico = new DefaultMutableTreeNode( mn1.convertir_interno_visual( (raiz._hijo1())._contenido()) );
			raiz_grafica.add(hijo4_grafico);
		}
		
		if (raiz._hijo5() != null){
			hijo5_grafico = new DefaultMutableTreeNode( mn1.convertir_interno_visual( (raiz._hijo1())._contenido()) );
			raiz_grafica.add(hijo5_grafico);
		}
		
		
		nodo q1 = raiz._hijo1();
		q1 = raiz._hijo1();
		if (q1 != null){
			if (q1._es_hoja() == false) sons(q1, hijo1_grafico);
		}//if
		
		nodo q2 = raiz._hijo2();
		if (q2 != null){
			if (q2._es_hoja() == false) sons(q2, hijo2_grafico);
		}//if
		
		nodo q3 = raiz._hijo3();
		if (q3 != null){
			if (q3._es_hoja() == false) sons(q3, hijo3_grafico);
		}//if
		
		nodo q4 = raiz._hijo4();
		if (q4 != null){
			if (q4._es_hoja() == false) sons(q4, hijo4_grafico);
		}//if
		
		nodo q5 = raiz._hijo5();
		if (q5 != null){
			if (q5._es_hoja() == false) sons(q5, hijo5_grafico);
		}//if
	
	}//sons
	
	
	
	//Las siguientes dos funciones fueron encontradas en la Red a través de Google en
	// http://javaalmanac.com/egs/javax.swing.tree/ExpandAll.html?l=rel
	// G R A C I A S....
	public void expandAll(JTree tree, boolean expand) {
		TreeNode root = (TreeNode)tree.getModel().getRoot();
		// Traverse tree from root
		expandAll(tree, new TreePath(root), expand);
	}//expandAll
	private void expandAll(JTree tree, TreePath parent, boolean expand) {
		// Traverse children
		TreeNode node = (TreeNode)parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration e=node.children(); e.hasMoreElements(); ) {
				TreeNode n = (TreeNode)e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}//for
		}//if
	 
		// Expansion or collapse must be done bottom-up
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}//if
	}//expandAll  :)
	
	
	
	public void mostrar_arbol (DefaultMutableTreeNode  root){
		this.root = root;
		
		JButton b_cerrar = new JButton("Cerrar");
		b_cerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent qq2){
				dispose();
		}});
		JTree jt = new JTree(root);
		
		JScrollPane jsp = new JScrollPane (jt);
    		Container c = getContentPane();
    		c.add (jsp, BorderLayout.CENTER);
		c.add (b_cerrar, BorderLayout.SOUTH);
		expandAll(jt,true);
  		setSize(400, 400);
		pack();
    		setVisible(true);

	}//mostrar arbol
	
	
	
	
	
	

}//class