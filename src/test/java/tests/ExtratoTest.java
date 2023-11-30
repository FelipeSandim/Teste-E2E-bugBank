package tests;

import commons.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ExtratoPage;
import utils.SeletoresAssert;

public class ExtratoTest extends BaseTest {
    SeletoresAssert seletor = new SeletoresAssert();
    ExtratoPage extrato = new ExtratoPage();
    @Test
    @DisplayName("Teste de extrato conferindo sado na conta depois de ter criado uma conta com saldo.")
    public void testExtratoSimplesComSaldo(){
        extrato.extratoSimplesComSaldoNaConta();
        Assert.assertEquals("Saldo disponível", extrato.getTextByCss(seletor.EXTRATO_MENSAGEM_SALDO_DISPONIVEL));
        Assert.assertEquals("R$ 1.000,00", extrato.getTextByCss(seletor.EXTRATO_SALDO_DISPONIVEL_EM_CONTA));
    }
    @Test
    @DisplayName("Teste de extrato conferindo se o saldo é Zero depois de criar uma conta sem saldo")
    public void testExtratoSimplesSemSaldo(){
        extrato.extratoSimplesSemSaldoNaConta();
        Assert.assertEquals("Saldo disponível", extrato.getTextByCss(seletor.EXTRATO_MENSAGEM_SALDO_DISPONIVEL));
        Assert.assertEquals("R$ 0,00", extrato.getTextByCss(seletor.EXTRATO_SALDO_DISPONIVEL_EM_CONTA));
    }
    @Test
    @DisplayName("Teste de extrato conferindo o valor que fica na conta depois de uma transferência e informando no extrato o valor que foi enviado")
    public void testExtratoComTransferencia(){
        extrato.extratoComTransferencia("55.33");
        Assert.assertEquals(extrato.sobraEmConta(), extrato.getTextByCss(seletor.EXTRATO_SALDO_DISPONIVEL_EM_CONTA));
        Assert.assertEquals("-R$ " + extrato.valores(), extrato.getTextByCss(seletor.EXTRATO_VALOR_ENVIADO_EM_TRANSFERENCIA));
    }
    @Test
    @DisplayName("Teste de extrato conferindo se o valor enviado foi recebido pela conta de destino.")
    public void testExtratoRecebido(){
        extrato.extratoComTransferenciaRecebida("200.00");
        Assert.assertEquals(extrato.recebidoEmConta(), extrato.getTextByCss(seletor.EXTRATO_SALDO_DISPONIVEL_EM_CONTA));
        Assert.assertEquals("R$ " + extrato.valores(), extrato.getTextByCss(seletor.EXTRATO_VALOR_RECEBIDO_EM_TRANSFERENCIA));
    }
}
