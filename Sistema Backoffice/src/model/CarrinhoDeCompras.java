package model;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.io.PrintWriter;
import java.io.FileWriter;

public class CarrinhoDeCompras {
    public Stack<ElementoCarrinho> carrinho; // Usando Stack para implementar uma pilha dinâmica
    public JTextField produtoTextField;
    public JTextArea carrinhoTextArea;
    public JComboBox<String> produtosComboBox;
    
    public CarrinhoDeCompras() {
        carrinho = new Stack<>(); // Inicializa a pilha
        produtosComboBox = new JComboBox<>(); // Inicializa o JComboBox
        carregarProdutosNoComboBox();
    }
    
    public void carregarProdutosNoComboBox() {
    	try {
	        String userDir = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	        File arquivoCSV = new File(userDir, "produto.csv");

	        if (arquivoCSV.exists()) {
	            BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV));
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(";");
	                if (parts.length >= 2) {
	                    String productName = parts[1];
	                    produtosComboBox.addItem(productName);  // Adicionar o nome do produto ao JComboBox
	                }
	            }
	            reader.close();
	        } else {
	            JOptionPane.showMessageDialog(null, "Arquivo de produtos não encontrado.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }

    public Produto buscarProdutoPorNome(String nome) {
    	try {
            String userDir = System.getProperty("user.home") + File.separator + "SistemaCadastro";
            File arquivoCSV = new File(userDir, "produto.csv");

            if (arquivoCSV.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 2) {
                        String productName = parts[1];
                        if (productName.equalsIgnoreCase(nome)) {
                            Produto produto = new Produto();
                            produto.codigo = parts[0];
                            produto.nome = productName;
                            produto.valor = Double.parseDouble(parts[2]);
                            produto.descricao = parts[3];
                            produto.quantidadeEmEstoque = Integer.parseInt(parts[4]);
                            reader.close();
                            return produto;
                        }
                    }
                }
                reader.close();
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo de produtos não encontrado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public Cliente buscarClientePorCodigo(String codigo) {
    	try {
            String userDir = System.getProperty("user.home") + File.separator + "SistemaCadastro";
            File arquivoCSV = new File(userDir, "cliente.csv");
            if (arquivoCSV.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 2) {
                        String clienteCod = parts[0];
                        if (clienteCod.equalsIgnoreCase(codigo)) {
                            Cliente cliente = new Cliente();
                            cliente.codigo = clienteCod;
                            reader.close();
                            return cliente;
                        }
                    }
                }
                reader.close();
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo de cliente não encontrado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void adicionarAoCarrinho(ElementoCarrinho elemento) {
        carrinho.push(elemento); // Adiciona o elemento no topo da pilha
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio");
        }
    }

    public ElementoCarrinho removerDoCarrinho() {
        if (!carrinho.isEmpty()) {
            return carrinho.pop(); // Remove o elemento do topo da pilha
        } else {
            return null;
        }
    }
    
    public void limparCarrinho() {
        carrinho.clear(); // Remove todos os elementos do carrinho
    }
    
    public void salvarCompraEmCSV() {
        try {
            String userDir = System.getProperty("user.home") + File.separator + "SistemaCadastro";
            File compraCSV = new File(userDir, "compra.csv");

            // Abra o arquivo no modo de adição (append)
            PrintWriter writer = new PrintWriter(new FileWriter(compraCSV, true));

            Stack<ElementoCarrinho> elementos = new Stack<>();
            elementos.addAll(carrinho);

            while (!elementos.isEmpty()) {
                ElementoCarrinho elemento = elementos.pop();
                Produto produto = elemento.produto; // Acessa o campo "produto"
                int quantidade = elemento.quantidade; // Acessa o campo "quantidade"
                Cliente cliente = elemento.cliente;
                writer.println(produto.codigo + ";" + produto.nome + ";" + produto.valor + ";" + quantidade + ";" + cliente.codigo);
            }

            writer.close();
            JOptionPane.showMessageDialog(null, "Compra finalizada e adicionada a compra.csv");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar a compra.");
        }
    }


}
