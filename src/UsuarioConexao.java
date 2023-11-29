import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UsuarioConexao {
    
    Connection conexao;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<Usuario> lista = new ArrayList<>();

    public ResultSet fazerLogin(Usuario objUsuario){

        conexao = new Conexao().conectaDB();

        try {

            String query = "select * from usuario_login where nm_usuario = ? and pw_senha = ?";

            pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getNm_usuario());
            pstm.setString(2, objUsuario.getSenha());

            rs = pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "UsuarioConexao.fazerLogin: " + erro, "Erro!", 0);
            return null;
        }
        

    }

    public int usuarioLogado(Usuario objUsuario) {

        conexao = new Conexao().conectaDB();

        try {

            String query = "select cd_usuario from usuario where nm_usuario = ?";

            pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getNm_usuario());

            rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
            

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "UsuarioConexao.usuarioLogado: " + erro, "Erro!", 0);
            return 0;
        }
        
    }

    public void fazerCadastro(Usuario objUsuario){

        conexao = new Conexao().conectaDB();

        try {

            String query = "insert into usuario(nm_usuario, ds_nome, pw_senha, ds_email) values (?, ?, ?, ?)";

            pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getNm_usuario());
            pstm.setString(2, objUsuario.getNome());
            pstm.setString(3, objUsuario.getSenha());
            pstm.setString(4, objUsuario.getEmail());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "UsuarioConexao.fazerCadastro: " + erro, "Erro!", 0);
        }
    

    }

    public ResultSet verificaUsuario(Usuario objUsuario){

        conexao = new Conexao().conectaDB();

        try {

            String query = "select * from usuario_login where nm_usuario = ?";

            pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getNm_usuario());

            rs = pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "UsuarioConexao.verificaUsuario: " + erro, "Erro!", 0);
            return null;
        }
    }

    public ResultSet verificaEmail(Usuario objUsuario){

        conexao = new Conexao().conectaDB();

        try {

            String query = "select * from usuario where ds_email = ?";

            pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getEmail());

            rs = pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "UsuarioConexao.verificaEmail: " + erro, "Erro!", 0);
            return null;
        }
    }

    public ArrayList<Usuario> pesquisaUsuario(Usuario objUsuario) {

        conexao = new Conexao().conectaDB();

        try {

            String query = "select nm_usuario, ds_nome from usuario where nm_usuario like ? or ds_nome like ?";
            
            pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getNm_usuario());
            pstm.setString(2, objUsuario.getNm_usuario());

            rs = pstm.executeQuery();

            while(rs.next()){

                Usuario objUsuarioPesquisa = new Usuario();
                objUsuarioPesquisa.setNm_usuario(rs.getString("nm_usuario"));
                objUsuarioPesquisa.setNome(rs.getString("ds_nome"));

                lista.add(objUsuarioPesquisa);

            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioConexao.pesquisaUsuario: " + erro, "Erro!", 0);
            return null;
        }

        return lista;

    }

    public ResultSet mostraDados(int usuarioLogado) {

        conexao = new Conexao().conectaDB();

        try {

            String query = "select ds_nome, nm_usuario, ds_ddd, ds_telefone, ds_regiao from usuario where cd_usuario = ?";

            pstm = conexao.prepareStatement(query); 

            pstm.setInt(1, usuarioLogado);

            return pstm.executeQuery();
            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "UsuarioConexao.mostraDados: " + erro, "Erro!", 0);
            return null;
        }


    }

    public ResultSet mostraJogos() {

        conexao = new Conexao().conectaDB();

        try {

            String query = "select nm_jogo from jogos";
            
            pstm = conexao.prepareStatement(query); 

            return pstm.executeQuery();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioConexao.mostraJogos: " + erro, "Erro!", 0);
            return null;
        }
    }

    public ResultSet mostraRegiao() {

        conexao = new Conexao().conectaDB();

        try {

            String query = "select ds_regiao from regiao";
            
            pstm = conexao.prepareStatement(query); 

            return pstm.executeQuery();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioConexao.mostraRegiao: " + erro, "Erro!", 0);
            return null;
        }
    }

    public void editaPerfilUsuario(Usuario objUsuario, int usuarioLogado){

        conexao = new Conexao().conectaDB();

        try {

            String query = "update usuario set ds_nome = ?, ds_regiao = ?, ds_ddd = ?, ds_telefone = ? where cd_usuario = ?;"
            + "commit;";

            pstm = conexao.prepareStatement(query); 

            pstm.setString(1, objUsuario.getNm_usuario());
            pstm.setString(2, objUsuario.getRegiao());
            pstm.setString(3, objUsuario.getDdd());
            pstm.setString(4, objUsuario.getTelefone());
            pstm.setInt(5, usuarioLogado);

            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "UsuarioConexao.editaPerfilUsuario: " + erro, "Erro!", 0);
        
        }

    }

    public void insereJogo(int usuarioLogado, Jogos objJogos){

        conexao = new Conexao().conectaDB();

        try {

            String query = "insert into jogo_usuario(cd_usuario, cd_jogo) values(?, (select cd_jogo from jogos where nm_jogo = ?));";

            pstm = conexao.prepareStatement(query); 

            pstm.setInt(1, usuarioLogado);
            pstm.setString(2, objJogos.getNm_jogo());

            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "UsuarioConexao.insereJogo: " + erro, "Erro!", 0);
        
        }

    }

}
