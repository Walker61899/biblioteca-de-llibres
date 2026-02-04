package EntornsDeDesenvolupament;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Biblioteca {

	/////////////////////////////////
	///     VARIABLES GLOBALS     ///
	/////////////////////////////////
	private ArrayList<Llibre> biblioteca = new ArrayList<Llibre>();
	private static ArrayList<Reserva> reserves   = new ArrayList<Reserva>();
	static File carpetaLlibres  = Exercici_BibliotecaDeLlibres.carpetaLlibres;
	static File carpetaReserves = Exercici_BibliotecaDeLlibres.carpetaReserves;
	
	//Colors
	public static String ColorRESET = "\33[0m";
	public static String ColorBase = "\33[0;44m";
	public static String ColorNegreta = "\33[1;44m";
	
	//Constructor de la biblioteca base
	public Biblioteca() {
	}
	
	
	
	
	/////////////////////////////////
	///      CARREGAR DADES       ///
	/////////////////////////////////
	public void carregarDades() {
		if (!carpetaLlibres.exists() || !carpetaLlibres.isDirectory()) {
			System.out.println("Ruta no vàlida.");
			return;
		}
		
		File[] llistaLlibres = carpetaLlibres.listFiles();
		
		if (llistaLlibres != null) {
			Arrays.sort(llistaLlibres, new Comparator<File>() {
				public int compare(File f1, File f2) {
					try {
						int id1 = Integer.parseInt(f1.getName());
						int id2 = Integer.parseInt(f2.getName());
						return Integer.compare(id1, id2);
					} catch (NumberFormatException e) {
						return f1.getName().compareTo(f2.getName());
					}
				}
			});
		}
		
		for (File fitxer : llistaLlibres) {
			processarFitxerLlibre(fitxer);
		}
		
		if (!carpetaReserves.exists() || !carpetaReserves.isDirectory()) {
			System.out.println("Ruta no vàlida.");
			return;
		}
		
		File[] llistaReserves = carpetaReserves.listFiles();
		
		for (File fitxer : llistaReserves) {
			processarFitxerReserva(fitxer);
		}
	}
	
	private void processarFitxerLlibre(File fitxer) {
		Scanner dadesLlibre = null;
		int contadorLinies = 0;
		
		int id_llibre;
		String titol      =   null;
		String autor      =   null;
		String tematica   =   null;
		
		try {
			dadesLlibre = new Scanner(fitxer);
			id_llibre = Integer.parseInt(fitxer.getName().replaceAll("[^0-9]",""));
			
			while (dadesLlibre.hasNextLine()) {
				switch(contadorLinies) {
				
					case 0: titol     =  dadesLlibre.nextLine(); break;
					case 1: autor     =  dadesLlibre.nextLine(); break;
					case 2: tematica  =  dadesLlibre.nextLine(); break;
				}
				contadorLinies++;
			}
			dadesLlibre.close();
			
			this.biblioteca.add(new Llibre(id_llibre, titol, autor, tematica));
			
		} catch (IOException e) {
			System.out.println("Error llegint el fitxer " + fitxer.getName());
		} finally {
			if (dadesLlibre != null) {
				dadesLlibre.close();
			}
		}
	}
	
	@SuppressWarnings("static-access")
	private void processarFitxerReserva(File fitxer) {
		Scanner dadesReserva = null;
		
		String usuari;
		String titolLlibre =   null;
		Llibre llibre;
		
		try {
			dadesReserva = new Scanner(fitxer);
			usuari = fitxer.getName();
			
			while (dadesReserva.hasNextLine()) {
				titolLlibre = dadesReserva.nextLine();
				for (Llibre llibreLlegit: biblioteca) {
					if (titolLlibre.trim().equalsIgnoreCase(llibreLlegit.titol.trim())) {
						llibre = llibreLlegit;
						this.reserves.add(new Reserva(usuari, llibre));
						break;
					}
				}				
				
			}
			dadesReserva.close();
			
			
		} catch (IOException e) {
			System.out.println("Error llegint el fitxer " + fitxer.getName());
		} finally {
			if (dadesReserva != null) {
				dadesReserva.close();
			}
		}
	}
	
	
	
	
	////////////////////////////////
	///       AFEGIR LLIBRE      ///
	////////////////////////////////
	@SuppressWarnings("resource")
	public void afegirLlibre(Llibre llibre) {
		Scanner sc = new Scanner(System.in);
		String opcio;
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Títol:     %-48s%s██  \n", ColorNegreta, ColorBase, llibre.titol, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Autor:     %-48s%s██  \n", ColorNegreta, ColorBase, llibre.autor, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Temàtica:  %-48s%s██  \n", ColorNegreta, ColorBase, llibre.tematica, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   Revisa les dades i prem [Enter] per confirmar o            ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   escriu [cancel·lar] per a cancel·lar l'acció               ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		opcio = sc.nextLine();
		
		if (opcio.isEmpty()) {
			try {
				File fitxer = new File(carpetaLlibres, String.valueOf(llibre.identificador));

	            FileWriter fw = new FileWriter(fitxer);
	            fw.write(llibre.titol + "\n");
	            fw.write(llibre.autor + "\n");
	            fw.write(llibre.tematica);
	            fw.close();
	            
				System.out.print("\n".repeat(20));
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   > Llibre afegit correctament a la biblioteca:              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.printf ("%s  ██%s        ID: %-46s    %s██  %s\n", ColorNegreta, ColorBase, llibre.identificador, ColorNegreta, ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.printf ("%s  ██%s        %-50s    %s██  %s\n", ColorNegreta, ColorBase, llibre.titol, ColorNegreta, ColorRESET);
				System.out.printf ("%s  ██%s         %-50s   %s██  %s\n", ColorNegreta, ColorBase, llibre.autor, ColorNegreta, ColorRESET);
				System.out.printf ("%s  ██%s         %-50s   %s██  %s\n", ColorNegreta, ColorBase, llibre.tematica, ColorNegreta, ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   Prem [Enter] per continuar...                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				sc.nextLine();
				
			} catch (IOException e) {
				System.out.print("\n".repeat(20));
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   > Ha sorgit un error desant la informació                  ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   Prem [Enter] per continuar...                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				sc.nextLine();
			}
		}
		
		
		biblioteca.add(llibre);
	}
	
	
	
	
	/////////////////////////////////
	///      MOSTRAR LLIBRES      ///
	/////////////////////////////////
	@SuppressWarnings("resource")
	public void mostrarLlibres() {
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
		for (Llibre llibreMostrat: biblioteca) {
			System.out.printf("%s  ██%s  %-3s\33[44;30m>\33[0;44m  %-50s    %s██  %s\n", ColorNegreta, ColorBase, llibreMostrat.identificador, llibreMostrat.titol, ColorNegreta, ColorRESET);
			if (contadorLinies == 8) {
				contadorLinies = 0;
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   \33[1;4;44mPrem [Enter] per mostrar la resta de la llista o \33[0;44m          ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   \33[1;4;44mescriu la ID d'un llibre per a veure la seva informació \33[0;44m   ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				
				opcio = sc.nextLine();			
				
				if (opcio.isEmpty()) {
					System.out.print("\n".repeat(20));
					System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				} else {
					for (Llibre llibre : biblioteca) {
						if (llibre.identificador == (Integer.parseInt(opcio))) {
							mostrarDadesLlibre(llibre);					
						}
					}
					mostrarLlibres();
					
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
		if (!opcio.isEmpty()) {
			for (Llibre llibre : biblioteca) {
				if (llibre.identificador == (Integer.parseInt(opcio))) {
					mostrarDadesLlibre(llibre);					
				}
			}
			mostrarLlibres();
			
		} else {
			return;
		}
		
	}
	
	@SuppressWarnings("resource")
	public void mostrarDadesLlibre(Llibre llibre) {
		Scanner sc = new Scanner(System.in);
		int accions = 0;
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s  ID: %-56s██  \n", ColorNegreta, ColorBase, llibre.identificador, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s  TÍTOL:     %-49s%s██  %s\n", ColorNegreta, ColorBase, llibre.titol, ColorNegreta, ColorRESET);
		System.out.printf ("%s  ██%s  AUTOR:     %-49s%s██  %s\n", ColorNegreta, ColorBase, llibre.autor, ColorNegreta, ColorRESET);
		System.out.printf ("%s  ██%s  TEMÀTICA:  %-49s%s██  %s\n", ColorNegreta, ColorBase, llibre.tematica, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██     1. Modificar la informació del llibre                    ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██     2. Reservar el llibre                                    ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██     3. Eliminar el llibre                                    ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██     4. Tornar al menú principal                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		try {
			accions = sc.nextInt();
		} catch (InputMismatchException e) {
			mostrarDadesLlibre(llibre);
		}
		
		switch (accions) {
		case 1:
			modificarLlibre(llibre);
			break;
		case 2:
			ReservarLlibre(llibre);
			break;
		case 3:
			eliminarLlibre(llibre);
			return;
		case 4:
			Exercici_BibliotecaDeLlibres.menu();
			return;
		}
		
		mostrarDadesLlibre(llibre);
	}	
	
	
	/////////////////////////////////
	///    FILTRE CERCA LLIBRE    ///
	/////////////////////////////////
	public ArrayList<Llibre> cercarLlibre(Filtre filtre, String cerca) {
		ArrayList<Llibre> resultats = new ArrayList<Llibre>();
		
		switch(filtre) {
		case TITOL:
			for (Llibre llibre : biblioteca) {
				if (llibre.getTitol().toUpperCase().contains(cerca.toUpperCase())) {
					resultats.add(llibre);
				}
			}
			break;
		case AUTOR:
			for (Llibre llibre : biblioteca) {
				if (llibre.getAutor().toUpperCase().contains(cerca.toUpperCase())) {
					resultats.add(llibre);
				}
			}
			break;
		case TEMATICA:
			for (Llibre llibre : biblioteca) {
				if (llibre.getTematica().toUpperCase().contains(cerca.toUpperCase())) {
					resultats.add(llibre);
				}
			}
			break;
		
		default:
			return resultats;
		}
		
		return resultats;
	}

	

	
	/////////////////////////////////
	///      RESERVAR LLIBRE      ///
	/////////////////////////////////
	@SuppressWarnings("resource")
	public static void ReservarLlibre(Llibre llibre) {
		Scanner sc = new Scanner(System.in);
		String usuari;
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   A quin nom vols registrar la reserva?                      ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Títol:     %-48s%s██  %s\n", ColorNegreta, ColorBase, llibre.titol, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Autor:     %-48s%s██  %s\n", ColorNegreta, ColorBase, llibre.autor, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Temàtica:  %-48s%s██  %s\n", ColorNegreta, ColorBase, llibre.tematica, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		usuari = sc.nextLine();
		
		if (usuari.trim().isEmpty()) {
			Exercici_BibliotecaDeLlibres.menu();
			return;
		}
		
		try {
			File fitxer = new File(carpetaReserves, usuari);
			
			if (fitxer.exists()) {
				FileWriter fw = new FileWriter(fitxer, true);
				fw.write(llibre.titol + "\n");
				fw.close();

			} else {
				FileWriter fw = new FileWriter(fitxer);
				fw.write(llibre.titol + "\n");
				fw.close();
			}
			
			Reserva reserva = new Reserva(usuari, llibre);
			reserves.add(reserva);
			
			System.out.print("\n".repeat(20));
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   > Reserva registrada correctament!                         ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.printf ("%s  ██%s   Usuari: %-50s%s ██  %s\n", ColorNegreta, ColorBase, usuari, ColorNegreta, ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.printf ("%s  ██%s   Títol: %-50s%s  ██  %s\n", ColorNegreta, ColorBase, llibre.titol, ColorNegreta, ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   Prem [Enter] per continuar...                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			sc.nextLine();
			
		} catch (IOException e) {
			System.out.println(ColorBase + "  > Error en guardar els canvis." + ColorRESET);
		}
		
	}
	
	@SuppressWarnings("resource")
	public static void mostrarReserves() {  //ARREGLAR
		Scanner sc = new Scanner(System.in);
		int contadorLinies = 0;
		String opcio = "";
		
		if (reserves.isEmpty()) {
			System.out.print("\n".repeat(20));
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   No hi ha cap reserva registrada.                           ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   Prem [Enter] per tornar al menú principal...               ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			sc.nextLine();
			return;
		}
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				
		for (Reserva reserva: reserves) {
			System.out.printf("%s  ██%s  %-10s\33[44;30m>\33[0;44m  %-47s%s██  %s\n", ColorNegreta, ColorBase, reserva.getUsuari(), reserva.getLlibreReservat().titol, ColorNegreta, ColorRESET);
			if (contadorLinies == 8) {
				contadorLinies = 0;
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   \33[1;4;44mPrem [Enter] per mostrar la resta de la llista o escriu\33[0;44m    ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   \33[1;4;44ml'usuari de la reserva per a veure la seva informació \33[0;44m     ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
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
					gestionarSeleccioReserva(opcio);
					mostrarReserves();
					
				}

			} else {
				contadorLinies++;
			}
		}
		for (int i = contadorLinies; i < 9; i++) {
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		}
		
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   Escriu l'usuari de la reserva per veure la seva informació ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   o prem [Enter] per tornar al menú principal                ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		String entrada = sc.nextLine();
		
		if (entrada.isEmpty()) {
			Exercici_BibliotecaDeLlibres.menu();
		}
		else {
			gestionarSeleccioReserva(entrada);
			mostrarReserves();
		}
	}
	
	@SuppressWarnings("resource")
	private static void gestionarSeleccioReserva(String entrada) {
		ArrayList<Reserva> reservesUsuari = new ArrayList<Reserva>();
		
		for (Reserva r : reserves) {
			if (r.getUsuari().equalsIgnoreCase(entrada.trim())) {
				reservesUsuari.add(r);
			}
		}
		
		if (reservesUsuari.isEmpty()) {
			return;
		}
		
		if (reservesUsuari.size() == 1) {
			mostrarDadesReserva(reservesUsuari.get(0));
		} else {
			Scanner sc = new Scanner(System.in);
			String opcio = "";
			int contadorLinies = 0;;
			
			System.out.print("\n".repeat(20));
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   L'usuari té més d'una reserva. Quin llibre vols veure?     ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			
			int i = 1;
			for (Reserva r : reservesUsuari) {
				System.out.printf("%s  ██%s   %d. %-56s%s██  %s\n", ColorNegreta, ColorBase, i, r.getLlibreReservat().getTitol(), ColorNegreta, ColorRESET);
				i++;
				if (contadorLinies == 8) {
					contadorLinies = 0;
					System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██   \33[1;4;44mEscull un llibre o prem [Enter] per \33[0;44m                       ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██   \33[1;4;44ma passar pàgina\33[0;44m                                            ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					
					opcio = sc.nextLine();			
					
					if (opcio.isEmpty()) {
						System.out.print("\n".repeat(20));
						System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
						System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
						System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
						System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
						System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
						System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
						System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
					} else {
						mostrarDadesReserva(reservesUsuari.get(Integer.parseInt(opcio) - 1));
					}

				} else {
					contadorLinies++;
				}
			}
			for (int j = contadorLinies; j < 7; j++) {
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			}				
			
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   \33[1;4;44mEscull un llibre o prem [Enter] per \33[0;44m                       ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   \33[1;4;44mtornar al menú principal \33[0;44m                                  ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			
			try {
				opcio = sc.nextLine();
				if (Integer.parseInt(opcio) > 0 && Integer.parseInt(opcio) <= reservesUsuari.size()) {
					mostrarDadesReserva(reservesUsuari.get(Integer.parseInt(opcio) - 1));
				}
			} catch (NumberFormatException e) {
				Exercici_BibliotecaDeLlibres.menu();
			}
		}
	}

	private static void mostrarDadesReserva(Reserva reserva) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Usuari: %-50s%s ██  %s\n", ColorNegreta, ColorBase, reserva.getUsuari(), ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Títol: %-50s%s  ██  %s\n", ColorNegreta, ColorBase, reserva.getLlibreReservat().getTitol(), ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██     1. Eliminar reserva                                      ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██     2. Tornar al menú principal                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		int opcio = 0;
		try {
			opcio = sc.nextInt();
		} catch (InputMismatchException e) {
			mostrarDadesReserva(reserva);
		}
		
		switch(opcio) {
		case 1:
			eliminarReserva(reserva);
			break;
		case 2:
			Exercici_BibliotecaDeLlibres.menu();
			break;
		default:
			mostrarDadesReserva(reserva);
		}
	}
	
	@SuppressWarnings("resource")
	private static void eliminarReserva(Reserva reserva) {
		Scanner sc = new Scanner(System.in);
		String confirmacio;
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   Segur que vols eliminar aquesta reserva?                   ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Usuari: %-51s%s██  %s\n", ColorNegreta, ColorBase, reserva.getUsuari(), ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Llibre: %-51s%s██  %s\n", ColorNegreta, ColorBase, reserva.getLlibreReservat().titol, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   Escriu [SÍ] per confirmar o qualsevol altra tecla per      ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   cancel·lar:                                                ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		confirmacio = sc.nextLine();
		
		if (confirmacio.equalsIgnoreCase("SÍ") || confirmacio.equalsIgnoreCase("SI")) {
			File fitxer = new File(carpetaReserves, reserva.getUsuari());
			ArrayList<String> linies = new ArrayList<String>();
			
			try {
				Scanner lector = new Scanner(fitxer);
				while (lector.hasNextLine()) {
					String linia = lector.nextLine();
					if (!linia.trim().equalsIgnoreCase(reserva.getLlibreReservat().titol.trim())) {
						linies.add(linia);
					}
				}
				lector.close();
				
				if (linies.isEmpty()) {
					fitxer.delete();
				} else {
					FileWriter fw = new FileWriter(fitxer);
					for (String linia : linies) {
						fw.write(linia + "\n");
					}
					fw.close();
				}
				
				reserves.remove(reserva);
				
				System.out.print("\n".repeat(20));
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   > Reserva eliminada correctament!                          ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   Prem [Enter] per continuar...                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				sc.nextLine();
				
				Exercici_BibliotecaDeLlibres.menu();
				
			} catch (IOException e) {
				mostrarDadesReserva(reserva);
			}
		} else {
			System.out.print("\n".repeat(20));
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   > Operació cancel·lada                                     ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   Prem [Enter] per continuar...                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			sc.nextLine();
			Exercici_BibliotecaDeLlibres.menu();
		}
	}

	
	
	
	////////////////////////////////
	///     MODIFICAR LLIBRE     ///
	////////////////////////////////
	@SuppressWarnings("resource")
	private void modificarLlibre(Llibre llibre) {
		Scanner sc = new Scanner(System.in);
		String opcio;
		int eleccio = 0;
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   Quin camp vols modificar?                                  ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██      1. Modificar el títol                                   ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██      2. Modificar l'autor                                    ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██      3. Modificar la temàtica                                ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██      4. Cancelar                                             ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		try {
			eleccio = sc.nextInt();
			sc.nextLine();
		} catch (InputMismatchException e) {
			modificarLlibre(llibre);
			return;
		}
		
		switch(eleccio) {
		case 1:
			System.out.print("\n".repeat(20));
			System.out.println(ColorBase + "Introdueix el nou títol del llibre:" + ColorRESET);
			System.out.print(ColorBase + "  > " + ColorRESET);
			opcio = sc.nextLine();
			if (!opcio.trim().isEmpty()) {
				llibre.titol = opcio;
				guardarLlibreAFitxer(llibre);
			}
			break;
		case 2:
			System.out.print("\n".repeat(20));
			System.out.println(ColorBase + "Introdueix el nou autor del llibre:" + ColorRESET);
			System.out.print(ColorBase + "  > " + ColorRESET);
			opcio = sc.nextLine();
			if (!opcio.trim().isEmpty()) {
				llibre.autor = opcio;
				guardarLlibreAFitxer(llibre);
			}
			break;
		case 3:
			System.out.print("\n".repeat(20));
			System.out.println(ColorBase + "Introdueix la nova temàtica del llibre:" + ColorRESET);
			System.out.print(ColorBase + "  > " + ColorRESET);
			opcio = sc.nextLine();
			if (!opcio.trim().isEmpty()) {
				llibre.tematica = opcio;
				guardarLlibreAFitxer(llibre);
			}
			break;
		case 4:
			return;
		}
	}
	
	private void guardarLlibreAFitxer(Llibre llibre) {
		try {
			File fitxer = new File(carpetaLlibres, String.valueOf(llibre.identificador));
			FileWriter fw = new FileWriter(fitxer);
			fw.write(llibre.titol + "\n");
			fw.write(llibre.autor + "\n");
			fw.write(llibre.tematica);
			fw.close();
			
			System.out.print("\n".repeat(20));
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   > Dades del llibre modificades correctament!               ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   Prem [Enter] per continuar...                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
		} catch (IOException e) {
			System.out.println(ColorBase + "  > Error en guardar els canvis." + ColorRESET);
		}
	}
	
	@SuppressWarnings("resource")
	private void eliminarLlibre(Llibre llibre) {
		Scanner sc = new Scanner(System.in);
		String confirmacio;
		
		System.out.print("\n".repeat(20));
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   Segur que vols eliminar aquest llibre?                     ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Títol: %-52s%s██  %s\n", ColorNegreta, ColorBase, llibre.titol, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.printf ("%s  ██%s   Autor: %-52s%s██  %s\n", ColorNegreta, ColorBase, llibre.autor, ColorNegreta, ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   Escriu [SÍ] per confirmar o qualsevol altra tecla per      ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██   cancel·lar:                                                ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
		
		confirmacio = sc.nextLine();
		
		if (confirmacio.equalsIgnoreCase("SÍ") || confirmacio.equalsIgnoreCase("SI")) {
			File fitxer = new File(carpetaLlibres, String.valueOf(llibre.identificador));
			if (fitxer.delete()) {
				biblioteca.remove(llibre);
				
				System.out.print("\n".repeat(20));
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   > Llibre eliminat correctament de la biblioteca!           ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   Prem [Enter] per continuar...                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				sc.nextLine();
				
				Exercici_BibliotecaDeLlibres.menu();
			} else {
				System.out.print("\n".repeat(20));
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   > Ha sorgit un error en l'eliminació del fitxer            ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██   Prem [Enter] per continuar...                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
				sc.nextLine();
			}
		} else {
			System.out.print("\n".repeat(20));
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   BIBLIOTECA                                                 ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   > Operació cancel·lada                                     ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██   Prem [Enter] per continuar...                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██                                                              ██  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "  ██████████████████████████████████████████████████████████████████  ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			System.out.println(ColorBase + "                                                                      ".replaceAll("█", "\33[1;44m█\33[0;44m") + ColorRESET);
			sc.nextLine();
			Exercici_BibliotecaDeLlibres.menu();
		}
	}
	
	
	
	
	//////////////////////////////////
	///      DADES BIBLIOTECA      ///
	//////////////////////////////////
	public int length() {
		return biblioteca.size();
	}
	
	public int getUltimaID() {
		if (biblioteca.isEmpty()) {
			return -1;
		}
		int ultimID = 0;
		for (Llibre llibre : biblioteca) {
			ultimID = llibre.identificador;
		}
		return ultimID;
	}
}
