package editor.model.join;

import editor.model.ColumnVo;

public class ColumnTitleJoinVo extends BaseJoinVo {
	private ColumnVo parentEntitisColumn;

public ColumnVo getParentEntitisColumn() {
	return parentEntitisColumn;
}

public void setParentEntitisColumn(ColumnVo parentEntitisColumn) {
	this.parentEntitisColumn = parentEntitisColumn;
}

}
