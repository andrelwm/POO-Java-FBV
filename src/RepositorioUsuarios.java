import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class RepositorioUsuarios {

        

    private Map<String, String> usuarios;
    protected RepositorioUsuarios() {
        this.usuarios = new HashMap<>();

    }
    protected void cadastrarUsuario(String nomeUsuario, String senha) {
        usuarios.put(nomeUsuario, senha);

        File arquivoRepo = new File("./src/usuarios.csv");

        try {
        if (!arquivoRepo.exists()) {
        //cria um arquivo (vazio)
        arquivoRepo.createNewFile();
        }
            
        //escreve no arquivo
        FileWriter fw = new FileWriter(arquivoRepo, true);
        BufferedWriter bw = new BufferedWriter(fw);

        String nome = usuarios.keySet().toString();

        bw.newLine();
        bw.write(nome + "," + usuarios.get(nomeUsuario));
        bw.close();
        fw.close();

        } catch (IOException ex) {
        ex.printStackTrace();
        }

        System.out.println("Usuário cadastrado com sucesso!");

    } 
    protected boolean fazerLogin(String nomeUsuario, String senha) {

        try (BufferedReader br = new BufferedReader(new FileReader("./src/usuarios.csv"))) {

            String itemRepo = br.readLine();
            while (itemRepo != null) {

                String[] campos = itemRepo.split(",");
                String nome = campos[0];
                nome = nome.replaceAll("[\\[\\]]", "");
                String senhaUsuario = campos[1];


                usuarios.put(nome, senhaUsuario);

                itemRepo = br.readLine();

            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String senhaArmazenada = usuarios.get(nomeUsuario);
        if (senhaArmazenada != null && senhaArmazenada.equals(senha)) {
            //System.out.println("Login bem-sucedido!");
            return true;
        } else {
            //System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
            return false;
        }
    }
    
}


