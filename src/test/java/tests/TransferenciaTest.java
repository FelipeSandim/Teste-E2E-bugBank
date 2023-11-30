package tests;

import commons.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.TransferenciaPage;

public class TransferenciaTest extends BaseTest {

    TransferenciaPage transferencia = new TransferenciaPage();

    @Test
    @DisplayName("Para a transferencia são criadas duas contas porque o sistema não armazena a conta criada em um banco de dados")
    public void testTransferencia(){
        transferencia.contaUm();
        transferencia.contaDois("500", "Teste de Transferencia");
        Assert.assertEquals("Transferencia realizada com sucesso", transferencia.modalTransferencia().toString());
    }
    @Test
    @DisplayName("Teste de transferencia de valores para a mesma conta")
    public void testTransferenciaParaMesmaConta(){
        transferencia.MesmaConta("100", "Teste de Transferencia");
        Assert.assertEquals("Nao pode transferir pra mesmo conta", transferencia.modalTransferencia().toString());
    }
    @Test
    @DisplayName("Teste de transferencia exedendo valor que tem em conta")
    public void testTransferenciaComValorAcimaDoLimite(){
        transferencia.contaUm();
        transferencia.contaDois("1100", "Teste de Transferencia");
        Assert.assertEquals("Você não tem saldo suficiente para essa transação", transferencia.modalTransferencia().toString());
    }
    @Test
    @DisplayName("Teste de transferencia com o remetente sem saldo em conta")
    public void testTransferenciaComContaRemetenteSemSaldo(){
        transferencia.contaUm();
        transferencia.contaSemSaldo("500", "Teste de Transferencia");
        Assert.assertEquals("Você não tem saldo suficiente para essa transação", transferencia.modalTransferencia().toString());
    }
    @Test
    @DisplayName("teste de transferencia com o valor zero")
    public void testTransferenciaComValorZero(){
        transferencia.contaUnica("400", "4", "0", "Teste de Transferencia");
        Assert.assertEquals("Valor da transferência não pode ser 0 ou negativo", transferencia.modalTransferencia());
    }
    @Test
    @DisplayName("Teste de transferencia sem informar o numero da conta")
    public void testTransferenciaSemInformarONumeroDaConta(){
        transferencia.contaUnica("", "44", "100", "Teste de Transferencia");
        Assert.assertEquals("Conta inválida ou inexistente", transferencia.modalTransferencia());
    }
    @Test
    @DisplayName("Teste de transferencia sem informar o numero da conta")
    public void testTransferenciaSemInformarODigito(){
        transferencia.contaUnica("1111", "", "100", "Teste de Transferencia");
        Assert.assertEquals("Conta inválida ou inexistente", transferencia.modalTransferencia());
    }
    @Test
    @DisplayName("Teste de transferencia passando um valor negativo.")
    public void testTransferenciaComValorNegativo(){
        transferencia.contaUnica("400", "4", "-500", "Teste de Transferencia");
        Assert.assertEquals("Valor da transferência não pode ser 0 ou negativo", transferencia.modalTransferencia());
    }
    @ParameterizedTest
    @MethodSource("dataProvider.ModelDataProvider#transferencialModelConta")
    @DisplayName("Teste parametrizado de transferencia passando parametros invalidos para o campo da conta")
    public void testTransferenciaParaContaInexistenteComCaracteresEspeciais(String conta, String digito, String valor, String descricao){
        transferencia.contaUnica(conta, digito, valor, descricao);
        Assert.assertEquals("Conta inválida ou inexistente", transferencia.modalTransferencia());
    }
    @ParameterizedTest
    @MethodSource("dataProvider.ModelDataProvider#transferencialModelDigito")
    @DisplayName("Teste parametrizado de transferencia passando parametros invalidos para o campo do dígito")
    public void testTransferenciaParaDigitoInexistenteComCaracteresEspeciais(String conta, String digito, String valor, String descricao){
        transferencia.contaUnica(conta, digito, valor, descricao);
        Assert.assertEquals("Conta inválida ou inexistente", transferencia.modalTransferencia());
    }
    @ParameterizedTest
    @MethodSource("dataProvider.ModelDataProvider#transferencialModelValor")
    @DisplayName("Teste parametrizado de transferencia passando parametros invalidos para o campo de valor")
    public void testTransferenciaComCaracteresEspeciais(String conta, String digito, String valor, String descricao){
        transferencia.contaUnica(conta, digito, valor, descricao);
        Assertions.assertEquals("transferValue must be a `number` type, but the final value was: `NaN` (cast from the value `\"" + valor + "\"`)." , transferencia.getTextByCss());
    }
}
