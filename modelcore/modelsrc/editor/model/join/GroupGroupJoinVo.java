package editor.model.join;

import editor.model.ColumnGroupVo;

/**
 * for say combined primary keys
 * @author AL1088
 *
 */
public class GroupGroupJoinVo extends BaseJoinVo {
	 private ColumnGroupVo parentEntitisColumnGroup;
	 private ColumnGroupVo targetEntitisColumnGroup;
	public ColumnGroupVo getParentEntitisColumnGroup() {
		return parentEntitisColumnGroup;
	}
	public void setParentEntitisColumnGroup(ColumnGroupVo parentEntitisColumnGroup) {
		this.parentEntitisColumnGroup = parentEntitisColumnGroup;
	}
	public ColumnGroupVo getTargetEntitisColumnGroup() {
		return targetEntitisColumnGroup;
	}
	public void setTargetEntitisColumnGroup(ColumnGroupVo targetEntitisColumnGroup) {
		this.targetEntitisColumnGroup = targetEntitisColumnGroup;
	}
}
