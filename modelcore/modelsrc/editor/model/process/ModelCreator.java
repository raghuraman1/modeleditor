package editor.model.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.namespace.QName;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import editor.model.xsd.jaxb.Model;

public class ModelCreator {
	public void createModel(File xsdDir, File modelXmlFile, Model model)
			throws SAXException, FileNotFoundException, IOException, JAXBException, PropertyException {
		URL resource=new SchemaExtractor().extractSchemaFile(xsdDir);

		JAXBContext jaxbContext = JAXBContext.newInstance(Model.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.diagrams.editor.org/schema ../xsd/schema.xsd ");
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
	    Schema schema = sf.newSchema(resource);
		jaxbMarshaller.setSchema(schema);
		jaxbMarshaller.setEventHandler(new MyValidationEventHandler()); 
		JAXBElement jaxbElement = new JAXBElement(
				  new QName("http://www.diagrams.editor.org/schema","model"),Model.class, model);
		
		jaxbMarshaller.marshal( jaxbElement, modelXmlFile);
	}

}
