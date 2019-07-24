package IO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.LevyDistribution;

import Huevo.*;
import Data.*;

public class cuckooSearch{
	private static ArrayList<nido> poblacion = new ArrayList<nido>();
	private static ArrayList<comuna> comunas = new ArrayList<comuna>();
	private static int cantHuevos;
	private static int cantNidos;
	
	public static void main (String arg[]) throws IOException, InvalidFormatException {		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Cuantos nidos usara en esta instancia: ");
		cantNidos=Integer.parseInt(teclado.nextLine());
		
		System.out.println("Cuantos huevos habra por nido");
		cantHuevos=Integer.parseInt(teclado.nextLine());
		
		cargarComunas();
		generarNidos();
		poblar();
		filtrarNidos(poblacion);
		
		return;
	}
	
	public static void cargarComunas() throws InvalidFormatException, IOException {
		excelRead data=new excelRead("Comunas.xls");
		comunas = data.getResults();
		
		
	}
	
	public static void generarNidos() throws InvalidFormatException, IOException{//se empiezan a generar los nidos con las soluciones
		for(int i=0;i<cantNidos;i++) {
			nido nest=new nido(cantHuevos);
			poblacion.add(nest);
		}
	}
	
	public static void poblar() {//genera un X numero de huevos y los va ingresando en un nido cuya posicion viene dada por Levy flight
		huevo egg = new huevo(comunas);
		for(int i=0;i<30000;i++) {
			egg.setSolucion();//falta mejorar
			int posicion=levyFligthPos();
			
			setHuevo(posicion,egg);
			
		}
	}
	
	public static void setHuevo (int i,huevo nuevo) {//se le entrega el huevo y la posicion del nido donde colocar dicho huevo.
		nido nest=poblacion.get(i);//se saca el nido en posicion "i"
		nest.setHuevo(nuevo);//si es una mejor solucion el huevo se agrega al nido
		
		poblacion.add(i,nest);//metemos el nido de vuelta en la misma posicion.
		
	}
	
	
	public static void deleteNido() {
		double max=0;//aqui se guardara el valor de el nido con mayor coste hasta el momento.
		double valor;;//valor del nido actual para comparar con el maximo
		
		int index=0;//Index's que se utilizaran para identificar la posicion del nido que sera eliminado al terminar las iteraciones
		
		nido nest;//nido auxiliar
		
		for(int i=0 ; i < cantNidos;i++) {
			
			nest=poblacion.get(i);
			valor = nest.getValorNido();//se saca el valor de el nido actual.
			
			if(valor>0) {//se ve si el nido efectivamente tiene soluciones (osea que su valor sea mayor a 0)
				
				if(max<valor) {
					
					index=i;//si el valor del nido actual es mayor al maximo previamente encontrado, entonces se guarda su index y se actualiza el maximo
					max=valor;
					
				}
				
			}else {
				
				poblacion.remove(i);//si el valor es 0 entonces se remueve el nido automaticamente
			
			}
			
		}
		
		nest= poblacion.get(index);
		
		System.out.println(nest.getValorNido());
		
		poblacion.remove(index);//al final de las iteraciones el nido con posicion " index" es removido de el Array.
		
	}
	
	public static void filtrarNidos(ArrayList<nido> pob) throws InvalidFormatException, IOException {// Filtra los nidos eliminando en cada iteracion el nido con mayor costo.
		
		nido nest=new nido();
		
		for(int i=0;i <(cantNidos-1);i++) {//se elimina el nido con mayor coste en cada iteracion hasta que solo queda 1 nido que contiene las soluciones mas optimas
			deleteNido();
		}
		nest = pob.get(0);//se consigue ese nido
		
		filtrarSoluciones(nest);
		
	}
	
	public static void filtrarSoluciones(nido optimo) {//de el ultimo nido obtenido de el cuckooSearch se obtiene el huevo con la aptitud mas alta y se imprime por pantalla
		huevo f = optimo.getBest();
		ArrayList<comuna> aux=f.getList();
		Iterator<comuna> i=aux.iterator();
		comuna actual=new comuna();
		while(i.hasNext()) {
			actual=i.next();
			if(actual.getAntena()>=1) {
			System.out.println(actual.getId()+"  "+ actual.getNombre());
			}
		}
		System.out.println("Coste en miles de dolares: " + actual.getCoste());
	}
	
	public void printf() {
	}
	
	public void relacionar() {
 	}
	
	public static int levyFligthPos() {
		double mu=0,c=3;
		LevyDistribution main =new LevyDistribution(mu, c); 
		int pos =(int) main.getLocation();
		return pos; 
	}
	

	
	
	
}
