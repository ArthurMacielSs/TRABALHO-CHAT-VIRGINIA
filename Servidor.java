import java.awt.Font;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket; 
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Servidor {

    JFrame tFrame;
    JTextField text;
    JLabel title;

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

      JFrame tFrame = new JFrame();
      tFrame.setSize(300, 300);
      tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      tFrame.setTitle("Servidor");
      tFrame.setLocationRelativeTo(null);
      tFrame.setLayout(null);
        
      tFrame.setVisible(true);

      ServerSocket servidor = new ServerSocket(this.porta);
      
      text = new JTextField("Porta 12345 aberta!");
      text.setBounds(10, 10, 150, 35);
      text.setFont(new Font ("Arial", Font.PLAIN, 25));

      JOptionPane.showMessageDialog(null, text.getText(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
      

      while (true) {
        // aceita um cliente
        Socket cliente = servidor.accept();
        title = new JLabel("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(10, 10, 400, 100);
        
        JOptionPane.showMessageDialog(null, title.getText(), "Conexão", JOptionPane.INFORMATION_MESSAGE);

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
            try {
              PrintStream mandar = new PrintStream(cliente.getOutputStream());
              mandar.println(msg);
            }
            catch(Exception e) {}

          }

      }
    }
}



