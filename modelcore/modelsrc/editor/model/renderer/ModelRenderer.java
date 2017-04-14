package editor.model.renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import editor.model.ColumnGroupVo;
import editor.model.ColumnVo;
import editor.model.EntityVo;
import editor.model.BaseEntityVo;
import editor.model.ModelException;
import editor.model.ModelVo;
import editor.model.join.BaseJoinVo;

public class ModelRenderer {
	boolean showEstimated=false;
	static final int  fontSize=Values.instance.getFontSize();
	static final int  margin=Values.instance.getMargin();
	Map<String, String> imageFormats= loadFormats();
	private Graphics2D graphics;
	private Font boldFont;
	private Font plainFont;
	private HashMap<String, String> loadFormats() {
		HashMap<String, String> formats = new HashMap<String, String>();
		formats.put("png", "PNG");
		formats.put("jpeg", "JPEG");
		formats.put("jpg", "JPEG");
		formats.put("gif", "gif");
		formats.put("bmp", "BMP");
	
		return formats;
	}
	EntityAssistant entityAssistant = new EntityAssistant();
	public BufferedImage render(ModelVo modelVo)
	{
		int imageWidth=10;
		int imageHeight=10;
		BaseEntityVo[] entities = modelVo.getEntities();
		
		for (BaseEntityVo entityVo : entities) 
		{
			Rectangle rectangle = entityAssistant.getRectangleEstimate(entityVo);
			imageWidth=Math.max(imageWidth, rectangle.getX1());
			imageHeight=Math.max(imageHeight, rectangle.getY1());
		}
		imageWidth+=10;
		imageHeight+=10;
		 BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);

	      graphics = bi.createGraphics();
	      graphics.setBackground(Color.WHITE);
	      graphics.setColor(Color.BLACK);
	      boldFont = new Font("TimesRoman", Font.BOLD, fontSize);
	      plainFont = new Font("TimesRoman", Font.PLAIN, fontSize);
	    
	      for (BaseEntityVo entityVo : entities) 
			{
	    	  renderEntity(entityVo);
			}
	     
		return bi;
		
	}
	
	public void renderEntity(BaseEntityVo entityVo1)
	{
		int width=10;
		int height=10;
		Rectangle rectangleEstimate = entityAssistant.getRectangleEstimate(entityVo1);
		 graphics.setColor(Color.BLACK);
		 WidthHeight widthHeight = getActualWidthHeight(entityVo1.getLabel(), boldFont);
		 width=Math.max(width, widthHeight.getWidth());
		 height=Math.max(height, widthHeight.getHeight());
		 height+=margin*5;
		 
		if(entityVo1 instanceof EntityVo)
		{
			EntityVo entityVo=(EntityVo) entityVo1;
			ColumnGroupVo[] columnGroups = entityVo.getColumnGroups();
			 for (ColumnGroupVo columnGroupVo : columnGroups) 
			 {
				 if(columnGroups.length>1)
				 {
					 height++;//to draw the group line
				 }
				 ColumnVo[] columns = columnGroupVo.getColumns();
				 for (ColumnVo columnVo : columns) 
				 {
					 widthHeight = getActualWidthHeight(columnVo.getName(), plainFont);
					 width=Math.max(width, widthHeight.getWidth());
					 height+=widthHeight.getHeight();
					 height+=margin*2;
				 }
			 }
		}
		else
		{
			 height=(height*2)-margin*2;
			
		}
		 
		 width+=margin*6;
		// graphics.setColor(Color.BLUE);
		 
		 graphics.drawRect(entityVo1.getX(), entityVo1.getY(), width, height);
		
		 
		 widthHeight = getActualWidthHeight(entityVo1.getLabel(), boldFont);
		 graphics.setColor(Color.GRAY);
		 graphics.fillRect(entityVo1.getX()+1, entityVo1.getY()+1, /*widthHeight.getWidth()+margin*3*/ width-1, widthHeight.getHeight()+margin*5-1);
		 graphics.setColor(Color.BLACK);
		 //for now will use actual width later will replace with fullwidth
		 graphics.drawRect(entityVo1.getX(), entityVo1.getY(), /*widthHeight.getWidth()+margin*3*/ width, widthHeight.getHeight()+margin*5);
		 int y=entityVo1.getY()+widthHeight.getHeight()+margin*5;
		 graphics.drawString(entityVo1.getLabel(),entityVo1.getX()+margin*3, entityVo1.getY()+widthHeight.getHeight());
		if(entityVo1 instanceof EntityVo)
		{
			EntityVo entityVo=(EntityVo) entityVo1;
			ColumnGroupVo[] columnGroups = entityVo.getColumnGroups();
			for (ColumnGroupVo columnGroupVo : columnGroups) 
			 {
				
				 if(columnGroups.length>1)
				 {
					 y++;
					 graphics.drawLine(entityVo.getX(), y, entityVo.getX()+width, y);
				 }
				 y++;
				 ColumnVo[] columns = columnGroupVo.getColumns();
				 for (ColumnVo columnVo : columns) 
				 {
					 
					 widthHeight = getActualWidthHeight(columnVo.getName(), plainFont);
					 graphics.drawString(columnVo.getName(),entityVo.getX()+margin*3, y+widthHeight.getHeight()-margin*2);
					 y+=widthHeight.getHeight()+margin*2;
				 }
			 }
		}
		 
		 
		 BaseJoinVo[] joins = entityVo1.getJoins();
		 for (BaseJoinVo baseJoinVo : joins) 
		 {
			 //we will draw the join lines fromhere later
		 }
		 
		 if(showEstimated)
		 {
			 graphics.setColor(Color.RED);
			 graphics.drawRect(rectangleEstimate.getX(), rectangleEstimate.getY(), rectangleEstimate.getWidth(), rectangleEstimate.getHeight());
		 }
	}

	private WidthHeight getActualWidthHeight(String text, Font font) {
		graphics.setFont(font);
		 FontMetrics fontMetrics = graphics.getFontMetrics();
	      int stringWidth = fontMetrics.stringWidth(text);
	      int stringHeight = fontMetrics.getAscent();
	      WidthHeight widthHeight=new WidthHeight();
	      widthHeight.setWidth(stringWidth);
	      widthHeight.setHeight(stringHeight);
		return widthHeight;
	}
	public void renderToFile(ModelVo modelVo, File file) throws IOException
	{
		BufferedImage bi=render(modelVo);
		int index=file.getName().lastIndexOf(".");
		if(index!=-1 && file.getName().length()>(index+1))
		{
			String extn=file.getName().substring(index+1).toLowerCase();
			String format = imageFormats.get(extn);
			if(format!=null)
			{
				ImageIO.write(bi, format, file);
			}
			else
			{
				throw new ModelException(file.getAbsolutePath()+" not having extension that is either of "+imageFormats.keySet());
			}
			
		}
		else
		{
			throw new ModelException(file.getAbsolutePath()+" not having extension");
		}
	}

}
