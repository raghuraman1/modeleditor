package editor.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import editor.model.join.BaseJoinVo;

public abstract class BaseEntityVo implements Serializable{
	
	
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
	


}
