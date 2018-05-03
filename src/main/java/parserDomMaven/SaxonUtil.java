/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserDomMaven;

import java.io.File;
import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.WhitespaceStrippingPolicy;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmValue;

/**
 * Clase auxiliar con los metodos necesarios
 * para correr expresiones xPath contra ficheros de texto XML
 * @author matinal
 */
public class SaxonUtil {
    String expresionXpath;//expresion como texto xpath
    String fichero;//nombre del fichero completo
    Processor proc;
    DocumentBuilder builder;//factoria para procesar expresiones xpath o cargar xml
    XPathCompiler xpc;//compilador de expresiones xpath
    XdmNode domDoc;//arbol dom en memoria resultado de cargar el ficehro xml
    
    /**
     * Crea un objeto de tipo saxonutil pero sin cargar ningun fichero xml
     */
    public SaxonUtil() {
       this.cargarFactoria();
    }
    
    /**
     * 
     */
    private void cargarFactoria(){
        this.proc = new Processor(false);
        this.builder = proc.newDocumentBuilder();
        this.builder.setLineNumbering(true);
        this.builder.setWhitespaceStrippingPolicy(WhitespaceStrippingPolicy.ALL);
        this.xpc = proc.newXPathCompiler();
    }
    
    /**
     * Asigna el fichero xml que queremos procesar y lo carga en memoria
     * @param fichero ruta al fichero a cargar en memoria
     * @throws net.sf.saxon.s9api.SaxonApiException excepcion problemas con el fichero
     */
    public void setFichero(String fichero) throws SaxonApiException{
        this.fichero=fichero;
        this.domDoc=this.builder.build(new File(fichero));
    }
    
    /**
     * Crea objeto saxonutil y carga el archivo que se le pasa como parametro 
     * en memoria ram
     * @param fichero 
     */
    public SaxonUtil(String fichero) throws SaxonApiException {
        this.cargarFactoria();
        this.setFichero(fichero);       
    }
    
    public XdmValue runXPath(String expresion) throws SaxonApiException{
        XdmValue resultado=null;
        XPathSelector xps=xpc.compile(expresion).load();
        xps.setContextItem(this.domDoc);
        resultado=xps.evaluate();
        
        return resultado;
    }
    
    public String xdm2string(XdmValue nodo){
        String resultado="";
        
        for (XdmItem item: nodo) {
           resultado+=item.getStringValue()+"\n";
        }
        
        return resultado;
    }
    
}
