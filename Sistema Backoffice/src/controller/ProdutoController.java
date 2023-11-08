package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import sp.fateczl.lucas.listaObj.*;
import model.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProdutoController implements ActionListener {
	private JTextField tfIDProduto;
	private JTextField tfNomeProduto;
	private JTextField tfDescrição;
	private JTextField tfValor;
	private JTextField tfEstoque;
	private	JTextArea taListaProduto;
	private JComboBox<TipoProduto> tipoProdutoComboBox;

	
	public ProdutoController(JTextField tfIDProduto, JTextField tfNomeProduto, JTextField tfDescrição,
			JTextField tfValor, JTextField tfEstoque, JTextArea taListaProduto,
			JComboBox<TipoProduto> tipoProdutoComboBox) {
		super();
		this.tfIDProduto = tfIDProduto;
		this.tfNomeProduto = tfNomeProduto;
		this.tfDescrição = tfDescrição;
		this.tfValor = tfValor;
		this.tfEstoque = tfEstoque;
		this.taListaProduto = taListaProduto;
		this.tipoProdutoComboBox = tipoProdutoComboBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
			if (cmd.equals("Cadastrar")) {
				try {
					cadastro();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (cmd.equals("Buscar")) {
				try {
					busca();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (cmd.equals("Excluir")) {
				excluir();
			}
	}

	private void excluir() {
	    String codigoExcluir = JOptionPane.showInputDialog("Digite o código do produto a ser excluído:");
	    
	    if (codigoExcluir != null && !codigoExcluir.isEmpty()) {
	        try {
	            boolean produtoExcluido = excluirProduto(codigoExcluir);
	            if (produtoExcluido) {
	                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso.");
	            } else {
	                JOptionPane.showMessageDialog(null, "Produto não encontrado ou não foi possível excluí-lo.", "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Erro ao excluir o produto.", "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Código de produto inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private boolean excluirProduto(String codigo) throws IOException {
	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	    File arq = new File(path, "produto.csv");
	    File tempFile = new File(path, "produto_temp.csv");
	    
	    if (arq.exists() && arq.isFile()) {
	        FileInputStream fis = new FileInputStream(arq);
	        InputStreamReader isr = new InputStreamReader(fis);
	        BufferedReader buffer = new BufferedReader(isr);
	        
	        FileWriter tempFileWriter = new FileWriter(tempFile, false);
	        PrintWriter tempPrintWriter = new PrintWriter(tempFileWriter);
	        
	        String linha;
	        boolean produtoExcluido = false;
	        
	        while ((linha = buffer.readLine()) != null) {
	            String[] vetLinha = linha.split(";");
	            
	            if (!vetLinha[0].equals(codigo)) {
	                tempPrintWriter.println(linha);
	            } else {
	                produtoExcluido = true;
	            }
	        }
	        
	        buffer.close();
	        isr.close();
	        fis.close();
	        tempPrintWriter.close();
	        tempFileWriter.close();
	        
	        if (produtoExcluido) {
	            arq.delete();
	            tempFile.renameTo(arq);
	        } else {
	            tempFile.delete();
	        }
	        
	        return produtoExcluido;
	    }
	    
	    return false;
	}


	private void busca() throws Exception {
		Produto produto = new Produto (); 
			produto.codigo = tfIDProduto.getText();
			produto.tipo = (TipoProduto) tipoProdutoComboBox.getSelectedItem();
		//	produto.nome = tfNomeProduto.getText();
			

	/*	Object selectedItem = tipoProdutoComboBox.getSelectedItem();
			if (selectedItem != null && selectedItem instanceof TipoProduto) {
				produto.tipo = (TipoProduto) selectedItem;   
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um tipo de produto válido.", "Erro", JOptionPane.ERROR_MESSAGE);
			} */ 
	    Lista produtos = new Lista(); 	
			if (!produto.codigo.equals("")) {
				produto = buscaCodigo(produto.codigo);
				if (produto != null ) {
					taListaProduto.setText("Codigo: "+produto.codigo+"  - Nome: "+
							produto.nome+"   Tipo produto: "+produto.tipo);
				}
		//	} else if (!produto.nome.equals("")) {
			//	produtos = buscaNome(produto.nome);
			} else if (produto.tipo != null) {
				produtos = buscaTipo(produto.tipo);
			} else {
				JOptionPane.showMessageDialog(null, "Preencha um campo", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		
			int tamanhoLista = produtos.size();
			StringBuffer buffer = new StringBuffer();
			if (tamanhoLista > 0) {
				for (int i = 0 ; i < tamanhoLista ; i++) {
					Produto p = (Produto) produtos.get(i);
					buffer.append("Codigo: "+p.codigo+"  - Nome: "+p.nome+"   - Tipo: "+produto.tipo+"\r\n");
				}	
				taListaProduto.setText(buffer.toString());
			}
			tfIDProduto.setText("");
			tfNomeProduto.setText("");
	}
		
	private Lista buscaTipo(TipoProduto tipo) throws IOException {
		Lista produtos = new Lista();
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	    File arq = new File(path, "produto.csv");
	    if (arq.exists() && arq.isFile()) {
	    	FileInputStream fis = new FileInputStream(arq);
	        InputStreamReader isr = new InputStreamReader(fis);
	        BufferedReader buffer = new BufferedReader(isr);
	        String linha = buffer.readLine();
	        while (linha != null) {
	        	String[] vetLinha = linha.split(";");
	            // Verifique se o tipo do produto na linha corresponde ao tipo selecionado.
	        	if (vetLinha[5].equals(tipo.codigoIdent)) {
	        		Produto produto = new Produto();
	                produto.codigo = vetLinha[0];
	                produto.nome = vetLinha[1];
	                
	                produtos.addFirst(produto);
	            }
	            linha = buffer.readLine();
	        }
	        buffer.close();
	        isr.close();
	        fis.close();
	    }
	    return produtos;
	}

	/*private Lista buscaNome(String nome) throws IOException {
		Lista produtos = new Lista();
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "produto.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if(vetLinha[1].equals(nome)) {
					Produto produto = new Produto();
					produto.codigo = vetLinha[0];
					produto.nome = vetLinha[1];
					produtos.addFirst(produto);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
			}
		return produtos;
		} */
		
	private Produto buscaCodigo(String codigo) throws IOException {
		Produto produto = new Produto();
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "produto.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if(vetLinha[0].equals(codigo)) {
					produto.codigo = vetLinha[0];
					produto.nome = vetLinha[1];
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
			}
		if (produto.codigo == null) {
			produto = null;
		}
		return produto;
		}

	private void cadastro() throws IOException {
		Produto produto = new Produto (); 
		
		produto.codigo = tfIDProduto.getText();
		produto.nome = tfNomeProduto.getText();  
		produto.valor = Double.parseDouble(tfValor.getText());
		produto.descricao = tfDescrição.getText();  
		produto.quantidadeEmEstoque = Integer.parseInt(tfEstoque.getText());
		produto.tipo = (TipoProduto) tipoProdutoComboBox.getSelectedItem();

		cadastraProduto(produto.toString());
		JOptionPane.showMessageDialog(null, "Produto Cadastrado");
		
		tfIDProduto.setText("");
		tfNomeProduto.setText("");
		tfValor.setText("");
		tfDescrição.setText("");
		tfEstoque.setText("");
	}

	private void cadastraProduto(String csvProduto) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File(path, "produto.csv");
		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvProduto+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}
} 
	