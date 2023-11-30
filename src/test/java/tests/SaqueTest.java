package tests;

import commons.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pages.SaquePage;

public class SaqueTest extends BaseTest {
    SaquePage saque = new SaquePage();

    @Test
    public void testSaque(){
        saque.saque();
        Assert.assertEquals("Funcionalidade em desenvolvimento", saque.modalSaque().toString());
    }
}
