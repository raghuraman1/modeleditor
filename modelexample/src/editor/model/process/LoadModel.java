package editor.model.process;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

public class LoadModel {
	
	
	public static void main(String[] args) throws JAXBException, SAXException, IOException 
	{
		
		new ModelLoader().buildAndRenderModel(new File("xml/sample.xml"), new File("output/sample.png"), new File("xsd"));
		
	}

	
}
