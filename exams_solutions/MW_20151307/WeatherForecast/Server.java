public class Server{
  public static void main(String[] args) throws RemoteException, NamingException{

    // Bind remote object to registry
    ForecastProviderImpl forecastProvider = new ForecastProviderImpl();
    Registry registry = LocateRegistry.getRegistry();
    registry.bind("forecast_provider", forecastProvider);

    // Wait for connections
    while(true){
      System.out.println("SOOOO MAANYYY LINEEESS");
    }

  }
}
