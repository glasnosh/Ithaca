/*
 *  "sucustrupadores.java"
 *	Crea un cuadro de di�logo modal para mostrar un "Acerca De" para la aplicaci�n.
 * 
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Mart�nez Garc�a <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*
	La ventana lleva una etiqueta arriba con el "icono_logo". El resto es un panel con pesta�as donde se organiza
	 la informaci�n. Se reproduce un midi mientras se muestra la ventana.
*/
 
import java.lang.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.midi.*;
import javax.swing.border.LineBorder;

class sucustrupadores extends JDialog{
	;
	public static ImageIcon icono_logo = new ImageIcon("png/icono_logo.png");
	public static ImageIcon icono_bug = new ImageIcon("png/bug.png");
	public static ImageIcon icono_cafe = new ImageIcon("png/cafe.png");
	public static ImageIcon icono_unileon = new ImageIcon("png/escudo_unileon.png");
	public static ImageIcon icono_acsucyl = new ImageIcon("png/logo_acsucyl.png");
	
	File f2 = new File ("midi/midi2.mid");
	private Font fuente1 = new Font("Courier",Font.PLAIN,10);
	private Font fuente2 = new Font("Dialog",Font.PLAIN,10);
	private Font fuente3 = new Font("Times",Font.BOLD,15);
	private Font fuente4 = new Font("Times",Font.BOLD,12);
	Sequencer seq;
	
public sucustrupadores(JFrame f){
		super(f,"About ITHACA",true);
				
		try		//este trocito de c�digo es el que hace sonar el midi mientras se muestra la ventana
		{	
			
			MidiFileFormat mff2 = MidiSystem.getMidiFileFormat(f2);
			Sequence S = MidiSystem.getSequence(f2);
			seq = MidiSystem.getSequencer();
			seq.open();
			seq.setSequence(S);
			seq.start();
		}
		catch(MidiUnavailableException ecc){}
		catch(InvalidMidiDataException ecc2){}
		catch(IOException ecc3){}			//posibles excepciones de la reproducci�n del midi

		JPanel panel_norte = new JPanel();
		JLabel etiqueta_norte = new JLabel(icono_logo);
		panel_norte.add(etiqueta_norte);	
		
		
		JButton boton = new JButton("Cerrar");
		boton.setMargin(new Insets(0,5,0,5));
		JPanel panel_sur = new JPanel();
		panel_sur.add(boton);
		JPanel _panel_sur = new JPanel(new BorderLayout());
		_panel_sur.add(panel_sur,BorderLayout.EAST);
		
		JTabbedPane panel_tableado = new JTabbedPane(SwingConstants.TOP);
		
		JPanel _pesta�a1 = new JPanel(new GridLayout(6,1));
		
		
		JLabel l1p1 = new JLabel("ITHACA Games v0.02  (1/Jun/06)- Sem�ntica de juegos");
		l1p1.setHorizontalAlignment(SwingConstants.CENTER);
		l1p1.setForeground(Color.blue);
		l1p1.setFont(fuente4);
		JLabel l2p1 = new JLabel("(c) 2005-2006 Los autores de Ithaca");
		l2p1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l3p1 = new JLabel("http://ithaca.berlios.de");
		l3p1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l4p1 = new JLabel("Este Software es de C�digo Abierto. Para m�s informaci�n consulte la licencia");
		l4p1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l6p1 = new JLabel("");
		l6p1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l5p1 = new JLabel("Puede distribuirlo (exclusivamente) bajo las condiciones de la GPL v2");
		l5p1.setHorizontalAlignment(SwingConstants.CENTER);
	
		
		_pesta�a1.add(l1p1);
		_pesta�a1.add(l2p1);
		_pesta�a1.add(l3p1);
		_pesta�a1.add(l4p1);
		//_pesta�a1.add(l6p1);
		_pesta�a1.add(l5p1);
		
		
		JPanel _pesta�a2 = new JPanel();
		JTextArea area_texto_pesta�a2 = new JTextArea(50,80);
		area_texto_pesta�a2.setEditable(false);
		
		_pesta�a2.add(area_texto_pesta�a2);
		area_texto_pesta�a2.setForeground(Color.blue);
		area_texto_pesta�a2.append("\n");
		area_texto_pesta�a2.append("\t\tEQUIPO DE PROYECTO ITHACA:\n");
		area_texto_pesta�a2.append("\n\n");
		area_texto_pesta�a2.append(" * Coordinaci�n:\n");
		area_texto_pesta�a2.append("\n\tFrancisco Salto Alemany <dfcfsa@unileon.es> (Universidad de Le�n)\n");
		area_texto_pesta�a2.append("\n\n");
		area_texto_pesta�a2.append(" * Desarrollo y Programaci�n:\n");
		area_texto_pesta�a2.append("\n\tJose Manuel Mart�nez Garc�a <glasnosh@gmail.com>\n");
		area_texto_pesta�a2.append("\n\n");
		area_texto_pesta�a2.append(" * L�gica, aplicaci�n docente y otras tareas:\n");
		area_texto_pesta�a2.append("\n\tFrancisco Salto Alemany <dfcfsa@unileon.es< (Universidad de Le�n)\n");
		area_texto_pesta�a2.append("\n\tGemma Robles V�zquez <gemm@usal.es> (Universidad de Salamanca)\n");
		area_texto_pesta�a2.append("\n\tJose Manuel M�ndez Rodr�guez <sefus@usal.es> (Universidad de Salamanca)\n");
		area_texto_pesta�a2.append("\n\tIratxe Romillo Garc�a <fjamira@hotmail.com> (Universidad de Le�n)\n");
		area_texto_pesta�a2.append("\n\n");
		area_texto_pesta�a2.append(" * Beta-Test:\n");
		area_texto_pesta�a2.append("\n\tLos anteriores\n");
		area_texto_pesta�a2.append("\n\n");
		area_texto_pesta�a2.append(" * Otras colaboraciones:\n");
		area_texto_pesta�a2.append("\n\tJuan Ram�n �lvarez Bautista (Universidad de Le�n)\n");
		area_texto_pesta�a2.append("\n\tAgencia de Calidad del Sistema Universitario de Castilla y Le�n\n");
		area_texto_pesta�a2.append("\n\tSalvador Vinardell Crespo (Universidad de Salamenca)\n");
		area_texto_pesta�a2.append("\n\tServicio de comunicaciones de la Universidad de Le�n\n");
		area_texto_pesta�a2.append("\n\tF�lix Barrio (Fundaci�n F.G.U.L.E.M.)\n");
		area_texto_pesta�a2.append("\n\tJorge Simarro (Fundaci�n F.G.U.L.E.M.)\n");
		area_texto_pesta�a2.append("\n\n");
		area_texto_pesta�a2.append(" * Set de iconos:\n");
		area_texto_pesta�a2.append("\n\tGracias al Proyecto KDE\n");
		area_texto_pesta�a2.setCaretPosition(1);
		JScrollPane pesta�a2 = new JScrollPane(_pesta�a2);
		
		
		
		
		JPanel pesta�a4 = new JPanel();
		JTextArea area_texto_pesta�a4 = new JTextArea();
		area_texto_pesta�a4.setEditable(false);
		area_texto_pesta�a4.setFont(fuente1);
		pesta�a4.add(area_texto_pesta�a4);
		JScrollPane _pesta�a4 = new JScrollPane(pesta�a4);
			
		FileReader entrada=null;
         	StringBuffer str=new StringBuffer();
         	try  {
			entrada=new FileReader("txt/gpl.txt");		//texto de la GPL para mostrarla al usuario
			int c;
			while((c=entrada.read())!=-1){
				str.append((char)c);
   			}//while
			area_texto_pesta�a4.append(str.toString());
		}catch (IOException ex) {System.out.println("sucustrupadores.java: error de lectura del archivo de licencia");
		}finally{
			//cierra el flujo de datos
			if(entrada!=null){
				try{
					entrada.close();
				}catch(IOException ex){}
			}
		}
		area_texto_pesta�a4.setCaretPosition(1);
	
		JPanel pesta�a3 = new JPanel(new BorderLayout());
		JLabel etiqueta_bug = new JLabel(icono_bug);
		pesta�a3.add(etiqueta_bug, BorderLayout.WEST);
		JLabel l1p3 = new JLabel(" PARA INFORMAR SOBRE FALLOS:");
		l1p3.setForeground(Color.BLUE);
		l1p3.setFont(fuente3);
		JLabel l2p3 = new JLabel(" Gracias por ayudarnos a mejorar este producto");
		l2p3.setFont(fuente1);
		JLabel l3p3 = new JLabel(" <glasnosh@gmail.com>");
		JLabel l6p3 = new JLabel(" <dfsfa@unileon.es>");
		JLabel l4p3 = new JLabel(" <http://ithaca.berlios.de>");
		JLabel l5p3 = new JLabel(" <http://developer.berlios.de/projects/ithaca/>");
		JPanel pesta�a3_centro = new JPanel(new GridLayout(6,1));
		pesta�a3_centro.add(l1p3);
		
		pesta�a3_centro.add(l3p3);
		pesta�a3_centro.add(l6p3);
		pesta�a3_centro.add(l4p3);
		pesta�a3_centro.add(l5p3);
		
		pesta�a3_centro.add(l2p3);
		pesta�a3_centro.setBorder(new LineBorder(Color.black,5,true));
		pesta�a3.add(pesta�a3_centro, BorderLayout.CENTER);
		
		
		
		JPanel pesta�a5 = new JPanel();
		JLabel l1p5 = new JLabel(icono_unileon);
		JLabel l2p5 = new JLabel("Patrocinado por la Universidad de Le�n");
		JLabel l3p5 = new JLabel("www.unileon.es");
		l3p5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel l4p5 = new JLabel(icono_acsucyl);
		JLabel l6p5 = new JLabel("Financiado por la Agencia de Calidad Universitaria");
		JLabel l7p5 = new JLabel("www.acsucyl.es");
		l7p5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel p1p5 = new JPanel(new GridLayout(2,1));
		p1p5.add(l2p5);
		p1p5.add(l3p5);
		JPanel p2p5 = new JPanel(new FlowLayout());
		p2p5.add(p1p5);
		p2p5.add(l1p5);
		
		
		JPanel p6p5 = new JPanel(new GridLayout(2,1));
		p6p5.add(l6p5);
		p6p5.add(l7p5);
		JPanel p7p5 = new JPanel(new FlowLayout());
		p7p5.add(p6p5);
		p7p5.add(l4p5);
		
		pesta�a5.add(p2p5);
		pesta�a5.add(p7p5);
		
		panel_tableado.setBorder(new LineBorder(Color.blue,1,true));
		panel_tableado.add("ITHACA",(new JScrollPane()).add(_pesta�a1));
		panel_tableado.add("Autores",pesta�a2);
		panel_tableado.add("Informar error",pesta�a3);
		panel_tableado.add("Licencia",_pesta�a4);
		panel_tableado.add("Patrocinio",pesta�a5);
		
		
		JPanel panel_global = new JPanel(new BorderLayout());
		panel_global.add(panel_norte,BorderLayout.NORTH);
		panel_global.add(panel_tableado,BorderLayout.CENTER);
		panel_global.add(_panel_sur, BorderLayout.SOUTH);
		
		getContentPane().add(panel_global);
				
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){
					dispose();
					seq.stop();
		}});
				
		boton.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e4){
					dispose();
					seq.stop();
		}});
		
		setSize(510,360);
		setLocationRelativeTo(null); 
		setResizable(false);
		setVisible(true);
	}//fin del constructor



}//fin de la clase