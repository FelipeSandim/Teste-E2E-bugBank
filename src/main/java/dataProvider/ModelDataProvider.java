package dataProvider;

import org.junit.jupiter.params.provider.Arguments;
import pages.BasePage;

import java.util.stream.Stream;

public class ModelDataProvider extends BasePage {
    public static Stream<Arguments> emailModel(){
        return Stream.of(
                Arguments.of("teste", "Homer Simpson", "teste1234", "teste1234"),
                Arguments.of("teste@teste", "Homer Simpson", "teste1234", "teste1234"),
                Arguments.of("teste@teste.123", "Homer Simpson", "teste1234", "teste1234"),
                Arguments.of("teste.com", "Homer Simpson", "teste1234", "teste1234"),
                Arguments.of("teste2teste.com", "Homer Simpson", "teste1234", "teste1234"),
                Arguments.of("teste teste@teste. com", "Homer Simpson", "teste1234", "teste1234")
        );
    }
    public static Stream<Arguments> transferencialModelValor(){
        return Stream.of(
                Arguments.of("teste"),
                Arguments.of("@"),
                Arguments.of("......."),
                Arguments.of("1a1a1a"),
                Arguments.of("@500"),
                Arguments.of("#500"),
                Arguments.of("$500"),
                Arguments.of("/500"),
                Arguments.of("%500"),
                Arguments.of("&500")
        );
    }
    public static Stream<Arguments> transferencialModelConta(){
        return Stream.of(
                Arguments.of("teste", "4", "500", "Teste de Transferencia"),
                Arguments.of("@", "4", "500", "Teste de Transferencia"),
                Arguments.of(".......", "4", "500", "Teste de Transferencia"),
                Arguments.of("1a1a1a", "4", "500", "Teste de Transferencia"),
                Arguments.of("@500", "4", "500", "Teste de Transferencia"),
                Arguments.of("#500", "4", "500", "Teste de Transferencia"),
                Arguments.of("$500", "4", "500", "Teste de Transferencia"),
                Arguments.of("/500", "4", "500", "Teste de Transferencia"),
                Arguments.of("%500", "4", "500", "Teste de Transferencia"),
                Arguments.of("&500", "4", "500", "Teste de Transferencia")
        );
    }
    public static Stream<Arguments> transferencialModelDigito(){
        return Stream.of(
                Arguments.of("400","teste", "500", "Teste de Transferencia"),
                Arguments.of("400","@", "500", "Teste de Transferencia"),
                Arguments.of("400",".......", "500", "Teste de Transferencia"),
                Arguments.of("400","1a1a1a", "500", "Teste de Transferencia"),
                Arguments.of("400","@500", "500", "Teste de Transferencia"),
                Arguments.of("400","#500", "500", "Teste de Transferencia"),
                Arguments.of("400","$500", "500", "Teste de Transferencia"),
                Arguments.of("400","/500", "500", "Teste de Transferencia"),
                Arguments.of("400","%500", "500", "Teste de Transferencia"),
                Arguments.of("400","&500", "500", "Teste de Transferencia")
        );
    }
}
