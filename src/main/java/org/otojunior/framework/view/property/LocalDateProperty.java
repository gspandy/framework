package org.otojunior.framework.view.property;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import com.vaadin.data.Property;

@SuppressWarnings("serial")
public class LocalDateProperty implements Property {
	LocalDate data;
	boolean readonly = false;

	@Override
	public void setValue(Object newValue) throws ReadOnlyException,
			ConversionException {
		if (readonly)
			throw new ReadOnlyException("Erro: prop. somente leitura");
		if (newValue instanceof LocalDate)
			this.data = (LocalDate) newValue;
		else if (newValue instanceof String) {
			DateTimeFormatter fmt = new DateTimeFormatterBuilder()
					.appendPattern("dd/MM/yyyy").toFormatter();
			this.data = LocalDate.parse((String) newValue, fmt);
		} else {
			throw new ConversionException("Erro: conversão");
		}
	}

	@Override
	public void setReadOnly(boolean newStatus) {
		this.readonly = newStatus;
	}

	@Override
	public boolean isReadOnly() {
		return readonly;
	}

	@Override
	public Object getValue() {
		return data;
	}

	@Override
	public Class<?> getType() {
		return LocalDate.class;
	}

	@Override
	public String toString() {
		return data.toString("dd/MM/yyyy");
	}
}
