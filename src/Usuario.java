
public class Usuario {

    int cod_usuario;
    String  email;
    String  senha;
    String  nome;
    String  regiao;
    String  telefone;

    private Usuario (String email, String senha, String nome, String regiao, String telefone) {

        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.regiao = regiao;
        this.telefone = telefone;

    }

}