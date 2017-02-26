package editor.model.process;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import editor.model.process.ModelCreator;
import editor.model.xsd.jaxb.Column;
import editor.model.xsd.jaxb.ColumnGroup;
import editor.model.xsd.jaxb.Coordinates;
import editor.model.xsd.jaxb.Entity;
import editor.model.xsd.jaxb.Model;

public class CreateModel {
public static void main(String[] args) throws JAXBException, FileNotFoundException, SAXException, IOException {
	File xsdDir = new File("xsd");
	
	File modelXmlFile = new File("xml/sample1.xml");
	
	
	//jaxbMarshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());
	Model model= new Model();
	Entity entity= new Entity();
	model.getEntity().add(entity);
	entity.setFullyQualifiedName("package1/pacake2/Entity2");
	Coordinates coordinates = new Coordinates();
	coordinates.setX(10);
	coordinates.setY(10);
	entity.setCoordinates(coordinates);
	ColumnGroup columnGroup = new ColumnGroup();
	columnGroup.setName("@entityBase");
	Column column= new Column();
	column.setName("abc");
	columnGroup.getColumn().add(column);
	entity.getColumnGroup().add(columnGroup);
	
	new ModelCreator().createModel(xsdDir, modelXmlFile, model);
	
}

}
