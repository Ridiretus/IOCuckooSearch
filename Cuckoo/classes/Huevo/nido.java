package Huevo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
public class nido{
	private ArrayList<huevo> soluciones= new ArrayList<huevo>();
	private int maxHuevos=0;
	private double valorNido=0;
	
	
	public nido() throws InvalidFormatException, IOException {
	}
	
	public nido (int max) {
		maxHuevos=max;
	}
	
	
public void setHuevo(huevo aux) {
		
		if(nidoLleno()) {
			int index=getMaxI();//consigue la posicion del huevo con mayor coste en el nido
			huevo eggMax=soluciones.get(index);
			double valorB=eggMax.getCoste();
			
			double valorA=aux.getCoste();//consigue el valor del huevo que quiere ingresar el nido
			
			if(valorA<valorB) {
				soluciones.set(index, aux);
			}
		}else {
			soluciones.add(aux);
		}
		
	}
	
	public void setHuevoPrueba(huevo aux) {//intenta ingresar un huevo nuevo al nido, si este esta lleno se elimina el huevo con mayor coste (siendo este parte del nido o el nuevo huevo)
		
		if(nidoLleno()) {
			int index=getMaxI();//consigue la posicion del huevo con mayor coste en el nido
			huevo eggMax=soluciones.get(index);
			double valorB=eggMax.getCoste();
			
			double valorA=aux.getCoste();//consigue el valor del huevo que quiere ingresar el nido
			
			if(valorA<valorB) {
				soluciones.set(index, aux);
			}
		}else {
			soluciones.add(aux);
		}
		
	}
	
	public int getMaxI() {//retorna el indice de la solucion con el mayor valor y por tanto la menos optima
		int i,j=0;
		double max=0;
		
		for( i=0;i<5;i++) {
			huevo nuevo = soluciones.get(i);
			if(nuevo.getCoste()>max) {
				max = nuevo.getCoste();
				j = i;
			}
		}
		return j;
	}
	
	public void setValorNido() {//consigue el coste total de una solucion
		double suma=0;
		
		for(int i=0;i<maxHuevos;i++) {
			huevo aux =soluciones.get(i);
			suma=suma+aux.getCoste();
		}
		valorNido=suma;
	}
	
	public double getValorNido() {
		return valorNido;
	}
	
	
	public boolean nidoLleno() {//retorna true si esta lleno y false si no
		boolean loEsta=true;
		if(soluciones.size()< maxHuevos) {
			loEsta=false;
		}
		
		return loEsta;
	}
	
	public ArrayList<huevo> getHuevos(){
		return soluciones;
	}
	
	public huevo getHuevo(int i) {
		return soluciones.get(i);
	}
	public huevo getBest() {
		Iterator<huevo> i=soluciones.iterator();
		huevo fin=new huevo();
		double min=0;
		int h=0,x=0;
		
		while(i.hasNext()) {
			h++;
			fin=i.next();
			if(min>fin.getCoste()) {
				min=fin.getCoste();
				x=h;		
			}
		}
		
		fin=soluciones.get(x);
		return fin;
	}
	
	public int getCantSol() {
		return soluciones.size();
	}
	
}
