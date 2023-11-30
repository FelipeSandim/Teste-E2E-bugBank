package tests;

import commons.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pages.PagamentoPage;

public class PagamentoTest extends BaseTest {

    PagamentoPage pagamento = new PagamentoPage();

    @Test
    public void testPagamento(){
        pagamento.pagamento();
        Assert.assertEquals("Funcionalidade em desenvolvimento", pagamento.modalPagamento().toString());
    }
}
