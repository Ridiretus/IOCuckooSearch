package Huevo;
import java.util.ArrayList;
import java.util.Iterator;
import Data.comuna;


public class huevo {
	private ArrayList<comuna> egg = new ArrayList<comuna>();
	public huevo (ArrayList<comuna> comunas) {
		egg=comunas;
	}
	public huevo() {
	}
	
	public double getCoste() {//retorna el coste que tendria la instalacion si se aplicara esta solucion.
		Iterator <comuna> i=egg.iterator();
		comuna actual;
		double suma=0;
		while(i.hasNext()) {
			actual=i.next();
			suma=suma+actual.getCoste();
		}
		return suma;
		
	}
	public int getId(int i) {
		comuna aux=egg.get(i); 
		return aux.getId();
				
	}
	
	public void setAntena(int index) {//cambia la variable antena de la comuna en la posicion index del array por un 1 para denotar que aqui se construira.
		comuna aux=egg.get(index);
		aux.setAntena();
		egg.set(index, aux);
	}
	
	public void setList(ArrayList<comuna> aux) {
		egg=aux;
	}
	public ArrayList<comuna> getList(){
		return egg;
	}
	
	public void setSolucion() {
		
		
	}
	
	public String getNombre(int i) {
		comuna aux = egg.get(i);
		return aux.getNombre();
	}
	
	public comuna getComuna(int i) {
		return egg.get(i);
	}
	
	public int getSize() {
		return egg.size();
	}
	
}