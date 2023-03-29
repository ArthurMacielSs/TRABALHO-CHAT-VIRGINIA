
import java.io.InputStream;
import java.util.Scanner;
import java.net.Socket;

public class TrataCliente implements Runnable {
 
   private InputStream cliente;
   private Servidor servidor;
   private String clienteIP;
 
   public TrataCliente(Socket cliente, Servidor servidor) {
    try{
      this.cliente = cliente.getInputStream();
    }
    catch(Exception e) {}
     this.servidor = servidor;
     this.clienteIP = cliente.getInetAddress().getHostAddress();
   }
 
   @Override
   public void run() {
     // quando chegar uma msg, distribui pra todos
     Scanner s = new Scanner(this.cliente);
     while (s.hasNextLine()) {
       servidor.distribuiMensagem(s.nextLine(), clienteIP);
     }
     s.close();
   }
 }