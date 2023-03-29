import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
 
   public static void main(String[] args) throws IOException {
     // inicia o servidor
     new Servidor(12345).executa();
   }
   
   private int porta;
   private List<Socket> clientes;
   
   public Servidor (int porta) {
     this.porta = porta;
     this.clientes = new ArrayList<>();
   }
   
   public void executa () throws IOException {
     ServerSocket servidor = new ServerSocket(this.porta);
     System.out.println("Porta 12345 aberta!");
     
     while (true) {
       // aceita um cliente
       Socket cliente = servidor.accept();
       System.out.println("Nova conexão com o cliente " +   
         cliente.getInetAddress().getHostAddress()
         
       );
       
       // adiciona saida do cliente à lista
       this.clientes.add(cliente);
       
       // cria tratador de cliente numa nova thread
       TrataCliente tc = new TrataCliente(cliente, this);
       new Thread(tc).start();
     }
 
   }
 
   public void distribuiMensagem(String msg, String clienteNaoEnvia) {
    // envia msg para todo mundo, exceto o servidor
    for (Socket cliente : this.clientes) {
        if (!cliente.getInetAddress().getHostAddress().equals(clienteNaoEnvia)) {
          try{
            PrintStream mandar = new PrintStream(cliente.getOutputStream());
            mandar.println(msg);
          }
          catch(Exception e){}

        }

    }
}
}



