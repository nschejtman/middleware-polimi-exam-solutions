public class Server{
  public static void main(Stringp[] args) throws RemoteException, NamingException{
    // Bind remote object to registry
    StockExchangeImpl stockExchangeImpl = new StockExchangeImpl();
    Registry registry = LocateRegistry.getRegistry();
    registry.bind("stock_exchange", stockExchangeImpl);

    // Wait for connections
    while(true){
      System.out.println("SOOOO MAANYYY LINEEESS");
    }

  }
}
