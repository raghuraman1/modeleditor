package editor.model.join;

import editor.model.ColumnVo;

public class ColumnColumnJoinVo extends BaseJoinVo {
	private ColumnVo parentEntitisColumn;
	private ColumnVo targetEntitisColumn;
	public ColumnVo getParentEntitisColumn() {
		return parentEntitisColumn;
	}
	public void setParentEntitisColumn(ColumnVo parentEntitisColumn) {
		this.parentEntitisColumn = parentEntitisColumn;
	}
	public ColumnVo getTargetEntitisColumn() {
		return targetEntitisColumn;
	}
	public void setTargetEntitisColumn(ColumnVo targetEntitisColumn) {
		this.targetEntitisColumn = targetEntitisColumn;
	}
	
	
}
