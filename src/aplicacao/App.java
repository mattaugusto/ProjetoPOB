package aplicacao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import fachada.Fachada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {

	private JFrame frmProjetoDePob;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextArea textArea_1;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextArea textArea_2;
	private JTextField textField_10;
	private JTextArea textArea_3;
	private JTextArea textArea_4;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextArea textArea_5;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmProjetoDePob.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProjetoDePob = new JFrame();
		frmProjetoDePob.setTitle("Projeto de POB - Feito por Joel e Matheus");
		frmProjetoDePob.setResizable(false);
		frmProjetoDePob.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Fachada.inicializar();
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				Fachada.finalizar();
			}
		});
		frmProjetoDePob.setBounds(100, 100, 626, 491);
		frmProjetoDePob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmProjetoDePob.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Evento", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idString = textField_13.getText().trim();
					String nome = textField.getText().trim();
					String inicio = textField_1.getText().trim();
					String fim = textField_2.getText().trim();
					
					if (nome.isEmpty()) {
						throw new Exception("Preencha o nome do evento");
					}
					
					if (inicio.isEmpty()) {
						throw new Exception("Preencha a data de inicio do evento");
					}
					
					if (fim.isEmpty()) {
						throw new Exception("Preencha a data de fim do evento");
					}
					
					if (!idString.isEmpty()) {
						Integer id = Integer.valueOf(idString);
						Fachada.atualizarEvento(id, nome, inicio, fim);
						JOptionPane.showMessageDialog(null, "Evento atualizado com sucesso");
					} else {
						Fachada.cadastrarEvento(nome, inicio, fim);
						JOptionPane.showMessageDialog(null, "Evento cadastrado com sucesso");
					}
					textArea.setText(Fachada.listarEventos());
					textField_13.setText("");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnNewButton.setBounds(86, 121, 49, 25);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(76, 40, 114, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(76, 65, 114, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(76, 92, 114, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 40, 70, 15);
		panel.add(lblNome);
		
		JLabel lblInicio = new JLabel("Inicio:");
		lblInicio.setBounds(12, 65, 76, 15);
		panel.add(lblInicio);
		
		JLabel lblFim = new JLabel("Fim:");
		lblFim.setBounds(12, 96, 70, 15);
		panel.add(lblFim);
		
		JButton button = new JButton("-");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String nome = textField.getText().trim();
					if (nome.isEmpty()) {
						throw new Exception("Preencha o nome do evento");
					}
					Fachada.apagarEvento(nome);
					textArea.setText(Fachada.listarEventos());	
					JOptionPane.showMessageDialog(null, "Evento deletado com sucesso");
					textField.setText("");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		button.setBounds(137, 121, 49, 25);
		panel.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 158, 595, 269);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton_1 = new JButton("(*)");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(Fachada.listarEventos());
			}
		});
		btnNewButton_1.setBounds(490, 123, 117, 25);
		panel.add(btnNewButton_1);
		
		textField_13 = new JTextField();
		textField_13.setBounds(76, 12, 114, 19);
		panel.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel lblId_1 = new JLabel("Id");
		lblId_1.setBounds(12, 14, 70, 15);
		panel.add(lblId_1);
		
		textField_21 = new JTextField();
		textField_21.setBounds(338, 12, 114, 19);
		panel.add(textField_21);
		textField_21.setColumns(10);
		
		JLabel lblCpf_2 = new JLabel("CPF");
		lblCpf_2.setBounds(279, 14, 70, 15);
		panel.add(lblCpf_2);
		
		JButton btnNewButton_7 = new JButton("+");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String cpf = textField_21.getText().trim();
					String evento = textField.getText().trim();
					if (cpf.isEmpty()) {
						throw new Exception("Preeencha o CPF");
					}
					if (evento.isEmpty()) {
						throw new Exception("Preeencha o nome do Evento");
					}
					Fachada.adicionarParticipanteEvento(evento, cpf);
					JOptionPane.showMessageDialog(null, "Participante cadastrado no evento");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnNewButton_7.setBounds(350, 34, 98, 25);
		panel.add(btnNewButton_7);
		
		textField_22 = new JTextField();
		textField_22.setBounds(338, 65, 114, 19);
		panel.add(textField_22);
		textField_22.setColumns(10);
		
		JLabel lblTituloPalestra = new JLabel("Titulo palestra");
		lblTituloPalestra.setBounds(208, 67, 112, 15);
		panel.add(lblTituloPalestra);
		
		JButton button_11 = new JButton("+");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String titulo = textField_22.getText().trim();
					String evento = textField.getText().trim();
					if (titulo.isEmpty()) {
						throw new Exception("Preeencha o titulo da palestra");
					}
					if (evento.isEmpty()) {
						throw new Exception("Preeencha o nome do Evento");
					}
					Fachada.adicionarPalestraEvento(evento, titulo);
					JOptionPane.showMessageDialog(null, "Palestra cadastrada no evento");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		button_11.setBounds(350, 89, 98, 25);
		panel.add(button_11);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Palestrante", null, panel_2, null);
		panel_2.setLayout(null);
		
		textField_7 = new JTextField();
		textField_7.setBounds(89, 69, 114, 19);
		panel_2.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(89, 43, 114, 19);
		panel_2.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(89, 100, 114, 19);
		panel_2.add(textField_9);
		textField_9.setColumns(10);
		
		JButton button_3 = new JButton("+");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idString = textField_14.getText().trim();
					String cpf = textField_8.getText().trim();
					String nome = textField_7.getText().trim();
					String email = textField_9.getText().trim();
					String titulacao = textField_12.getText().trim();
					
					if (cpf.isEmpty()) {
						throw new Exception("Preencha o CPF");
					}
					
					if (nome.isEmpty()) {
						throw new Exception("Preencha o nome");
					}
					
					if (email.isEmpty()) {
						throw new Exception("Preencha o e-mail");
					}
					
					if (titulacao.isEmpty()) {
						throw new Exception("Preencha a titulação");
					}
					
					if (!idString.isEmpty()) {
						Integer id = Integer.valueOf(idString);
						Fachada.atualizarPalestrante(id, nome, cpf, email, titulacao);
						JOptionPane.showMessageDialog(null, "Palestrante atualizado com sucesso");
					} else {
						Fachada.cadastrarPalestrante(nome, cpf, email, titulacao);
						JOptionPane.showMessageDialog(null, "Palestrante cadastrado com sucesso");
					}
					textArea_2.setText(Fachada.listarPalestrantes());
					textField_8.setText("");
					textField_7.setText("");
					textField_9.setText("");
					textField_12.setText("");
					textField_14.setText("");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		button_3.setBounds(89, 163, 51, 25);
		panel_2.add(button_3);
		
		JButton button_4 = new JButton("-");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String cpf = textField_8.getText().trim();
					if (cpf.isEmpty()) {
						throw new Exception("Preencha o CPF");
					}
					Fachada.apagarPalestrante(cpf);
					JOptionPane.showMessageDialog(null, "Palestrante deletado com sucesso");
					textArea_2.setText(Fachada.listarPalestrantes());
					textField_8.setText("");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		button_4.setBounds(149, 163, 51, 25);
		panel_2.add(button_4);
		
		JLabel lblNome_2 = new JLabel("Nome");
		lblNome_2.setBounds(12, 69, 70, 15);
		panel_2.add(lblNome_2);
		
		JLabel lblCpf_1 = new JLabel("CPF");
		lblCpf_1.setBounds(12, 43, 70, 15);
		panel_2.add(lblCpf_1);
		
		JLabel lblNewLabel = new JLabel("E-mail");
		lblNewLabel.setBounds(12, 98, 70, 15);
		panel_2.add(lblNewLabel);
		
		JLabel lblTitulao = new JLabel("Titula\u00E7\u00E3o");
		lblTitulao.setBounds(12, 134, 70, 15);
		panel_2.add(lblTitulao);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 197, 595, 230);
		panel_2.add(scrollPane_2);
		
		textArea_2 = new JTextArea();
		textArea_2.setLineWrap(true);
		scrollPane_2.setViewportView(textArea_2);
		
		JButton btnNewButton_3 = new JButton("(*)");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_2.setText(Fachada.listarPalestrantes());
			}
		});
		btnNewButton_3.setBounds(490, 160, 117, 25);
		panel_2.add(btnNewButton_3);
		
		textField_12 = new JTextField();
		textField_12.setBounds(89, 131, 114, 19);
		panel_2.add(textField_12);
		textField_12.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setBounds(89, 12, 114, 19);
		panel_2.add(textField_14);
		textField_14.setColumns(10);
		
		JLabel lblId_2 = new JLabel("Id");
		lblId_2.setBounds(12, 14, 70, 15);
		panel_2.add(lblId_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Participante", null, panel_1, null);
		panel_1.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(93, 57, 114, 19);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(93, 31, 114, 19);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(93, 88, 114, 19);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(93, 119, 114, 19);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNome_1 = new JLabel("Nome:");
		lblNome_1.setBounds(12, 61, 70, 15);
		panel_1.add(lblNome_1);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(12, 33, 70, 15);
		panel_1.add(lblCpf);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(12, 92, 70, 15);
		panel_1.add(lblEmail);
		
		JLabel lblInstituio = new JLabel("Institui\u00E7\u00E3o");
		lblInstituio.setBounds(12, 123, 88, 15);
		panel_1.add(lblInstituio);
		
		JButton button_1 = new JButton("+");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idString = textField_20.getText().trim();
					String cpf = textField_4.getText().trim();
					String nome = textField_3.getText().trim();
					String email = textField_5.getText().trim();
					String instituicao = textField_6.getText().trim();
					
					if (cpf.isEmpty()) {
						throw new Exception("Preencha o CPF");
					}
					if (nome.isEmpty()) {
						throw new Exception("Preencha o nome");
					}
					if (email.isEmpty()) {
						throw new Exception("Preencha o email");
					}
					if (instituicao.isEmpty()) {
						throw new Exception("Preencha a instituição");
					}
					if (!idString.isEmpty()) {
						Integer id = Integer.valueOf(idString);
						Fachada.atualizarParticipante(id, nome, cpf, email, instituicao);
						JOptionPane.showMessageDialog(null, "Participante atualizado com sucesso");
					} else {
						Fachada.cadastrarParticipante(nome, cpf, email, instituicao);
						JOptionPane.showMessageDialog(null, "Participante cadastrado com sucesso");
					}
					textField_20.setText("");
					textField_4.setText("");
					textField_3.setText("");
					textField_5.setText("");
					textField_6.setText("");
					textArea_1.setText(Fachada.listarParticipantes());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		button_1.setBounds(110, 144, 44, 25);
		panel_1.add(button_1);
		
		JButton btnNewButton_2 = new JButton("-");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String cpf = textField_4.getText();
					if (cpf.isEmpty()) {
						throw new Exception("Preencha o CPF");
					}
					Fachada.apagarParticipante(cpf);
					textField_4.setText("");
					JOptionPane.showMessageDialog(null, "Participante deletado com sucesso");
					textArea_1.setText(Fachada.listarParticipantes());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnNewButton_2.setBounds(163, 144, 44, 25);
		panel_1.add(btnNewButton_2);
		
		JButton button_2 = new JButton("(*)");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_1.setText(Fachada.listarParticipantes());
			}
		});
		button_2.setBounds(490, 144, 117, 25);
		panel_1.add(button_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 177, 595, 250);
		panel_1.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setEditable(false);
		scrollPane_1.setViewportView(textArea_1);
		
		textField_20 = new JTextField();
		textField_20.setBounds(93, 5, 114, 19);
		panel_1.add(textField_20);
		textField_20.setColumns(10);
		
		JLabel lblId_3 = new JLabel("Id");
		lblId_3.setBounds(12, 7, 70, 15);
		panel_1.add(lblId_3);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Titula\u00E7\u00E3o", null, panel_3, null);
		panel_3.setLayout(null);
		
		textField_10 = new JTextField();
		textField_10.setBounds(70, 43, 114, 19);
		panel_3.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(12, 45, 70, 15);
		panel_3.add(lblTitulo);
		
		JButton button_5 = new JButton("+");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String titulo = textField_10.getText();
					String idString = textField_11.getText();
					if (titulo.isEmpty()) {
						throw new Exception("Preencha o titulo");
					}
					if (!idString.isEmpty()) {
						
						Integer id = Integer.valueOf(idString);
						Fachada.atualizarTitulacao(id, titulo);
						JOptionPane.showMessageDialog(null, "Titulação atualizada com sucesso");
					} else {
						Fachada.cadastrarTitulacao(titulo);
						JOptionPane.showMessageDialog(null, "Titulação cadastrada com sucesso");
					}
					textArea_3.setText(Fachada.listarTitulacoes());
					textField_10.setText("");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		button_5.setBounds(91, 63, 44, 25);
		panel_3.add(button_5);
		
		JButton button_6 = new JButton("-");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String titulo = textField_10.getText();
					if (titulo.isEmpty()) {
						throw new Exception("Preencha o titulo");
					}
					Fachada.apagarTitulacao(titulo);
					textField_10.setText("");
					textArea_3.setText(Fachada.listarTitulacoes());
					JOptionPane.showMessageDialog(null, "Titulação deletada com sucesso");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		button_6.setBounds(140, 63, 44, 25);
		panel_3.add(button_6);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 100, 595, 327);
		panel_3.add(scrollPane_3);
		
		textArea_3 = new JTextArea();
		textArea_3.setLineWrap(true);
		textArea_3.setEditable(false);
		scrollPane_3.setViewportView(textArea_3);
		
		JButton button_7 = new JButton("(*)");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_3.setText(Fachada.listarTitulacoes());
			}
		});
		button_7.setBounds(490, 63, 117, 25);
		panel_3.add(button_7);
		
		textField_11 = new JTextField();
		textField_11.setBounds(70, 12, 114, 19);
		panel_3.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(12, 14, 70, 15);
		panel_3.add(lblId);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Palestra", null, panel_5, null);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(12, 12, 70, 15);
		panel_5.add(lblNewLabel_1);
		
		textField_15 = new JTextField();
		textField_15.setBounds(100, 10, 114, 19);
		panel_5.add(textField_15);
		textField_15.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Titulo");
		lblNewLabel_2.setBounds(12, 39, 70, 15);
		panel_5.add(lblNewLabel_2);
		
		textField_16 = new JTextField();
		textField_16.setBounds(100, 37, 114, 19);
		panel_5.add(textField_16);
		textField_16.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Descrição");
		lblNewLabel_3.setBounds(12, 66, 70, 15);
		panel_5.add(lblNewLabel_3);
		
		textField_17 = new JTextField();
		textField_17.setBounds(100, 64, 114, 19);
		panel_5.add(textField_17);
		textField_17.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Duração");
		lblNewLabel_4.setBounds(12, 93, 70, 15);
		panel_5.add(lblNewLabel_4);
		
		textField_18 = new JTextField();
		textField_18.setBounds(100, 91, 114, 19);
		panel_5.add(textField_18);
		textField_18.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Cpf");
		lblNewLabel_5.setBounds(12, 120, 70, 15);
		panel_5.add(lblNewLabel_5);
		
		textField_19 = new JTextField();
		textField_19.setBounds(100, 118, 114, 19);
		panel_5.add(textField_19);
		textField_19.setColumns(10);
		
		JButton button_8 = new JButton("+");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String idString = textField_15.getText();
					String titulo = textField_16.getText();
					String desc = textField_17.getText();
					String duracao = textField_18.getText();
					String cpf = textField_19.getText();
					
					if (titulo.isEmpty()) {
						throw new Exception("Preencha o titulo");
					}
					
					if (desc.isEmpty()) {
						throw new Exception("Preencha a descrição");
					}
					
					if (duracao.isEmpty()) {
						throw new Exception("Preencha a duração");
					}
					
					if (cpf.isEmpty()) {
						throw new Exception("Preencha o CPF");
					}
					
					if (!idString.isEmpty()) {
						Integer id = Integer.valueOf(idString);
						Fachada.atualizarPalestra(id, titulo, desc, duracao, cpf);
						JOptionPane.showMessageDialog(null, "Palestra atualizada com sucesso");
					} else {
						Fachada.cadastrarPalestra(titulo, desc, duracao, cpf);
						JOptionPane.showMessageDialog(null, "Palestra cadastrada com sucesso");
					}
					textArea_5.setText(Fachada.listarPalestras());
					textField_15.setText("");
					textField_16.setText("");
					textField_17.setText("");
					textField_18.setText("");
					textField_19.setText("");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		button_8.setBounds(100, 142, 54, 25);
		panel_5.add(button_8);
		
		JButton button_9 = new JButton("-");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String titulo = textField_16.getText();
					if (titulo.isEmpty()) {
						throw new Exception("Preencha o titulo");
					}
					Fachada.apagarPalestra(titulo);
					textField_16.setText("");
					textArea_5.setText(Fachada.listarPalestras());
					JOptionPane.showMessageDialog(null, "Palestra deletada com sucesso");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		button_9.setBounds(156, 142, 54, 25);
		panel_5.add(button_9);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(12, 171, 595, 256);
		panel_5.add(scrollPane_5);
		
		textArea_5 = new JTextArea();
		textArea_5.setEditable(false);
		textArea_5.setLineWrap(true);
		scrollPane_5.setViewportView(textArea_5);
		
		JButton button_10 = new JButton("(*)");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_5.setText(Fachada.listarPalestras());
			}
		});
		button_10.setBounds(490, 142, 117, 25);
		panel_5.add(button_10);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Consultas", null, panel_4, null);
		panel_4.setLayout(null);
		
		JButton btnConsulta = new JButton("Consulta 1");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_4.setText(Fachada.consulta1());
			}
		});
		btnConsulta.setBounds(86, 12, 119, 25);
		panel_4.add(btnConsulta);
		
		JButton btnConsulta_1 = new JButton("Consulta 2");
		btnConsulta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_4.setText(Fachada.consulta2());
			}
		});
		btnConsulta_1.setBounds(217, 12, 117, 25);
		panel_4.add(btnConsulta_1);
		
		JButton btnConsulta_2 = new JButton("Consulta 3");
		btnConsulta_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_4.setText(Fachada.consulta3());
			}
		});
		btnConsulta_2.setBounds(346, 12, 117, 25);
		panel_4.add(btnConsulta_2);
		
		JButton btnNewButton_4 = new JButton("Consulta 4");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String titulo = JOptionPane.showInputDialog("Qual a titulação");
					textArea_4.setText(Fachada.consulta4(titulo));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnNewButton_4.setBounds(86, 49, 117, 25);
		panel_4.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Consulta 5");
		btnNewButton_5.setBounds(217, 49, 117, 25);
		panel_4.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Consulta 6");
		btnNewButton_6.setBounds(346, 49, 117, 25);
		panel_4.add(btnNewButton_6);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 89, 595, 338);
		panel_4.add(scrollPane_4);
		
		textArea_4 = new JTextArea();
		textArea_4.setEditable(false);
		textArea_4.setLineWrap(true);
		scrollPane_4.setViewportView(textArea_4);
	}
}
