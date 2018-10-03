package no.hvl.dat108;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import java.util.List;

import static junit.framework.Assert.assertTrue;


public class ServletTest {

    @Test
    public void testServlet() throws Exception {
        CartItem fisk = new CartItem("Laks");
        CartItem bil  = new CartItem("Lastebil");
        CartItem fugl = new CartItem("Ugle");
        CartItem fag = new CartItem("dat108");


        String yes = fisk.getName();
        assertTrue(fisk.getName().equals("Laks"));
        assertTrue(bil.getName().equals("Lastebil"));
        assertTrue(fugl.getName().equals("Ugle"));
        assertTrue(fag.getName().equals("dat108"));

        Cart handlevogn = new Cart();

        handlevogn.addItem(fisk);
        handlevogn.addItem(bil);
        handlevogn.addItem(fugl);
        handlevogn.addItem(fag);

        List<CartItem> liste = handlevogn.getItems();
        assertTrue(liste.get(0) == fisk);
        assertTrue(liste.get(1) == bil);
        assertTrue(liste.get(2) == fugl);
        assertTrue(liste.get(3) == fag);
    }

}
