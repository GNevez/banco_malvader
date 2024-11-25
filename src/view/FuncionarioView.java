package view;

import controllers.RelatorioController;
import controllers.UsuarioController;
import dao.FuncionarioDAO;
import models.*;

import javax.swing.*;
import java.awt.*;

public class FuncionarioView extends JFrame {

    private final JLabel iconeLabel;
    private final JLabel tituloLabel;
    private final JButton aberturaButton;
    private final JButton encerramentoButton;
    private final JButton consultaButton;
    private final JButton alteracaoButton;
    private final JButton cadastroButton;
    private final JButton relatorioButton;
    private final JButton sairButton;

    public FuncionarioView(Cliente usuario) {
        super("Bem vindo " + usuario.getNome());

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // layout da tela

        Font primariaFont = new Font("SansSerif", Font.BOLD, 30); // fonte
        Font secundariaFont = new Font("SansSerif", Font.BOLD, 15);

        // label de titulo
        tituloLabel = new JLabel("Area do Funcionario");
        tituloLabel.setFont(primariaFont); // setar fonte
        tituloLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // botoes
        aberturaButton = new JButton("Abrir Conta");
        aberturaButton.setFont(secundariaFont);
        aberturaButton.setPreferredSize(new Dimension(200, 50));
        aberturaButton.setMaximumSize(new Dimension(200, 50));
        aberturaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        aberturaButton.addActionListener(e -> abrirConta());

        encerramentoButton = new JButton("Encerrar Conta");
        encerramentoButton.setFont(secundariaFont);
        encerramentoButton.setPreferredSize(new Dimension(200, 50));
        encerramentoButton.setMaximumSize(new Dimension(200, 50));
        encerramentoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        encerramentoButton.addActionListener(e -> encerrarConta());

        consultaButton = new JButton("Consultar Dados");
        consultaButton.setFont(secundariaFont);
        consultaButton.setPreferredSize(new Dimension(200, 50));
        consultaButton.setMaximumSize(new Dimension(200, 50));
        consultaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        consultaButton.addActionListener(e -> consultarDados());

        alteracaoButton = new JButton("Alterar Dados");
        alteracaoButton.setFont(secundariaFont);
        alteracaoButton.setPreferredSize(new Dimension(200, 50));
        alteracaoButton.setMaximumSize(new Dimension(200, 50));
        alteracaoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        alteracaoButton.addActionListener(e -> alterarDados());

        cadastroButton = new JButton("Cadastrar Funcionario");
        cadastroButton.setFont(secundariaFont);
        cadastroButton.setPreferredSize(new Dimension(200, 50));
        cadastroButton.setMaximumSize(new Dimension(200, 50));
        cadastroButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cadastroButton.addActionListener(e -> cadastrarFuncionario());

        relatorioButton = new JButton("Gerar Relatório");
        relatorioButton.setFont(secundariaFont);
        relatorioButton.setPreferredSize(new Dimension(200, 50));
        relatorioButton.setMaximumSize(new Dimension(200, 50));
        relatorioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        relatorioButton.addActionListener(e -> gerarRelatorio());

        sairButton = new JButton("Sair");
        sairButton.setFont(secundariaFont);
        sairButton.setPreferredSize(new Dimension(200, 50));
        sairButton.setMaximumSize(new Dimension(200, 50));
        sairButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        sairButton.addActionListener(e -> {
            dispose(); // fecha a TelaCliente
            // volta para a TelaInicial
            MainView mainView = new MainView(new UsuarioController());
            mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainView.setSize(500, 500);
            mainView.setVisible(true);
            mainView.setLocationRelativeTo(null);
        });

        // icone
        ImageIcon icone = new ImageIcon(getClass().getResource("/Logo.png"));
        Image img = icone.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        iconeLabel = new JLabel(new ImageIcon(img));
        iconeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // adicionar elementos
        add(iconeLabel);
        add(Box.createVerticalStrut(20));
        add(tituloLabel);
        add(Box.createVerticalStrut(20));
        add(aberturaButton);
        add(Box.createVerticalStrut(20));
        add(encerramentoButton);
        add(Box.createVerticalStrut(20));
        add(consultaButton);
        add(Box.createVerticalStrut(20));
        add(alteracaoButton);
        add(Box.createVerticalStrut(20));
        add(cadastroButton);
        add(Box.createVerticalStrut(20));
        add(relatorioButton);
        add(Box.createVerticalStrut(20));
        add(sairButton);
        add(Box.createVerticalStrut(20));
    }

    // Eventos dos botões
    private void abrirConta() {
        String[] tiposConta = {"Poupança", "Corrente"};
        String tipoConta = (String) JOptionPane.showInputDialog(this, "Selecione o tipo de conta:", "Abertura de Conta",
                JOptionPane.PLAIN_MESSAGE, null, tiposConta, tiposConta[0]);

        if (tipoConta != null) {
            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

            panel.add(new JLabel("Agência:"));
            JTextField agenciaField = new JTextField();
            panel.add(agenciaField);

            panel.add(new JLabel("Número da conta:"));
            JTextField numeroContaField = new JTextField();
            panel.add(numeroContaField);

            panel.add(new JLabel("Nome do cliente:"));
            JTextField nomeClienteField = new JTextField();
            panel.add(nomeClienteField);

            panel.add(new JLabel("CPF:"));
            JTextField cpfField = new JTextField();
            panel.add(cpfField);

            panel.add(new JLabel("Data de nascimento:"));
            JTextField dataNascimentoField = new JTextField();
            panel.add(dataNascimentoField);

            panel.add(new JLabel("Telefone:"));
            JTextField telefoneField = new JTextField();
            panel.add(telefoneField);

            panel.add(new JLabel("Endereço:"));
            JTextField enderecoField = new JTextField();
            panel.add(enderecoField);

            panel.add(new JLabel("Senha:"));
            JPasswordField senhaField = new JPasswordField();
            panel.add(senhaField);

            JTextField limiteField = null;
            JTextField dataVencimentoField = null;

            if (tipoConta.equals("Corrente")) {
                panel.add(new JLabel("Limite da conta:"));
                limiteField = new JTextField();
                panel.add(limiteField);

                panel.add(new JLabel("Data de vencimento:"));
                dataVencimentoField = new JTextField();
                panel.add(dataVencimentoField);
            }

            int result = JOptionPane.showConfirmDialog(this, panel, "Abertura de Conta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                ContaCliente conta;
                String agencia = agenciaField.getText();
                String numeroConta = numeroContaField.getText();
                String nomeCliente = nomeClienteField.getText();
                String cpf = cpfField.getText();
                String dataNascimento = dataNascimentoField.getText();
                String telefone = telefoneField.getText();
                String endereco = enderecoField.getText();
                String senha = new String(senhaField.getPassword());

                if (tipoConta.equals("Corrente")) {
                    double limite = Double.parseDouble(limiteField.getText());
                    String dataVencimento = dataVencimentoField.getText();
                    conta = new ContaCliente(agencia, numeroConta, nomeCliente, cpf, dataNascimento,
                            telefone, endereco, senha, tipoConta, limite, dataVencimento);
                } else {
                    conta = new ContaCliente(agencia, numeroConta, nomeCliente, cpf, dataNascimento,
                            telefone, endereco, senha, tipoConta);
                }

                FuncionarioDAO funcionarioDao = new FuncionarioDAO();


                JOptionPane.showMessageDialog(this, funcionarioDao.inserirConta(conta));
            }
        }
    }


    private void encerrarConta() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        panel.add(new JLabel("Senha do Administrador:"));
        JPasswordField senhaAdminField = new JPasswordField();
        panel.add(senhaAdminField);

        panel.add(new JLabel("Número da conta:"));
        JTextField numeroContaField = new JTextField();
        panel.add(numeroContaField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Encerrar Conta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String senhaAdmin = new String(senhaAdminField.getPassword());
            if (senhaAdmin.equals("admin123")) {
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

                JOptionPane.showMessageDialog(this, funcionarioDAO.encerrarConta(numeroContaField.getText()));
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void consultarDados() {
        String[] opcoes = {"Conta", "Funcionário", "Cliente"};
        String escolha = (String) JOptionPane.showInputDialog(this, "Escolha uma opção para consultar:", "Consulta de Dados",
        JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

        if (escolha != null) {
            String cpf = JOptionPane.showInputDialog(this, "Digite o CPF do usuário:", "Consulta de Dados", JOptionPane.PLAIN_MESSAGE);

            if (cpf != null && !cpf.trim().isEmpty()) {

                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

                UsuarioConta contas = funcionarioDAO.consultarDadosUsuario(cpf);

                if(contas != null) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Layout vertical

                    panel.add(new JLabel("Nome: " + contas.getNome()));
                    panel.add(new JLabel("Email: " + contas.getEmail()));
                    panel.add(new JLabel("CPF: " + contas.getCpf()));
                    panel.add(new JLabel("Data de Nascimento: " + contas.getDataNascimento()));
                    panel.add(new JLabel("Telefone: " + contas.getTelefone()));
                    panel.add(new JLabel("Tipo de Usuário: " + contas.getTipoUsuario()));

                    // Exibindo as contas do usuário
                    panel.add(new JLabel("Contas do Usuário:"));
                    for (UsuarioConta.Conta conta : contas.getContas()) {
                        panel.add(new JLabel("Conta: " + conta.getNumeroConta() +
                                ", Agência: " + conta.getAgencia() +
                                ", Saldo: " + conta.getSaldo() +
                                ", Tipo de Conta: " + conta.getTipoConta()));
                    }

                    JOptionPane.showMessageDialog(null, panel, "Dados do Usuário", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Caso o usuário não seja encontrado ou não haja dados
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado ou dados inconsistentes.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "CPF não informado. A consulta não será realizada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void alterarDados() {

            JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
            JTextField cpfField = new JTextField();
            panel.add(new JLabel("CPF do usuário:"));
            panel.add(cpfField);

            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

            panel.add(new JLabel("Novo telefone:"));
            JTextField telefoneField = new JTextField();
            panel.add(telefoneField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Alteração de Dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String cpf = cpfField.getText();

                // Usando FuncionarioDAO para consultar os dados do usuário
                UsuarioConta usuarioConta = funcionarioDAO.consultarDadosUsuario(cpf);

                if (usuarioConta != null) {

                    String telefone = telefoneField.getText();
                    System.out.println(telefone);
                    funcionarioDAO.alterarDadosUsuario(cpf, telefone);

                    JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
    }


    private void cadastrarFuncionario() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        panel.add(new JLabel("CPF:"));
        JTextField cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("Cargo:"));
        JTextField cargoField = new JTextField();
        panel.add(cargoField);


        int result = JOptionPane.showConfirmDialog(this, panel, "Cadastro de Funcionário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String senhaAdmin = JOptionPane.showInputDialog(this, "Digite a senha do administrador:");
            if (senhaAdmin != null && senhaAdmin.equals("admin123")) {
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                funcionarioDAO.alterarCargoFuncionario(cpfField.getText(), cargoField.getText());
                JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void gerarRelatorio() {
        String senhaAdmin = JOptionPane.showInputDialog(this, "Digite a senha do administrador:");
        if (senhaAdmin != null && senhaAdmin.equals("admin123")) {
            String cpfDoUsuario = JOptionPane.showInputDialog(this, "Digite o CPF do usuário que deseja o relatório:");

            FuncionarioDAO dao = new FuncionarioDAO();
            RelatorioUsuario relatorio = dao.gerarRelatorioDAO(cpfDoUsuario);

            if (relatorio != null) {
                RelatorioController controller = new RelatorioController();
                controller.exportarRelatorioParaCSV(relatorio);
                JOptionPane.showMessageDialog(this, "Relatório exportado com sucesso!");

                // Criando a estrutura da janela do relatório
                JFrame relatorioFrame = new JFrame("Relatório do Usuário");
                relatorioFrame.setSize(500, 400);
                relatorioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                relatorioFrame.setLocationRelativeTo(null);

                // Criando a área de texto e configurando o layout
                JTextArea textArea = new JTextArea();
                textArea.setEditable(false);

                // Montando o conteúdo do relatório
                StringBuilder relatorioTexto = new StringBuilder();
                relatorioTexto.append("Relatório do Usuário - CPF: ").append(cpfDoUsuario).append("\n\n");

                // Informações das Contas
                for (UsuarioConta.Conta conta : relatorio.getContas()) {
                    relatorioTexto.append("Conta: ").append(conta.getNumeroConta())
                            .append(" | Agência: ").append(conta.getAgencia())
                            .append(" | Saldo: R$ ").append(conta.getSaldo())
                            .append("\n");
                }
                relatorioTexto.append("\n");

                // Informações das Transações
                for (Transacao transacao : relatorio.getTransacoes()) {
                    relatorioTexto.append("Transação: ").append(transacao.getTipoTransacao())
                            .append(" | Valor: R$ ").append(transacao.getValor())
                            .append(" | Data: ").append(transacao.getDataTransacao())
                            .append("\n");
                }

                // Definindo o texto no JTextArea e adicionando um scroll
                textArea.setText(relatorioTexto.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                relatorioFrame.add(scrollPane);

                // Exibindo a janela
                relatorioFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Relatorio não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}