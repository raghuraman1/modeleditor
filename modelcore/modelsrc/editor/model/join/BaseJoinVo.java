package editor.model.join;

import java.awt.Graphics2D;
import java.io.Serializable;

import editor.model.BaseEntityVo;

public abstract class BaseJoinVo implements Serializable{
	protected int howMany;//-1 means many, 1 and -1 will be usual//only to show in diagram as is-1 will be shown as * or n
	protected BaseEntityVo parent;
	protected BaseEntityVo targetEntity;
	protected LineType lineType=LineType.Solid;//default to solid
	protected ArrowHead arrowHead=ArrowHead.Empty;//default

	protected String joinName;
	private boolean joined;
	
	
	
	
	
	public int getHowMany() {
		return howMany;
	}
	public void setHowMany(int howMany) {
		this.howMany = howMany;
	}
	public BaseEntityVo getParent() {
		return parent;
	}
	public void setParent(BaseEntityVo parent) {
		this.parent = parent;
	}
	public BaseEntityVo getTargetEntity() {
		return targetEntity;
	}
	public void setTargetEntity(BaseEntityVo targetEntity) {
		this.targetEntity = targetEntity;
	}
	public LineType getLineType() {
		return lineType;
	}
	public void setLineType(LineType lineType) {
		this.lineType = lineType;
	}
	public ArrowHead getArrowHead() {
		return arrowHead;
	}
	public void setArrowHead(ArrowHead arrowHead) {
		this.arrowHead = arrowHead;
	}


	public String getJoinName() {
		return joinName;
	}


	public void setJoinName(String joinName) {
		this.joinName = joinName;
	}

}
