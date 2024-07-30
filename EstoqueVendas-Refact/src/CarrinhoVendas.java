public class CarrinhoVendas {

    private static int pedidoInicial = 100;
    private int numeroDoPedido;
    private Produto produto;
    private int quantidade;
    private double precoItemCarrinho;
    private double precoTotalCarrinho;



    public CarrinhoVendas(Produto produto, int quantidade, double precoItemCarrinho, double precoTotalCarrinho) {
        this.numeroDoPedido= pedidoInicial++;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoItemCarrinho = precoItemCarrinho;
        this.precoTotalCarrinho = precoTotalCarrinho;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "\n#"+numeroDoPedido +" - "+produto.getNome() +", " +quantidade + ", R$" + precoItemCarrinho + ", R$" + precoTotalCarrinho;
    }

}
