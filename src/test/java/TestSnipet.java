import static org.junit.Assert.*;

import org.junit.Test;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;

/**
 * 
 */

/**
 * @author otojunior
 *
 */
public class TestSnipet {

	@Test
	public void test() {
		Layout lay = new FormLayout();
		
		TabSheet sheet = new TabSheet();
		Tab tab = sheet.addTab(lay);
		System.out.println(tab);
		assertNotNull(tab);
		
	}

}
