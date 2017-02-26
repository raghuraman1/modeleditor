package editor.model;

import java.io.Serializable;

public class ColumnGroupVo implements Serializable{
	
	ColumnVo[] columns;
	
	EntityVo parent;
	String name;
	
	public EntityVo getParent() {
		return parent;
	}
	public void setParent(EntityVo parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ColumnVo[] getColumns() {
		return columns;
	}
	public void setColumns(ColumnVo[] columns) {
		this.columns = columns;
		for (ColumnVo columnVo : columns) 
		{
			this.getParent().addColumn(columnVo.getName(), columnVo);
		}
		
	}

}
