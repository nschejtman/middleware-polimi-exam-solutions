public class StockExchangeImpl extends UnicastRemoteObject implements StockExchange{
  private Map<String, Stock> stocks; // Maps stockcode string to stock obj.

  public Stock getStock(String stockCode) throws RemoteException{
    return stocks.get(stockCode);
  }

}
