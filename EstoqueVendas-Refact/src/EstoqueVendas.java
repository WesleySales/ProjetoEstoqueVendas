import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstoqueVendas {

    private List<Produto> produtoList;
    private List<CarrinhoVendas> carrinhoVendasList;

    private double faturamento=0;
    public EstoqueVendas() {
        this.produtoList = new ArrayList<>();
        this.carrinhoVendasList = new ArrayList<>();
    }



    //Cadastrar um novo produto
    public void cadastrarProduto(String nome, double preco, int estoque){
        produtoList.add(new Produto(nome,preco,estoque));
        //System.out.println("Produto adicionado com sucesso!");
    }

    public void realizarCompra(String nome, int quantidade) {
        double valorItem = 0;
        if (!produtoList.isEmpty()) {
            for (Produto p : produtoList) {
                if (p.getNome().equalsIgnoreCase(nome)) {
                    valorItem = p.getPreco() * quantidade;
                    atualizarEstoque(p,quantidade);
                    carrinhoVendasList.add(new CarrinhoVendas(p, quantidade, p.getPreco(), valorItem));
                    faturamento+=valorItem;
                }
            }
        } else {
            System.out.println("Produto nao encontrado na lista");
        }
    }

    public void exibirVendas(){
        System.out.println(carrinhoVendasList);
    }
    public void atualizarEstoque(Produto p, int quantidade){
            int novaQuantidade=0;
            novaQuantidade=p.getEstoque()-quantidade;
            p.setEstoque(novaQuantidade);
    }
    public void deletarProduto(String nome){
        if(!produtoList.isEmpty()){
            for(Produto p: produtoList)
                if (p.getNome().equalsIgnoreCase(nome)) {
                    produtoList.remove(p);
                    //System.out.println("Produto deletado com sucesso!");
                }
//        } else {
//            throw new RuntimeException("A lista está vazia");
        }
    }

    public void editarProduto(String nome, String novoNome, double novoPreco, int novaQuantidade){
        if(!produtoList.isEmpty()){
            for(Produto p: produtoList){
                if(p.getNome().equalsIgnoreCase(nome)){
                    p.setNome(novoNome);
                    p.setPreco(novoPreco);
                    p.setEstoque(novaQuantidade);
                    System.out.println("Produto editado com sucesso!");
                }
            }
        } else {
            throw new RuntimeException("A lista está vazia");
        }
    }

    public double getFaturamento(){
        return faturamento;
    }

    //Exibir produtos cadastrados
    public void exibirProdutosCadastrados(){
        System.out.println(produtoList);
    }

    public void exibirDetalhesDeVendas(){
        System.out.println(String.format("Faturamento: R$ %.2f",faturamento));
        System.out.println(String.format("N° Vendas: %d", carrinhoVendasList.size()));
        System.out.println(String.format("Ticket Médio: R$ %.2f",faturamento/carrinhoVendasList.size()));
    }


    public List<Produto> ordenarPorPreco(){
        List<Produto> listaPorPreco = new ArrayList<>(produtoList);
        if(!produtoList.isEmpty()){
            Collections.sort(listaPorPreco, new ComparatorPorPreco());
            return listaPorPreco;
        }
        else{
            throw new RuntimeException("A lista está vazia");
        }
    }

//    public void adicionarAoCarrinho(Produto produto, String nome, int quantidade){
//        List<CarrinhoVendas> carrinhoList = new ArrayList<>();
//        double precoCarrinho=0;
//        if(!produtoList.isEmpty()){
//            for(Produto p:produtoList){
//                if (p.getNome().equalsIgnoreCase(nome)){
//
//                    precoCarrinho+=p.getPreco()*quantidade;
//                    carrinhoList.add(p);
//                }
//            }
//
//        }else {
//            System.out.println("Produto nao encontrado");
//        }
//    }



    public List<Produto> ordenarPorEstoque(){
        List<Produto> listaPorEstoque = new ArrayList<>(produtoList);
        if(!produtoList.isEmpty()){
            Collections.sort(listaPorEstoque);
            return listaPorEstoque;
        }
        else{
            throw new RuntimeException("A lista está vazia");
        }
    }
    //Pesquisar produto por nome
    public List<Produto> pesquisarPorNome(String nome){
        List<Produto> produtosPorNome = new ArrayList<>();
        if(!produtoList.isEmpty()){
            for(Produto p:produtoList){
                if (p.getNome().equalsIgnoreCase(nome)){
                    produtosPorNome.add(p);
                }
            }
            return produtosPorNome;
        } else {
            throw new RuntimeException("A lista de produtos está vazia");
        }
    }
    //Pesquisar produto por SKU
    public List<Produto> pesquisarPorSKU(int SKU){
        List<Produto> produtosPorSKU = new ArrayList<>();
        if(!produtoList.isEmpty()){
            for(Produto p:produtoList){
                if (p.getSKU()==SKU){
                    produtosPorSKU.add(p);
                }
            }
            return produtosPorSKU;
        } else {
            throw new RuntimeException("A lista de produtos está vazia");
        }
    }

    public List<Produto> pesquisarPorPreco(double precoMinimo, double precoMaximo){
        List<Produto> produtosPorPreco = new ArrayList<>();
        if(!produtoList.isEmpty()){
            for(Produto p:produtoList){
                if (p.getPreco() >= precoMinimo && p.getPreco() <=precoMaximo){
                    produtosPorPreco.add(p);
                }
            }
            return produtosPorPreco;
        } else {
            throw new RuntimeException("A lista de produtos está vazia");
        }
    }


    public static void main(String[] args) {
        EstoqueVendas estoqueVendas = new EstoqueVendas();

        estoqueVendas.cadastrarProduto("Produto 1",39,60);
        estoqueVendas.cadastrarProduto("Produto 2", 36.60,35);
        estoqueVendas.cadastrarProduto("Produto 3", 35,30);
        estoqueVendas.exibirProdutosCadastrados();

        estoqueVendas.realizarCompra("Produto 1", 20);
        estoqueVendas.realizarCompra("Produto 2", 10);
        estoqueVendas.realizarCompra("Produto 1", 40);
        estoqueVendas.realizarCompra("Produto 1", 1);
        estoqueVendas.realizarCompra("Produto 3", 20);

        estoqueVendas.exibirVendas();
        System.out.println("\n__________________________\n");
        estoqueVendas.exibirDetalhesDeVendas();




//        System.out.println("\nTestando ordenação por estoque (do menor pro maior): "+estoqueVendas.ordenarPorEstoque());
//        System.out.println("\nTestando ordenação por preço (do menor pro maior): "+estoqueVendas.ordenarPorPreco());

       // estoqueVendas.editarProduto("Produto 2","Produto Editado",45,60);
       // estoqueVendas.deletarProduto("Produto 1");
       // estoqueVendas.exibirProdutosCadastrados();

//        System.out.println("\nTestando pesquisa por nome: "+estoqueVendas.pesquisarPorNome("Produto 3"));
//        System.out.println("\nTestando pesquisa por SKU: "+estoqueVendas.pesquisarPorSKU("3202"));
//        System.out.println("\nTestando pesquisa por preço: "+estoqueVendas.pesquisarPorPreco(32,36));
    }
}
