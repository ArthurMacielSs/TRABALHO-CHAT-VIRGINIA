package chat; //colca a classe código dentro do pacote chat 

import static chat.Interface.HistoricoMsg;
import static chat.InterfaceC.historicoC;
import static chat.InterfaceC.infoC;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionListener;
import static chat.InterfaceC.digitaC;
import static chat.InterfaceC.enviaC;

public class Cliente implements Runnable {

    // declara variaveis
    public String nome;
    private String host;
    private int porta;
    private Socket cliente;
    private Recebedor r;
    private PrintStream saida;
    public boolean envia = false;

    public Cliente(String host, int porta, String nome) {
        this.host = host;
        this.porta = porta;
        this.nome = nome;
    }

    public void run() {

        try {

            
            cliente = new Socket(this.host, this.porta);
            infoC.setText("O cliente se conectou ao servidor! \n");

            // thread para receber mensagens do servidor
            r = new Recebedor(cliente.getInputStream());
            new Thread(r).start();

            // lê msgs do teclado e manda pro servidor
            // Scanner teclado = new Scanner(System.in);
            //while (teclado.hasNextLine()) {
            saida = new PrintStream(cliente.getOutputStream());
            // saida.println(this.nome + ": " + teclado.nextLine());
            while (true) {
                Thread.sleep(0);
            if (envia == true) {
                    String e = digitaC.getText();
                    //historicoC.setText("(" + this.nome + ")" + " " + e + "\n");
                    saida.println( this.nome + "//" + e);
                    envia =false;
                }
            }

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void sendMessage() {

    }

    public void teste(ActionEvent actionEvent) {

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}
