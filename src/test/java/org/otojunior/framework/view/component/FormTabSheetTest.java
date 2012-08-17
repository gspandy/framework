package org.otojunior.framework.view.component;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;
import org.otojunior.framework.presentation.component.FormTabSheet;
import org.otojunior.framework.presentation.component.FormTabSheet.FieldInfo;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;

/**
 * @author otojr
 *
 */
public class FormTabSheetTest {
	@SuppressWarnings("unused")
	private static class Usuario {
		private String nome;
		private String login;
		private String password;
		private Date dataNasc;
		private boolean ativo;
		
		public String getNome() { return nome; }
		public void setNome(String nome) { this.nome = nome; }
		public String getLogin() { return login; }
		public void setLogin(String login) { this.login = login; }
		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }
		public Date getDataNasc() {	return dataNasc; }
		public void setDataNasc(Date dataNasc) { this.dataNasc = dataNasc; }
		public boolean isAtivo() { return ativo; }
		public void setAtivo(boolean ativo) { this.ativo = ativo; }
	}
	
	private String ABA_PRINCIPAL = "Principal";
	private String ABA_SECUNDARIA = "Secundária";
	
	/**
	 * 
	 */
	@Test
	public void testUsingDefaultConstructor() {
		Map<Object, FieldInfo> p = new HashMap<Object, FieldInfo>();
		p.put("nome", new FieldInfo(ABA_PRINCIPAL, new TextField("Nome")));
		p.put("login", new FieldInfo(ABA_PRINCIPAL, new TextField("Login")));
		p.put("password", new FieldInfo(ABA_PRINCIPAL, new PasswordField("Senha")));
		p.put("dataNasc", new FieldInfo(ABA_SECUNDARIA, new DateField("Data de Nascimento")));
		p.put("ativo", new FieldInfo(ABA_SECUNDARIA, new CheckBox("Ativo?")));
		
		BeanItem<Usuario> beanItem = new BeanItem<Usuario>(new Usuario());
		FormTabSheet f = new FormTabSheet(p, beanItem);
		
		Tab tabPrincipal = f.getTab(ABA_PRINCIPAL);
		assertNotNull(tabPrincipal);
		assertTrue(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Nome"));
		assertTrue(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Login"));
		assertTrue(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Senha"));
		assertFalse(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Data de Nascimento"));
		
		assertTrue(containsComponent((Layout)f.getTab(ABA_SECUNDARIA).getComponent(), "Data de Nascimento"));
		assertFalse(containsComponent((Layout)f.getTab(ABA_SECUNDARIA).getComponent(), "Login"));
	}
	
	/**
	 * 
	 */
	@Test
	public void testUsingConvenientConstructor() {
		BeanItem<Usuario> beanItem = new BeanItem<Usuario>(new Usuario());
		Object[][] config = {
			{"nome", ABA_PRINCIPAL, new TextField("Nome")},
			{"login", ABA_PRINCIPAL, new TextField("Login")},
			{"password", ABA_PRINCIPAL, new PasswordField("Senha")},
			{"dataNasc", ABA_SECUNDARIA, new DateField("Data de Nascimento")},
			{"ativo", ABA_SECUNDARIA, new CheckBox("Ativo?")}};
		FormTabSheet f = new FormTabSheet(config, beanItem);
		
		Tab tabPrincipal = f.getTab(ABA_PRINCIPAL);
		assertNotNull(tabPrincipal);
		assertTrue(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Nome"));
		assertTrue(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Login"));
		assertTrue(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Senha"));
		assertFalse(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Data de Nascimento"));
		
		assertTrue(containsComponent((Layout)f.getTab(ABA_SECUNDARIA).getComponent(), "Data de Nascimento"));
		assertFalse(containsComponent((Layout)f.getTab(ABA_SECUNDARIA).getComponent(), "Login"));
	}
	
	/**
	 * @param iterator
	 * @return
	 */
	private Collection<Component> toCollection(Iterator<Component> iterator) {
		Collection<Component> col = new ArrayList<Component>();
		while (iterator.hasNext()) {
			Component next = iterator.next();
			col.add(next);
		}
		return col;
	}
	
	/**
	 * @param layout
	 * @param caption
	 * @return
	 */
	private boolean containsComponent(Layout layout, String caption) {
		Collection<Component> components = toCollection(layout.getComponentIterator());
		for (Component component : components)
			if (caption.equals(component.getCaption()))	return true;
		return false;
	}
}
