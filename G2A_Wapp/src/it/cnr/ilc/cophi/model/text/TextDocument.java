/**
 * 
 */
package it.cnr.ilc.cophi.model.text;

import it.cnr.ilc.cophi.model.entity.EntityComponent;
import it.cnr.ilc.cophi.model.entity.EntityComposite;

import java.util.TreeSet;

/**
 * @author simone
 *
 */
public class TextDocument extends EntityComposite{


		public TextDocument() {
			super.setComponents(new TreeSet<EntityComponent>(/* TODO comparator */));
		}
}
