public class ForecastProviderImpl extends UnicastRemoteObject implements ForecastProvider{
  private Map<Integer, List<DayForecast>> forecast;

  public List<DayForecast> getForecast(Integer postalCode) throws RemoteException{
    //Maybe some casting necessary, IDK, what is a Map? What is Java? Monkey see, monkey do
    return forecast.get(postalCode);
  }
}
