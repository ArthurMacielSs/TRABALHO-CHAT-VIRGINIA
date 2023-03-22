/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * @author Virginia
 */
public class Recebedor implements Runnable {
 
   private InputStream servidor;
 
   public Recebedor(InputStream servidor) {
     this.servidor = servidor;
   }
 
   @Override
   public void run() {
     // recebe msgs do servidor e imprime na tela
     Scanner s = new Scanner(this.servidor);
     while (s.hasNextLine()) {
        String mensagem = s.nextLine().split(" ")[1].replace("/", "");
        if (mensagem != s.nextLine()) {
            System.out.println(mensagem);
            System.out.println(s.nextLine()); 
        }
       
     }
   }
 }