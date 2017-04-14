package editor.model;

import java.util.HashMap;
import java.util.Map;

public class EntityVo extends BaseEntityVo {
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
