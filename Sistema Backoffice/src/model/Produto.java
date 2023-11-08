package model;

public class Produto {
    public String codigo;
    public String nome;
    public Double valor;
    public String descricao;
    public int quantidadeEmEstoque;
    public TipoProduto tipo;

    
	@Override
	public String toString() {
		return codigo+";"+nome+";"+valor+";"+descricao+";"+quantidadeEmEstoque+";"+tipo; 
				//+ valor + descricao
		        //+ quantidadeEmEstoque +tipo;
	}

}

