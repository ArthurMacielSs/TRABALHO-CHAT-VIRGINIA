package chat; //colca a classe código dentro do pacote chat 

//declara bibliotecas 
import static chat.Interface.HistoricoMsg;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//cria a classe e a transforma em uma classe 
public class Servidor implements Runnable {

    private int porta;
    private List<Socket> clientes;

    public Servidor(int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<>();
    }

    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(this.porta);
            HistoricoMsg.setText("Porta 12345 aberta!\n");

            while (true) {
                // aceita um cliente
                Socket cliente = servidor.accept();
                HistoricoMsg.setText(HistoricoMsg.getText() + ("Nova conexão com o cliente \n") + cliente.getInetAddress().getHostAddress() + "\n");

                // adiciona saida do cliente à lista
                this.clientes.add(cliente);

                // cria tratador de cliente numa nova thread
                TrataCliente tc = new TrataCliente(cliente, this);
                new Thread(tc).start();
            }

        } catch (IOException e) { //fim do bloco try catch
        }
    }

    public void distribuiMensagem(String msg, String clienteNaoEnvia) {
        // envia msg para todo mundo, exceto o servidor
        for (Socket cliente : this.clientes) {
            //if (!cliente.getInetAddress().getHostAddress().equals(clienteNaoEnvia)) {
                try {
                    PrintStream mandar = new PrintStream(cliente.getOutputStream());
                    mandar.println(msg);
                } catch (Exception e) {
                }

            //}

        }
    }
}
