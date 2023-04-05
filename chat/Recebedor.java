package chat; //colca a classe código dentro do pacote chat 

import java.io.InputStream; //importa a biblioteca InputStream 
import java.util.Scanner; //importa a biblioteca InputStream 

import static chat.InterfaceC.historicoC;

public class Recebedor implements Runnable { //cria a classe recebedor (de mensagem) e a transforma em uma thread 

    private InputStream servidor; //Cria a variavel servidor no tipo InputStream o que quer dizer que ela lida com uma entrarda de dados 

    public Recebedor(InputStream servidor) { //metodo construtor que recebe uma variavel e inica nossa classe 
        this.servidor = servidor; //variavel servidor é passada para o nosso objeto 
    }

    @Override
    public void run() { //metodo run que roda a nossa thread 
        // recebe msgs do servidor e imprime na tela
        Scanner s = new Scanner(this.servidor);
        //cria uma variavel s do tipo scanner e a inicializa, passando como parametro o atributo servidor do objeto classe 
        //Scanner é uma variavel que administra a entrada de string 
        while (s.hasNextLine()) {
            String[] message = s.nextLine().split("//");
            InterfaceC.addMessage(message[0], message[1]); //troquei o historicoC.setText por um sout 
        }
    }
}
