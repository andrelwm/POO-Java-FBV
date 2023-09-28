import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class testeconexao {
    
    Connection conexao;

    public ResultSet fazerLogin(Usuario objUsuario){

        conexao = new conexao().conectaDB();

        try {

            String query = "select * from usuario_login where nm_usuario = ? and pw_senha = ?";

            PreparedStatement pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getNm_usuario());
            pstm.setString(2, objUsuario.getSenha());

            ResultSet rs = pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "testeconexao.fazerLogin: " + erro, "Erro!", 0);
            return null;
        }
        



    }

    public void fazerCadastro(Usuario objUsuario){

        conexao = new conexao().conectaDB();

        try {

            String query = "insert into usuario(nm_usuario, pw_senha, ds_email) values (?, ?, ?)";

            PreparedStatement pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getNm_usuario());
            pstm.setString(2, objUsuario.getSenha());
            pstm.setString(3, objUsuario.getEmail());

            pstm.execute();
            pstm.close();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "testeconexao.fazerCadastro: " + erro, "Erro!", 0);
        }
    

    }

    public ResultSet verificaUsuario(Usuario objUsuario){

        conexao = new conexao().conectaDB();

        try {

            String query = "select * from usuario_login where nm_usuario = ?";

            PreparedStatement pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getNm_usuario());

            ResultSet rs = pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "testeconexao.verificaUsuario: " + erro, "Erro!", 0);
            return null;
        }
    }

    public ResultSet verificaEmail(Usuario objUsuario){

        conexao = new conexao().conectaDB();

        try {

            String query = "select * from usuario where ds_email = ?";

            PreparedStatement pstm = conexao.prepareStatement(query);
            pstm.setString(1, objUsuario.getEmail());

            ResultSet rs = pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "testeconexao.verificaEmail: " + erro, "Erro!", 0);
            return null;
        }
    }

}
