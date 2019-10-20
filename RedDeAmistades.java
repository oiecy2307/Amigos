package Amigos;

import java.util.ArrayList;



public class RedDeAmistades {
 public ArrayList<Persona> personas = new ArrayList<Persona>();
 
 public ArrayList<Persona> getPersonas() {
     return personas;
 }
 
 /*
  * El metodo existeNodo retorna verdadero en caso de que la cadena que se recibe como parametro 
   coindida con el nombre de alguna ciudad o nombre de torre dentro de los nodos contenidos en el 
   grafo, y regresa falso si no existe.
   */
 public boolean existePersona(Persona p) {
 	
     for(int i = 0; i<this.personas.size();i++) {
         if (this.personas.get(i).equals(p)) {
             
         	return true;
         }
         
     }
     return false;
 }
 
 
/*
  El metodo buscarCamino recibe dos objetos CiudadNodo, uno indica el origen y el otro indica el destino
  si ambos nodos existen, se ejecuta el metodo trazarCamino, que devuelve un boolean, si se encuentra una 
  ruta o camino devuelve verdadero, caso contrario devuelve falso. 
  
  Dicho boolean asignado a una variable que dependiendo su valor determina la impresion en consola, si es verdadero 
  imprime la ruta desde el origen hasta el destino, caso contrario imprime que no hay ruta para el origen y el destino 
  en cuestion.
  
      */
 public boolean buscarCamino(Persona source, Persona destination) {
 	String aux = "";
 	ArrayList<String> visit = new ArrayList<String>();
 	boolean val = false ;
 	
     if (this.existePersona(source) && this.existePersona(destination)) 
     	
       val =   trazarCamino(source , source, destination, visit);
     
     //quitar comparacion
     if(val) {
     	
     	for(int i =0; i<visit.size(); i++) {
       	  aux = aux + " => " + visit.get(i).toString()   ;
       	
          }
     	
     	System.out.println("+ "  + aux);
     	return true;
     	
     }else{
     	
     	System.out.println("- " + source.getNombre()  +  " => " + destination.getNombre());
          return false;
     }
       
     
 }

 
 /*
  Este metodo busca un nodo dentro del grafo en base a la ciudad, si lo encuentra retorna un objeto
  de tipo CiudadNodo, caso contrario retorna un nulo.
  
  */
 public Persona obtenPersona(Persona p) {
 	Persona aux ;
 	
 	for(int i = 0; i<this.personas.size(); i ++) {
 		if(this.personas.get(i).equals(p))
 			return (this.personas.get(i));
 	}
 	
 	return null;
 }
 
 public Persona obtenPersona(int posicion) {
	 	Persona aux ;
	 	
	 	for(int i = 0; i<this.personas.size(); i ++) {
	 		if(this.personas.get(i).getPosicion()==posicion)
	 			return (this.personas.get(i));
	 	}
	 	
	 	return null;
	 }
 
 /*El metodo agregaNodo evalua si la lista de nodos está vacia, de asi serlo le agrega el nodo que recibimos como parametro, 
 sino, se busca entre los nodos una coincidencia en base al nombre de la torre, si existe el metodo termina, caso contrario
 el nodo es agregado a la lista de nodos.
 */
 public void agregaPersona(Persona persona) {
 	
 	if(this.personas.isEmpty()) {
 		
 		this.personas.add(persona);
 		
 		return;
 	}
 	
 	
 		if(this.existePersona(persona)) {
 			
 		return;
 		
 		} else {
 			
 		this.personas.add(persona);
 		
 		return;
 	
 		}
 	
 }
 
 
 /*
  El metodo trazarCamino comienza por instanciar un ArrayList de tipo Enlace que contendrá los nodos adyacentes o vecinos del 
  objeto source que entra como parametro, se evalua si el objeto source de tipo CiudadNodo ya ha sido visitado, de ser asi el 
  metodo retorna falso para terminar el programa, caso contrario agrega el nodo a la lista de visitados, despues de agregar el nodo
  se evalua si el nombre de la ciudad es igual al nombre de la ciudad del nodo destino, de ser asi retorna un verdadero, caso contrario
  se recorre la lista de adyacentes del objeto source, para ejecutar el metodo trazarCamino con un nuevo vecino, que en esta nueva ejecucion
  representará al objeto source.
  
  Dado que ya no haya mas vecinos y no se encuentre un camino, se ira removiendo el ultimo visitado durante cada ejecucion.  
  */
 
 private boolean trazarCamino(Persona source, Persona origen, Persona destination, ArrayList<String> visited ) {
 	
 	ArrayList<Amistad> adyacentes = obtenPersona(source).getAmistades();
 	
 	if (visited.contains(source.getNombre())) {
 		
         return false;
     }
 	
     visited.add(source.getNombre());
     
     if (source.getNombre().compareToIgnoreCase(destination.getNombre()) == 0) {
     	
         return true;
     }
     for (int i = 0; i<adyacentes.size();i++) {
     	
         if (trazarCamino(adyacentes.get(i).getPersona2(), origen, destination, visited)) {
         	
             return true;
         }
        
     }
     
     visited.remove(visited.get(visited.size()-1));
     return false;
 }
 
}
