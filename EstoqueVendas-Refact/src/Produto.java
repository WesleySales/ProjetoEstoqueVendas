import java.util.Comparator;

public class Produto implements Comparable<Produto> {

    private static int geradorSKU = 1000;
    private String nome;
    private int SKU;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.SKU = geradorSKU++;
        this.preco = preco;
        this.estoque = estoque;
    }

    public int compareTo(Produto p) {
        return Integer.compare(this.estoque, p.getEstoque());
    }

    public String getNome() {
        return nome;
    }

    public int getSKU() {
        return SKU;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSKU(int SKU) {
        this.SKU = SKU;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return
                "\nnome=" + nome +
                        ", SKU= #" + SKU +
                        ", preco= R$ "+ preco +
                        ", estoque=" + estoque;
    }
}

    class ComparatorPorPreco implements Comparator<Produto> {
        @Override
        public int compare(Produto p1, Produto p2) {

            return Double.compare(p1.getPreco(), p2.getPreco());
        }
    }

