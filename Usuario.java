public class Usuario {

    private String nome;
    private String senha;
    private Conta conta;

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.conta = new Conta(); // cada usuário tem uma conta
    }

    public boolean verificarSenha(String tentativa) {
        return senha.equals(tentativa);
    }

    public Conta getConta() {
        return conta;
    }

    public String getNome() {
        return nome;
    }
}
