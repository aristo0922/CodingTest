package August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2086 {

  static int MAX = 1000000000;

  static int[] arr;
  static long sum = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    long from = Long.parseLong(st.nextToken());
    long to = Long.parseLong(st.nextToken());

    if (from >= MAX) {
      from = from % MAX;
    }
    if (to >= MAX) {
      to = to % MAX;
    }

    arr = new int[(int) to + 1];
    arr[0] = 0;
    arr[1] = 1;

    cycle(2, (int) from, (int) to);
    System.out.println(sum);
  }

  public static void cycle(int now, int from, int to) {
    if (now > to) {
      return;
    }
    arr[now] = arr[now - 1] + arr[now - 2];
    int node = arr[now];
    if (now >= from && now <= to) {
      sum += node;
    }

    if(sum >= MAX){
      sum = sum%MAX;
    }

    cycle(++now, from, to);
  }
}
