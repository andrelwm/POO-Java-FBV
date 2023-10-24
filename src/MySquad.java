import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MySquad extends JFrame{

    //Declaração de variáveis
    final private Font fontePrincipal = new Font("Arial", Font.BOLD, 18);
    final private Color textoPrincipal = new Color(255, 255, 255);
    final private Color fundoPrincipal = new Color(25, 25, 112);
    private JTextField tfUsuario, tfEmail, pfSenha, pesquisaField, tfNovoUsuario, pfNovaSenha, nickField, regiaoField;
    private JList<String> amigosList, rankingList;
    private JComboBox<String> cbjogos;
    private JTable tabelaUsuarios;
    private DefaultTableModel model;
    private String usuarioLogado;
    private JComboBox<String> jogosFavoritosComboBox;


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
        lbSenha.setForeground(textoPrincipal);
        lbSenha.setHorizontalAlignment(SwingConstants.CENTER);

        pfSenha = new JPasswordField();
        pfSenha.setFont(fontePrincipal);

        JButton btnEntrar = new JButton("Entrar");
        //btnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("./images/teste.png")));
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
                MySquad menu = new MySquad();
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
        painelPrincipal.setBackground(fundoPrincipal);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        painelPrincipal.add(formPainel, BorderLayout.NORTH);
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
        lbNovoUsuario.setForeground(textoPrincipal);
        lbNovoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        tfNovoUsuario = new JTextField();
        tfNovoUsuario.setFont(fontePrincipal);

        JLabel lbNovaSenha = new JLabel("Senha");
        lbNovaSenha.setFont(fontePrincipal);
        lbNovaSenha.setForeground(textoPrincipal);
        lbNovaSenha.setHorizontalAlignment(SwingConstants.CENTER);

        pfNovaSenha = new JPasswordField();
        pfNovaSenha.setFont(fontePrincipal);

        JLabel lbEmail = new JLabel("E-mail");
        lbEmail.setFont(fontePrincipal);
        lbEmail.setForeground(textoPrincipal);
        lbEmail.setHorizontalAlignment(SwingConstants.CENTER);

        tfEmail = new JTextField();
        tfEmail.setFont(fontePrincipal);

        JButton btnConcluiCadastro = new JButton("Cadastrar");
        btnConcluiCadastro.setFont(fontePrincipal);
        btnConcluiCadastro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (Cadastrar()) {
                    MySquad menu = new MySquad();
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
        painelPrincipal.setBackground(fundoPrincipal);
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

        //Barra de Navegação
        JPanel navigationBar = new JPanel();
        GridLayout navigationbarlayout = new GridLayout(1, 6);
        navigationbarlayout.setHgap(20);
        navigationBar.setLayout(navigationbarlayout);
        navigationBar.setPreferredSize(new Dimension(800, 50));
        navigationBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        navigationBar.setBackground(fundoPrincipal);

        // Menu de navegação
        JPanel navigationMenu = new JPanel();
        GridLayout navigationlayout = new GridLayout(1, 4);
        navigationlayout.setHgap(5);
        navigationMenu.setLayout(navigationlayout);
        navigationMenu.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        navigationMenu.setBackground(fundoPrincipal);
        JButton homeButton = new JButton("Início");
        JButton gamesButton = new JButton("Jogos");
        JButton profileButton = new JButton("Perfil");
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    MySquad menu = new MySquad();
                    menu.ProfileEditor();
                    setVisible(false);

            }
        });
        JButton friendsButton = new JButton("Amigos");
        friendsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    MySquad menu = new MySquad();
                    menu.friendsPanel();
                    setVisible(false);

            }
        });
        navigationMenu.add(homeButton);
        navigationMenu.add(profileButton);
        navigationMenu.add(gamesButton);
        navigationMenu.add(friendsButton);
        navigationBar.add(navigationMenu, BorderLayout.WEST);

        //Barra de Pesquisa
        JPanel navigationPesquisa = new JPanel();
        GridLayout pesquisalayout = new GridLayout(1, 3);
        pesquisalayout.setHgap(5);
        navigationPesquisa.setLayout(pesquisalayout);
        navigationPesquisa.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 5));
        navigationPesquisa.setBackground(fundoPrincipal);
        navigationBar.add(navigationPesquisa, BorderLayout.EAST);
        pesquisaField = new JTextField(20);
        JButton pesquisaButton = new JButton("Pesquisar");
        pesquisaButton.setPreferredSize(new Dimension(50, 30));
        pesquisaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                realizarPesquisa();
                
            }
        });
        navigationPesquisa.add(pesquisaField);
        navigationPesquisa.add(pesquisaButton);

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


        //Painel de resultados        
        tabelaUsuarios = new JTable(model);
        JScrollPane resultados = new JScrollPane(tabelaUsuarios);
        resultados.setPreferredSize(new Dimension(100, 100));
        mainPanel.add(resultados, BorderLayout.CENTER);
        
        add(mainPanel);
        setVisible(true);
    }


    public void friendsPanel() {

            setTitle("My Squad - Amigos");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);

            //Barra de Navegação
            JPanel navigationBar = new JPanel();
            GridLayout navigationbarlayout = new GridLayout(1, 6);
            navigationbarlayout.setHgap(20);
            navigationBar.setLayout(navigationbarlayout);
            navigationBar.setPreferredSize(new Dimension(800, 50));
            navigationBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            navigationBar.setBackground(fundoPrincipal);

            // Menu de navegação
            JPanel navigationMenu = new JPanel();
            GridLayout navigationlayout = new GridLayout(1, 4);
            navigationlayout.setHgap(5);
            navigationMenu.setLayout(navigationlayout);
            navigationMenu.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
            navigationMenu.setBackground(fundoPrincipal);
            JButton homeButton = new JButton("Início");
            homeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                        MySquad menu = new MySquad();
                        menu.Principal();
                        setVisible(false);

                }
            });

            JButton gamesButton = new JButton("Jogos");

            JButton profileButton = new JButton("Perfil");
            profileButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                        MySquad menu = new MySquad();
                        menu.ProfileEditor();
                        setVisible(false);

                }
            });

            JButton friendsButton = new JButton("Amigos");
            friendsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                        MySquad menu = new MySquad();
                        menu.friendsPanel();
                        setVisible(false);

                }
            });

            navigationMenu.add(homeButton);
            navigationMenu.add(profileButton);
            navigationMenu.add(gamesButton);
            navigationMenu.add(friendsButton);
            navigationBar.add(navigationMenu, BorderLayout.WEST);

            //Barra de Pesquisa
            JPanel navigationPesquisa = new JPanel();
            GridLayout pesquisalayout = new GridLayout(1, 3);
            pesquisalayout.setHgap(5);
            navigationPesquisa.setLayout(pesquisalayout);
            navigationPesquisa.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 5));
            navigationPesquisa.setBackground(fundoPrincipal);
            navigationBar.add(navigationPesquisa, BorderLayout.EAST);
            pesquisaField = new JTextField(20);
            JButton pesquisaButton = new JButton("Pesquisar");
            pesquisaButton.setPreferredSize(new Dimension(50, 30));
            pesquisaButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    realizarPesquisa();
                    
                }
            });
            navigationPesquisa.add(pesquisaField);
            navigationPesquisa.add(pesquisaButton);
    
            JPanel onlinePanel = new JPanel();
            onlinePanel.setBorder(BorderFactory.createTitledBorder("Amigos Online"));
            onlinePanel.setLayout(new BoxLayout(onlinePanel, BoxLayout.Y_AXIS));
    
            JPanel offlinePanel = new JPanel();
            offlinePanel.setBorder(BorderFactory.createTitledBorder("Amigos Offline"));
            offlinePanel.setLayout(new BoxLayout(offlinePanel, BoxLayout.Y_AXIS));
    
            JPanel gamesPanel = new JPanel();
            gamesPanel.setBorder(BorderFactory.createTitledBorder("Jogos em Andamento"));
            gamesPanel.setLayout(new BoxLayout(gamesPanel, BoxLayout.Y_AXIS));
    
            JButton addFriendButton = new JButton("Adicionar Amigo");
            addFriendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para adicionar amigos
                }
            });
    
            JButton sendInviteButton = new JButton("Enviar Convite");
            sendInviteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para enviar convites
                }
            });
    
            setLayout(new BorderLayout());
            add(navigationBar, BorderLayout.NORTH);
            add(onlinePanel, BorderLayout.WEST);
            add(offlinePanel, BorderLayout.CENTER);
            add(gamesPanel, BorderLayout.EAST);
    
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(addFriendButton);
            buttonPanel.add(sendInviteButton);
            add(buttonPanel, BorderLayout.SOUTH);
    
            setVisible(true);
            setLocationRelativeTo(null);
    }
    
    //PAINEL DE EDIÇÃO DE PERFIL//
    public void ProfileEditor() {

        // Configurações iniciais da janela
        setTitle("My Squad - Editar Perfil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        //Barra de Navegação
        JPanel navigationBar = new JPanel();
        GridLayout navigationbarlayout = new GridLayout(1, 6);
        navigationbarlayout.setHgap(20);
        navigationBar.setLayout(navigationbarlayout);
        navigationBar.setPreferredSize(new Dimension(800, 50));
        navigationBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        navigationBar.setBackground(fundoPrincipal);

        // Menu de navegação
        JPanel navigationMenu = new JPanel();
        GridLayout navigationlayout = new GridLayout(1, 4);
        navigationlayout.setHgap(5);
        navigationMenu.setLayout(navigationlayout);
        navigationMenu.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        navigationMenu.setBackground(fundoPrincipal);
        JButton homeButton = new JButton("Início");
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    MySquad menu = new MySquad();
                    menu.Principal();
                    setVisible(false);

            }
        });

        JButton gamesButton = new JButton("Jogos");

        JButton profileButton = new JButton("Perfil");
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    MySquad menu = new MySquad();
                    menu.ProfileEditor();
                    setVisible(false);

            }
        });

        JButton friendsButton = new JButton("Amigos");
        friendsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    MySquad menu = new MySquad();
                    menu.friendsPanel();
                    setVisible(false);

            }
        });

        navigationMenu.add(homeButton);
        navigationMenu.add(profileButton);
        navigationMenu.add(gamesButton);
        navigationMenu.add(friendsButton);
        navigationBar.add(navigationMenu, BorderLayout.WEST);

        //Barra de Pesquisa
        JPanel navigationPesquisa = new JPanel();
        GridLayout pesquisalayout = new GridLayout(1, 3);
        pesquisalayout.setHgap(5);
        navigationPesquisa.setLayout(pesquisalayout);
        navigationPesquisa.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 5));
        navigationPesquisa.setBackground(fundoPrincipal);
        navigationBar.add(navigationPesquisa, BorderLayout.EAST);
        pesquisaField = new JTextField(20);
        JButton pesquisaButton = new JButton("Pesquisar");
        pesquisaButton.setPreferredSize(new Dimension(50, 30));
        pesquisaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                realizarPesquisa();
                
            }
        });
        navigationPesquisa.add(pesquisaField);
        navigationPesquisa.add(pesquisaButton);

        // Painel para organizar o formulário e botoões
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BorderLayout());
        profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        //profilePanel.setBackground(fundoPrincipal);

        //Painel para organizar os componentes
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(14, 3));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //formPanel.setBackground(fundoPrincipal);
        profilePanel.add(formPanel, BorderLayout.CENTER);

        // Componente para adicionar foto (neste exemplo, apenas um rótulo)
        JLabel fotoLabel = new JLabel("Foto: ");
        fotoLabel.setFont(fontePrincipal);
        //fotoLabel.setForeground(textoPrincipal);
        JButton adicionarFotoButton = new JButton("Selecionar Foto");
        adicionarFotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: " +
                chooser.getSelectedFile().getName());
        }

            }
        });

        fotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(fotoLabel);
        formPanel.add(adicionarFotoButton);

        // Campo para editar o nickname
        JLabel nickLabel = new JLabel("Nickname: ");
        nickLabel.setFont(fontePrincipal);
        //nickLabel.setForeground(textoPrincipal);
        nickField = new JTextField(20);
        nickLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(nickLabel);
        formPanel.add(nickField);

        // Campo para editar o país
        JLabel regiaoLabel = new JLabel("Região: ");
        regiaoLabel.setFont(fontePrincipal);
        //regiaoLabel.setForeground(textoPrincipal);
        regiaoField = new JTextField(20);
        regiaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(regiaoLabel);
        formPanel.add(regiaoField);

        // Campo para selecionar jogos favoritos
        JLabel jogosLabel = new JLabel("Jogos Favoritos: ");
        jogosLabel.setFont(fontePrincipal);
        //jogosLabel.setForeground(textoPrincipal);
        cbjogos = new JComboBox<String>();
        cbjogos.addItem("Selecione um jogo");
        cbjogos = mostrarJogos();
        jogosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(jogosLabel);
        formPanel.add(cbjogos);

        // Botão de salvar
        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para salvar as informações do perfil
                String nickname = nickField.getText();
                String pais = regiaoField.getText();
                String jogoSelecionado = (String) jogosFavoritosComboBox.getSelectedItem();

                // Implemente a lógica de salvamento aqui
                // salvar o banco de dados aqui andre 

                // Exemplo: exibindo os dados no console
                System.out.println("Nickname: " + nickname);
                System.out.println("País: " + pais);
                System.out.println("Jogo Favorito: " + jogoSelecionado);
            }
        });

        //Painel para o botão de salvar
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(100, 35));
        buttonPanel.setOpaque(false);
        buttonPanel.add(salvarButton);
        getRootPane().setDefaultButton(salvarButton);
        profilePanel.add(buttonPanel, BorderLayout.SOUTH);
        profilePanel.add(navigationBar, BorderLayout.NORTH);

        

        // Adicione o painel à janela
        add(profilePanel);

        //Centraliza o painel principal
        setLocationRelativeTo(null);

        // Exiba a janela
        setVisible(true);
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

            UsuarioConexao objVerifica = new UsuarioConexao();
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

                    UsuarioConexao objconexao = new UsuarioConexao();
                    objconexao.fazerCadastro(objUsuario);
                    sucesso = true;

                }
            }

            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "MySquad.Logar: "+ erro, "ERRO!", 0);

        }
        return sucesso;
        
    }

    private void realizarPesquisa() {

        try {

            String termoPesquisa = ('%' + pesquisaField.getText() + '%').toString();

            Usuario objUsuarioPesquisa = new Usuario();
            objUsuarioPesquisa.setNm_usuario(termoPesquisa);

            UsuarioConexao objPesquisa = new UsuarioConexao();

            model = new DefaultTableModel();
            model.setNumRows(0);

            ArrayList<Usuario> lista = objPesquisa.pesquisaUsuario(objUsuarioPesquisa);

            //System.out.println();

            for(int num = 0; num < lista.size(); num++){

                model.addRow(new Object[]{
                    lista.get(num).getNm_usuario(),
                    lista.get(num).getNome()
                });  
                
            }

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "MySquad.realizarPesquisa: "+ erro, "ERRO!", 0);
            
        }
        
    }

    
    private void Logar() {

        try {

                String nomeUsuario = tfUsuario.getText().toString();
                String senha = pfSenha.getText().toString();

                Usuario objUsuario = new Usuario();
                objUsuario.setNm_usuario(nomeUsuario);
                objUsuario.setSenha(senha);

                UsuarioConexao objconexao = new UsuarioConexao();
                ResultSet rsconexao = objconexao.fazerLogin(objUsuario);

                if (rsconexao.next()) {

                    usuarioLogado = nomeUsuario;
                    MySquad menu = new MySquad();
                    menu.Principal();
                    setVisible(false);

                } else {

                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorreta!", "My Squad - Login", 0);
                    tfUsuario.setText("");
                    pfSenha.setText("");

                }

            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "MySquad.Logar: "+ erro, "ERRO!", 0);
            }

    }

    private JComboBox<String> mostrarJogos(){

        try {

            UsuarioConexao objConexao = new UsuarioConexao();
            
            ResultSet rs = objConexao.mostraJogos();

            while(rs.next()) {

                //lista.add(rs.getString(1));
                cbjogos.addItem(rs.getString(1));

            }


        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "MySquad.mostraJogos: " + erro, "ERRO!", 0);
        }

        return cbjogos;

    }

    
    public static void main(String[] args) {
        MySquad menuLogin = new MySquad();
        menuLogin.Login();
        }

}
