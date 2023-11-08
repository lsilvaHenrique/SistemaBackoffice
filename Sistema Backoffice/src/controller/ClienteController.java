package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import model.*;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClienteController implements ActionListener{
	private JTextField tfCodigoCliente;
	private JTextField tfCPF;
	private JTextField tfNomePF;
	private JTextField tfEnderecoPF;
	private JTextField tfNmrEnderecoPF;
	private JTextField tfComplemento;
	private JTextField tfCEPPF;
	private JTextField tfCNPJ;
	private JTextField tfNomePJ;
	private JTextField tfEndPJ;
	private JTextField tfNmrEndPJ;
	private JTextField tfComplementoPJ;
	private JTextField tfCEPPJ;
	private JTextField tfTelefonePJ;
	private JTextField tjEmailPJ;
	private JTextField tfCelularPF;
	private JTextArea taListaCliente;
	
	public ClienteController(JTextField tfCodigoCliente, JTextField tfCPF, JTextField tfNomePF, JTextField tfEnderecoPF, JTextField 	tfNmrEnderecoPF, JTextField tfComplemento, JTextField tfCEPPF, JTextField tfCNPJ, JTextField tfNomePJ, JTextField 	tfEndPJ, JTextField tfNmrEndPJ, JTextField tfComplementoPJ, JTextField tfCEPPJ, JTextField tfTelefonePJ,
			JTextField tjEmailPJ, JTextField tfCelularPF, JTextArea taListaCliente) {
		this.tfCodigoCliente = tfCodigoCliente;
		this.tfCPF = tfCPF;
		this.tfNomePF = tfNomePF;
		this.tfEnderecoPF = tfEnderecoPF;
		this.tfNmrEnderecoPF = tfNmrEnderecoPF;
		this.tfComplemento = tfComplemento;
		this.tfCEPPF = tfCEPPF;
		this.tfCNPJ = tfCNPJ;
		this.tfNomePJ = tfNomePJ;
		this.tfEndPJ = tfEndPJ;
		this.tfNmrEndPJ = tfNmrEndPJ;
		this.tfComplementoPJ = tfComplementoPJ;
		this.tfCEPPJ = tfCEPPJ;
		this.tfTelefonePJ = tfTelefonePJ;
		this.tjEmailPJ = tjEmailPJ;
		this.tfCelularPF = tfCelularPF;
		this.taListaCliente = taListaCliente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Cadastrar")) {
			try {
				cadastro();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
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
	    String codigo = tfCodigoCliente.getText();
	    if (!codigo.isEmpty()) {
	        Cliente cliente = buscaCodigo(codigo);

	        if (cliente != null) {
	            String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	            File arq = new File(path, "cliente.csv");

	            if (arq.exists() && arq.isFile()) {
	                BufferedReader buffer = new BufferedReader(new FileReader(arq));
	                String linha;
	                StringBuilder novoConteúdo = new StringBuilder();

	                while ((linha = buffer.readLine()) != null) {
	                    String[] vetLinha = linha.split(";");
	                    if (vetLinha.length >= 5 && vetLinha[0].equals(codigo)) {
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

	                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");

	                // Limpe os campos após a exclusão
	                tfCodigoCliente.setText("");
	                tfCPF.setText("");
	                tfNomePF.setText("");
	                tfCNPJ.setText("");
	                tfNomePJ.setText("");
	                tfEnderecoPF.setText("");
	                tfEndPJ.setText("");
	                tfNmrEnderecoPF.setText("");
	                tfNmrEndPJ.setText("");
	                tfComplemento.setText("");
	                tfComplementoPJ.setText("");
	                tfCEPPF.setText("");
	                tfCEPPJ.setText("");
	                tfTelefonePJ.setText("");
	                tfCelularPF.setText("");
	                tjEmailPJ.setText("");
	                taListaCliente.setText("");
	            } else {
	                JOptionPane.showMessageDialog(null, "O arquivo de clientes não existe.", "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Preencha o campo de código.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}


	private void busca() throws Exception {
		Cliente cliente = new Cliente();
		cliente.codigo = tfCodigoCliente.getText();
		cliente.cpf = tfCPF.getText();
		cliente.nome = tfNomePF.getText();
		cliente.cnpj = tfCNPJ.getText();
		cliente.nomeFantasia = tfNomePJ.getText();	
		
		if (!cliente.codigo.equals("")) {
			cliente = buscaCodigo(cliente.codigo);
			if (cliente != null ) {
				taListaCliente.setText("Codigo: "+cliente.codigo+"  - CPF: "+
			cliente.cpf+"  - CNPJ: "+cliente.cnpj+"  - Nome: "+cliente.nome+"   - Nome Fantasia: "+cliente.nomeFantasia);
			}
	    } else {
			JOptionPane.showMessageDialog(null, "Preencha um campo", "Erro", JOptionPane.ERROR_MESSAGE);
		}
				
	}
		
	/*private Cliente buscaCodigo(String codigo) throws IOException {
		Cliente cliente = new Cliente();
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File arq = new File(path, "cliente.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader buffer = new BufferedReader(isr);
			String linha = buffer.readLine();
			while (linha != null) {
				String[] vetLinha = linha.split(";");
				if(vetLinha[0].equals(codigo)) {
					cliente.codigo = vetLinha[0];
					cliente.cpf = vetLinha[1];
					cliente.cnpj = vetLinha[2];
					cliente.nome = vetLinha[3];
					cliente.nomeFantasia = vetLinha[4];
				
					break;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			isr.close();
			fis.close();
			}
		if (cliente.codigo == null) {
			cliente = null;
		}
		return cliente;
		}
	 */

	private Cliente buscaCodigo(String codigo) throws IOException {
	    Cliente cliente = new Cliente();
	    cliente.codigo = codigo;
	    String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
	    File arq = new File(path, "cliente.csv");
	    if (arq.exists() && arq.isFile()) {
	        FileInputStream fis = new FileInputStream(arq);
	        InputStreamReader isr = new InputStreamReader(fis);
	        BufferedReader buffer = new BufferedReader(isr);
	        String linha = buffer.readLine();
	        while (linha != null) {
	            String[] vetLinha = linha.split(";");
	            if (vetLinha.length >= 5 && vetLinha[0].equals(codigo)) {
	                if (vetLinha[1] != null && !vetLinha[1].isEmpty()) {
	                    cliente.cpf = vetLinha[1];
	                }
	                if (vetLinha[2] != null && !vetLinha[2].isEmpty()) {
	                    cliente.cnpj = vetLinha[2];
	                }
	                if (vetLinha[3] != null && !vetLinha[3].isEmpty()) {
	                    cliente.nome = vetLinha[3];
	                }
	                if (vetLinha[4] != null && !vetLinha[4].isEmpty()) {
	                    cliente.nomeFantasia = vetLinha[4];
	                }
	                break;
	            }
	            linha = buffer.readLine();
	        }
	        buffer.close();
	        isr.close();
	        fis.close();
	    }
	    return cliente;
	}

	
	private void cadastro() throws IOException {
		Cliente cliente = new Cliente();
		cliente.codigo = tfCodigoCliente.getText();
		cliente.cpf = tfCPF.getText();
		cliente.nome = tfNomePF.getText();
		cliente.cnpj = tfCNPJ.getText();
		cliente.nomeFantasia = tfNomePJ.getText();
		cliente.enderecoPF = tfEnderecoPF.getText();
		cliente.enderecoPJ = tfEndPJ.getText();
		cliente.numeroEndPF = tfNmrEnderecoPF.getText();
		cliente.numeroEndPJ = tfNmrEndPJ.getText();
		cliente.complementoPF = tfComplemento.getText();
		cliente.complementoPJ = tfComplementoPJ.getText();
		cliente.cepPF = tfCEPPF.getText();
		cliente.cepPJ = tfCEPPJ.getText();
		cliente.telefone = tfTelefonePJ.getText();
		cliente.celular = tfCelularPF.getText();
		cliente.email =  tjEmailPJ.getText();
		       
		
		cadastraCliente(cliente.toString());
		JOptionPane.showMessageDialog(null, "Cliente Cadastrado");
		
		tfCodigoCliente.setText("");
		tfCPF.setText("");
		tfNomePF.setText("");
		tfCNPJ.setText("");
		tfNomePJ.setText("");
		tfEnderecoPF.setText("");
		tfEndPJ.setText("");
		tfNmrEnderecoPF.setText("");
		tfNmrEndPJ.setText("");
		tfComplemento.setText("");
		tfComplementoPJ.setText("");
		tfCEPPF.setText("");
		tfCEPPJ.setText("");
		tfTelefonePJ.setText("");
		tfCelularPF.setText("");
		tjEmailPJ.setText("");
	}

	private void cadastraCliente(String csvCliente) throws IOException {
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File arq = new File(path, "cliente.csv");
		boolean existe = false;
		if (arq.exists()) {
			existe = true;
		}
		FileWriter fw = new FileWriter(arq, existe);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(csvCliente+"\r\n");
		pw.flush();
		pw.close();
		fw.close();
	}
}
