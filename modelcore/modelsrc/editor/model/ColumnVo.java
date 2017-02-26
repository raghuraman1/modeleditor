package editor.model;

import java.io.Serializable;

public class ColumnVo implements Serializable{
	String[] icons;
	String name;
	String type;
	String visibilty;
	ColumnGroupVo parent;
	public String[] getIcons() {
		return icons;
	}
	public void setIcons(String[] icons) {
		this.icons = icons;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVisibilty() {
		return visibilty;
	}
	public void setVisibilty(String visibilty) {
		this.visibilty = visibilty;
	}
	public ColumnGroupVo getParent() {
		return parent;
	}
	public void setParent(ColumnGroupVo parent) {
		this.parent = parent;
	}

}
