package editor.model;

public class StrategyHolder {
	
	private EntityNameStrategy entityNameStrategy= new DefaultEntityNameStrategy();
	
	public EntityNameStrategy getEntityNameStrategy() {
		return entityNameStrategy;
	}

	public static final StrategyHolder instance= new StrategyHolder();

}
