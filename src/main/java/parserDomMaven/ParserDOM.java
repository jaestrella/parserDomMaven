/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserdommaven;

import java.io.File;
import java.util.Scanner;
import javax.xml.transform.stream.StreamSource;
import net.sf.saxon.s9api.*;
/**
 *
 * @author matinal
 */
public class ParserDOM
{
    
   
    public static void main( String[] args )
    {
        System.out.println("Nombre del archivo de la hoja de transformacion");
        Scanner scanner = new Scanner(System.in);
        String hoja =args[1];
        System.out.println("Nombre del archivo del xml");
        Scanner scanner2 = new Scanner(System.in);
        String xml =args[0];
        System.out.println("Nombre del archivo de salida html");
        Scanner scanner3 = new Scanner(System.in);
        String html =args[2];
    
    	if (args.length==3) {
	    	try {
	    		// from S9APIExamples.java (S9HE)
		    	Processor proc = new Processor(false);
		        XsltCompiler comp = proc.newXsltCompiler();
		        System.out.println("Cargando hoja de estilo: " + hoja);
		        XsltExecutable exp = comp.compile(new StreamSource(new File(hoja)));
		        System.out.println("Cargando XML: "+xml);
		        XdmNode source = proc.newDocumentBuilder().build(new StreamSource(new File(xml)));
		        Serializer out = proc.newSerializer();
		        out.setOutputProperty(Serializer.Property.METHOD, "html");
		        out.setOutputProperty(Serializer.Property.INDENT, "yes");
		        out.setOutputFile(new File(html));
		        XsltTransformer trans = exp.load();
		        trans.setInitialContextNode(source);
		        trans.setDestination(out);
		        trans.transform();
		        System.out.println("\n¡¡¡¡EXITO!!! Output written to "+html+"\n\n");
	    	} catch (Exception ex) {
	    		System.out.println("Error:: "+ex.getMessage());
	    	}	        
    	}
    }
}
    
