
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserdommaven;

/**
 *
 * @author matinal
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


/**
 *
 * @author Administrador
 */
public class DomUtil {
    
    public static DocumentBuilder newBuilder(boolean validation)
        throws ParserConfigurationException {
        
            // Creamos la factor�a JAXP para los constructores de documentos
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            // Configuramos la factor�a
            domFactory.setValidating(validation);
            domFactory.setNamespaceAware(true);
            // Creamos el contructor de documentos (posteriormente se podr� usar 
            // para leer archivos XML o bien crearlos y almacenarlos o transmitirlos
            DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
            // Establecemos el manejador de errores (XML bien formado, v�lido...)
            // domBuilder.setErrorHandler(null);
            return domBuilder;
    }
    
    public static Document newDocument()
            throws ParserConfigurationException {
        
        DocumentBuilder domBuilder = newBuilder(false);

        Document document = domBuilder.newDocument();
        
        return document;
    }
    
    public static Document parse(String path, boolean validation) 
        throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder domBuilder = newBuilder(validation);
        
        Document document = domBuilder.parse(new File (path));
        
        return document;
    }
    
    /**
     * Guarda el documento XML que se le pasa como par�metro 
     * en un fichero de texto
     * @param doc el documento XML cargado en memoria (DOM)
     * @param file_path la path y el nombre de archivo donde lo guardamos
     * @throws TransformerException 
     */
    public static void save(Document doc, String file_path) 
            throws TransformerException{
        try {
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(file_path));
            
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            
            transformer.transform(source, result);
            
            System.out.println("File saved!");
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DomUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}