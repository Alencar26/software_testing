package entidades;

public class Filme {

    private int estoque;
    private int aluguel;

    public void setEstoque(Integer qntUnidades) {
        this.estoque = qntUnidades;
    }

    public void setAluguel(Integer valorAluguel) {
        this.aluguel = valorAluguel;
    }

    public int getAluguel() {
        return aluguel;
    }

    public Integer getEstoque() {
        return estoque;
    }
}
