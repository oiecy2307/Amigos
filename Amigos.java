package Amigos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Amigos {
	
	
	public Amigos() throws FileNotFoundException, IOException {
		
	
		
		RedDeAmistades g = new RedDeAmistades();
		leerCatalogo("catalogo.txt", g);
		leerRelaciones("relacione.txt", g);
		ignorarLineas(g.ignorados);
		
	}
	
	public static void main(String [] args) throws IOException{
		new Amigos();
	}
	public  static void leerCatalogo(String archivo, RedDeAmistades g) throws FileNotFoundException, IOException {
		
		String cadena;
		//ArrayList<String> ignorados = new ArrayList<String>();
		FileReader fr = new FileReader(archivo);
		BufferedReader bf = new BufferedReader(fr);
		
		while((cadena = bf.readLine())!=null) {
			
			revisarPatronAmigos(cadena,  g);
			
			
		}
		bf.close();
		//ignorarLineas(ignorados);
	}
	
	
public static void leerRelaciones(String archivo, RedDeAmistades g) throws FileNotFoundException, IOException {
		
		String cadena;
		ArrayList<String> ignorados = new ArrayList<String>();
		FileReader fr = new FileReader(archivo);
		BufferedReader bf = new BufferedReader(fr);
		
		while((cadena = bf.readLine())!=null) {
			
			revisarPatronRelaciones(cadena,  g);
			
			
		}
		bf.close();
		//ignorarLineas(ignorados);
	}
	
	public static void ignorarLineas(ArrayList<String> ignorados) {
		File f;
		f = new File("Ignorados.txt");
		long lineas;
		
		try{
		
		FileWriter w = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(w);
		PrintWriter wr = new PrintWriter(bw); 

			for (int i = 0; i<ignorados.size();i++) {
				
			wr.append(ignorados.get(i)+"\n"); //concatenamos en el archivo sin borrar lo existente
			}
			wr.close();	
			bw.close();
			
			//return;	
		
		
		
		}catch(IOException e){};

	}
	public static Date cadenaToFecha(String  fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
		  try {

	            Date date = formatter.parse(fecha);
	            //System.out.println(date);
	            //System.out.println(formatter.format(date));
	            return date;
	        } catch (ParseException e) {
	        	
	            e.printStackTrace();
	            return null;
	        }
	 
	}
	
	public static void revisarPatronAmigos(String cadena, RedDeAmistades g) {
	
		String regEx1 = "([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}/\\d{2}/\\d{4})";
		String[] amigo;
		String aux; 
		Pattern p =  Pattern.compile(regEx1);
		Matcher m = p.matcher(cadena);
		
		Persona persona;
		 
		if(m.find()) {
			
		cadena =m.group();
		
		if(cadena.contains(",")){
			//aux = m.group().replaceAll("(,)", " ");
			amigo = cadena.split("\\s*,\\s*");
			persona = new Persona(amigo[0],amigo[1],amigo[2].charAt(0),cadenaToFecha(amigo[3]),g.personas.size()+1);
			g.agregaPersona(persona);
			
			return;
			
		}
		
		}
		
		//Si la cadena no satisface a la expresion regular, el metodo termina, permitiendo una nueva ejecucion con la siguiente cadena.
		//System.out.println("Linea Invalida");
		 g.ignorados.add(cadena);
		return;
		
	}
	
	public static boolean isInteger(String numero){
	    try{
	        Integer.parseInt(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	public static void revisarPatronRelaciones(String cadena, RedDeAmistades g) {
	
		String regExRelacion = 
				//Agrega Relacion
				"\\d+\\s*amigo\\s*\\d+|"
				+ "(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*"
				+ "amigo\\s*(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))|"
				+ "(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*"
				+ "amigo\\s*\\d+|"
				+"\\d+\\s*amigo"
				+"(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*|"
				
				//Eliminar Relacion
				+ "\\d+\\s*eliminar\\s*\\d+|"
				+ "(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*"
				+ "eliminar\\s*(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))|"
				+ "\\d+\\s*eliminar\\s*"
				+ "(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*|"
				+ "(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*"
				+ "eliminar\\s*\\d+|"
				
				//Preguntar amistad
				+ "(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*"
				+ "amigos\\s*(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))"
				+ "|\\d+\\s*amigos\\s*\\d+|"
				+ "(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*"
				+ "amigos\\s*\\d+|"
				+ "\\d+\\s*amigos\\s*"
				+ "(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*|"
				
				//Preguntar nivel
				+ "amigos\\s*\\d+\\s+\\d+|"
				+ "amigos\\s*(([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4}))\\s*\\d+";
		
		String[] amigo;
		String[] amigo1;
		String[] amigo2;
		String posAux;
		String fecAux;
		String aux []; 
		Pattern p =  Pattern.compile(regExRelacion);
		Matcher m = p.matcher(cadena);
		String sub;
		Persona p1;
		Persona p2;
		 
		if(m.find()) {
			
		cadena =m.group();
		
		if(cadena.contains("amigos")){
			//aux = m.group().replaceAll("(,)", " ");
			 sub = cadena.substring(0,6);
			if(sub.equals("amigos")) {
				//System.out.println("Empieza con la palabra amigos");
				amigo = cadena.split("amigos\\s*");//|"
				 aux = amigo[1].split(" ");	//+ "amigos\\s([a-zA-Z\\s*]{1,20})\\s*,\\s*([a-zA-Z\\s*]{1,20})\\s*,\\s*([MmFf])\\s*,\\s*(\\d{2}[/]\\d{2}[/]\\d{4})\\s*\\d+");
				if(isInteger(aux[0]) && isInteger(aux[1])) {
					int persona1 = Integer.parseInt(aux[0]);
					int nivel = Integer.parseInt(aux[1]);
					//Buscar amigos por nivel (posicion)
					g.buscarCamino(persona1, nivel);
					return;
			}else {
				aux = amigo[1].split(",");
				posAux = aux[3].substring(10, aux[3].length());
				fecAux = aux[3].substring(0, 10);
				p1 = new Persona(aux[0],aux[1],aux[2].charAt(0),cadenaToFecha(fecAux));
				String posfinal = posAux.replaceAll(" ", "");
				//System.out.println("No son numeros");
				//Buscar amigos por nivel (nombre)
				g.buscarCamino(p1, Integer.parseInt(posfinal));
				return;
			}
			
			}
			
			
			amigo = cadena.split("\\s*amigos\\s*");
			if(isInteger(amigo[0])&&isInteger(amigo[1])) {
				int persona1 = Integer.parseInt(amigo[0]);
				int persona2 = Integer.parseInt(amigo[1]);
				//Buscar Amistades
 				System.out.println(persona1 + " amigos  " + persona2 + " ? " + g.buscarAmistad(persona1,persona2));
				return;
			}
			if(isInteger(amigo[1])) {
				amigo1= amigo[0].split(",");
				amigo2= amigo[1].split(",");
				p1 = new Persona(amigo1[0],amigo1[1],amigo1[2].charAt(0),cadenaToFecha(amigo1[3]),g.personas.size()+1);
				int persona2 = Integer.parseInt(amigo[1]);
				//Buscar Amistades
			
 				System.out.println(p1.nombre + " amigos " + g.obtenPersona(persona2).nombre + " ? "  + g.buscarAmistad(p1,g.obtenPersona(persona2)));
				
				return;
			}
			if(isInteger(amigo[0])) {
				amigo1= amigo[0].split(",");
				amigo2= amigo[1].split(",");
				int persona1 = Integer.parseInt(amigo[0]);
				p2 = new Persona(amigo2[0],amigo2[1],amigo2[2].charAt(0),cadenaToFecha(amigo2[3]),g.personas.size()+1);
				
				//Buscar Amistades
			
 				System.out.println(g.obtenPersona(persona1).nombre + " amigos " + p2.nombre + " ? " + g.buscarAmistad(g.obtenPersona(persona1),p2));
				
				return;
			}
			amigo1= amigo[0].split(",");
			amigo2= amigo[1].split(",");
			p1 = new Persona(amigo1[0],amigo1[1],amigo1[2].charAt(0),cadenaToFecha(amigo1[3]),g.personas.size()+1);
			p2 = new Persona(amigo2[0],amigo2[1],amigo2[2].charAt(0),cadenaToFecha(amigo2[3]),g.personas.size()+1);
			
			//Buscar Amistades
			
			System.out.println(p1.nombre + " amigos "+ p2.nombre + " ? " + g.buscarAmistad(p1,p2));
			
			//g.obtenPersona(p1).amistades.remove(new Amistad(g.obtenPersona(p1),g.obtenPersona(p2)));
			//g.obtenPersona(p2).amistades.remove(new Amistad(g.obtenPersona(p2),g.obtenPersona(p1)));
			//persona = new Persona(amigo[0],amigo[1],amigo[2].charAt(0),cadenaToFecha(amigo[3]),g.personas.size()+1);
			//g.agregaPersona(persona);
			
			return;
			
		}
		
		if(cadena.contains("amigo")){
			//aux = m.group().replaceAll("(,)", " ");
			amigo = cadena.split("\\s*amigo\\s*");
			if(isInteger(amigo[0])&&isInteger(amigo[1])) {
				int persona1 = Integer.parseInt(amigo[0]);
				int persona2 = Integer.parseInt(amigo[1]);
				//Mejorar los metodos addAmistad
				g.obtenPersona(persona1).addAmistad(new Amistad(g.obtenPersona(persona1),g.obtenPersona(persona2)));
				g.obtenPersona(persona2).addAmistad(new Amistad(g.obtenPersona(persona2),g.obtenPersona(persona1)));
				return;
			}
			
			if(isInteger(amigo[1])) {
				amigo1= amigo[0].split(",");
				amigo2= amigo[1].split(",");
				p1 = new Persona(amigo1[0],amigo1[1],amigo1[2].charAt(0),cadenaToFecha(amigo1[3]),g.personas.size()+1);
				int persona2 = Integer.parseInt(amigo[1]);
				//Mejorar los metodos addAmistad
				g.obtenPersona(p1).addAmistad(new Amistad(g.obtenPersona(p1),g.obtenPersona(persona2)));
				g.obtenPersona(persona2).addAmistad(new Amistad(g.obtenPersona(persona2),g.obtenPersona(p1)));
				return;
			}
			
			if(isInteger(amigo[0])) {
				amigo1= amigo[0].split(",");
				amigo2= amigo[1].split(",");
				int persona1 = Integer.parseInt(amigo[0]);
				p2 = new Persona(amigo2[0],amigo2[1],amigo2[2].charAt(0),cadenaToFecha(amigo2[3]),g.personas.size()+1);
				
				//Mejorar los metodos addAmistad
				g.obtenPersona(persona1).addAmistad(new Amistad(g.obtenPersona(persona1),g.obtenPersona(p2)));
				g.obtenPersona(p2).addAmistad(new Amistad(g.obtenPersona(p2),g.obtenPersona(persona1)));
				return;
			}
			
			//amigo = cadena.split("\\s*amigo\\s*");
			amigo1= amigo[0].split(",");
			amigo2= amigo[1].split(",");
			p1 = new Persona(amigo1[0],amigo1[1],amigo1[2].charAt(0),cadenaToFecha(amigo1[3]),g.personas.size()+1);
			p2 = new Persona(amigo2[0],amigo2[1],amigo2[2].charAt(0),cadenaToFecha(amigo2[3]),g.personas.size()+1);
			g.obtenPersona(p1).addAmistad(new Amistad(g.obtenPersona(p1),g.obtenPersona(p2)));
			g.obtenPersona(p2).addAmistad(new Amistad(g.obtenPersona(p2),g.obtenPersona(p1)));
	
			
			return;
			
		}
		if(cadena.contains("eliminar")){
			//aux = m.group().replaceAll("(,)", " ");
			amigo = cadena.split("\\s*eliminar\\s*");
			if(isInteger(amigo[0])&&isInteger(amigo[1])) {
				int persona1 = Integer.parseInt(amigo[0]);
				int persona2 = Integer.parseInt(amigo[1]);
				g.obtenPersona(persona1).eliminarAmistad(persona2);
				g.obtenPersona(persona2).eliminarAmistad(persona1);
				return;
			}
			if(isInteger(amigo[1])) {
				amigo1= amigo[0].split(",");
				amigo2= amigo[1].split(",");
				p1 = new Persona(amigo1[0],amigo1[1],amigo1[2].charAt(0),cadenaToFecha(amigo1[3]),g.personas.size()+1);
				int persona2 = Integer.parseInt(amigo[1]);
				//Mejorar los metodos addAmistad
				g.obtenPersona(p1).eliminarAmistad(persona2);
				g.obtenPersona(persona2).eliminarAmistad(p1);
				return;
			}
			if(isInteger(amigo[0])) {
				amigo1= amigo[0].split(",");
				amigo2= amigo[1].split(",");
				int persona1 = Integer.parseInt(amigo[0]);
				p2 = new Persona(amigo2[0],amigo2[1],amigo2[2].charAt(0),cadenaToFecha(amigo2[3]),g.personas.size()+1);
				
				//Mejorar los metodos addAmistad
				g.obtenPersona(persona1).eliminarAmistad(p2);//.addAmistad(new Amistad(g.obtenPersona(persona1),g.obtenPersona(p2)));
				g.obtenPersona(p2).eliminarAmistad(persona1);//.addAmistad(new Amistad(g.obtenPersona(p2),g.obtenPersona(persona1)));
				return;
			}
			//amigo = cadena.split("\\s*amigo\\s*");
			amigo1= amigo[0].split(",");
			amigo2= amigo[1].split(",");
			p1 = new Persona(amigo1[0],amigo1[1],amigo1[2].charAt(0),cadenaToFecha(amigo1[3]),g.personas.size()+1);
			p2 = new Persona(amigo2[0],amigo2[1],amigo2[2].charAt(0),cadenaToFecha(amigo2[3]),g.personas.size()+1);
			g.obtenPersona(p1).eliminarAmistad(p2);
			g.obtenPersona(p2).eliminarAmistad(p1);
			//persona = new Persona(amigo[0],amigo[1],amigo[2].charAt(0),cadenaToFecha(amigo[3]),g.personas.size()+1);
			//g.agregaPersona(persona);
			
			return;
			
		}
		
		
	
		}
		
		
		
		
		
		//Si la cadena no satisface a la expresion regular, el metodo termina, permitiendo una nueva ejecucion con la siguiente cadena.
		 System.out.println("Linea Invalida");
		 g.ignorados.add(cadena);
		return;
	}
	
	
}
