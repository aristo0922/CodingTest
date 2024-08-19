package August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B6550 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = br.readLine();
    String[] words=null;
    if(line == null || line.isEmpty()){
      return;
    }

    do{
      words = line.split(" ");
      if(words.length <= 1) return;
      char[] S = words[0].toCharArray();
      char[] T = words[1].toCharArray();
      solution(S, T);

      line = br.readLine();
      if(line == null || line.isEmpty()){
        return;
      }
      words = line.split(" ");
    } while (words.length == 2 && words != null);
  }

  public static void solution(char[] S, char[] T) {
    int sPointer = 0, tPointer = 0;
    int size = T.length;
    int sPosition = S.length;
    try{
      while (tPointer < size && sPointer < sPosition) {
        if (S[sPointer] == T[tPointer++]) {
          sPointer++;
        }
      }
      if (sPointer == sPosition) {
        System.out.println("Yes");
        return;
      }
    }catch (NullPointerException e){

    }
    System.out.println("No");
  }
}