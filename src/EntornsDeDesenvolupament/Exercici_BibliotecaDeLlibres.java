package EntornsDeDesenvolupament;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class Exercici_BibliotecaDeLlibres {
	
	/////////////////////////////////
	///     VARIABLES GLOBALS     ///
	/////////////////////////////////
	public static File carpetaLlibres  = new File("src/biblioteca/llibres");
	public static File carpetaReserves = new File("src/biblioteca/reserves");
	public final static Biblioteca biblioteca = new Biblioteca();
	
	//Colors
	public static String ColorRESET = "\33[0m";
	public static String ColorBase = "\33[0;44m";
	public static String ColorNegreta = "\33[1;44m";	
	
	
	
	
	////////////////////////////////
	///           MAIN           ///
	////////////////////////////////
	public static void main(String[] args) {
		
		//Càrrega de llibres de la carpeta de fitxers
		biblioteca.carregarDades();
				
		//Mostra del menú i inici del programa
		menu();
	}
	
	
	
	
	/////////////////////////////////
	///       MENÚ PRICIPAL       ///
	/////////////////////////////////
	@SuppressWarnings("resource")
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		int opcio = 0;
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ");
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   1. Afegir nous llibres a la biblioteca                     ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   2. Cercar llibres                                          ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   3. Mostrar tots els llibres                                ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   4. Mostrar reserves                                        ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		try {
			opcio = sc.nextInt();			
		} catch (InputMismatchException e) {
			menu();
		}
		
		switch(opcio) {
		case 1:
			registrarNouLlibre();
			break;
		case 2:
			cercarLlibre();
			break;
		case 3:
			biblioteca.mostrarLlibres();
			break;
		case 4:
			Biblioteca.mostrarReserves();
			break;
		}
		menu();
	}
	
	
	
	
	////////////////////////////////
	///     REGISTRAR LLIBRE     ///
	////////////////////////////////
	@SuppressWarnings("resource")
	public static void registrarNouLlibre() {
		Scanner sc = new Scanner(System.in);
		int id_llibre;
		String titol = "";
		String autor = "";
		String tematica = "";
		String accions = "";
		
		id_llibre = biblioteca.getUltimaID() + 1;
		
		accions = "\33[1;4;44mEscriu un TÍTOL pel llibre \33[0;44m";
		menuRegistrarNouLlibre(titol, autor, tematica, accions);
		titol = sc.nextLine();
		
		accions = "\33[1;4;44mEscriu un AUTOR pel llibre \33[0;44m";
		menuRegistrarNouLlibre(titol, autor, tematica, accions);
		autor = sc.nextLine();
		
		accions = "\33[1;4;44mEscriu una TEMÀTICA pel llibre \33[0;44m";
		menuRegistrarNouLlibre(titol, autor, tematica, accions);
		tematica = sc.nextLine();
		
		biblioteca.afegirLlibre(new Llibre(id_llibre, titol, autor, tematica));
	}
	
	public static void menuRegistrarNouLlibre(String titol, String autor, String tematica, String accions) {
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Títol:     %-48s%s██  %s\n", ColorNegreta, ColorBase, titol, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Autor:     %-48s%s██  %s\n", ColorNegreta, ColorBase, autor, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Temàtica:  %-48s%s██  %s\n", ColorNegreta, ColorBase, tematica, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   %-60s               %s██  %s\n", ColorNegreta, ColorBase, accions, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[1;44m") + ColorRESET);
	}
	
	
	
	
	/////////////////////////////////
	///       CERCAR LLIBRE       ///
	/////////////////////////////////
	@SuppressWarnings("resource")
	public static void cercarLlibre() {
		Scanner sc = new Scanner(System.in);;
		int opcio = 0;
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   Escull un criteri de cerca:                                ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██      1. Cercar per títol                                     ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██      2. Cercar per autor                                     ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██      3. Cercar per temàtica                                  ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██      4. Tornar al menú principal                             ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		try {
			opcio = sc.nextInt();
			sc.nextLine();
			
		} catch (InputMismatchException e) {
			cercarLlibre();
		}			
		
		switch(opcio) {
		case 1:
			menuCercaLlibres(Filtre.TITOL);
			break;
		case 2:
			menuCercaLlibres(Filtre.AUTOR);
			break;
		case 3:
			menuCercaLlibres(Filtre.TEMATICA);
			break;
		case 4:
			menu();
		}
		
		cercarLlibre();
	}
	
	@SuppressWarnings("resource")
	public static void menuCercaLlibres(Filtre criteri) {
		ArrayList<Llibre> llibresTrobats = null;
		Scanner sc = new Scanner(System.in);
		String cerca = "";
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██   %sCercant llibre per:   %-37s%s██  %s\n",ColorNegreta, ColorBase, criteri, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   Introdueix la cerca:                                       ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		cerca = sc.nextLine();
		llibresTrobats = biblioteca.cercarLlibre(criteri, cerca);
		
		mostrarResultatsDeCerca(llibresTrobats);
	}
	
	@SuppressWarnings("resource")
	public static void mostrarResultatsDeCerca(ArrayList<Llibre> llibresTrobats) {
		String opcio = "";
		Scanner sc = new Scanner(System.in);
		int contadorLinies = 0;
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		for (Llibre llibreMostrat: llibresTrobats) {
			System.out.printf("%s  ██%s  %-3s\33[44;30m>\33[0;44m  %-54s%s██  %s\n", ColorNegreta, ColorBase, llibreMostrat.identificador, llibreMostrat.titol, ColorNegreta, ColorRESET);
			if (contadorLinies == 8) {
				contadorLinies = 0;
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   \33[1;4;44mPrem [Enter] per mostrar la resta de la llista o \33[0;44m          ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   \33[1;4;44mescriu la ID d'un llibre per a veure la seva informació \33[0;44m   ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				
				opcio = sc.nextLine();			
				
				if (opcio == "") {
					System.out.print("\n".repeat(20));
					System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				} else {
					for (Llibre llibre : llibresTrobats) {
						if (llibre.identificador == (Integer.parseInt(opcio))) {
							biblioteca.mostrarDadesLlibre(llibre);					
						}
					}
					menu();
					
				}

			} else {
				contadorLinies++;
			}
		}
		for (int i = contadorLinies; i < 9; i++) {
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		}
		
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   \33[1;4;44mPrem [Enter] tornar al menú principal o \33[0;44m                   ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   \33[1;4;44mescriu la ID d'un llibre per a veure la seva informació \33[0;44m   ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		opcio = sc.nextLine();
		if (opcio != "") {
			for (Llibre llibre : llibresTrobats) {
				if (llibre.identificador == (Integer.parseInt(opcio))) {
					biblioteca.mostrarDadesLlibre(llibre);					
				}
			}
			menu();
			
		} else {
			return;
		}
		
	}
}
