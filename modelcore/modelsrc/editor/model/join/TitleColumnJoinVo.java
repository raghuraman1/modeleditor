package editor.model.join;

import editor.model.ColumnVo;

public class TitleColumnJoinVo extends BaseJoinVo {
 private ColumnVo targetEntitisColumn;

public ColumnVo getTargetEntitisColumn() {
	return targetEntitisColumn;
}

public void setTargetEntitisColumn(ColumnVo targetEntitisColumn) {
	this.targetEntitisColumn = targetEntitisColumn;
}

}
