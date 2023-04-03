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

    private int porta; //declara o elemento porta 
    private List<PrintStream> clientes; // Um objeto "PrintStream" representa uma saída de impressão para um fluxo de saída, como um arquivo ou a tela do console.

    public Servidor(int porta) { //metódo construtor
        this.porta = porta; //inicia a variavel porta 
        this.clientes = new ArrayList<>(); //incia uma arrayList na variavel cliente 
    }

    public void run() { //inicia o metódo run para rodar a thread 
        try {

            ServerSocket servidor = new ServerSocket(this.porta); //abre o servidor socket (comunicação entre dois computadores) e o inicia com uma porta 
            HistoricoMsg.setText("Porta 12345 aberta! \n"); //coloca o texto para rodar na interface grafica 
                       
            while (true) {
                // aceita um cliente
                Socket cliente = servidor.accept(); //pega o cliente que tentou se conectar e o armazena nessa classe de um socket cliente
                //accept quando chega um cliente passa ele para a classe cliente 
                HistoricoMsg.setText(HistoricoMsg.getText()+("Nova conexão com o cliente "
                        + cliente.getInetAddress().getHostAddress() //notifica que o cliente foi aceito e imprime seu ip 
                )+ ("\n"));

                // adiciona saida do cliente à lista, imprimindo para todos 
                PrintStream ps = new PrintStream(cliente.getOutputStream()); //PrintStream = objeto que imprime/trata dados, armazena a saída do socket
                //Output stream grava dados, input recebe
                this.clientes.add(ps); //escreve os dados na lista

                // cria tratador de cliente numa nova thread
                TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);//instancia um objeto tc da classe 
                //trata cliente, que recebe os dados do cliente 
                
                /*O método "getInputStream()" é usado para obter um objeto InputStream a partir do qual é possível ler os dados que estão
                sendo enviados pelo cliente para o servidor através da conexão estabelecida. O objeto
                InputStream que é retornado representa a entrada de dados a partir da qual o servidor pode ler os bytes enviados pelo cliente.*/
                
                new Thread(tc).start(); //incia a thread tc 
            }
        } catch (IOException e) { //fim do bloco try catch

        }
        
        /*NESSE CÓDIGO O OBJETO CLIENTE ENTRA NO SERVIDOR, E ADICONA TODOS SEUS DADOS DIGITADOS A UMA LISTA, ELE ENTÃO
        ENTRA COMO PARAMENTRO NA CLASSE TRATA CLIENTE, QUE PEGA O FLUXO DE DADOS DO CLIENTE, O ARMAZENA EM UM SCAN, E
        ENQUANTO AINDA TIVER SAÍDA ELA JOGA ESSA SAÍDA NA AREA DE MENSAGENS DO SERVIDOR)
        */

    }

    public void distribuiMensagem(String msg) { //Recebe como parametro a entrada de dados do cliente (nextline
        // envia msg para todo mundo, exceto o servidor
        
        for (PrintStream cliente : this.clientes) { //PrintStream é uma classe relacionada a saida de dados cria uma variavel desse tipo
            //esse for roda todos os elementos da lista criada anteriormente no server
            //esse cliente não tem nada a ver com o socket 
            if (cliente != System.out) { //se o cliente for diferente da saida do servidor
                cliente.println(msg); //envia a mensagem a todos os clientes 
            }
        }

    }
}
