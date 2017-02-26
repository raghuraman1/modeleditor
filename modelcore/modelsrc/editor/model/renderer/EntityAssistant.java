package editor.model.renderer;

import editor.model.ColumnGroupVo;
import editor.model.ColumnVo;
import editor.model.EntityVo;

public class EntityAssistant {
	static final int  fontSize=Values.instance.getFontSize();
	static final int  margin=Values.instance.getMargin();
	Rectangle getRectangleEstimate(EntityVo entityVo)
	{
		int width=10;
		int height=10;
		
		
		WidthHeight widthHeight=calculateWidthHeight(entityVo.getLabel().length());
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
			height++;
			ColumnVo[] columns = columnGroupVo.getColumns();
			for (ColumnVo columnVo : columns) 
			{
				
				widthHeight=calculateWidthHeight(columnVo.getName().length());
				width=Math.max(width, widthHeight.getWidth());
				height+=widthHeight.getHeight();
				height+=margin*2;
				height++;
			}
		}
		 width+=margin*3;
		Rectangle rectangle= new Rectangle();
		rectangle.setX(entityVo.getX());
		rectangle.setY(entityVo.getY());
		rectangle.setWidth(width);
		rectangle.setHeight(height);
		return rectangle;
	}
	
	public static WidthHeight calculateWidthHeight(int charWidth)
	{
		WidthHeight widthHeight= new WidthHeight();
		widthHeight.setHeight(fontSize);
		widthHeight.setWidth((fontSize*charWidth)/2);
		return widthHeight;
	}

}
