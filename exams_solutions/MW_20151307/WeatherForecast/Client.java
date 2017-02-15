public class Client{
  public static void main(String[] args) throws RemoteException, NamingException{

    // Get the remote object
    Registry registry = LocateRegistry.getRegistry();
    ForecastProvider forecastProvider =
      (ForecastProvider) registry.lookup("forecast_provider");

    forecastProvider.getForecast();

  }
}
