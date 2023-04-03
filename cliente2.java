public class cliente2 {
    private String host; 
   private int porta;
   
      //Declara metódos 
   public Cliente (String host, int porta) { //metodo construtor 
     this.host = host; //inicia o Host 
     this.porta = porta; //inicia a porta 
   }
      //Declara metódos 

   public void run (){
       try {
     Socket cliente = new Socket(this.host, this.porta); //inicia o socket com os dados do cliente 
     MostraMsgCliente.setText("O cliente se conectou ao servidor! \n");
 
     // thread para receber mensagens do servidor
     Recebedor r = new Recebedor(cliente.getInputStream()); //inicia um objeto do tipo recebedor 
     new Thread(r).start(); //inicia o recebedor como uma thread e o inicia 
     
     // lê msgs do teclado e manda pro servidor
     
     Scanner teclado = new Scanner(System.in);
     
     PrintStream saida = new PrintStream(cliente.getOutputStream());
     enviaCliente.addActionListener(this::teste);
     

//while (DigitaCliente.isEnabled()) {
      // MostraMsgCliente.setText(teclado.nextLine());
      
      
     //}
     
     saida.close();
     teclado.close();
     cliente.close();  
     
     } catch (IOException e) { //fim do bloco try catch

        }
   }
   public void teste(ActionEvent actionEvent) {
       MostraMsgCliente.setText(MostraMsgCliente.getText() +DigitaCliente.getText() + ("\n"));
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
