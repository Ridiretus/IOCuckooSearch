package Data;

public class comuna {
	private int id = 0;
	private double coste ;
	private String nombre = null;
	private int vecinos[];
	private int antena=0;
	
	public void setId(int i) {
		this.id = i;
		
	}
	
	public int getId() {
		return id;
	} 
	
	public void setCoste(double coste) {
		this.coste = coste;
	}
	
	public double getCoste() {
		return coste;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setVecinos(int [] aux) {
		vecinos = new int[aux.length];
		vecinos = aux;
	}
	
	public int[] getVecinos() {
		return vecinos;
	}
	public int getVecinosSize() {
		return vecinos.length;
	}
	public int getVecinoId(int i) {
		return vecinos[i];
	}
	
	public int getLength() {
		return vecinos.length;
	}
	public int getAntena() {
		return antena;
	}
	
	public void setAntena() {//marca la comuna para que se sepa que aqui se construira.
		antena=1;
	}
	
	public void print() {
		System.out.print(id);
	}
}
