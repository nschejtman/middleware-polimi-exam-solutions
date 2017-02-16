public class Stock implements Serializable{
  /** Max and min for the stock on an given day **/
  private float max;
  private float min;

  private float val; // Current/last known value
  private String stockCode;

  public Stock(float max, float min, float val, String stockCode){
    this.max = max;
    this.min = min;
    this.val = val;
    this.stockCode = stockCode;
  }

  // Assume getters and setters

}
