package model;

public class ElementoCarrinho {
	    public Produto produto;
	    public int quantidade;
	    public Cliente cliente;
	    
		public ElementoCarrinho(Produto produto, int quantidade, Cliente cliente) {
			super();
			this.produto = produto;
			this.quantidade = quantidade;
			this.cliente = cliente;
		}

		@Override
		public String toString() {
			return produto+";"+quantidade+";"+cliente;
		}
	    
		
	}
