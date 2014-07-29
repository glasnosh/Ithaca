/*
 *  "Yylex_SECOND.java"
 *     	Esta clase ha sido creada con JLex y corresponde al analizador léxico secundario del programa. Trabaja conjuntamente con 	
 *	parser_SECOND. 
 *	Este no es muy diferente de "Yylex_FIRST" salvo por que va a trabajar siempre con texto correcto, puesto que solamente es 
 * 	 llamado cuando el parser principal ha aceptado la sentencia del usuario y la ha transformado en algo totalmente manejable
 *	 por el nucleo. Esto si la sentencia del usuario es léxica/sintacticamente correcta. 
 * 	El primer parser lo que hace es hacer el análisis léxico/sintáctico de lo que da el usuario. Si ello es incorrecto se para. Si 
 *	 es correcto y se puede evaluar, lo transforma en algo que entiende este segundo parser y se llama.
 *
 *  Copyright (C) 2005, 2006 Jose Manuel Martínez García <glasnosh@gmail.com>
 *
 *  This program is "Open Source" software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License (explicitly version 2 
 *   of the License).
 */

import java.lang.*;
import java.io.*;
import java.util.*;
import java_cup.runtime.Symbol;


class Yylex_SECOND implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 256;
	private final int YY_EOF = 257;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex_SECOND (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Yylex_SECOND.java: Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex_SECOND (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Yylex_SECOND.java: Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	
	Yylex_SECOND (String cadena){
		this();
		if (null == cadena) {
			throw (new Error("Yylex_SECOND.java: Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new StringReader(cadena));
	}
	
	private Yylex_SECOND () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}
	
	

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NOT_ACCEPT,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NOT_ACCEPT,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NOT_ACCEPT,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NOT_ACCEPT,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NOT_ACCEPT,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NOT_ACCEPT,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NOT_ACCEPT,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NOT_ACCEPT,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NOT_ACCEPT,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NOT_ACCEPT,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NOT_ACCEPT,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NOT_ACCEPT,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NOT_ACCEPT,
		/* 146 */ YY_NOT_ACCEPT,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NOT_ACCEPT,
		/* 150 */ YY_NOT_ACCEPT,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NOT_ACCEPT,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NOT_ACCEPT,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NOT_ACCEPT,
		/* 159 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,258,
"34:9,33:2,34:2,0,34:18,33,34:7,22,23,34:2,32,28,34:2,24:10,34:2,30,31,29,34" +
":2,2,21,20,12,8,5,9,15,19,16,34,6,18,11,4,13,14,10,7,1,3,34:4,17,34:3,26,34" +
":23,25,34:53,27,34:83,35:2")[0];

	private int yy_rmap[] = unpackFromString(1,160,
"0,1,2:8,3,2:26,4,5,6,2,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,2" +
"5,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,5" +
"0,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,7" +
"5,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,1" +
"00,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118," +
"119,120,121,122,123,124,125")[0];

	private int yy_nxt[][] = unpackFromString(126,36,
"-1,1,38,40:2,128,40:2,42,44,46,40,48,50,40:4,52,54,56,40,2,3,4,5,6,7,58,40," +
"60,8,9,10,40,11,-1:2,37,-1:102,10,-1:5,132,-1:38,39,-1:3,130,-1:6,129,41,-1" +
":2,131,-1:24,61,-1:27,64,-1:40,45,-1:3,47,-1:30,151,-1:39,150,-1:30,135,-1:" +
"34,49,-1:32,136,-1:42,157,-1:43,66,-1:27,51,-1:10,149,-1:30,67,-1:23,53,-1:" +
"16,134,-1:23,69,-1:45,158,-1:39,71,-1:17,55,-1:61,12,-1:34,57,-1:36,13,-1:3" +
"4,59,-1:15,73,-1:46,74,-1:22,14,-1:39,75,-1:36,139,-1:28,15,-1:34,81,-1:34," +
"138,-1:42,141,-1:3,142,-1:40,82,-1:21,16,-1:35,17,-1:43,153,-1:44,83,-1:33," +
"84,-1:20,18,-1:39,19,-1:37,85,-1:33,20,-1:47,87,-1:23,88,-1:29,92,-1,93,-1:" +
"33,21,-1:39,143,-1:31,22,-1:41,23,-1:42,96,-1:31,97,-1:43,144,-1:18,98,-1:4" +
"7,99,-1:26,100,-1:14,101,-1:16,102,-1:18,159,-1:23,103,-1:39,104,-1:25,24,-" +
"1:48,106,-1:31,108,-1:27,109,-1:36,145,-1:14,154,-1:20,155,-1:33,110,-1:43," +
"146,-1:29,111,-1:35,25,-1:35,26,-1:39,27,-1:39,112,-1:31,113,-1:45,116,-1:2" +
"7,28,-1:33,29,-1:38,119,-1:26,30,-1:36,121,-1:34,122,-1:37,123,-1:33,31,-1:" +
"48,124,-1:22,32,-1:51,148,-1:28,125,-1:34,33,-1:29,34,-1:46,127,-1:22,35,-1" +
":37,36,-1:33,43,-1:36,63,-1:42,62,-1:27,133,-1:34,72,-1:50,76,-1:26,70,-1:3" +
"6,78,-1:37,79,-1:28,140,-1:50,89,-1:29,86,-1:42,94,-1:26,90,-1:33,91,-1:33," +
"105,-1:41,107,-1:33,147,-1:37,118,-1:41,120,-1:28,126,-1:34,68,-1:27,65,-1:" +
"40,77,-1:36,80,-1:35,95,-1:33,114,-1:35,115,-1:35,117,-1:39,152,-1:39,137,-" +
"1:25,156,-1:31");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return (new Symbol(sym_SECOND.EOF));
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						{return new Symbol(sym_SECOND.ERROR);}
					case -2:
						break;
					case 2:
						{return new Symbol(sym_SECOND.P_I,yytext());}
					case -3:
						break;
					case 3:
						{return new Symbol(sym_SECOND.P_D,yytext());}
					case -4:
						break;
					case 4:
						{return new Symbol(sym_SECOND.DIGITO,yytext());}
					case -5:
						break;
					case 5:
						{return new Symbol(sym_SECOND.OR,yytext());}
					case -6:
						break;
					case 6:
						{return new Symbol(sym_SECOND.AND,yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(sym_SECOND.NOT,yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(sym_SECOND.EQUAL,yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym_SECOND.SEPARATOR,yytext());}
					case -10:
						break;
					case 10:
						{}
					case -11:
						break;
					case 11:
						
					case -12:
						break;
					case 12:
						{return new Symbol(sym_SECOND.IMPLICA,yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym_SECOND.BIMPLICA,yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym_SECOND.AZUL,yytext());}
					case -15:
						break;
					case 15:
						{return new Symbol(sym_SECOND.ROJO,yytext());}
					case -16:
						break;
					case 16:
						{return new Symbol(sym_SECOND.CUBO,yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym_SECOND.TAUTO,yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym_SECOND.ABAJO,yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym_SECOND.FALSE,yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym_SECOND.ENTRE,yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym_SECOND.ARRIBA,yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym_SECOND.ESFERA,yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(sym_SECOND.GRANDE,yytext());}
					case -24:
						break;
					case 24:
						{return new Symbol(sym_SECOND.DERECHA,yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(sym_SECOND.AMARILLO,yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym_SECOND.PEQUENHO,yytext());}
					case -27:
						break;
					case 27:
						{return new Symbol(sym_SECOND.PIRAMIDE,yytext());}
					case -28:
						break;
					case 28:
						{return new Symbol(sym_SECOND.ALREDEDOR,yytext());}
					case -29:
						break;
					case 29:
						{return new Symbol(sym_SECOND.MASGRANDE,yytext());}
					case -30:
						break;
					case 30:
						{return new Symbol(sym_SECOND.MISMAFILA,yytext());}
					case -31:
						break;
					case 31:
						{return new Symbol(sym_SECOND.IZQUIERDA,yytext());}
					case -32:
						break;
					case 32:
						{return new Symbol(sym_SECOND.MISMAFORMA,yytext());}
					case -33:
						break;
					case 33:
						{return new Symbol(sym_SECOND.MISMOCOLOR,yytext());}
					case -34:
						break;
					case 34:
						{return new Symbol(sym_SECOND.MASPEQUENHO,yytext());}
					case -35:
						break;
					case 35:
						{return new Symbol(sym_SECOND.MISMACOLUMNA,yytext());}
					case -36:
						break;
					case 36:
						{return new Symbol(sym_SECOND.MISMOTAMANHO,yytext());}
					case -37:
						break;
					case 38:
						{return new Symbol(sym_SECOND.ERROR);}
					case -38:
						break;
					case 40:
						{return new Symbol(sym_SECOND.ERROR);}
					case -39:
						break;
					case 42:
						{return new Symbol(sym_SECOND.ERROR);}
					case -40:
						break;
					case 44:
						{return new Symbol(sym_SECOND.ERROR);}
					case -41:
						break;
					case 46:
						{return new Symbol(sym_SECOND.ERROR);}
					case -42:
						break;
					case 48:
						{return new Symbol(sym_SECOND.ERROR);}
					case -43:
						break;
					case 50:
						{return new Symbol(sym_SECOND.ERROR);}
					case -44:
						break;
					case 52:
						{return new Symbol(sym_SECOND.ERROR);}
					case -45:
						break;
					case 54:
						{return new Symbol(sym_SECOND.ERROR);}
					case -46:
						break;
					case 56:
						{return new Symbol(sym_SECOND.ERROR);}
					case -47:
						break;
					case 58:
						{return new Symbol(sym_SECOND.ERROR);}
					case -48:
						break;
					case 60:
						{return new Symbol(sym_SECOND.ERROR);}
					case -49:
						break;
					case 128:
						{return new Symbol(sym_SECOND.ERROR);}
					case -50:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
