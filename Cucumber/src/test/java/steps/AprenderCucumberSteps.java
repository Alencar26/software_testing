package steps;

import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.java.ParameterType;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AprenderCucumberSteps {

    private int contador = 0;

    @Dado("que o valor do contador é {int}")
    public void queOValorDoContadorÉ(Integer int1) {
        contador = int1;
    }

    @Quando("eu incrementar em {int}")
    public void euIncrementarEm(Integer int1) {
        contador += int1;
    }

    @Test
    @Então("o valor do contador será {int}")
    public void oValorDoContadorSerá(Integer int1) {
        Assert.assertEquals(int1.intValue(),contador);
    }

    Date entrega = new Date();

    @ParameterType("\\d{2}/\\d{2}/\\d{4}")
    public Date data(String s){
        System.out.println("PASSOU AQUI");
        DateFormat format = new SimpleDateFormat("dd/MM/yyyyy");
        try{
            Date retorno = format.parse(s);
            return retorno;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Dado("que o prazo é dia {data}")
    public void queOPrazoÉDia(Date data) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, dia);
//        cal.set(Calendar.MONTH, mes - 1); // para o JAVA Janeiro começa em 0 #JavaEstranhão
//        cal.set(Calendar.YEAR, ano);
//        entrega = cal.getTime();
        entrega = data;
    }
    // o teste a seguir está usando REGEX
    @Quando("^a entrega atrasar em (\\d+) (dia|dias|mes|meses)$")
    public void aEntregaAtrasarEmDias(Integer quantidade, String tempo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(entrega);
        if(tempo.equals("dias") || tempo.equals("dia"))
            cal.add(Calendar.DAY_OF_MONTH, quantidade);
        if(tempo.equals("meses") || tempo.equals("mes"))
            cal.add(Calendar.MONTH, quantidade);
        entrega = cal.getTime();
    }
    @Test // o teste a seguir está usando REGEX
    @Então("^a entrega será efetuada em (\\d{2}\\/\\d{2}\\/\\d{4})$")
    public void aEntregaSeráEfetuadaEm(String data) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = format.format(entrega);
        Assert.assertEquals(data, dataFormatada);
    }

    @Dado("^que o ticket( especial)? é (A.\\d{3})$")
    public void queOTicketÉ(String tipoTicket, String ticket) {

    }
    @Dado("que o valor da passagem é R$ {double}")
    public void queOValorDaPassagemÉR$(Double double1) {

    }
    @Dado("^que o nome do passageiro é (\"(.{5,20})\")$")
    public void queONomeDoPassageiroÉ(String string) {

    }

    @ParameterType("9\\d{3}-\\d{4}")
    public String telefone(String s){
        return s;
    }
    @Dado("que o telefone do passageiro é {telefone}")
    public void queOTelefoneDoPassageiroÉ(String telefone) {

    }
    @Quando("criar os steps")
    public void criarOsSteps() {

    }
    @Então("o teste vai funcionar")
    public void oTesteVaiFuncionar() {

    }

}
