
public class Usuario {

    private int cod_usuario;
    private String  nm_usuario, email, senha, nome, regiao, telefone;
    
    public String getNm_usuario() {
        return nm_usuario;
    }
    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }
    public int getCod_usuario() {
        return cod_usuario;
    }
    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getRegiao() {
        return regiao;
    }
    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
