import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class MenuPrincipal extends JFrame{

    RepositorioUsuarios repositorio = new RepositorioUsuarios();
    final private Font fontePrincipal = new Font("Arial", Font.BOLD, 18);
    final private Color corPrincipal = new Color(255, 255, 255);
    private JTextField tfUsuario, tfEmail, pfSenha, pesquisaField, tfNovoUsuario, pfNovaSenha;
    private JList<String> amigosList, rankingList;


    private void Login(){

        //Label e Text Field do usuário
        JLabel lbUsuario = new JLabel("Usuario");
        lbUsuario.setFont(fontePrincipal);
        lbUsuario.setForeground(Color.WHITE);
        lbUsuario.setHorizontalAlignment(SwingConstants.CENTER);

        tfUsuario = new JTextField();
        tfUsuario.setFont(fontePrincipal);

        //Label e Password Field da senha
        JLabel lbSenha = new JLabel("Senha");
        lbSenha.setFont(fontePrincipal);
        lbSenha.setForeground(corPrincipal);
        lbSenha.setHorizontalAlignment(SwingConstants.CENTER);

        pfSenha = new JPasswordField();
        pfSenha.setFont(fontePrincipal);

        //Mensagem de Erro para o caso de erro de senha ou usuário
        JLabel lbError = new JLabel();
        lbError.setFont(fontePrincipal);
        lbError.setForeground(Color.WHITE);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(fontePrincipal);
        btnEntrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            Logar();               
                    
            }

        });

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(fontePrincipal);
        btnCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal menu = new MenuPrincipal();
                menu.Cadastro();
                setVisible(false);
            }
        });        

        JPanel painelBotao  = new JPanel();
        painelBotao.setLayout(new GridLayout(1, 2, 20, 7));
        painelBotao.setPreferredSize(new Dimension(110, 35));
        painelBotao.setOpaque(false);
        painelBotao.add(btnEntrar);
        painelBotao.add(btnCadastrar);
        getRootPane().setDefaultButton(btnEntrar);

        JPanel formPainel = new JPanel();
        formPainel.setLayout(new GridLayout(4, 1, 5, 5));
        formPainel.setOpaque(false);
        formPainel.add(lbUsuario);
        formPainel.add(tfUsuario);
        formPainel.add(lbSenha);
        formPainel.add(pfSenha);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(25, 25, 112));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelPrincipal.add(formPainel, BorderLayout.NORTH);
        painelPrincipal.add(lbError, BorderLayout.CENTER);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        add(painelPrincipal);

        setTitle("MySquad");
        setSize(350, 300);
        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    private void Cadastro() {

        //Campos do cadastro
        JLabel lbNovoUsuario = new JLabel("Usuário");
        lbNovoUsuario.setFont(fontePrincipal);
        lbNovoUsuario.setForeground(corPrincipal);
        lbNovoUsuario.setHorizontalAlignment(SwingConstants.CENTER);

        tfNovoUsuario = new JTextField();
        tfNovoUsuario.setFont(fontePrincipal);

        JLabel lbNovaSenha = new JLabel("Senha");
        lbNovaSenha.setFont(fontePrincipal);
        lbNovaSenha.setForeground(corPrincipal);
        lbNovaSenha.setHorizontalAlignment(SwingConstants.CENTER);

        pfNovaSenha = new JPasswordField();
        pfNovaSenha.setFont(fontePrincipal);

        JLabel lbEmail = new JLabel("E-mail");
        lbEmail.setFont(fontePrincipal);
        lbEmail.setForeground(corPrincipal);
        lbEmail.setHorizontalAlignment(SwingConstants.CENTER);

        tfEmail = new JTextField();
        tfEmail.setFont(fontePrincipal);

        JButton btnConcluiCadastro = new JButton("Cadastrar");
        btnConcluiCadastro.setFont(fontePrincipal);
        btnConcluiCadastro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (Cadastrar()) {
                    MenuPrincipal menu = new MenuPrincipal();
                    JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "MySquad - Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    menu.Login();
                    setVisible(false);
                }
                
            }
        });  

        //Painel para os campos do cadastro
        JPanel formPainel = new JPanel();
        formPainel.setLayout(new GridLayout(6, 3, 10, 10));
        formPainel.setOpaque(false);
        formPainel.add(lbNovoUsuario);
        formPainel.add(tfNovoUsuario);
        formPainel.add(lbNovaSenha);
        formPainel.add(pfNovaSenha);
        formPainel.add(lbEmail);
        formPainel.add(tfEmail);

        //Painel do botão de conclusão do cadastro
        JPanel painelBotao  = new JPanel();
        //painelBotao.setLayout(new GridLayout(1, 2, 20, 7));
        painelBotao.setPreferredSize(new Dimension(110, 35));
        painelBotao.setOpaque(false);
        painelBotao.add(btnConcluiCadastro);
        getRootPane().setDefaultButton(btnConcluiCadastro);

        //Painel Principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(25, 25, 112));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 100, 10, 100));
        painelPrincipal.add(formPainel, BorderLayout.NORTH);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        setTitle("My Squad - Cadastro");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        add(painelPrincipal);

    }

    private void Principal() {
        setTitle("My Squad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Barra de navegação
        JPanel navigationBar = new JPanel();
        navigationBar.setBackground(new Color(25, 25, 112));
        JButton homeButton = new JButton("Início");
        JButton gamesButton = new JButton("Jogos");
        JButton storeButton = new JButton("Loja");
        JButton communityButton = new JButton("Comunidade");
        navigationBar.add(homeButton);
        navigationBar.add(gamesButton);
        navigationBar.add(storeButton);
        navigationBar.add(communityButton);
        mainPanel.add(navigationBar, BorderLayout.NORTH);

        // Barra de amigos
        JPanel amigosPanel = new JPanel();
        amigosPanel.setLayout(new BorderLayout());
        JLabel amigosLabel = new JLabel("Amigos Online");
        amigosList = new JList<>(new String[]{"Amigo 1", "Amigo 2", "Amigo 3"});
        amigosPanel.add(amigosLabel, BorderLayout.NORTH);
        amigosPanel.add(new JScrollPane(amigosList), BorderLayout.CENTER);
        mainPanel.add(amigosPanel, BorderLayout.WEST);

        // Ranking
        JPanel rankingPanel = new JPanel();
        rankingPanel.setLayout(new BorderLayout());
        //rankingPanel.setBackground(new Color(135, 206, 235));
        JLabel rankingLabel = new JLabel("Ranking");
        rankingList = new JList<>(new String[]{"Jogador 1", "Jogador 2", "Jogador 3"});
        rankingPanel.add(rankingLabel, BorderLayout.NORTH);
        rankingPanel.add(new JScrollPane(rankingList), BorderLayout.CENTER);
        //rankingList.setBackground(new Color(145, 210, 250));
        mainPanel.add(rankingPanel, BorderLayout.EAST);

        // Barra de pesquisa
        JPanel pesquisaPanel = new JPanel();
        //pesquisaPanel.setBackground(new Color(135, 206, 235));
        pesquisaField = new JTextField(20);
        JButton pesquisaButton = new JButton("Pesquisar");
        pesquisaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarPesquisa();
            }
        });
        pesquisaPanel.add(pesquisaField);
        pesquisaPanel.add(pesquisaButton);
        mainPanel.add(pesquisaPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void realizarPesquisa() {
        String termoPesquisa = pesquisaField.getText();
        // Implemente a lógica de pesquisa aqui
        JOptionPane.showMessageDialog(this, "Pesquisando por: " + termoPesquisa);
    }

    private boolean Cadastrar() {

        boolean sucesso = false;
        
        try {

            String novoUsuario = tfNovoUsuario.getText().toString();
            String novaSenha = pfNovaSenha.getText().toString();
            String email = tfEmail.getText().toString();

            Usuario objUsuarioVerifica = new Usuario();
            objUsuarioVerifica.setNm_usuario(novoUsuario);
            objUsuarioVerifica.setEmail(email);

            testeconexao objVerifica = new testeconexao();
            ResultSet rsUsuario = objVerifica.verificaUsuario(objUsuarioVerifica);
            ResultSet rsEmail = objVerifica.verificaEmail(objUsuarioVerifica);

            if(rsUsuario.next()) {

                JOptionPane.showMessageDialog(null, "Nome de Usuário já existe", "MySquad - Cadastro", 0);

            } else if (rsEmail.next()){

                JOptionPane.showMessageDialog(null, "Já existe uma conta cadastrada com o email informado!", "MySquad - Cadastro", 0);

            } else {

                if ((tfNovoUsuario.getText().isEmpty()) | (pfNovaSenha.getText().isEmpty()) | (tfEmail.getText().isEmpty())){

                    JOptionPane.showMessageDialog(null, "Preencha todos os campos, por favor!", "MySquad - Cadastro", 0);
                    
                } else {

                    Usuario objUsuario = new Usuario();
                    objUsuario.setNm_usuario(novoUsuario);
                    objUsuario.setSenha(novaSenha);
                    objUsuario.setEmail(email);

                    testeconexao objconexao = new testeconexao();
                    objconexao.fazerCadastro(objUsuario);
                    sucesso = true;

                }
            }

            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "MenuPrincipal.Logar: "+ erro, "ERRO!", 0);

        }
        return sucesso;
        
           

    }

    
    private void Logar() {

        try {

                String nomeUsuario = tfUsuario.getText().toString();
                String senha = pfSenha.getText().toString();

                Usuario objUsuario = new Usuario();
                objUsuario.setNm_usuario(nomeUsuario);
                objUsuario.setSenha(senha);

                testeconexao objconexao = new testeconexao();
                ResultSet rsconexao = objconexao.fazerLogin(objUsuario);

                if (rsconexao.next()) {

                    MenuPrincipal menu = new MenuPrincipal();
                    menu.Principal();
                    setVisible(false);

                } else {

                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorreta!", "My Squad - Login", 0);
                    tfUsuario.setText("");
                    pfSenha.setText("");

                }

            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "MenuPrincipal.Logar: "+ erro, "ERRO!", 0);
            }

    }

    
    public static void main(String[] args) {
        MenuPrincipal menuLogin = new MenuPrincipal();
        menuLogin.Login();
        }

}
