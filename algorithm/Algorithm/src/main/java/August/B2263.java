package August;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B2263 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter sb = new BufferedWriter(new OutputStreamWriter(System.out));
  static int[] inOrder, postOrder;
  static int  size;

  public static void main(String[] args) throws IOException {
    size = Integer.parseInt(br.readLine());
    set(size);

    findPreOrders(0,size - 1, 0, size - 1);
    sb.flush();
  }

  public static void findPreOrders(int inStart, int inEnd, int postStart, int postEnd)
      throws IOException {
    if(inStart > inEnd || postStart > postEnd || inEnd <0 || postEnd < 0 || postStart < 0) return;
    if(inEnd>=size || postEnd >= size) return;
    int root = postOrder[postEnd];

    int index=0;

    for (int i = 0; i <= inEnd; i++) {
      if(inOrder[i] == root){
        index= i;
        break;
      }
    }

    sb.write(root + " ");

    int leftSize = index - inStart;
    findPreOrders(inStart, index - 1, postStart, postStart + leftSize - 1);
    findPreOrders(index + 1, inEnd, postStart + leftSize, postEnd - 1);
  }

  public static void set(int size) throws IOException {
    inOrder = new int[size];
    postOrder = new int[size];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < size; i++) {
      inOrder[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < size; i++) {
      postOrder[i] = Integer.parseInt(st.nextToken());
    }
  }

}
