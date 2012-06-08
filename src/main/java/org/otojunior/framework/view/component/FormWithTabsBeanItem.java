/**
 * 
 */
package org.otojunior.framework.view.component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

/**
 * @author otojr
 *
 */
@SuppressWarnings("serial")
public class FormWithTabsBeanItem extends Form {
	/*
	 * Component tabsheet.
	 */
	private TabSheet tabSheet;
	
	/*
	 * Specify which tab (tabId) is associated with the field (propertyId) component.
	 * <key:propertyId, value:tabId> 
	 */
	private Map<Object, String> propertyTabAssociation;
	
	/*
	 * Associate the tab (tabId) with its content (container)
	 * <key:tabId value:layout>
	 */
	private Map<String, Layout> tabContentAssociation;  
	
	/**
	 * @param order 
	 * @param beanItem Bean (May be an Entity).
	 * @param defaultFieldFactory 
	 * @param fieldInTabLocation 
	 */
	public FormWithTabsBeanItem(Map<Object, String> propertyTabAssociation, BeanItem<?> beanItem, DefaultFieldFactory defaultFieldFactory) {
		super(new VerticalLayout());
		this.tabSheet = new TabSheet();
		this.propertyTabAssociation = propertyTabAssociation;
		this.tabContentAssociation = new HashMap<String, Layout>();
		this.setVisibleItemProperties(propertyTabAssociation.keySet());
		this.setItemDataSource(beanItem);
	}
	
	/**
	 * @param beanItem Bean (May be an Entity).
	 * @param fieldInTabLocation 
	 */
	public FormWithTabsBeanItem(Map<Object, String> propertyTabAssociation, BeanItem<?> beanItem, FormFieldFactory formFieldFactory) {
		super(new VerticalLayout());
		this.tabSheet = new TabSheet();
		this.propertyTabAssociation = propertyTabAssociation;
		this.tabContentAssociation = new HashMap<String, Layout>();
		this.setFormFieldFactory(formFieldFactory);
		this.setItemDataSource(beanItem);
	}
	
	@Override
	public void attach() {
		super.attach();
		getLayout().addComponent(tabSheet);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void attachField(Object propertyId, Field field) {
		if (propertyId == null || field == null)
            return;

		// Obtain the tab-ID
		String tabId = propertyTabAssociation.get(propertyId);
		if (tabId == null) return;
		
		// Obtain (or create) the content of the tab.
		Layout layout = null;
		if (!tabContentAssociation.containsKey(tabId)) {
			layout = new FormLayout();
			layout.setCaption(tabId);
			tabContentAssociation.put(tabId, layout);
			tabSheet.addTab(layout);
		} else {
			layout = tabContentAssociation.get(tabId);
		}
		
		if (layout instanceof CustomLayout)
            ((CustomLayout) layout).addComponent(field, propertyId.toString());
        else layout.addComponent(field);
	}

	/**
	 * Obtem tab.
	 * @param tabId
	 * @return
	 */
	public Tab getTab(String tabId) {
		Layout layout = tabContentAssociation.get(tabId);
		if (layout != null)
			return tabSheet.getTab(layout);
		return null;
	}
}
