package editor.model.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import org.xml.sax.SAXException;

public class SchemaExtractor {
	
public URL extractSchemaFile(File extractXsdToDirectory) throws SAXException, FileNotFoundException, IOException {
		
		
		String schemaFileName="schema.xsd";
		URL resource = this.getClass().getResource("/"+schemaFileName);
		if(extractXsdToDirectory!=null )
		{
			if(!extractXsdToDirectory.exists())
			{
				boolean made = extractXsdToDirectory.mkdirs();
				if(!made)
				{
					System.err.println("could not create directory "+extractXsdToDirectory.getAbsolutePath());
				}
			}
			if(extractXsdToDirectory.exists() && extractXsdToDirectory.isDirectory())
			{
				File destination= new File(extractXsdToDirectory, schemaFileName);
				try(PrintWriter out= new PrintWriter(new FileWriter(destination));
						BufferedReader br= new BufferedReader(new InputStreamReader(resource.openStream())))
				{
					for (String line=br.readLine(); line!=null; line=br.readLine()) 
					{
						out.println(line);
						out.flush();
					}
				}
				
			}
			else
			{
				System.err.println("Path specified  "+extractXsdToDirectory.getAbsolutePath()+" is not a directory.So cannot extract schema file into that");
			}
			
			
		}
		return resource;
}

}
