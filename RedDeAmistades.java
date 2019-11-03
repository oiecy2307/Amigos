package Amigos;

import java.util.ArrayList;

public class RedDeAmistades {
	public ArrayList<Persona> personas = new ArrayList<Persona>();
	public ArrayList<String> ignorados = new ArrayList<String>();

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	/*
	 * El metodo existeNodo retorna verdadero en caso de que la cadena que se recibe
	 * como parametro coindida con el nombre de alguna ciudad o nombre de torre
	 * dentro de los nodos contenidos en el grafo, y regresa falso si no existe.
	 */
	public boolean existePersona(Persona p) {

		for (int i = 0; i < this.personas.size(); i++) {
			if (this.personas.get(i).equals(p)) {

				return true;
			}

		}
		return false;
	}



	public boolean existePersona(int p) {

		for (int i = 0; i < this.personas.size(); i++) {
			if (this.personas.get(i).pos == p) {

				return true;
			}

		}
		return false;
	}

	/*
	 * El metodo buscarCamino recibe dos objetos CiudadNodo, uno indica el origen y
	 * el otro indica el destino si ambos nodos existen, se ejecuta el metodo
	 * trazarCamino, que devuelve un boolean, si se encuentra una ruta o camino
	 * devuelve verdadero, caso contrario devuelve falso.
	 * 
	 * Dicho boolean asignado a una variable que dependiendo su valor determina la
	 * impresion en consola, si es verdadero imprime la ruta desde el origen hasta
	 * el destino, caso contrario imprime que no hay ruta para el origen y el
	 * destino en cuestion.
	 * 
	 */
	public void buscarCamino(Persona source, int target) {
		String aux = "";
		ArrayList<String> visit = new ArrayList<String>();
		ArrayList<Persona> nivelamigos = new ArrayList<Persona>();

		boolean val = false;
		int nivel = 0;
		int nivelOri = 0;
		// int target = 3;

		if (this.existePersona(source))
			
			nivelamigos=trazarCamino(this.obtenPersona(source),  target);
		
		if (nivelamigos!=null) {
			for(Persona x : nivelamigos ) {
				aux = aux + x.nombre + " " + x.apellido+ ", ";
			}
			
			System.out.println(aux + "son amigos de nivel " + target + " de " + source.nombre);
		}else {
			System.out.println(source.nombre + " no tiene amigos de nivel " + target );
		}
		
		

	}

	public void buscarCamino(int source, int target) {
		String aux = "";
		ArrayList<String> visit = new ArrayList<String>();
		ArrayList<Persona> nivelamigos = new ArrayList<Persona>();
		boolean val = false;
		int nivel = 0;
		int nivelOri = 0;
		// int target = 3;

		if (this.existePersona(source))
        
			nivelamigos=trazarCamino(this.obtenPersona(source),  target);
		if (nivelamigos!=null ) {
			for(Persona x : nivelamigos ) {
				aux = aux + x.nombre + " " + x.apellido + ", ";
			}
			
			System.out.println(aux + "son amigos de nivel " + target + " de " + source);
		}else {
			System.out.println(source + " no tiene amigos de nivel " + target );
		}

	}

	/*
	 * private boolean trazarCamino(Persona source, Persona source1, int target,int
	 * nivel, ArrayList<Persona> amigosnivel, ArrayList<Persona> visitados) {
	 * 
	 * ArrayList<Amistad> adyacentes = obtenPersona(source).getAmistades(); for(int
	 * i=0; i<adyacentes.size();i++) {
	 * if(adyacentes.get(i).persona2.equals(source1)) { adyacentes.remove(i); }
	 * 
	 * }
	 * 
	 * if (this.esVisitado(source)) {
	 * 
	 * return false; }
	 * 
	 * this.visitados.add(source); nivel++;
	 * 
	 * 
	 * if (target==nivel && !adyacentes.isEmpty()) {
	 * 
	 * System.out.println("El objetivo " + target +" es igual a " + nivel); for (int
	 * j = 0; j<adyacentes.size();j++) {
	 * amigosnivel.add(adyacentes.get(j).persona2);
	 * 
	 * } return true; } for (int i = 0; i<adyacentes.size();i++) { source1 =
	 * adyacentes.get(i).persona1;
	 * 
	 * trazarCamino(adyacentes.get(i).persona2, source1 ,target, nivel, amigosnivel,
	 * this.visitados);
	 * 
	 * }
	 * 
	 * // visited.remove(visited.get(visited.size()-1)); nivel--; return false; }
	 */
	/*
	 * private boolean trazarCamino(Persona source, Persona source1, int target,int
	 * nivel, ArrayList<Persona> amigosnivel, ArrayList<Persona> visitados) {
	 * 
	 * ArrayList<Amistad> adyacentes = obtenPersona(source).getAmistades();
	 * 
	 * if (!this.esVisitado(source)) { visitados.add(source); } for(int i=0;
	 * i<adyacentes.size();i++) { //visitados.add(adyacentes.get(i).persona2);
	 * if(adyacentes.get(i).persona2.equals(source1)) { adyacentes.remove(i); }
	 * 
	 * } for (int i = 0; i<adyacentes.size();i++) {
	 * 
	 * if (!this.esVisitado(adyacentes.get(i).persona2)) {
	 * visitados.add(adyacentes.get(i).persona2); }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * if (target==nivel) {
	 * 
	 * //System.out.println("El objetivo " + target +" es igual a " + nivel); //for
	 * (int j = 0; j<adyacentes.size();j++) { amigosnivel.add(source); return true;
	 * //visitados.add(adyacentes.get(j).persona2); }
	 * 
	 * 
	 * 
	 * //visitados.addAll(); for (int i = 0; i<adyacentes.size();i++) { source1 =
	 * adyacentes.get(i).persona1;
	 * 
	 * trazarCamino(adyacentes.get(i).persona2, source1 ,target, nivel+1,
	 * amigosnivel, this.visitados);
	 * 
	 * }
	 * 
	 * // visited.remove(visited.get(visited.size()-1)); nivel--; return false; }
	 */

	public ArrayList<Persona> trazarCamino(Persona source, int nivel) {
		ArrayList<Persona> nivelActual = new ArrayList<Persona>();
		ArrayList<Persona> nivelSig = new ArrayList<Persona>();
		ArrayList<Persona> visitados = new ArrayList<Persona>();
		int i=0;
		nivelActual.add(source);

		while (i < nivel) {// Procesar Nivel Actual , iteracion por nivel
			
			if (nivelActual.isEmpty())
				return null;

			else {
				for(Persona p : nivelActual ) {
					
					for(Amistad a : p.getAmistades()){
						
						if (!(visitados.contains(a.persona2) || nivelSig.contains(a.persona2) ||nivelActual.contains(a.persona2))) {
							nivelSig.add(a.persona2);
						}
					}
				}
			
				 for(Persona x : nivelActual ) {
					visitados.add(x);
				}
					// Añadir los del nivel actual a visitados
				
				nivelActual.clear();
				nivelActual.addAll(nivelSig);
				nivelSig.clear();
			}
		i++;
		}
		

		return nivelActual;
	}



	/*
	 * Este metodo busca una persona dentro del grafo en base al objeto persona, si lo
	 * encuentra retorna un objeto de tipo CiudadNodo, caso contrario retorna un
	 * nulo.
	 * 
	 */
	public Persona obtenPersona(Persona p) {
		Persona aux;

		for (int i = 0; i < this.personas.size(); i++) {
			if (this.personas.get(i).equals(p))
				return (this.personas.get(i));
		}

		return null;
	}

	public Persona obtenPersona(int posicion) {
		Persona aux;

		for (int i = 0; i < this.personas.size(); i++) {
			if (this.personas.get(i).getPosicion() == posicion)
				return (this.personas.get(i));
		}

		return null;
	}

	public boolean buscarAmistad(Persona p1, Persona p2) {
		if (existePersona(p1) && existePersona(p2)) {
			for (int i = 0; i < this.obtenPersona(p1).amistades.size(); i++) {
				if (this.obtenPersona(p1).amistades.get(i).persona2.equals(p2)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public boolean buscarAmistad(int p1, int p2) {
		if (existePersona(p1) && existePersona(p2)) {
			for (int i = 0; i < this.obtenPersona(p1).amistades.size(); i++) {
				if (this.obtenPersona(p1).amistades.get(i).persona2.pos == p2) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	/*
	 * El metodo agregaPersona evalua si la lista de nodos está vacia, de asi serlo le
	 * agrega el nodo que recibimos como parametro, sino, se busca entre los nodos
	 * una coincidencia en base al nombre de la torre, si existe el metodo termina,
	 * caso contrario el nodo es agregado a la lista de nodos.
	 */
	public void agregaPersona(Persona persona) {

		if (this.personas.isEmpty()) {

			this.personas.add(persona);

			return;
		}

		if (this.existePersona(persona)) {

			return;

		} else {

			this.personas.add(persona);

			return;

		}

	}

	/*
	 * El metodo trazarCamino comienza por instanciar un ArrayList de tipo Enlace
	 * que contendrá los nodos adyacentes o vecinos del objeto source que entra como
	 * parametro, se evalua si el objeto source de tipo CiudadNodo ya ha sido
	 * visitado, de ser asi el metodo retorna falso para terminar el programa, caso
	 * contrario agrega el nodo a la lista de visitados, despues de agregar el nodo
	 * se evalua si el nombre de la ciudad es igual al nombre de la ciudad del nodo
	 * destino, de ser asi retorna un verdadero, caso contrario se recorre la lista
	 * de adyacentes del objeto source, para ejecutar el metodo trazarCamino con un
	 * nuevo vecino, que en esta nueva ejecucion representará al objeto source.
	 * 
	 * Dado que ya no haya mas vecinos y no se encuentre un camino, se ira
	 * removiendo el ultimo visitado durante cada ejecucion.
	 */

	/*private boolean trazarCamino(Persona source, Persona origen, Persona destination, ArrayList<String> visited) {

		ArrayList<Amistad> adyacentes = obtenPersona(source).getAmistades();

		if (visited.contains(source.getNombre())) {

			return false;
		}

		visited.add(source.getNombre());

		if (source.getNombre().compareToIgnoreCase(destination.getNombre()) == 0) {

			return true;
		}
		for (int i = 0; i < adyacentes.size(); i++) {

			if (trazarCamino(adyacentes.get(i).getPersona2(), origen, destination, visited)) {

				return true;
			}

		}

		visited.remove(visited.get(visited.size() - 1));
		return false;
	}
*/
}
