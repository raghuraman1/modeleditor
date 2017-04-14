package editor.model.renderer;

import editor.model.ColumnGroupVo;
import editor.model.ColumnVo;
import editor.model.EntityVo;
import editor.model.BaseEntityVo;

public class EntityAssistant {
	static final int  fontSize=Values.instance.getFontSize();
	static final int  margin=Values.instance.getMargin();
	Rectangle getRectangleEstimate(BaseEntityVo entityVo1)
	{
		int width=10;
		int height=10;
		
		
		WidthHeight widthHeight=calculateWidthHeight(entityVo1.getLabel().length());
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
		}
		else
		{
			 height=(height*2)-margin*2;
		}
		
		 width+=margin*3;
		Rectangle rectangle= new Rectangle();
		rectangle.setX(entityVo1.getX());
		rectangle.setY(entityVo1.getY());
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
