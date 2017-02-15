public interface ForecastServer extends Remote{
  public List<DayForecast> getForecast(int postalCode) throws RemoteException;
}
