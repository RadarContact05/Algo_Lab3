import java.io.*;

public class Trader extends Thread{
  private DataBuffer<StockPick> stockPicks;
  private int nrPicks; // nr of stock picks for printing to log-file each second
  private int endTime; // time to run in seconds

  public Trader(DataBuffer<StockPick> stockPicks,
                int bufferSize,
                int nrPicks,
                int endTime){
    this.stockPicks = stockPicks;
    this.nrPicks = nrPicks;
    this.endTime = endTime;
  }

  public void run(){
    try{
      OutputStreamWriter writer =
        new OutputStreamWriter(new FileOutputStream("log.txt"));
      writer.write("Start\n");
      writer.close();
    }
    catch(IOException e){}
    int time = 0;
    while (time < endTime){
      try{
        sleep(1000);
      }catch( InterruptedException e){}

      /*
          * Your code here:

          * 1: Empty stockPicks and add the elements
          to an empty priority queue.

          * 2: Take the largest nrPicks from the priority
          queue and print them in priority order
          to the file log.txt.
          OBS: you should extend the file, not override
          what is already there.
      */

     StockPick[] picks;
     synchronized (stockPicks) {
      // Move all picks out of the buffer into an array
      int size = stockPicks.size();
      picks = new StockPick[size];
      for (int i = 0; i < size; i++) {
          picks[i] = stockPicks.dequeue();
      }

     }
      // Build a max‐heap from that array
      MaxPQ<StockPick> pq = new MaxPQ<>(picks);

      try {
          Writer out = new OutputStreamWriter(
              new FileOutputStream("log.txt", true)
          );
          // Take the top nrPicks items and write them
          for (int i = 0; i < nrPicks && !pq.isEmpty(); i++) {
              StockPick best = pq.delMax();
              out.append(best.toString()).append("\n");
          }
          out.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
   }

      time++;
      System.out.println("Time elapsed: "
                         + time
                         + " seconds.");
      }

    public static void main(String[] cmdLn){
        int bufferSize = 50;
        DataBuffer<StockPick> stockPicks =
              new DataBuffer<StockPick>(bufferSize);
        // StockPicker 1
        String[] stocks1 = {"TSLA", "CCJ", "GME",
                            "UUUU", "MFST", "GOOGL",
                            "AAPL", "AMZN"};

        StockPicker Stockpicker1 =
          new StockPicker("North America analyzer",
                          stockPicks,
                          stocks1,
                          10);


        // StockPicker 2
        String[] stocks2 = {"ETH", "BTC"};

        StockPicker Stockpicker2 =
          new StockPicker("Cryptocurrencices analyzer",
                           stockPicks,
                           stocks2,
                           10);

        // trader
        Trader trader =
        new Trader(stockPicks,
                   bufferSize,
                   3,
                   10);

        // run simulation
        Stockpicker1.start();
        Stockpicker2.start();
        trader.start();
      }
    }
