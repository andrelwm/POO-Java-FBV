import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuPrincipal extends JFrame{

    RepositorioUsuarios repositorio = new RepositorioUsuarios();
    final private Font fontePrincipal = new Font("Arial", Font.BOLD, 18);
    JTextField tfUsuario, pfSenha;
    private JTextField pesquisaField;
    private JList<String> amigosList, rankingList;


    public void Login(){

        JLabel lbUsuario = new JLabel("Usuario");
        lbUsuario.setFont(fontePrincipal);

        tfUsuario = new JTextField();
        tfUsuario.setFont(fontePrincipal);

        JLabel lbSenha = new JLabel("Senha");
        lbSenha.setFont(fontePrincipal);

        pfSenha = new JPasswordField();
        pfSenha.setFont(fontePrincipal);

        JLabel lbError = new JLabel();
        lbError.setFont(fontePrincipal);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(fontePrincipal);
        btnEntrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    
                String nomeUsuario = tfUsuario.getText().toString();
                String senha = pfSenha.getText().toString();
                boolean login = repositorio.fazerLogin(nomeUsuario, senha);

                if (login == false) {
                    lbError.setText("Usuário ou senha incorreta! Tente novamente!");
                    tfUsuario.setText("");
                    pfSenha.setText("");
                } else {
                    MenuPrincipal menu = new MenuPrincipal();
                    menu.Principal();
                    setVisible(false);
                }
                    
            }

        });

        JPanel painelBotao  = new JPanel();
        //painelBotao.setLayout(new GridLayout(1, 2, 7, 7));
        painelBotao.setPreferredSize(new Dimension(125, 40));
        painelBotao.setOpaque(false);
        painelBotao.add(btnEntrar);

        JPanel formPainel = new JPanel();
        formPainel.setLayout(new GridLayout(4, 1, 5, 5));
        formPainel.setOpaque(false);
        formPainel.add(lbUsuario);
        formPainel.add(tfUsuario);
        formPainel.add(lbSenha);
        formPainel.add(pfSenha);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(128, 128, 255));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelPrincipal.add(formPainel, BorderLayout.NORTH);
        painelPrincipal.add(lbError, BorderLayout.CENTER);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        add(painelPrincipal);

        setTitle("MySquad Teste");
        setSize(350, 300);
        setMinimumSize(new Dimension(300, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public void Principal() {
        setTitle("My Squad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Barra de navegação
        JPanel navigationBar = new JPanel();
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
        JLabel rankingLabel = new JLabel("Ranking");
        rankingList = new JList<>(new String[]{"Jogador 1", "Jogador 2", "Jogador 3"});
        rankingPanel.add(rankingLabel, BorderLayout.NORTH);
        rankingPanel.add(new JScrollPane(rankingList), BorderLayout.CENTER);
        mainPanel.add(rankingPanel, BorderLayout.EAST);

        // Barra de pesquisa
        JPanel pesquisaPanel = new JPanel();
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

    public static void main(String[] args) {
        MenuPrincipal menuLogin = new MenuPrincipal();
        menuLogin.Login();
    }

}







