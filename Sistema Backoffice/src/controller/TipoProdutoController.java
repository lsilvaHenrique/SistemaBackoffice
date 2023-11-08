package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList; 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import model.*;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TipoProdutoController implements ActionListener{
	private JTextField tfTipoProduto;
	private JTextField tfCodigoIdent;
	private JTextArea taTipoProduto;
	
	
	public TipoProdutoController(JTextField tfTipoProduto, JTextField tfCodigoIdent, JTextArea taTipoProduto) {
		this.tfTipoProduto = tfTipoProduto;
		this.tfCodigoIdent = tfCodigoIdent;
		this.taTipoProduto = taTipoProduto;
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
			try {
				excluir();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
}

	private void excluir() throws IOException {
	    String codigoIdent = tfCodigoIdent.getText();
	    if (!codigoIdent.isEmpty()) {
	        TipoProduto tipoProduto = buscaCodigo(codigoIdent);

	        if (tipoProduto != null) {
	            String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	            File arq = new File(path, "tipoProduto.csv");

	            if (arq.exists() && arq.isFile()) {
	                BufferedReader buffer = new BufferedReader(new FileReader(arq));
	                String linha;
	                StringBuilder novoConteúdo = new StringBuilder();

	                while ((linha = buffer.readLine()) != null) {
	                    String[] vetLinha = linha.split(";");
	                    if (vetLinha.length >= 2 && vetLinha[0].equals(codigoIdent)) {
	                        // Não adicione a linha ao novo conteúdo, efetivamente excluindo-a.
	                    } else {
	                        novoConteúdo.append(linha).append("\n");
	                    }
	                }

	                buffer.close();

	                // Reescreva o arquivo com o novo conteúdo
	                PrintWriter writer = new PrintWriter(arq);
	                writer.print(novoConteúdo.toString());
	                writer.close();

	                JOptionPane.showMessageDialog(null, "Tipo de produto excluído com sucesso.");

	                // Limpe os campos após a exclusão
	                tfCodigoIdent.setText("");
	                tfTipoProduto.setText("");
	                taTipoProduto.setText("");
	            } else {
	                JOptionPane.showMessageDialog(null, "O arquivo de tipos de produtos não existe.", "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Tipo de produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Preencha o campo de código de identificação.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}


	private void busca() throws Exception {
		TipoProduto tipoProduto = new TipoProduto();
		tipoProduto.codigoIdent = tfCodigoIdent.getText();
		
		if (!tipoProduto.codigoIdent.equals("")) {
			tipoProduto = buscaCodigo(tipoProduto.codigoIdent);
			if (tipoProduto != null ) {
				taTipoProduto.setText("Codigo: "+tipoProduto.codigoIdent+"  - Tipo Produto: "+tipoProduto.tipoProduto);
			}
	    } else {
			JOptionPane.showMessageDialog(null, "Preencha um campo", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private TipoProduto buscaCodigo(String codigoIdent) throws IOException {
		TipoProduto tipoProduto = new TipoProduto();
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "tipoProduto.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if(vetLinha[0].equals(codigoIdent)) {
					tipoProduto.codigoIdent = vetLinha[0];
					tipoProduto.tipoProduto = vetLinha[1];
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
			}
		if (tipoProduto.codigoIdent == null) {
			tipoProduto = null;
		}
		return tipoProduto;
	}


	private void cadastro() throws IOException {
		TipoProduto tipoProduto = new TipoProduto();
		tipoProduto.codigoIdent = tfCodigoIdent.getText();
		tipoProduto.tipoProduto = tfTipoProduto.getText();
		
		cadastraTipoProduto(tipoProduto.toString());
		JOptionPane.showMessageDialog(null, "Tipo de produto Cadastrado");
		
		tfCodigoIdent.setText("");
		tfTipoProduto.setText("");
	}

	private void cadastraTipoProduto(String csvTipoProduto) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File(path, "tipoProduto.csv");
		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvTipoProduto+"\r\n");
		pw.flush();
		pw.close();
		fw.close();	
	}
	
	String path = System.getProperty("user.home") + File.separator + "SistemaCadastro" + File.separator + "tipoProduto.csv";
	
	 public List<TipoProduto> lerTiposDeProdutosDoCSV(String path) {
	        List<TipoProduto> tiposDeProdutos = new ArrayList<>();

	        try {
	            BufferedReader br = new BufferedReader(new FileReader(path));
	            String linha;

	            while ((linha = br.readLine()) != null) {
	                String[] partes = linha.split(";");
	                if (partes.length == 2) {
	                    String codigoIdent = partes[0];
	                    String tipoProduto = partes[1];
	                    TipoProduto tp = new TipoProduto();
	                    tp.codigoIdent = codigoIdent;
	                    tp.tipoProduto = tipoProduto;
	                    tiposDeProdutos.add(tp);
	                }
	            }

	            br.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return tiposDeProdutos;
	    }
	
}
	

