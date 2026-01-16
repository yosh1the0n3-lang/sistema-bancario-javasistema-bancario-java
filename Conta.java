import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Conta {

    private int saldo = 0;
    private ArrayList<String> historico = new ArrayList<>();

    private String agora() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.now().format(fmt);
    }

    public int getSaldo() {
        return saldo;
    }

    public ArrayList<String> getHistorico() {
        return historico;
    }

    public void depositar(int valor) {
        if (valor > 0) {
            saldo += valor;
            historico.add(agora() + "| - Deposito " + valor);
        }
    }

    public boolean sacar(int valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            historico.add(agora() + "| - Saque " + valor);
            return true;
        }
        return false;
    }

    public boolean transferir(int valor, Conta destino, String nomeDestino) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            destino.saldo += valor;

            historico.add(agora() + " | → Transferência para " + nomeDestino + ": " + valor);
            destino.historico.add(agora() + " | ← Transferência recebida: " + valor);

            return true;
        }
        return false;
    }
}