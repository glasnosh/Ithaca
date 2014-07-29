/*
 *  "sucustrupadores.java"
 *	Crea un cuadro de diálogo modal para mostrar un "Acerca De" para la aplicación.
 * 
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

/*
	La ventana lleva una etiqueta arriba con el "icono_logo". El resto es un panel con pestañas donde se organiza
	 la información. Se reproduce un midi mientras se muestra la ventana.
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
				
		try		//este trocito de código es el que hace sonar el midi mientras se muestra la ventana
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
		catch(IOException ecc3){}			//posibles excepciones de la reproducción del midi

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
		
		JPanel _pestaña1 = new JPanel(new GridLayout(6,1));
		
		
		JLabel l1p1 = new JLabel("ITHACA Games v0.02  (1/Jun/06)- Semántica de juegos");
		l1p1.setHorizontalAlignment(SwingConstants.CENTER);
		l1p1.setForeground(Color.blue);
		l1p1.setFont(fuente4);
		JLabel l2p1 = new JLabel("(c) 2005-2006 Los autores de Ithaca");
		l2p1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l3p1 = new JLabel("http://ithaca.berlios.de");
		l3p1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l4p1 = new JLabel("Este Software es de Código Abierto. Para más información consulte la licencia");
		l4p1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l6p1 = new JLabel("");
		l6p1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel l5p1 = new JLabel("Puede distribuirlo (exclusivamente) bajo las condiciones de la GPL v2");
		l5p1.setHorizontalAlignment(SwingConstants.CENTER);
	
		
		_pestaña1.add(l1p1);
		_pestaña1.add(l2p1);
		_pestaña1.add(l3p1);
		_pestaña1.add(l4p1);
		//_pestaña1.add(l6p1);
		_pestaña1.add(l5p1);
		
		
		JPanel _pestaña2 = new JPanel();
		JTextArea area_texto_pestaña2 = new JTextArea(50,80);
		area_texto_pestaña2.setEditable(false);
		
		_pestaña2.add(area_texto_pestaña2);
		area_texto_pestaña2.setForeground(Color.blue);
		area_texto_pestaña2.append("\n");
		area_texto_pestaña2.append("\t\tEQUIPO DE PROYECTO ITHACA:\n");
		area_texto_pestaña2.append("\n\n");
		area_texto_pestaña2.append(" * Coordinación:\n");
		area_texto_pestaña2.append("\n\tFrancisco Salto Alemany <dfcfsa@unileon.es> (Universidad de León)\n");
		area_texto_pestaña2.append("\n\n");
		area_texto_pestaña2.append(" * Desarrollo y Programación:\n");
		area_texto_pestaña2.append("\n\tJose Manuel Martínez García <glasnosh@gmail.com>\n");
		area_texto_pestaña2.append("\n\n");
		area_texto_pestaña2.append(" * Lógica, aplicación docente y otras tareas:\n");
		area_texto_pestaña2.append("\n\tFrancisco Salto Alemany <dfcfsa@unileon.es< (Universidad de León)\n");
		area_texto_pestaña2.append("\n\tGemma Robles Vázquez <gemm@usal.es> (Universidad de Salamanca)\n");
		area_texto_pestaña2.append("\n\tJose Manuel Méndez Rodríguez <sefus@usal.es> (Universidad de Salamanca)\n");
		area_texto_pestaña2.append("\n\tIratxe Romillo García <fjamira@hotmail.com> (Universidad de León)\n");
		area_texto_pestaña2.append("\n\n");
		area_texto_pestaña2.append(" * Beta-Test:\n");
		area_texto_pestaña2.append("\n\tLos anteriores\n");
		area_texto_pestaña2.append("\n\n");
		area_texto_pestaña2.append(" * Otras colaboraciones:\n");
		area_texto_pestaña2.append("\n\tJuan Ramón Álvarez Bautista (Universidad de León)\n");
		area_texto_pestaña2.append("\n\tAgencia de Calidad del Sistema Universitario de Castilla y León\n");
		area_texto_pestaña2.append("\n\tSalvador Vinardell Crespo (Universidad de Salamenca)\n");
		area_texto_pestaña2.append("\n\tServicio de comunicaciones de la Universidad de León\n");
		area_texto_pestaña2.append("\n\tFélix Barrio (Fundación F.G.U.L.E.M.)\n");
		area_texto_pestaña2.append("\n\tJorge Simarro (Fundación F.G.U.L.E.M.)\n");
		area_texto_pestaña2.append("\n\n");
		area_texto_pestaña2.append(" * Set de iconos:\n");
		area_texto_pestaña2.append("\n\tGracias al Proyecto KDE\n");
		area_texto_pestaña2.setCaretPosition(1);
		JScrollPane pestaña2 = new JScrollPane(_pestaña2);
		
		
		
		
		JPanel pestaña4 = new JPanel();
		JTextArea area_texto_pestaña4 = new JTextArea();
		area_texto_pestaña4.setEditable(false);
		area_texto_pestaña4.setFont(fuente1);
		pestaña4.add(area_texto_pestaña4);
		JScrollPane _pestaña4 = new JScrollPane(pestaña4);
			
		FileReader entrada=null;
         	StringBuffer str=new StringBuffer();
         	try  {
			entrada=new FileReader("txt/gpl.txt");		//texto de la GPL para mostrarla al usuario
			int c;
			while((c=entrada.read())!=-1){
				str.append((char)c);
   			}//while
			area_texto_pestaña4.append(str.toString());
		}catch (IOException ex) {System.out.println("sucustrupadores.java: error de lectura del archivo de licencia");
		}finally{
			//cierra el flujo de datos
			if(entrada!=null){
				try{
					entrada.close();
				}catch(IOException ex){}
			}
		}
		area_texto_pestaña4.setCaretPosition(1);
	
		JPanel pestaña3 = new JPanel(new BorderLayout());
		JLabel etiqueta_bug = new JLabel(icono_bug);
		pestaña3.add(etiqueta_bug, BorderLayout.WEST);
		JLabel l1p3 = new JLabel(" PARA INFORMAR SOBRE FALLOS:");
		l1p3.setForeground(Color.BLUE);
		l1p3.setFont(fuente3);
		JLabel l2p3 = new JLabel(" Gracias por ayudarnos a mejorar este producto");
		l2p3.setFont(fuente1);
		JLabel l3p3 = new JLabel(" <glasnosh@gmail.com>");
		JLabel l6p3 = new JLabel(" <dfsfa@unileon.es>");
		JLabel l4p3 = new JLabel(" <http://ithaca.berlios.de>");
		JLabel l5p3 = new JLabel(" <http://developer.berlios.de/projects/ithaca/>");
		JPanel pestaña3_centro = new JPanel(new GridLayout(6,1));
		pestaña3_centro.add(l1p3);
		
		pestaña3_centro.add(l3p3);
		pestaña3_centro.add(l6p3);
		pestaña3_centro.add(l4p3);
		pestaña3_centro.add(l5p3);
		
		pestaña3_centro.add(l2p3);
		pestaña3_centro.setBorder(new LineBorder(Color.black,5,true));
		pestaña3.add(pestaña3_centro, BorderLayout.CENTER);
		
		
		
		JPanel pestaña5 = new JPanel();
		JLabel l1p5 = new JLabel(icono_unileon);
		JLabel l2p5 = new JLabel("Patrocinado por la Universidad de León");
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
		
		pestaña5.add(p2p5);
		pestaña5.add(p7p5);
		
		panel_tableado.setBorder(new LineBorder(Color.blue,1,true));
		panel_tableado.add("ITHACA",(new JScrollPane()).add(_pestaña1));
		panel_tableado.add("Autores",pestaña2);
		panel_tableado.add("Informar error",pestaña3);
		panel_tableado.add("Licencia",_pestaña4);
		panel_tableado.add("Patrocinio",pestaña5);
		
		
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