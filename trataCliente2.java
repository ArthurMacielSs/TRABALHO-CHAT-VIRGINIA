package chat; //colca a classe código dentro do pacote chat 

import java.io.InputStream;
import java.util.Scanner;

public class TrataCliente2 implements Runnable { //trasnforma a classe em uma thread 
 
   private InputStream cliente; //inputstream é uma classe que le dados de um fluxo de entrada
   private Servidor servidor; //cria uma variavel(objeto) da classe servidor 
 
   public TrataCliente(InputStream cliente, Servidor servidor) { //metodo construtor, onde entram um cliente e um sercidor inicializados 
     this.cliente = cliente; // recebe cliente 
     this.servidor = servidor; //recebe servidor 
   }
 
   @Override
   public void run() {
     // quando chegar uma msg, distribui pra todos
     Scanner s = new Scanner(this.cliente); //cria uma variavel scanner que pega os dados digitados pelo cliente e armazena
     while (s.hasNextLine()){ //enquanto o escanner ainda tiver linhas de entrada para ler 
       servidor.distribuiMensagem(s.nextLine()); //imprime a linha de entrada lida na saída do servidor
     }
     s.close(); //fecha o scanner 
   }
 }
