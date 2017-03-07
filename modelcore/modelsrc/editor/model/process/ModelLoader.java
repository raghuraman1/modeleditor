package editor.model.process;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import editor.model.ColumnGroupVo;
import editor.model.ColumnVo;
import editor.model.EntityVo;
import editor.model.ModelException;
import editor.model.ModelVo;
import editor.model.join.ArrowHead;
import editor.model.join.BaseJoinVo;
import editor.model.join.ColumnColumnJoinVo;
import editor.model.join.ColumnTitleJoinVo;
import editor.model.join.GroupGroupJoinVo;
import editor.model.join.LineType;
import editor.model.join.TitleColumnJoinVo;
import editor.model.renderer.ModelRenderer;
import editor.model.xsd.jaxb.BaseJoin;
import editor.model.xsd.jaxb.Column;
import editor.model.xsd.jaxb.ColumnColumnJoin;
import editor.model.xsd.jaxb.ColumnGroup;
import editor.model.xsd.jaxb.ColumnTitleJoin;
import editor.model.xsd.jaxb.Coordinates;
import editor.model.xsd.jaxb.Entity;
import editor.model.xsd.jaxb.GroupGroupJoin;
import editor.model.xsd.jaxb.Model;
import editor.model.xsd.jaxb.TitleColumnJoin;

public class ModelLoader {
	public  void buildAndRenderModel(File modelFile, File modelCoordsFile, File target,  File extractXsdToDirectory) throws SAXException, JAXBException, IOException {
		ModelLoader loadJaxb = new ModelLoader();
		ModelVo modelVo = loadJaxb.buildModel(modelFile, modelCoordsFile, extractXsdToDirectory);
		new ModelRenderer().renderToFile(modelVo, target);;
	}
	
	public  BufferedImage buildAndRenderModel(InputStream fileInputStream, InputStream coordsFileInputStream, File extractXsdToDirectory) throws SAXException, JAXBException, IOException {
		ModelLoader loadJaxb = new ModelLoader();
		ModelVo modelVo = loadJaxb.buildModel(fileInputStream, coordsFileInputStream, extractXsdToDirectory);
		return new ModelRenderer().render(modelVo);
	}
	
	
	
	
	


	public  ModelVo buildModel(File file, File modelCoordsFile,File extractXsdToDirectory) throws JAXBException, SAXException, IOException {
		 ModelVo modelVo=null;
		 try( FileInputStream fis= new FileInputStream(file);FileInputStream coordsFis= modelCoordsFile!=null?new FileInputStream(modelCoordsFile):null;)
		{
			modelVo=buildModel(fis, coordsFis, extractXsdToDirectory);
		}
		 return modelVo;
	 }
	public  ModelVo buildModel(InputStream fileInputStream, InputStream coordsFileInputStream, File extractXsdToDirectory) throws JAXBException, SAXException, IOException {
		Properties cordProps=null;
		if(coordsFileInputStream!=null)
		{
			cordProps=new Properties();
			cordProps.load(coordsFileInputStream);
		}
		String packageName = Model.class.getPackage().getName();
		JAXBContext jc = JAXBContext.newInstance( packageName );
		Unmarshaller u = jc.createUnmarshaller();
		URL resource=new SchemaExtractor().extractSchemaFile(extractXsdToDirectory);
		//the above attemots to extract the xsd is only a useful optional feature
		//dont careif above crashes or not
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
        Schema schema = sf.newSchema(resource);
		u.setSchema(schema);
		u.setEventHandler(new MyValidationEventHandler());
		JAXBElement loaded = (JAXBElement) u.unmarshal(fileInputStream);
		Model model = (Model) loaded.getValue();
		List<Entity> entities = model.getEntity();
		EntityVo[] entityVos= new EntityVo[entities.size()];
		ModelVo modelVo= new ModelVo();
		
		for (int enttyIndex = 0; enttyIndex < entityVos.length; enttyIndex++) 
		{
			Entity entity=entities.get(enttyIndex);
		
			EntityVo entityVo= new EntityVo();
			entityVos[enttyIndex]=entityVo;
			String fullyQualifiedName = entity.getFullyQualifiedName();
			entityVo.setFullyQualifiedName(fullyQualifiedName);
			
			String[] icons=new String[entity.getIcon().size()];
			entity.getIcon().toArray(icons);
			entityVo.setIcons(icons);
			Coordinates coordinates = entity.getCoordinates();
			if(coordinates!=null)
			{
				try
				{
				boolean set=setCoordinatesFromProps(cordProps, entityVo, fullyQualifiedName);
				if(!set)
				{
					entityVo.setX(coordinates.getX());
					entityVo.setY(coordinates.getY());
				}
				}
				catch(Exception e)
				{
					throw new ModelException(e);
				}
				
			}
			else
			{
				throw new RuntimeException(" is this valid");
			}
			entityVo.setParent(modelVo);
			List<ColumnGroup> columnGroups = entity.getColumnGroup();
			ColumnGroupVo[] columnGroupVos= new ColumnGroupVo[columnGroups.size()];
			
			for (int i = 0; i < columnGroupVos.length; i++) 
			{
				ColumnGroup columnGroup = columnGroups.get(i);
				ColumnGroupVo columnGroupVo= new ColumnGroupVo();
				columnGroupVos[i]=columnGroupVo;
				columnGroupVo.setName(columnGroup.getName());
				columnGroupVo.setParent(entityVo);
				List<Column> columns = columnGroup.getColumn();
				ColumnVo[] columnVos= new ColumnVo[columns.size()];
				
				
				for (int j = 0; j < columnVos.length; j++) 
				{
					Column column = columns.get(j);
					ColumnVo columnVo = new ColumnVo();
					columnVos[j]= columnVo;
					columnVo.setName(column.getName());
					columnVo.setType(column.getType());
					columnVo.setVisibilty(column.getVisibility());
					columnVo.setParent(columnGroupVo);
					String[] columnVoIcons=new String[column.getIcon().size()];
					column.getIcon().toArray(columnVoIcons);
					
					columnVo.setIcons(columnVoIcons);
				}
				columnGroupVo.setColumns(columnVos);
			}
			entityVo.setColumnGroups(columnGroupVos);
			
			
			
	
		}
		modelVo.setEntities(entityVos);
		EntityVo[] entityVos2 = modelVo.getEntities();
		for (int i = 0; i < entityVos2.length; i++) 
		{
			EntityVo entityVo=entityVos2[i];
			Entity entity = entities.get(i);
			List<BaseJoin> joins = entity.getJoin();
			BaseJoinVo[] joinVos= new BaseJoinVo[joins.size()];
			for (int j = 0; j < joinVos.length; j++) 
			{
				BaseJoin join=joins.get(j);
				BaseJoinVo joinVo=null;
				if(join instanceof ColumnColumnJoin)
				{
					joinVo=new ColumnColumnJoinVo();
				}	
				else if(join instanceof ColumnTitleJoin)
				{
					joinVo=new ColumnTitleJoinVo();
				}
				else if(join instanceof TitleColumnJoin)
				{
					joinVo=new TitleColumnJoinVo();
				}
				else if(join instanceof GroupGroupJoin)
				{
					joinVo=new GroupGroupJoinVo();	
				}
				if(joinVo!=null)
				{
					joinVo.setHowMany(join.getHowMany());
					joinVo.setParent(entityVo);
					joinVo.setTargetEntity(modelVo.getEntity(join.getTargetEntityFqn()));
					joinVo.setLineType(LineType.valueOf(join.getLineType()));
					joinVo.setArrowHead(ArrowHead.valueOf(join.getArrowHead()));
					joinVos[j]=joinVo;
					if(join instanceof ColumnColumnJoin)
					{
						ColumnColumnJoin actualJoin=(ColumnColumnJoin) join;
						ColumnColumnJoinVo actualJoinVo=(ColumnColumnJoinVo) joinVo;
						actualJoinVo.setParentEntitisColumn(entityVo.getColumnVo(actualJoin.getParentEntitisColumn()));
						actualJoinVo.setTargetEntitisColumn(actualJoinVo.getTargetEntity().getColumnVo(actualJoin.getTargetEntitisColumn()));
					}	
					else if(join instanceof ColumnTitleJoin)
					{
						ColumnTitleJoin actualJoin=(ColumnTitleJoin) join;
						ColumnTitleJoinVo actualJoinVo=(ColumnTitleJoinVo) joinVo;
						actualJoinVo.setParentEntitisColumn(entityVo.getColumnVo(actualJoin.getParentEntitisColumn()));
					}
					else if(join instanceof TitleColumnJoin)
					{
						TitleColumnJoin actualJoin=(TitleColumnJoin) join;
						TitleColumnJoinVo actualJoinVo=(TitleColumnJoinVo) joinVo;
						actualJoinVo.setTargetEntitisColumn(actualJoinVo.getTargetEntity().getColumnVo(actualJoin.getTargetEntitisColumn()));
					}
					else if(join instanceof GroupGroupJoin)
					{
						GroupGroupJoin actualJoin=(GroupGroupJoin) join;
						GroupGroupJoinVo actualJoinVo=(GroupGroupJoinVo) joinVo;
						actualJoinVo.setParentEntitisColumnGroup(entityVo.getColumnGroupVo(((GroupGroupJoin) join).getParentEntitisColumnGroup()));
						actualJoinVo.setTargetEntitisColumnGroup(actualJoinVo.getTargetEntity().getColumnGroupVo(((GroupGroupJoin) join).getTargetEntitisColumnGroup()));
					}
				}
				
			}
			entityVo.setJoins(joinVos);
			
			
			
		}
		return modelVo;
	}

	private boolean setCoordinatesFromProps(Properties cordProps, EntityVo entityVo, String fullyQualifiedName) {
		boolean set=false;
		if(cordProps!=null)
		{
			String xyPair = cordProps.getProperty(fullyQualifiedName);
			if(xyPair!=null)
			{
				xyPair=xyPair.trim();
				String[] arr = xyPair.split(",");
				if(arr.length==2)
				{
					int x=Integer.parseInt(arr[0]);
					int y=Integer.parseInt(arr[1]);
					entityVo.setX(x);
					entityVo.setY(y);
					set=true;
				}
			}
		}
		return set;
	}

}
