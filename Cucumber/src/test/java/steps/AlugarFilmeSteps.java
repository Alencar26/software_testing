package steps;

import entidades.Filme;
import entidades.TipoAluguel;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import entidades.AluguelService;
import org.junit.Assert;
import org.junit.Test;
import serviços.NotaAluguel;
import utils.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class AlugarFilmeSteps {

    private Filme filme;
    private AluguelService aluguel = new AluguelService();
    private NotaAluguel nota;
    private String erro;
    private TipoAluguel tipoAluguel;

    @Dado("um filme com estoque de {int} unidades")
    public void umFilmeComEstoqueDeUnidades(Integer qntUnidades) {
        filme = new Filme();
        filme.setEstoque(qntUnidades);
    }
    @Dado("que o preço de aluguel seja R$ {int}")
    public void queOPreçoDeAluguelSejaR$(Integer valorAluguel) {
        filme.setAluguel(valorAluguel);
    }

    @Dado("um filme")
    public void umFilme(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);

        filme = new Filme();
        filme.setEstoque(Integer.parseInt(map.get("estoque")));
        filme.setAluguel(Integer.parseInt(map.get("preco")));
        String tipo = map.get("tipo");
        tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL :
                tipo.equals("extendido")? TipoAluguel.EXTENDIDO : TipoAluguel.COMUM;
    }

    @Quando("alugar")
    public void alugar() {
        try {
            nota = aluguel.alugar(filme, tipoAluguel);
        } catch (RuntimeException e){
            erro = e.getMessage();
        }
    }

    @Test
    @Então("o preço do aluguel será R$ {int}")
    public void oPreçoDoAluguelSeráR$(Integer valorAluguel) {
        Assert.assertEquals(valorAluguel.intValue(), nota.getPreco());
    }

    @Test
    @Então("o estoque do filme será {int} unidade")
    public void oEstoqueDoFilmeSeráUnidade(Integer qntEstoque) {
        Assert.assertEquals(qntEstoque, filme.getEstoque());
    }

    @Test
    @Então("não será possível por falta de estoque")
    public void nãoSeráPossívelPorFaltaDeEstoque() {
        Assert.assertEquals("Filme sem estoque", erro);
    }

    @ParameterType(".*")
    public TipoAluguel tipoAluguel(String s){
        return s.equals("semanal")? TipoAluguel.SEMANAL :
                s.equals("extendido")? TipoAluguel.EXTENDIDO : TipoAluguel.COMUM;
    }

    @Dado("que o tipo de aluguel seja {tipoAluguel}")
    public void queOTipoDeAluguelSejaExtendido(TipoAluguel tipo) {
        tipoAluguel = tipo;
    }

    @Test
    @Então("^a data de entrega será em (\\d+) dias?$")
    public void aDataDeEntregaSeráEmDias(Integer dias) {
        Date dataEsperada = DateUtils.obterDataDiferencaDias(dias);
        Date dataReal = nota.getDataEntrega();

        // se não formatar a data o java vai considerar os milisegundos e vai quebrar o teste
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
    }
    @Então("a pontuação recebida será de {int} pontos")
    public void aPontuaçãoRecebidaSeráDePontos(Integer pontuacao) {
        Assert.assertEquals(pontuacao.intValue(), nota.getPontuacao());
    }
}
