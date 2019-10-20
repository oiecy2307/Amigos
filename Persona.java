
package Amigos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Persona {

	public String nombre;
	public String apellido;
	public char sexo;
	public Date fecha;
	public int pos;
	public ArrayList<Amistad> amistades = new ArrayList<Amistad>();
	
	public Persona(String nombre, String apellido, char sexo, Date fecha, int pos) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.sexo=sexo;
		this.fecha=fecha;
		this.pos=pos;
	}
	
	public String getNombre() {
        return nombre;
    }
	
	public int getPosicion() {
        return pos;
    }
	
 
 /* 
 El metodo removeEdge recorre todos los enlaces de un nodo en particular para eliminar un enlace en base a la ciudad 
 o torre destino que es recibida como parametro.
   */
    public void removeEdge(String destino) {
    	
    	for(int i = 0; i<this.amistades.size();i++) {
    		
            if (this.amistades.get(i).getPersona2().getNombre().compareToIgnoreCase(destino)==0) {
            	
                this.amistades.remove(i);
                
                return;
                
            }
            
        }
    	
    	
    }
 
    /*El metodo addAmistad evalua si la lista de amistades está vacia, de asi serlo le agrega la amistad que recibimos como parametro, 
     sino, se busca entre las personas una coincidencia en base al nombre de la misma, si existe el metodo termina, caso contrario
     la amistad es agregada a la lista de amistades.
     */
    public void addAmistad(Amistad amistad) {
    	
    	if(this.amistades.isEmpty()) {
    		
    		this.amistades.add(amistad);
    		
    		return;
    	}
    	
    	
    	for(int i = 0; i<this.amistades.size();i++) {
    		
            if (this.amistades.get(i).getPersona2().equals(amistad.getPersona2())) {
            	
            	System.out.println("Si existe el enlace con " + amistad.getPersona2().getNombre());
            	
            	return;
            
            }else {
            	
            	amistades.add(amistad);
    	return;
    	
            }
    	}
    }
 
    public ArrayList<Amistad> getAmistades() {
        return amistades;
    }
 
    private String cadenaToFecha(Date  fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
	            String date = formatter.format(fecha);
	            return date;      
	}
    
    /* 
    El metodo removeEdge recorre todos los enlaces de un nodo en particular para eliminar un enlace en base a la ciudad 
    o torre destino que es recibida como parametro.
      */
       public void eliminarAmistad(Persona destino) {
       	
       	for(int i = 0; i<this.amistades.size();i++) {
       		
               if (this.amistades.get(i).getPersona2().equals(destino)) {
               	
                   this.amistades.remove(i);
                   
                   return;
                   
               }
               
           }
       	
       	
       }
       
       public void eliminarAmistad(int destino) {
          	
          	for(int i = 0; i<this.amistades.size();i++) {
          		
                  if (this.amistades.get(i).getPersona2().pos==destino) {
                  	
                      this.amistades.remove(i);
                      
                      return;
                      
                  }
                  
              }
          	
          	
          }
    
       /*El metodo addEdge evalua si la lista de enlaces está vacia, de asi serlo le agrega el enlace que recibimos como parametro, 
        sino, se busca entre los destinos una coincidencia en base al nombre de la torre, si existe el metodo termina, caso contrario
        el enlace es agregado a la lista de enlaces.
        */
      
    
   public boolean equals(Object o) {
	  
	   Persona p = (Persona) o; 
     
       if(this.nombre.compareToIgnoreCase(p.nombre)==0 &&
    	  this.apellido.compareToIgnoreCase(p.apellido) ==0 &&
         String.valueOf(this.sexo).compareToIgnoreCase(String.valueOf(p.sexo)) == 0 &&
         cadenaToFecha(this.fecha).compareTo(cadenaToFecha(p.fecha))== 0) {
      
	   return true;
       }
       return false;
   }
	
}
