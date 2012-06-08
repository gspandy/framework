package org.otojunior.framework.view.component;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

/**
 * Form with tabs.
 * @author otojunior
 */
public class FormWithTabs extends Form {
	private static final long serialVersionUID = -5001173176037736874L;

	// Componentes
	private TabSheet tabSheet;
	
	// Outros controles
	private Map<String, ComponentContainer> tabs;
	private ComponentContainer tempContainer;

	/**
	 * Default constructor.
	 */
	public FormWithTabs() {
		tabSheet = new TabSheet();
		tabs = new HashMap<String, ComponentContainer>();
		setLayout(new VerticalLayout());
		getLayout().addComponent(tabSheet);
	}

	/**
	 * Adiciona campos num container.
	 * @param propertyId Property ID.
	 * @param field Field.
	 * @param tab Tab.
	 */
	public void addField(Object propertyId, Field field, ComponentContainer tab) {
		tempContainer = tab;
		tabSheet.addTab(tab);
		super.addField(propertyId, field);
	}
	
	/**
	 * @deprecated
	 * Adiciona campos num container.
	 * @param propertyId Property ID.
	 * @param field Field.
	 * @param tab Tab.
	 */
	@Deprecated
	public void addField(Object propertyId, Field field, String tabId) {
		if (!tabs.containsKey(tabId)) {
			FormLayout newLayout = new FormLayout();
			newLayout.setCaption(tabId);
			tabs.put(tabId, newLayout);
		}
		Layout tab = (Layout)tabs.get(tabId);;
		addField(propertyId, field, tab);
	}
	
	/**
	 * @deprecated
	 * Get tab.
	 * @param tabId Tab identifier.
	 * @return {@link Tab}
	 */
	@Deprecated
	public Tab getTab(String tabId) {
		ComponentContainer container = tabs.get(tabId);
		return tabSheet.getTab(container);
	}

	/**
	 * Attach field in a container,
	 * @param propertyId Property ID.
	 * @param field Field.
	 */
	@Override
	protected void attachField(Object propertyId, Field field) {
		if (propertyId == null || field == null) return;
        if (tempContainer instanceof CustomLayout)
            ((CustomLayout) tempContainer).addComponent(field, propertyId.toString());
        else tempContainer.addComponent(field);
	}
}
