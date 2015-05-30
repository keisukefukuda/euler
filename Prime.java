import java.util.ArrayList;

public class Prime {
  public static void main(String argv[]) {
    Prime p = new Prime();
  }

  private ArrayList<Long> primes;

  public Prime() {
    primes = new ArrayList<Long>();
    primes.add(2L);
    primes.add(3L);
    primes.add(5L);
    primes.add(7L);
    primes.add(11L);
  }
}

