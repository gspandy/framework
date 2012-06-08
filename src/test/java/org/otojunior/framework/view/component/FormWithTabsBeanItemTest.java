/**
 * 
 */
package org.otojunior.framework.view.component;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet.Tab;

/**
 * @author otojr
 *
 */
public class FormWithTabsBeanItemTest {
	private static class Usuario {
		private String nome;
		private String login;
		private String password;
		private Date dataNasc;
		private boolean ativo;
		/**
		 * @return the nome
		 */
		public String getNome() {
			return nome;
		}
		/**
		 * @param nome the nome to set
		 */
		public void setNome(String nome) {
			this.nome = nome;
		}
		/**
		 * @return the login
		 */
		public String getLogin() {
			return login;
		}
		/**
		 * @param login the login to set
		 */
		public void setLogin(String login) {
			this.login = login;
		}
		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/**
		 * @return the dataNasc
		 */
		public Date getDataNasc() {
			return dataNasc;
		}
		/**
		 * @param dataNasc the dataNasc to set
		 */
		public void setDataNasc(Date dataNasc) {
			this.dataNasc = dataNasc;
		}
		/**
		 * @return the ativo
		 */
		public boolean isAtivo() {
			return ativo;
		}
		/**
		 * @param ativo the ativo to set
		 */
		public void setAtivo(boolean ativo) {
			this.ativo = ativo;
		}
		
	}
	
	private String ABA_PRINCIPAL = "Principal";
	private String ABA_SECUNDARIA = "Secundária";
	
	@Test
	public void test() {
		Map<Object, String> p = new HashMap<Object, String>();
		p.put("nome", ABA_PRINCIPAL);
		p.put("login", ABA_PRINCIPAL);
		p.put("password", ABA_PRINCIPAL);
		p.put("dataNasc", ABA_SECUNDARIA);
		p.put("ativo", ABA_SECUNDARIA);
		
		BeanItem<Usuario> beanItem = new BeanItem<Usuario>(new Usuario());
		FormWithTabsBeanItem f = new FormWithTabsBeanItem(p, beanItem);
		
		Tab tabPrincipal = f.getTab(ABA_PRINCIPAL);
		assertNotNull(tabPrincipal);
		assertTrue(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Nome"));
		assertTrue(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Login"));
		assertTrue(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Password"));
		assertFalse(containsComponent((Layout)f.getTab(ABA_PRINCIPAL).getComponent(), "Data Nasc"));
		
		assertTrue(containsComponent((Layout)f.getTab(ABA_SECUNDARIA).getComponent(), "Data Nasc"));
		assertFalse(containsComponent((Layout)f.getTab(ABA_SECUNDARIA).getComponent(), "Login"));
		
	}
	
	private <T> Collection<T> toCollection(Iterator<T> iterator) {
		Collection<T> col = new ArrayList<T>();
		while (iterator.hasNext())
			col.add(iterator.next());
		return col;
	}
	
	private boolean containsComponent(Layout layout, String caption) {
		Collection<Component> components = toCollection(layout.getComponentIterator());
		for (Component component : components)
			if (component.getCaption().equals(caption))	return true;
		return false;
	}

}
