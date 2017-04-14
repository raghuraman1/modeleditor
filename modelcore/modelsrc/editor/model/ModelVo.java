package editor.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ModelVo implements Serializable {
	private Map<String, BaseEntityVo> entitiesMap;
	private BaseEntityVo[] entities;
	public BaseEntityVo[] getEntities() {
		return entities;
	}
	public BaseEntityVo getEntity(String entityFqn)
	{
		return this.entitiesMap.get(entityFqn);
	}
	
	public void setEntities(BaseEntityVo[] entities) {
		this.entities = entities;
		
		this.entitiesMap=new HashMap<String, BaseEntityVo>();
		for (BaseEntityVo entityVo : this.entities) {
			entitiesMap.put(entityVo.getFullyQualifiedName(), entityVo);
		}
	}
	
	
}
