package Data;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class excelRead {
	private ArrayList<comuna> resultado= new ArrayList<comuna>();
	public excelRead(String nombre) throws InvalidFormatException, IOException {
		resultado=read(nombre);
	}
    public ArrayList<comuna> read(String name)throws IOException, InvalidFormatException{
    	boolean idcoste = true;
    	boolean aux = true;
    	ArrayList<comuna>  comunas = new ArrayList<comuna>();
    	comuna comunaActual = new comuna();
    	
    	File data=new File(name);
        Workbook workbook = WorkbookFactory.create(data);

        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        
        while(rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            
            while (cellIterator.hasNext()) {
            	Cell cell = cellIterator.next();
            	if( cell.getCellTypeEnum() == CellType.STRING) {
            		if(aux == true) {
            			comunaActual.setNombre(cell.getStringCellValue());
            			aux = false;
                		
            		}else {
            			String value=cell.getStringCellValue();
            			comunaActual.setVecinos(getVecinos(value));
            			aux = true;
            		}
            	}else {
            		if(idcoste == true) {
            			if((int)cell.getNumericCellValue()!=0) {
            				comunaActual.setId((int)cell.getNumericCellValue());
                			
            			}else {
            				return comunas;
            				
            			}
            			idcoste = false;
            			
            			
            		}else {
            			comunaActual.setCoste((int)cell.getNumericCellValue());
            			idcoste = true;
            			
            		}
            	}
            	if((aux==true) && (idcoste==true)) {
            		comunas.add(comunaActual);
            		comunaActual =new comuna();
            	}
            }
        }
        return comunas;
    }
    
    
    public static int[] getVecinos(String cell) {
    	String value=cell;
    	String[] valores=value.split(",");
    	int[] fin= new int[valores.length];
    	
    	for(int i=0;i<valores.length;i++) {
    		fin[i] = Integer.parseInt(valores[i]);
    	}
    	return fin;
    }
    
    public ArrayList<comuna> getResults(){
    	return resultado;
    }
    
    public void print(ArrayList<comuna>aux) {
    	for(int i=0;i<aux.size();i++) {
    		comuna nuevo=aux.get(i);
    		nuevo.getId();
    	}
    }
}

