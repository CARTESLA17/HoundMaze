package co.rifagap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		Coordenadas puntoDeInicio = new Coordenadas();
		puntoDeInicio.setX(54);
		puntoDeInicio.setY(77);
		Coordenadas puntoFinal = new Coordenadas();
		puntoFinal.setX(12);
		puntoFinal.setY(20);
		File archivo = new File("./resources/Hound Maze(tsv).txt");
		ArrayList<String> lineasDelArchivo = new ArrayList<>();
		ArrayList<PiezaDeLaberinto> camino = new ArrayList<PiezaDeLaberinto>();
 		PiezaDeLaberinto [][] laberinto = null;
		if( archivo.exists() && archivo.isFile() && archivo.canRead()){
			try {
				BufferedReader bRDelArchivo = new BufferedReader(new FileReader(archivo));
				cargarLineasDelArchivoEnArray(lineasDelArchivo, bRDelArchivo);
				bRDelArchivo.close();
				laberinto = armarLaberinto(laberinto, lineasDelArchivo);
				encontrarCamino(camino, laberinto, puntoDeInicio, puntoFinal);				
				/*for( int y=0;y<laberinto[0].length;y++){
					for( int x=0; x<laberinto.length; x++){
						if ( laberinto[x][y]!=null) {
							System.out.print(laberinto[x][y].getRepresentacion());
						} else {
							System.out.print("-");
						}						
					}
					System.out.println();
				}*/			
			} catch (FileNotFoundException e) {
				System.out.println("Error en la lectura del archivo!");
			} catch (IOException e) {
				System.out.println("Error en la lectura del archivo!");
			} 			
		}
	}
	
	public static void encontrarCamino(ArrayList<PiezaDeLaberinto> camino, PiezaDeLaberinto [][] laberinto, Coordenadas puntoDeInicio, Coordenadas puntoFinal){
		PiezaDeLaberinto pieza = new PiezaDeLaberinto();
		pieza.setCoordenadas(puntoDeInicio);
		camino.add(pieza);
		boolean continuarBuscando=true;
		while (continuarBuscando){
			if( pudeDesplazarme( camino, laberinto) ){
				if ( camino.get(camino.size()-1).getCoordenadas().getX()==puntoFinal.getX() 
						&& camino.get(camino.size()-1).getCoordenadas().getY()==puntoFinal.getY()) {
					imprimirCamino(camino);
					continuarBuscando = false;
				}
			} else if ( camino.get(camino.size()-1).getCoordenadas().getX()==puntoDeInicio.getX() 
					&& camino.get(camino.size()-1).getCoordenadas().getY()==puntoDeInicio.getY() ){
				System.out.println("No hay camino!");
				continuarBuscando = false;				
			} else {
				laberinto[camino.get(camino.size()-1).getCoordenadas().getX()][camino.get(camino.size()-1).getCoordenadas().getY()].setPiezaHabilitada(false);
				camino.remove(camino.size()-1);
			} 			
		};	
	}
	
	public static void imprimirCamino( ArrayList<PiezaDeLaberinto> camino){
		System.out.print("[");
		for(PiezaDeLaberinto pieza: camino){
			System.out.print("["+pieza.getCoordenadas().getX()+","+pieza.getCoordenadas().getY()+"]");			
		}
		System.out.print("]");
	}
	
	public static boolean pudeDesplazarme(ArrayList<PiezaDeLaberinto> camino, PiezaDeLaberinto [][] laberinto){
		PiezaDeLaberinto pieza = camino.get(camino.size()-1);
		boolean pudeDesplazarme = false;
		//Hacia la derecha
		if ( pieza.isDerecha() && pieza.getCoordenadas().getX()+1<laberinto.length 
				&& laberinto[pieza.getCoordenadas().getX()+1][pieza.getCoordenadas().getY()]!=null 
				&& laberinto[pieza.getCoordenadas().getX()+1][pieza.getCoordenadas().getY()].isPiezaHabilitada() ) {
				camino.get(camino.size()-1).setDerecha(false);
				camino.add(laberinto[pieza.getCoordenadas().getX()+1][pieza.getCoordenadas().getY()]);
				pudeDesplazarme=true;
		//Hacia abajo
		} else if ( pieza.isAbajo() && pieza.getCoordenadas().getY()+1<laberinto[0].length 
				&& laberinto[pieza.getCoordenadas().getX()][pieza.getCoordenadas().getY()+1]!=null 
				&& laberinto[pieza.getCoordenadas().getX()][pieza.getCoordenadas().getY()+1].isPiezaHabilitada() ){
				camino.get(camino.size()-1).setAbajo(false);
				camino.add(laberinto[pieza.getCoordenadas().getX()][pieza.getCoordenadas().getY()+1]);
				pudeDesplazarme=true;
		//Hacia la izquierda
		} else if ( pieza.isIzquierda() && pieza.getCoordenadas().getX()-1>=0
				&& laberinto[pieza.getCoordenadas().getX()-1][pieza.getCoordenadas().getY()]!=null 
				&& laberinto[pieza.getCoordenadas().getX()-1][pieza.getCoordenadas().getY()].isPiezaHabilitada() ) {
				camino.get(camino.size()-1).setIzquierda(false);
				camino.add(laberinto[pieza.getCoordenadas().getX()-1][pieza.getCoordenadas().getY()] );
				pudeDesplazarme=true;
		//Hacia arriba
		} else if ( pieza.isArriba() && pieza.getCoordenadas().getY()-1>=0
				&& laberinto[pieza.getCoordenadas().getX()][pieza.getCoordenadas().getY()-1]!=null 
				&& laberinto[pieza.getCoordenadas().getX()][pieza.getCoordenadas().getY()-1].isPiezaHabilitada() ) {			
				camino.get(camino.size()-1).setArriba(false);
				camino.add(laberinto[pieza.getCoordenadas().getX()][pieza.getCoordenadas().getY()-1] );
				pudeDesplazarme=true;
		}
		return pudeDesplazarme;
	}
	
	public static void cargarLineasDelArchivoEnArray(ArrayList<String> lineasDelArchivo, BufferedReader bRDelArchivo) throws IOException{
		String linea="";
		int contador=1;
		while( (linea=bRDelArchivo.readLine()) != null ){
			if( contador>5 ){
				lineasDelArchivo.add(linea);	
			}else{
				contador++;
			}	
		}		
	}
	
	public static PiezaDeLaberinto [][] armarLaberinto(PiezaDeLaberinto [][] laberinto, ArrayList<String> lineasDelArchivo){
		int x = 0;
		int y = lineasDelArchivo.size();
		if( !lineasDelArchivo.isEmpty() ){
			String lineaUno=lineasDelArchivo.get(0);
			for(int i=5; i<lineaUno.length(); i++){
				if( lineaUno.charAt(i)=='F'){
					i++;
				}
				x++;
			}
			x++;
		}
		laberinto = new PiezaDeLaberinto [x][y];
		y=0;
		for( String linea: lineasDelArchivo){
			x=0;
			for( int j=5; j<linea.length(); j++){
				if( linea.charAt(j)=='F'){
					Coordenadas coordenadas = new Coordenadas();
					coordenadas.setX(x);
					coordenadas.setY(y);
					PiezaDeLaberinto pieza = new PiezaDeLaberinto();
					pieza.setCoordenadas(coordenadas);
					pieza.setRepresentacion('F');					
					laberinto[x][y]=pieza;
					j++;
				}
				x++;
			}
			y++;			
		}		
		return laberinto;
	}

}
