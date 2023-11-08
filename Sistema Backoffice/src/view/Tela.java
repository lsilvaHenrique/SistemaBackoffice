package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.*;
import model.*;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Stack;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Tela extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfIDProduto;
	private JTextField tfNomeProduto;
	private JTextField tfDescrição;
	private JTextField tfValor;
	private JTextField tfEstoque;
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
	private JTextField tfCodigoCliente;
	private JTextField tfCodigoIdent;
	private JTextField tfTipoProduto;
    private JComboBox<String> produtosComboBox;
    private JComboBox<String> clienteComboBox;
    private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public Tela() {
		setTitle("Backoffice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 624, 441);
		contentPane.add(tabbedPane);
		
		JPanel tabTipoProduto = new JPanel();
		tabbedPane.addTab("Tipo de Produto", null, tabTipoProduto, "Cadastrar tipos de produtos");
		tabTipoProduto.setLayout(null);
		
		JLabel lblCodigoIdent = new JLabel("Codigo Identificador");
		lblCodigoIdent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoIdent.setBounds(38, 44, 158, 22);
		tabTipoProduto.add(lblCodigoIdent);
		
		JLabel lblTipoProduto = new JLabel("Tipo do produto");
		lblTipoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoProduto.setBounds(38, 121, 158, 22);
		tabTipoProduto.add(lblTipoProduto);
		
		tfCodigoIdent = new JTextField();
		tfCodigoIdent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCodigoIdent.setBounds(38, 77, 158, 20);
		tabTipoProduto.add(tfCodigoIdent);
		tfCodigoIdent.setColumns(10);
		
		tfTipoProduto = new JTextField();
		tfTipoProduto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfTipoProduto.setBounds(38, 154, 158, 20);
		tabTipoProduto.add(tfTipoProduto);
		tfTipoProduto.setColumns(10);
		
		JButton btnCadastrarTipoProd = new JButton("Cadastrar");
		btnCadastrarTipoProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCadastrarTipoProd.setBounds(38, 209, 95, 28);
		tabTipoProduto.add(btnCadastrarTipoProd);
		
		JButton btnBuscarTipoProd = new JButton("Buscar");
		btnBuscarTipoProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscarTipoProd.setBounds(236, 70, 88, 28);
		tabTipoProduto.add(btnBuscarTipoProd);
		
		JButton btnExcluirTipoProd = new JButton("Excluir");
		btnExcluirTipoProd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExcluirTipoProd.setBounds(157, 211, 95, 27);
		tabTipoProduto.add(btnExcluirTipoProd);
		
		JPanel tabProduto = new JPanel();
		tabbedPane.addTab("Produto", null, tabProduto, "Cadastro de produtos");
		tabProduto.setLayout(null);
		
		JLabel lblNomeProduto = new JLabel("Nome Produto");
		lblNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeProduto.setBounds(41, 139, 136, 17);
		tabProduto.add(lblNomeProduto);
		
		JLabel lblCodProduto = new JLabel("Codigo Produto");
		lblCodProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodProduto.setBounds(41, 83, 112, 20);
		tabProduto.add(lblCodProduto);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(329, 39, 58, 14);
		tabProduto.add(lblValor);
		
		JLabel lblDescrição = new JLabel("Descrição ");
		lblDescrição.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescrição.setBounds(41, 198, 112, 17);
		tabProduto.add(lblDescrição);
		
		JLabel lblEstoque = new JLabel("Quantidade em estoque");
		lblEstoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstoque.setBounds(329, 108, 182, 17);
		tabProduto.add(lblEstoque);
		
		tfIDProduto = new JTextField();
		tfIDProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfIDProduto.setBounds(41, 108, 136, 20);
		tabProduto.add(tfIDProduto);
		tfIDProduto.setColumns(10);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNomeProduto.setBounds(41, 167, 196, 20);
		tabProduto.add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		tfDescrição = new JTextField();
		tfDescrição.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDescrição.setBounds(41, 226, 196, 45);
		tabProduto.add(tfDescrição);
		tfDescrição.setColumns(10);
		
		tfValor = new JTextField();
		tfValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfValor.setBounds(329, 64, 136, 20);
		tabProduto.add(tfValor);
		tfValor.setColumns(10);
		
		tfEstoque = new JTextField();
		tfEstoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfEstoque.setBounds(329, 136, 149, 20);
		tabProduto.add(tfEstoque);
		tfEstoque.setColumns(10);
		
		JButton btnCadastrarProduto = new JButton("Cadastrar");
		btnCadastrarProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrarProduto.setBounds(275, 236, 112, 35);
		tabProduto.add(btnCadastrarProduto);
		
		JButton btnBuscarProduto = new JButton("Buscar");
		btnBuscarProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscarProduto.setBounds(197, 99, 97, 35);
		tabProduto.add(btnBuscarProduto);
		
		JButton btnExcluirProduto = new JButton("Excluir");
		btnExcluirProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluirProduto.setBounds(430, 236, 112, 35);
		tabProduto.add(btnExcluirProduto);
		
		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(0, 282, 619, 131);
		tabProduto.add(scrollPaneProduto);
		
		JTextArea taListaProduto = new JTextArea();
		scrollPaneProduto.setViewportView(taListaProduto);
		
		JComboBox<TipoProduto> tipoProdutoComboBox = new JComboBox<TipoProduto>();
		tipoProdutoComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tipoProdutoComboBox.setBounds(41, 52, 253, 20);
	
		tabProduto.add(tipoProdutoComboBox);
					
		JPanel tabCliente = new JPanel();
		tabbedPane.addTab("Cliente", null, tabCliente, "Cadastro de clientes");
		tabCliente.setLayout(null);
		
		JLabel lblPessoaFisica = new JLabel("Pessoa Física");
		lblPessoaFisica.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPessoaFisica.setBounds(52, 11, 138, 28);
		tabCliente.add(lblPessoaFisica);
		
		JLabel lblPessoaJuridica = new JLabel("Pessoa Juridica");
		lblPessoaJuridica.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPessoaJuridica.setBounds(450, 18, 147, 14);
		tabCliente.add(lblPessoaJuridica);
		
		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCPF.setBounds(10, 65, 86, 14);
		tabCliente.add(lblCPF);
		
		JLabel lblNomePF = new JLabel("Nome\r\n");
		lblNomePF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNomePF.setBounds(10, 90, 46, 14);
		tabCliente.add(lblNomePF);
		
		JLabel lblEndereçoPF = new JLabel("Endereço");
		lblEndereçoPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEndereçoPF.setBounds(10, 151, 84, 14);
		tabCliente.add(lblEndereçoPF);
		
		JLabel lblNmrEndPF = new JLabel("Nº");
		lblNmrEndPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNmrEndPF.setBounds(10, 176, 46, 14);
		tabCliente.add(lblNmrEndPF);
		
		JLabel lblComplementoPF = new JLabel("Complemento");
		lblComplementoPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComplementoPF.setBounds(106, 179, 104, 14);
		tabCliente.add(lblComplementoPF);
		
		tfCPF = new JTextField();
		tfCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCPF.setBounds(43, 65, 160, 20);
		tabCliente.add(tfCPF);
		tfCPF.setColumns(10);
		
		tfNomePF = new JTextField();
		tfNomePF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfNomePF.setBounds(52, 90, 225, 20);
		tabCliente.add(tfNomePF);
		tfNomePF.setColumns(10);
		
		JLabel lbLou = new JLabel("OU");
		lbLou.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbLou.setBounds(298, 18, 46, 14);
		tabCliente.add(lbLou);
		
		tfEnderecoPF = new JTextField();
		tfEnderecoPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfEnderecoPF.setBounds(68, 149, 201, 20);
		tabCliente.add(tfEnderecoPF);
		tfEnderecoPF.setColumns(10);
		
		tfNmrEnderecoPF = new JTextField();
		tfNmrEnderecoPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfNmrEnderecoPF.setBounds(42, 176, 54, 20);
		tabCliente.add(tfNmrEnderecoPF);
		tfNmrEnderecoPF.setColumns(10);
		
		tfComplemento = new JTextField();
		tfComplemento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfComplemento.setBounds(188, 176, 114, 20);
		tabCliente.add(tfComplemento);
		tfComplemento.setColumns(10);
		
		JLabel lblCepPF = new JLabel("CEP");
		lblCepPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCepPF.setBounds(10, 204, 46, 14);
		tabCliente.add(lblCepPF);
		
		tfCEPPF = new JTextField();
		tfCEPPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCEPPF.setBounds(43, 201, 114, 20);
		tabCliente.add(tfCEPPF);
		tfCEPPF.setColumns(10);
		
		JLabel lblCNPJ = new JLabel("CNPJ");
		lblCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCNPJ.setBounds(352, 65, 46, 14);
		tabCliente.add(lblCNPJ);
		
		JLabel lblNomeFantasiaPJ = new JLabel("Nome Fantasia");
		lblNomeFantasiaPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNomeFantasiaPJ.setBounds(352, 93, 114, 14);
		tabCliente.add(lblNomeFantasiaPJ);
		
		JLabel lblEnderecoPJ = new JLabel("Endereço");
		lblEnderecoPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEnderecoPJ.setBounds(352, 118, 86, 14);
		tabCliente.add(lblEnderecoPJ);
		
		JLabel lblNmrEnderecoPJ = new JLabel("Nº");
		lblNmrEnderecoPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNmrEnderecoPJ.setBounds(543, 118, 46, 14);
		tabCliente.add(lblNmrEnderecoPJ);
		
		JLabel lblComplementoPJ = new JLabel("Complemento");
		lblComplementoPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComplementoPJ.setBounds(352, 142, 106, 14);
		tabCliente.add(lblComplementoPJ);
		
		JLabel lblCEPPJ = new JLabel("CEP");
		lblCEPPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCEPPJ.setBounds(531, 143, 46, 14);
		tabCliente.add(lblCEPPJ);
		
		JLabel lblTelefonePJ = new JLabel("Telefone");
		lblTelefonePJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTelefonePJ.setBounds(352, 167, 86, 14);
		tabCliente.add(lblTelefonePJ);
		
		JLabel lblEmailPJ = new JLabel("Email");
		lblEmailPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmailPJ.setBounds(352, 189, 70, 14);
		tabCliente.add(lblEmailPJ);
		
		tfCNPJ = new JTextField();
		tfCNPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCNPJ.setBounds(392, 62, 124, 20);
		tabCliente.add(tfCNPJ);
		tfCNPJ.setColumns(10);
		
		tfNomePJ = new JTextField();
		tfNomePJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfNomePJ.setBounds(436, 91, 173, 20);
		tabCliente.add(tfNomePJ);
		tfNomePJ.setColumns(10);
		
		tfEndPJ = new JTextField();
		tfEndPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfEndPJ.setBounds(412, 116, 121, 20);
		tabCliente.add(tfEndPJ);
		tfEndPJ.setColumns(10);
		
		tfNmrEndPJ = new JTextField();
		tfNmrEndPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfNmrEndPJ.setBounds(563, 116, 46, 20);
		tabCliente.add(tfNmrEndPJ);
		tfNmrEndPJ.setColumns(10);
		
		tfComplementoPJ = new JTextField();
		tfComplementoPJ.setBounds(430, 141, 98, 20);
		tabCliente.add(tfComplementoPJ);
		tfComplementoPJ.setColumns(10);
		
		tfCEPPJ = new JTextField();
		tfCEPPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCEPPJ.setBounds(553, 143, 61, 20);
		tabCliente.add(tfCEPPJ);
		tfCEPPJ.setColumns(10);
		
		tfTelefonePJ = new JTextField();
		tfTelefonePJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfTelefonePJ.setBounds(412, 167, 104, 20);
		tabCliente.add(tfTelefonePJ);
		tfTelefonePJ.setColumns(10);
		
		tjEmailPJ = new JTextField();
		tjEmailPJ.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tjEmailPJ.setBounds(412, 189, 104, 20);
		tabCliente.add(tjEmailPJ);
		tjEmailPJ.setColumns(10);
		
		JButton btnNCadastrarCliente = new JButton("Cadastrar");
		btnNCadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNCadastrarCliente.setBounds(249, 230, 104, 28);
		tabCliente.add(btnNCadastrarCliente);
		
		JButton btnExcluirCliente = new JButton("Excluir");
		btnExcluirCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExcluirCliente.setBounds(363, 230, 104, 28);
		tabCliente.add(btnExcluirCliente);
		
		JButton btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscarCliente.setBounds(141, 230, 98, 28);
		tabCliente.add(btnBuscarCliente);
		
		JScrollPane scrollPaneCliente = new JScrollPane();
		scrollPaneCliente.setBounds(0, 269, 619, 133);
		tabCliente.add(scrollPaneCliente);
		
		JTextArea taListaCliente = new JTextArea();
		scrollPaneCliente.setViewportView(taListaCliente);
		
		JLabel lblCelularPF = new JLabel("Celular");
		lblCelularPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCelularPF.setBounds(10, 115, 61, 14);
		tabCliente.add(lblCelularPF);
		
		tfCelularPF = new JTextField();
		tfCelularPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfCelularPF.setBounds(52, 115, 158, 20);
		tabCliente.add(tfCelularPF);
		tfCelularPF.setColumns(10);
		
		ProdutoController pCont = new ProdutoController(tfIDProduto, tfNomeProduto, tfDescrição, tfValor, 
		tfEstoque, taListaProduto, tipoProdutoComboBox);
		
		JLabel lblCodigoIdentificador = new JLabel("Identificador do Tipo de produto");
		lblCodigoIdentificador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigoIdentificador.setBounds(41, 25, 212, 17);
		tabProduto.add(lblCodigoIdentificador);
		
		btnCadastrarProduto.addActionListener(pCont);
		btnBuscarProduto.addActionListener(pCont);
		btnExcluirProduto.addActionListener(pCont);
		
		
		tfCodigoCliente = new JTextField();
		tfCodigoCliente.setBounds(260, 43, 86, 20);
		tabCliente.add(tfCodigoCliente);
		tfCodigoCliente.setColumns(10);
		
		ClienteController cCont = new ClienteController(tfCodigoCliente,tfCPF, tfNomePF, 
				tfEnderecoPF, tfNmrEnderecoPF, tfComplemento, tfCEPPF, tfCNPJ, tfNomePJ, 
				tfEndPJ, tfNmrEndPJ, tfComplementoPJ, tfCEPPJ, tfTelefonePJ, tjEmailPJ, tfCelularPF, taListaCliente);
		
		JLabel lblCodigo = new JLabel("Codigo Cliente");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigo.setBounds(170, 45, 98, 14);
		tabCliente.add(lblCodigo);
		
		btnNCadastrarCliente.addActionListener(cCont);
		btnBuscarCliente.addActionListener(cCont);
		btnExcluirCliente.addActionListener(cCont);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 246, 599, 156);
		tabTipoProduto.add(scrollPane);
		
		JTextArea taTipoProduto = new JTextArea();
		scrollPane.setViewportView(taTipoProduto);
		
		String path = System.getProperty("user.home") + File.separator + "SistemaCadastro" + File.separator + "tipoProduto.csv";
		
		TipoProdutoController tpCont = new TipoProdutoController(tfTipoProduto, tfCodigoIdent, taTipoProduto);
		
		List<TipoProduto> tiposDeProdutos = tpCont.lerTiposDeProdutosDoCSV(path);
		
		for (TipoProduto tipo : tiposDeProdutos) {
            tipoProdutoComboBox.addItem(tipo);
        }

        tabProduto.add(tipoProdutoComboBox);
          
        btnBuscarTipoProd.addActionListener(tpCont);
		btnCadastrarTipoProd.addActionListener(tpCont);
		btnExcluirTipoProd.addActionListener(tpCont);
        
        JPanel tabCarrinho = new JPanel();
        tabbedPane.addTab("Carrinho de compra", null, tabCarrinho, null);
        tabCarrinho.setLayout(null);
        
        JLabel lblProduto = new JLabel("Produto");
        lblProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProduto.setBounds(62, 72, 69, 14);
        tabCarrinho.add(lblProduto);
        
        produtosComboBox = new JComboBox<String>();
        produtosComboBox.setBounds(123, 70, 129, 22);
        tabCarrinho.add(produtosComboBox);
        
        clienteComboBox = new JComboBox<String>();
        clienteComboBox.setBounds(123, 28, 129, 22);
        tabCarrinho.add(clienteComboBox);
        
        JButton btnAdicionarCarrinho = new JButton("Adicionar");
        btnAdicionarCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAdicionarCarrinho.setBounds(36, 118, 89, 23);
        tabCarrinho.add(btnAdicionarCarrinho);
        
        JButton btnRemoverCarrinho = new JButton("Remover");
        btnRemoverCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRemoverCarrinho.setBounds(163, 118, 89, 23);
        tabCarrinho.add(btnRemoverCarrinho);
        
        JTextArea taCarrinho = new JTextArea();
        taCarrinho.setBounds(317, 27, 279, 172);
        tabCarrinho.add(taCarrinho);
        
        JTextArea taCheckout = new JTextArea();
        taCheckout.setBounds(596, 27, 13, 172);
        tabCarrinho.add(taCheckout);
        
        JButton btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnFinalizarCompra.setBounds(74, 176, 147, 23);
        tabCarrinho.add(btnFinalizarCompra);
        
        JButton btnMostrarCompras = new JButton("Mostrar Compras");
        btnMostrarCompras.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnMostrarCompras.setBounds(256, 365, 147, 37);
        tabCarrinho.add(btnMostrarCompras);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 238, 599, 109);
        tabCarrinho.add(scrollPane_1);
        
        JTextArea taCompras = new JTextArea();
        scrollPane_1.setViewportView(taCompras);
        
        JLabel lblCliente = new JLabel("Código Cliente");
        lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCliente.setBounds(24, 26, 89, 22);
        tabCarrinho.add(lblCliente);
               
        CarrinhoDeCompras tcp = new CarrinhoDeCompras(); 
        
        
        btnAdicionarCarrinho.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigoCliente = (String) clienteComboBox.getSelectedItem();
                String nomeProduto = (String) produtosComboBox.getSelectedItem();
                
                if (nomeProduto != null && !nomeProduto.isEmpty() && codigoCliente != null) {
                    Produto produto = tcp.buscarProdutoPorNome(nomeProduto);
                    Cliente cliente = tcp.buscarClientePorCodigo(codigoCliente); // Chame o método no objeto criado
                    if (produto != null && cliente != null) {
                        ElementoCarrinho elemento = new ElementoCarrinho(produto, 1, cliente);
                        tcp.adicionarAoCarrinho(elemento);
                        JOptionPane.showMessageDialog(null, "Produto Adicionado");
                        taCarrinho.append(produto.toString() + "\n");
                        taCarrinho.append(cliente.codigo + "\n\n");
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto ou cliente não encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um cliente e um produto.");
                }
            }
        });


        btnRemoverCarrinho.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ElementoCarrinho elementoRemovido = tcp.removerDoCarrinho();
                if (elementoRemovido != null) {
                    taCarrinho.setText(""); 
                    Stack<ElementoCarrinho> elementos = tcp.carrinho;
                    for (ElementoCarrinho elemento : elementos) {
                        taCarrinho.append(elemento.toString() + "\n"); 
                    }
                    JOptionPane.showMessageDialog(frame, "Produto removido: " + elementoRemovido.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "Carrinho vazio.");
                }
            }
        });
      
        carregarProdutosNoComboBox();
        carregarClienteNoComboBox();
        
        
        btnFinalizarCompra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkout();
                tcp.limparCarrinho();
                taCarrinho.setText(" ");
            }

			private void checkout() {
        	double total = 0;
            taCheckout.setText("");
            Queue<ElementoCarrinho> elementos = new LinkedList<>();
            elementos.addAll(tcp.carrinho);
                while (!elementos.isEmpty()) {
                	ElementoCarrinho elemento = elementos.poll(); 
                	Produto produto = elemento.produto;
                	int quantidade = elemento.quantidade;
                	Cliente cliente = elemento.cliente;
                	double subtotal = quantidade * produto.valor;
                    total += subtotal;

                    taCheckout.append("Codigo Cliente: " + cliente.codigo + "\n");
                    taCheckout.append("Produto: " + produto.nome + "\n");
                    taCheckout.append("Quantidade: " + quantidade + "\n");
                    taCheckout.append("Subtotal: R$" + subtotal + "\n\n");
                }

                taCheckout.append("Total da Compra: R$" + total);
                JOptionPane.showMessageDialog(null, taCheckout, "Detalhes da Compra", JOptionPane.INFORMATION_MESSAGE);
                tcp.salvarCompraEmCSV();
            //    tcp.carrinho.clear(); 
        }

        });
        
        btnMostrarCompras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mostrarComprasNoTextArea();
            	JOptionPane.showMessageDialog(null, "Todas as compras disponíveis!");
            }

			private void mostrarComprasNoTextArea() {
				try {
			        String userDir = System.getProperty("user.home") + File.separator + "SistemaCadastro";
			        File compraCSV = new File(userDir, "compra.csv");

			        if (compraCSV.exists()) {
			            BufferedReader reader = new BufferedReader(new FileReader(compraCSV));
			            StringBuilder comprasText = new StringBuilder();
			            String line;

			            while ((line = reader.readLine()) != null) {
			                String[] parts = line.split(";");
			                if (parts.length >= 4) {
			                    String produtoCodigo = parts[0];
			                    String produtoNome = parts[1];
			                    double produtoValor = Double.parseDouble(parts[2]);
			                    int quantidade = Integer.parseInt(parts[3]);
			                    String codigoCliente = parts[4];
			                    	                    
			                    comprasText.append("Codigo Cliente: ").append(codigoCliente).append("\n");
			                    comprasText.append("Produto: ").append(produtoNome).append("\n");
			                    comprasText.append("Código Produto: ").append(produtoCodigo).append("\n");
			                    comprasText.append("Valor: R$").append(produtoValor).append("\n");
			                    comprasText.append("Quantidade: ").append(quantidade).append("\n\n");			                    
			                }
			            }

			            taCompras.setText(comprasText.toString());
			            reader.close();
			        } else {
			            taCompras.setText("Nenhuma compra encontrada.");
			        }
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
        });
             
	}
		private void carregarProdutosNoComboBox() {
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
	                    produtosComboBox.addItem(productName);  
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
		
		private void carregarClienteNoComboBox() {
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
	                    clienteComboBox.addItem(clienteCod);
	                	                }
	            }
	            reader.close();
				} else {
					JOptionPane.showMessageDialog(null, "Arquivo de cliente não encontrado.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 }