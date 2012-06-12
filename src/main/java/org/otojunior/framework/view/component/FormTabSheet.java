package org.otojunior.framework.view.component;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.VerticalLayout;

/**
 * Form with tabs working with TO (Transfer Object) 
 * @author otojr
 *
 */
@SuppressWarnings("serial")
public class FormTabSheet extends Form {
	/*
	 * Component tabsheet.
	 */
	private TabSheet tabSheet;
	
	/*
	 * Specify which tab (tabId) is associated with the field (propertyId) component.
	 * <key:propertyId, value:FieldInfo> 
	 */
	private Map<Object, FieldInfo> propertyTabAssociation;
	
	/*
	 * Associate the tab (tabId) with its content (container)
	 * <key:tabId value:layout>
	 */
	private Map<String, Layout> tabContentAssociation;  
	
	/**
	 * @param order 
	 * @param transferObject TO (Transfer Object).
	 * @param defaultFieldFactory 
	 * @param fieldInTabLocation 
	 */
	public FormTabSheet(Map<Object, FieldInfo> propertyTabAssociation, BeanItem<?> transferObject) {
		super(new VerticalLayout());
		this.tabSheet = new TabSheet();
		this.propertyTabAssociation = propertyTabAssociation;
		this.tabContentAssociation = new HashMap<String, Layout>();
		this.setFormFieldFactory(new InternalFormFieldFactory());
		this.setVisibleItemProperties(propertyTabAssociation.keySet());
		this.setItemDataSource(transferObject, propertyTabAssociation.keySet());
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
		FieldInfo fieldInfo = propertyTabAssociation.get(propertyId);
		if (fieldInfo == null) return;
		
		// Obtain (or create) the content of the tab.
		Layout layout = null;
		if (!tabContentAssociation.containsKey(fieldInfo.getTabId())) {
			layout = new FormLayout();
			layout.setCaption(fieldInfo.getTabId());
			tabContentAssociation.put(fieldInfo.getTabId(), layout);
			tabSheet.addTab(layout);
		} else {
			layout = tabContentAssociation.get(fieldInfo.getTabId());
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
	
	/**
	 * Field Information
	 * @author Oto Junior (otojunior@gmail.com)
	 */
	public static class FieldInfo {
		private String tabId;
		private Field field;
		private boolean visible = true;
		private boolean enabled = true;
		
		public FieldInfo(String tabId, Field field) {
			this.tabId = tabId;
			this.field = field;
		}
		
		public FieldInfo(String tabId, Field field, boolean visible, boolean enabled) {
			this(tabId, field);
			this.visible = visible;
			this.enabled = enabled;
		}
		
		public String getTabId() { return tabId; }
		public Field getField() { return field; }
		public boolean isVisible() { return visible; }
		public boolean isEnabled() { return enabled; }
	}
	
	/**
	 * Internal Form Field Factory
	 * @author Oto Junior (otojunior@gmail.com)
	 */
	private class InternalFormFieldFactory extends DefaultFieldFactory {
		@Override
		public Field createField(Item item, Object propertyId, Component uiContext) {
			FieldInfo fieldInfo = propertyTabAssociation.get(propertyId);
			if (fieldInfo != null) {
				Field field = fieldInfo.getField();
				field.setVisible(fieldInfo.isVisible());
				field.setEnabled(fieldInfo.isEnabled());
				return field;
			}
			return super.createField(item, propertyId, uiContext);
		}
	}
}
