package editor.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ModelVo implements Serializable {
	private Map<String, EntityVo> entitiesMap;
	private EntityVo[] entities;
	public EntityVo[] getEntities() {
		return entities;
	}
	public EntityVo getEntity(String entityFqn)
	{
		return this.entitiesMap.get(entityFqn);
	}
	
	public void setEntities(EntityVo[] entities) {
		this.entities = entities;
		
		this.entitiesMap=new HashMap<String, EntityVo>();
		for (EntityVo entityVo : this.entities) {
			entitiesMap.put(entityVo.getFullyQualifiedName(), entityVo);
		}
	}
	
	
}
