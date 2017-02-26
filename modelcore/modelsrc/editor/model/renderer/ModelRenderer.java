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
		EntityVo[] entities = modelVo.getEntities();
		
		for (EntityVo entityVo : entities) 
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
	    
	      for (EntityVo entityVo : entities) 
			{
	    	  renderEntity(entityVo);
			}
	     
		return bi;
		
	}
	
	public void renderEntity(EntityVo entityVo)
	{
		int width=10;
		int height=10;
		Rectangle rectangleEstimate = entityAssistant.getRectangleEstimate(entityVo);
		 graphics.setColor(Color.BLACK);
		 WidthHeight widthHeight = getActualWidthHeight(entityVo.getLabel(), boldFont);
		 width=Math.max(width, widthHeight.getWidth());
		 height=Math.max(height, widthHeight.getHeight());
		 height+=margin*5;
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
		 width+=margin*6;
		// graphics.setColor(Color.BLUE);
		 
		 graphics.drawRect(entityVo.getX(), entityVo.getY(), width, height);
		
		 
		 widthHeight = getActualWidthHeight(entityVo.getLabel(), boldFont);
		 graphics.setColor(Color.GRAY);
		 graphics.fillRect(entityVo.getX()+1, entityVo.getY()+1, /*widthHeight.getWidth()+margin*3*/ width-1, widthHeight.getHeight()+margin*5-1);
		 graphics.setColor(Color.BLACK);
		 //for now will use actual width later will replace with fullwidth
		 graphics.drawRect(entityVo.getX(), entityVo.getY(), /*widthHeight.getWidth()+margin*3*/ width, widthHeight.getHeight()+margin*5);
		 int y=entityVo.getY()+widthHeight.getHeight()+margin*5;
		 graphics.drawString(entityVo.getLabel(),entityVo.getX()+margin*3, entityVo.getY()+widthHeight.getHeight());
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
		 
		 BaseJoinVo[] joins = entityVo.getJoins();
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
