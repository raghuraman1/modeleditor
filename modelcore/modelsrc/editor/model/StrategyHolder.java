package editor.model;

public class StrategyHolder {
	
	private EntityNameStrategy entityNameStrategy= new DefaultEntityNameStrategy();
	
	public void setEntityNameStrategy(EntityNameStrategy entityNameStrategy) {
		this.entityNameStrategy = entityNameStrategy;
	}

	public EntityNameStrategy getEntityNameStrategy() {
		return entityNameStrategy;
	}

	public static final StrategyHolder instance= new StrategyHolder();

}
