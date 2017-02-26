package editor.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import editor.model.join.BaseJoinVo;

public class EntityVo implements Serializable{
	
	
	private Map<String, ColumnVo> columnsMap=new HashMap<>();
	private Map<String, ColumnGroupVo > columnGroupsMap;//=new HashMap<>();
	public ColumnVo addColumn(String columnName, ColumnVo value) {
		return columnsMap.put(columnName, value);
	}
	
	public ColumnVo getColumnVo(String columnVo)
	{
		return columnsMap.get(columnVo);
	}
	
	public ColumnGroupVo getColumnGroupVo(String groupName)
	{
		return columnGroupsMap.get(groupName);
	}
	int x;
	

	int y;
	ColumnGroupVo[] columnGroups;
	
	String[] icons;
	String fullyQualifiedName;
	public String getLabel()
	{
		return StrategyHolder.instance.getEntityNameStrategy().getEntityLabell(this);
		
	}
	
	String visibilty;
	ModelVo parent;
	BaseJoinVo[] joins;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public String[] getIcons() {
		return icons;
	}
	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}
	public String getVisibilty() {
		return visibilty;
	}
	public ModelVo getParent() {
		return parent;
	}
	public BaseJoinVo[] getJoins() {
		return joins;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void setIcons(String[] icons) {
		this.icons = icons;
	}
	public void setFullyQualifiedName(String fullyQualifiedName) {
		this.fullyQualifiedName = fullyQualifiedName;
	}
	public void setVisibilty(String visibilty) {
		this.visibilty = visibilty;
	}
	public void setParent(ModelVo parent) {
		this.parent = parent;
	}
	public void setJoins(BaseJoinVo[] joins) {
		this.joins = joins;
	}
	public ColumnGroupVo[] getColumnGroups() {
		return columnGroups;
	}
	public void setColumnGroups(ColumnGroupVo[] columnGroups) {
		this.columnGroups = columnGroups;
		
		this.columnGroupsMap=new HashMap<>();
		for (ColumnGroupVo columnGroupVo : columnGroups) {
			if(columnGroupVo.getName()!=null)
			{
				this.columnGroupsMap.put(columnGroupVo.getName(), columnGroupVo);	
			}
			
		}
		
	}
	


}
