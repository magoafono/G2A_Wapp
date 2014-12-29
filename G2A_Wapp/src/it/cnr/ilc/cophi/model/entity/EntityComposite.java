/**
 * 
 */
package it.cnr.ilc.cophi.model.entity;

import java.util.List;
import java.util.TreeSet;

/**
 * @author simone
 *
 */
public abstract class EntityComposite extends EntityComponent {

	/**
	 * 
	 */
	private TreeSet<EntityComponent> components; 
	
	/**
	 * @return the components
	 */
	public TreeSet<EntityComponent> getComponents() {
		return components;
	}

	/**
	 * @param components the components to set
	 */
	public void setComponents(TreeSet<EntityComponent> components) {
		this.components = components;
	}

	/* (non-Javadoc)
	 * @see it.cnr.ilc.cophi.model.entity.EntityComponent#getChildren()
	 */
	@Override
	public List<EntityComponent> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see it.cnr.ilc.cophi.model.entity.EntityComponent#getChild(int)
	 */
	@Override
	public EntityComponent getChild(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see it.cnr.ilc.cophi.model.entity.EntityComponent#exec()
	 */
	@Override
	public String exec() {
		// TODO Auto-generated method stub
		return null;
	}

}
