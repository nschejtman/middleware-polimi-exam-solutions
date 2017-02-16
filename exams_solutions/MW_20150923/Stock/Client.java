public class Client{
  public static void main(String[] args) throws RemoteException, NamingException{
    // Get the remote object
    Registry registry = LocateRegistry.getRegistry();
    StockExchange stockExchange =
      (StockExchange) registry.lookup("stock_exchange");

    Stock googleStock = stockExchange.getStock("GOOGL");

  }
}
