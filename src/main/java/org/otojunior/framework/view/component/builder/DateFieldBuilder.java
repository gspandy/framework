/**
 * 
 */
package org.otojunior.framework.view.component.builder;

import java.util.Date;
import java.util.Locale;

import com.vaadin.data.Property;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PopupDateField;

/**
 * @author otojunior
 *
 */
public class DateFieldBuilder {
	/**
	 * Create the field
	 * @param caption Caption
	 * @return
	 */
	public static DateField create() {
		DateField dateField = new PopupDateField();
		configure(dateField);
		return dateField;
	}
	
	/**
	 * Create the field
	 * @param caption Caption
	 * @return
	 */
	public static DateField create(Property dataSource) {
		DateField dateField = new PopupDateField(dataSource);
		configure(dateField);
		return dateField;
	}
	
	/**
	 * Create the field
	 * @param caption Caption
	 * @return
	 */
	public static DateField create(String caption) {
		DateField dateField = new PopupDateField(caption);
		configure(dateField);
		return dateField;
	}
	
	/**
	 * Create the field
	 * @param caption Caption
	 * @return
	 */
	public static DateField create(String caption, Date date) {
		DateField dateField = new PopupDateField(caption, date);
		configure(dateField);
		return dateField;
	}
	
	/**
	 * Create the field
	 * @param caption Caption
	 * @return
	 */
	public static DateField create(String caption, Property dataSource) {
		DateField dateField = new PopupDateField(caption, dataSource);
		configure(dateField);
		return dateField;
	}
	
	/**
	 * Configure field
	 * @param dateField
	 */
	private static void configure(DateField dateField) {
		dateField.setResolution(DateField.RESOLUTION_DAY);
		dateField.setLocale(new Locale("pt", "BR"));
		dateField.setDateFormat("dd/MM/yyyy");
	}
}
