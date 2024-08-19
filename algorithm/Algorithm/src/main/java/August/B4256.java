package August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4256 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int[] pre;
  static int[] in;
  static int size;
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    int games = Integer.parseInt(br.readLine());

    for (int i = 0; i < games; i++) {
      size = Integer.parseInt(br.readLine());
      setArr(size);
      findPostOrder(0, 0, size-1);
      sb.append("\n");
    }

    System.out.println(sb.toString());
  }

  public static void findPostOrder(int rIndex, int begin, int end) {
    if(rIndex>=size) return;
    int root = pre[rIndex];
    for (int i = begin; i <= end; i++) {
      if (root == in[i]) {
        findPostOrder(rIndex + 1, begin, i-1 );
        findPostOrder(rIndex + 1 + i - begin, i+1, end);
        sb.append(root + " ");
        return;
      }
    }
  }

  public static void setArr(int size) throws IOException {
    pre = new int[size];
    in = new int[size];

    String[] inputs = br.readLine().split(" ");
    for (int i = 0; i < size; i++) {
      pre[i] = Integer.parseInt(inputs[i]);
    }
    inputs = br.readLine().split(" ");
    for (int i = 0; i < size; i++) {
      in[i] = Integer.parseInt(inputs[i]);
    }
  }

}
