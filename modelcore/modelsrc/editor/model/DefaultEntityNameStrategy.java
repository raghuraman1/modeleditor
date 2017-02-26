package editor.model;

public class DefaultEntityNameStrategy implements EntityNameStrategy {

	@Override
	public String getEntityLabell(EntityVo entity) {
		String fqn=entity.getFullyQualifiedName();
		String use=fqn;
		int lastIndex=fqn.lastIndexOf(".");
		if(lastIndex!=-1 && fqn.length()>(lastIndex+1))
		{
			use=fqn.substring(lastIndex+1);
		}
		else
		{
			lastIndex=fqn.lastIndexOf("/");
			if(lastIndex!=-1 && fqn.length()>(lastIndex+1))
			{
				use=fqn.substring(lastIndex+1);
			}
			else
			{
				lastIndex=fqn.lastIndexOf("\\");
				if(lastIndex!=-1 && fqn.length()>(lastIndex+1))
				{
					use=fqn.substring(lastIndex+1);
				}
				
				
			}
		}
			
			
		
		return use;
	}

}
