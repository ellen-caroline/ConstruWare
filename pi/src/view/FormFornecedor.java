	package view;
	
	
	import java.awt.Color;
	import java.awt.EventQueue;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.text.ParseException;
	import java.util.List;
	
	import javax.swing.DefaultComboBoxModel;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JDesktopPane;
	import javax.swing.JFormattedTextField;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTabbedPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.border.EmptyBorder;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.text.MaskFormatter;
	
	import dao.FornecedorDAO;
	import model.Fornecedor;
	import utilitarios.Util;
	
	public class FormFornecedor extends JFrame {
		
	
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private final JPanel panel = new JPanel();
		private JTextField txtNome;
		private JTextField txtCNPJ;
		private JTextField txtTelefone;
		private JTextField txtRua;
		private JTextField txtNum;
		private JTextField txtBairro;
		private JTextField txtCEP;
		private JTextField txtCidade;
		private JTextField txtEmail;
		private JTextField txtId;
		private JComboBox<String> cbEstado; 
		private JTextField txtPesquisaNome;
		private JTable tabela;
		
		private void atualizarTabela() {
		    FornecedorDAO dao = new FornecedorDAO();
		    List<Fornecedor> lista = dao.Listar();
		    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		    modelo.setRowCount(0); 
		    for (Fornecedor f : lista) {
		        modelo.addRow(new Object[]{
		            f.getId(),
		            f.getNome(),
		            f.getCnpj(),
		            f.getTelefone(),
		            f.getEmail(),
		            f.getCep(),
		            f.getEstado(),
		            f.getCidade(),
		            f.getRua(),
		            f.getBairro(),
		            f.getNum(),
		            
		            
		            
		        });
		    }
		}
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FormFornecedor frame = new FormFornecedor();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						frame.atualizarTabela();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		
		/**
		 * Create the frame.
		 * @throws ParseException 
		 */
		public FormFornecedor() throws ParseException {
			setTitle("Formulário de Fornecedores");
			setBackground(new Color(127, 127, 127));
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 725, 438);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(127, 127, 127));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			
			JDesktopPane desktopPane = new JDesktopPane();
			desktopPane.setBounds(12, 12, 1, 1);
			contentPane.add(desktopPane);
			panel.setBackground(new Color(3, 52, 124));
			panel.setBounds(0, 0, 714, 40);
			contentPane.add(panel);
			
			JLabel lblCadastroDeFornecedor = new JLabel("CADASTRO DE FORNECEDORES");
			lblCadastroDeFornecedor.setBackground(new Color(0, 0, 0));
			lblCadastroDeFornecedor.setFont(new Font("Liberation Sans", Font.BOLD, 25));
			lblCadastroDeFornecedor.setForeground(new Color(0, 0, 0));
			panel.add(lblCadastroDeFornecedor);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setToolTipText("");
			tabbedPane.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			tabbedPane.setBorder(null);
			tabbedPane.setForeground(new Color(0, 0, 0));
			tabbedPane.setBackground(new Color(3, 52, 124));
			tabbedPane.setBounds(-2, 46, 730, 300);
			contentPane.add(tabbedPane);
			
			JPanel dadosPessoais = new JPanel();
			dadosPessoais.setBorder(null);
			dadosPessoais.setBackground(new Color(119, 118, 123));
			tabbedPane.addTab("Dados Fornecedores", null, dadosPessoais, null);
			tabbedPane.setEnabledAt(0, true);
			dadosPessoais.setLayout(null);
			
			JLabel lblCpf = new JLabel("CNPJ: ");
			lblCpf.setForeground(new Color(0, 0, 0));
			lblCpf.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblCpf.setBounds(47, 44, 55, 15);
			dadosPessoais.add(lblCpf);
			
			txtNome = new JTextField();
			txtNome.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent evt) {
					if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
						String nome = txtNome.getText();
						Fornecedor obj = new Fornecedor();
						FornecedorDAO dao = new FornecedorDAO();
						
						obj = dao.Pesquisar(nome);
						if(obj.getNome() != null) {
							txtId.setText(String.valueOf(obj.getId()));
							txtNome.setText(obj.getNome());
							txtCNPJ.setText(obj.getCnpj());
							txtTelefone.setText(obj.getTelefone());
							txtEmail.setText(obj.getEmail());
							txtCEP.setText(obj.getCep());
							cbEstado.setSelectedItem(obj.getEstado());
							txtCidade.setText(obj.getCidade());
							txtRua.setText(obj.getRua());
							txtBairro.setText(obj.getBairro());
							txtNum.setText(String.valueOf(obj.getNum()));
							
							} else{
								JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
							}
					}
				}
			});
			
			txtNome.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtNome.setText("\n");
			txtNome.setColumns(10);
			txtNome.setBounds(101, 74, 410, 19);
			dadosPessoais.add(txtNome);
			
			JLabel lblCpf_1_1 = new JLabel("NOME: ");
			lblCpf_1_1.setForeground(new Color(0, 0, 0));
			lblCpf_1_1.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblCpf_1_1.setBounds(43, 76, 60, 15);
			dadosPessoais.add(lblCpf_1_1);
			
			JLabel lblCpf_1_1_1 = new JLabel("CELULAR: ");
			lblCpf_1_1_1.setForeground(new Color(0, 0, 0));
			lblCpf_1_1_1.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblCpf_1_1_1.setBounds(465, 37, 78, 19);
			dadosPessoais.add(lblCpf_1_1_1);
			
			JLabel lblEndereo = new JLabel("ENDEREÇO:");
			lblEndereo.setForeground(new Color(0, 0, 0));
			lblEndereo.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblEndereo.setBounds(197, 111, 90, 15);
			dadosPessoais.add(lblEndereo);
			
			txtRua = new JTextField();
			txtRua.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtRua.setBounds(289, 108, 389, 19);
			dadosPessoais.add(txtRua);
			txtRua.setColumns(10);
			
			JLabel lblCep = new JLabel("CEP:");
			lblCep.setForeground(new Color(0, 0, 0));
			lblCep.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblCep.setBounds(58, 111, 35, 15);
			dadosPessoais.add(lblCep);
			
			JLabel lblN = new JLabel("N°:");
			lblN.setForeground(new Color(0, 0, 0));
			lblN.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblN.setBounds(70, 141, 26, 15);
			dadosPessoais.add(lblN);
			
			txtNum = new JTextField();
			txtNum.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtNum.setBounds(101, 140, 41, 19);
			dadosPessoais.add(txtNum);
			txtNum.setColumns(10);
			
			JLabel lblBairro = new JLabel("BAIRRO:");
			lblBairro.setForeground(new Color(0, 0, 0));
			lblBairro.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblBairro.setBounds(161, 144, 61, 15);
			dadosPessoais.add(lblBairro);
			
			txtBairro = new JTextField();
			txtBairro.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtBairro.setBounds(231, 139, 114, 19);
			dadosPessoais.add(txtBairro);
			txtBairro.setColumns(10);
			
			JLabel lblCidade = new JLabel("CIDADE: ");
			lblCidade.setForeground(new Color(0, 0, 0));
			lblCidade.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblCidade.setBounds(489, 141, 61, 15);
			dadosPessoais.add(lblCidade);
			
			txtCidade = new JTextField();
			txtCidade.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtCidade.setBounds(557, 139, 119, 19);
			dadosPessoais.add(txtCidade);
			txtCidade.setColumns(10);
			
			JLabel lblUf = new JLabel("ESTADO:");
			lblUf.setForeground(new Color(0, 0, 0));
			lblUf.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblUf.setBounds(356, 141, 70, 15);
			dadosPessoais.add(lblUf);
			
			JLabel lblEmail = new JLabel("E-MAIL:");
			lblEmail.setForeground(new Color(0, 0, 0));
			lblEmail.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblEmail.setBounds(38, 177, 60, 15);
			dadosPessoais.add(lblEmail);
			
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtEmail.setBounds(101, 175, 244, 19);
			dadosPessoais.add(txtEmail);
			txtEmail.setColumns(10);
			
			JButton btnNovo = new JButton("NOVO");
			btnNovo.setForeground(new Color(0, 0, 0));
			btnNovo.setBackground(new Color(101, 146, 201));
			btnNovo.setIcon(new ImageIcon(FormFornecedor.class.getResource("/imagens/add.png")));
			btnNovo.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			btnNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Util util = new Util();
					util.LimpaTela(dadosPessoais);
				}
			});
			btnNovo.setBounds(57, 215, 114, 36);
			dadosPessoais.add(btnNovo);
			
			
	
			JButton btnSalvar = new JButton("SALVAR");
			btnSalvar.setBackground(new Color(101, 146, 201));
			btnSalvar.setForeground(new Color(0, 0, 0));
			btnSalvar.setIcon(new ImageIcon(FormFornecedor.class.getResource("/imagens/salvar.png")));
			btnSalvar.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			btnSalvar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
	
				 Fornecedor obj = new Fornecedor();
				 obj.setNome(txtNome.getText());
				 obj.setCnpj(txtCNPJ.getText());
				 obj.setTelefone(txtTelefone.getText());
				 obj.setEmail(txtEmail.getText());
				 obj.setCep(txtCEP.getText());
				 obj.setEstado(cbEstado.getSelectedItem().toString());
				 obj.setCidade(txtCidade.getText());
				 obj.setRua(txtRua.getText());
				 obj.setBairro(txtBairro.getText());
				 obj.setNum(Integer.valueOf(txtNum.getText()));
				 
				 FornecedorDAO dao = new FornecedorDAO();
				 dao.Salvar(obj);
				 Util util = new Util();
				 util.LimpaTela(dadosPessoais);
				 
				}
			});
			btnSalvar.setBounds(206, 215, 124, 36);
			dadosPessoais.add(btnSalvar);
			
			JButton btnExcluir = new JButton("EXCLUIR");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Fornecedor obj = new Fornecedor();
					obj.setId(Integer.valueOf(txtId.getText()));
					FornecedorDAO dao = new FornecedorDAO();
					dao.Excluir(obj);
					Util util = new Util();
					util.LimpaTela(dadosPessoais);
				}
			});
			btnExcluir.setForeground(new Color(0, 0, 0));
			btnExcluir.setBackground(new Color(101, 146, 201));
			btnExcluir.setIcon(new ImageIcon(FormFornecedor.class.getResource("/imagens/excluir.png")));
			btnExcluir.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			btnExcluir.setBounds(521, 215, 135, 36);
			dadosPessoais.add(btnExcluir);
			
			JButton btnEditar = new JButton("EDITAR");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Fornecedor obj = new Fornecedor();
					 obj.setId(Integer.valueOf(txtId.getText()));
					 obj.setNome(txtNome.getText());
					 obj.setCnpj(txtCNPJ.getText());
					 obj.setTelefone(txtTelefone.getText());
					 obj.setEmail(txtEmail.getText());
					 obj.setCep(txtCEP.getText());
					 obj.setEstado(cbEstado.getSelectedItem().toString());
					 obj.setCidade(txtCidade.getText());
					 obj.setRua(txtRua.getText());
					 obj.setBairro(txtBairro.getText());
					 obj.setNum(Integer.valueOf(txtNum.getText()));
					 
					 FornecedorDAO dao = new FornecedorDAO();
					 dao.Editar(obj);
					 Util util = new Util();
					 util.LimpaTela(dadosPessoais);
				}
			});
			btnEditar.setBackground(new Color(101, 146, 201));
			btnEditar.setForeground(new Color(0, 0, 0));
			btnEditar.setIcon(new ImageIcon(FormFornecedor.class.getResource("/imagens/editar.png")));
			btnEditar.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			btnEditar.setBounds(364, 215, 122, 36);
			dadosPessoais.add(btnEditar);
			
			JLabel lblCodigo = new JLabel("CÓDIGO:");
			lblCodigo.setForeground(new Color(0, 0, 0));
			lblCodigo.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblCodigo.setBounds(28, 14, 70, 15);
			dadosPessoais.add(lblCodigo);
			
			txtId = new JTextField();
			txtId.setEnabled(false);
			txtId.setEditable(false);
			txtId.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtId.setBounds(100, 12, 70, 19);
			dadosPessoais.add(txtId);
			txtId.setColumns(10);
			
			txtCNPJ = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
			txtCNPJ.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtCNPJ.setBounds(100, 42, 168, 19);
			dadosPessoais.add(txtCNPJ);
			
			txtTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
			txtTelefone.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtTelefone.setBounds(541, 34, 126, 25);
			dadosPessoais.add(txtTelefone);
			
			txtCEP = new JFormattedTextField(new MaskFormatter("#####-###"));
			txtCEP.setFont(new Font("Liberation Sans", Font.PLAIN, 14));
			txtCEP.setBounds(101, 109, 90, 19);
			dadosPessoais.add(txtCEP);
			
			cbEstado = new JComboBox<>();
			cbEstado.setModel(new DefaultComboBoxModel<>(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
			cbEstado.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			cbEstado.setBounds(423, 140, 50, 19);
			dadosPessoais.add(cbEstado);
			
			JButton btnPesquisar_1 = new JButton("PESQUISAR");
			btnPesquisar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String nome = txtNome.getText();
					Fornecedor obj = new Fornecedor();
					FornecedorDAO dao = new FornecedorDAO();
					
					obj = dao.Pesquisar(nome);
					if(obj.getNome() != null) {
						txtId.setText(String.valueOf(obj.getId()));
						txtNome.setText(obj.getNome());
						txtCNPJ.setText(obj.getCnpj());
						txtTelefone.setText(obj.getTelefone());
						txtEmail.setText(obj.getEmail());
						txtCEP.setText(obj.getCep());
						cbEstado.setSelectedItem(obj.getEstado());
						txtCidade.setText(obj.getCidade());
						txtRua.setText(obj.getRua());
						txtBairro.setText(obj.getBairro());
						txtNum.setText(String.valueOf(obj.getNum()));
						
					} else{
						JOptionPane.showMessageDialog(null, "Fornecedor não Encontrado!");
					}
				}
			});
			btnPesquisar_1.setIcon(new ImageIcon(FormFornecedor.class.getResource("/imagens/pesquisar.png")));
			btnPesquisar_1.setForeground(Color.BLACK);
			btnPesquisar_1.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			btnPesquisar_1.setBackground(new Color(3, 52, 124));
			btnPesquisar_1.setBounds(521, 65, 156, 36);
			dadosPessoais.add(btnPesquisar_1);
			
			JPanel consultaFornecedor = new JPanel();
			consultaFornecedor.setBorder(null);
			consultaFornecedor.setForeground(new Color(0, 0, 0));
			consultaFornecedor.setBackground(new Color(119, 118, 123));
			tabbedPane.addTab("Consulta de Fornecedores", null, consultaFornecedor, null);
			consultaFornecedor.setLayout(null);
			
			JLabel lblCpf_1_1_2 = new JLabel("NOME: ");
			lblCpf_1_1_2.setForeground(new Color(0, 0, 0));
			lblCpf_1_1_2.setFont(new Font("Liberation Sans", Font.BOLD, 14));
			lblCpf_1_1_2.setBounds(138, 14, 60, 15);
			consultaFornecedor.add(lblCpf_1_1_2);
			
			txtPesquisaNome = new JTextField();
			txtPesquisaNome.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					String nome = "%"+txtPesquisaNome.getText()+"%";
				    FornecedorDAO dao = new FornecedorDAO();
				    List<Fornecedor> lista = dao.Filtrar(nome);
				    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
				    modelo.setRowCount(0); 
				    for (Fornecedor fornecedor : lista) {
				        modelo.addRow(new Object[]{
				        		
					            fornecedor.getId(),
					            fornecedor.getNome(),
					            fornecedor.getCnpj(),
					            fornecedor.getTelefone(),
					            fornecedor.getEmail(),
					            fornecedor.getCep(),
					            fornecedor.getEstado(),
					            fornecedor.getCidade(),
					            fornecedor.getRua(),
					            fornecedor.getBairro(),
					            fornecedor.getNum(),
					            
				        });
				    }
					
				}
			});
			txtPesquisaNome.setText("\n");
			txtPesquisaNome.setColumns(10);
			txtPesquisaNome.setBounds(195, 12, 373, 19);
			consultaFornecedor.add(txtPesquisaNome);
			
			JScrollPane painelDados = new JScrollPane();
			tabela = 		new JTable();
			tabela.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
						int selectedRow = tabela.getSelectedRow();
			            txtId.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
			            txtNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
			            txtCNPJ.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
			            txtTelefone.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
			            txtEmail.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
			            txtCEP.setText(tabela.getValueAt(tabela.getSelectedRow(), 5).toString());
			            cbEstado.setSelectedItem(tabela.getValueAt(tabela.getSelectedRow(), 6).toString());
			            txtCidade.setText(tabela.getValueAt(tabela.getSelectedRow(), 7).toString());
			            txtRua.setText(tabela.getValueAt(tabela.getSelectedRow(), 8).toString());
			            txtBairro.setText(tabela.getValueAt(tabela.getSelectedRow(), 9).toString());
			            txtNum.setText(tabela.getValueAt(tabela.getSelectedRow(), 10).toString());
			            
			            
			            tabbedPane.setSelectedIndex(1);
			        }
			});		
			tabela.setFont(new Font("Liberation Sans", Font.PLAIN, 12));
			tabela.setBorder(null);
			tabela.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"IdFornecedor", "Nome", "CNPJ", "Telefone", "Email", "CEP", "Estado", "Cidade", "Rua", "Bairro", "Num"
				}
			));
			painelDados.setViewportView(tabela);
			painelDados.setBounds(0, 40, 714, 219);
			consultaFornecedor.add(painelDados);
		}
	}
