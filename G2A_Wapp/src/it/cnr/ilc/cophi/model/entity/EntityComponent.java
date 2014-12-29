/**
 * 
 */
package it.cnr.ilc.cophi.model.entity;

import it.cnr.ilc.cophi.model.exception.LeafException;

import java.util.List;

/**
 * @author simone
 *
 *
 */
public abstract class EntityComponent {

	/**
	 * Indica il tipo del componente (class attributo XML di sequenza o elemento)
	 */
	private String typeClass;

	/**
	 * Restituisce la lista dei componenti figlio.
	 * @return List of EntityComponent
	 */
	abstract public List<EntityComponent> getChildren();
	
	/**
	 * 
	 * @return
	 */
	abstract public EntityComponent getChild(int i);
	
	/**
	 * 
	 * @param ec
	 * @throws LeafException
	 */
	public void add (EntityComponent ec) throws LeafException {
		
		if(ec instanceof EntityUnit) throw new LeafException();
	}

	/**
	 * 
	 * @param ec
	 * @throws LeafException
	 */
	public void remove (EntityComponent ec) throws LeafException {
		
		if(ec instanceof EntityUnit) throw new LeafException();
	}
	
	/**
	 * Rappresenta il comportamento del Component
	 * @return
	 */
	abstract public String exec();
	
	public String getTypeClass() {
		return typeClass;
	}

	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
	}
	
	
}
