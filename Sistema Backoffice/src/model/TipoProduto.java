package model;

public class TipoProduto {
    public String codigoIdent;
    public String tipoProduto; 

        @Override
        public String toString() {
            return codigoIdent + ";" + tipoProduto;
        }
    }
