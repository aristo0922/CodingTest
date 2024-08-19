package August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B6550_re {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] words;
    do{
      String line = br.readLine();
      if(line == null || line.equals("\n")) return;
      words = line.replaceAll("\n", "").split(" ");
      if(words.length<2) return;

    } while(cycle(words[0], words[1]));
  }

  static boolean cycle(String word1, String word2){
    char[] compare1 = word1.toCharArray();
    char[] compare2 = word2.toCharArray();

    int pointer1=0, pointer2=0;
    int size1 = compare1.length, size2 = compare2.length;

    while(pointer1 < size1 && pointer2 < size2){
      if(compare1[pointer1] == compare2[pointer2++]){
        pointer1++;
      }
    }

    if(pointer1 < size1) {
      System.out.println("No");
      return true;
    }
    System.out.println("Yes");
    return true;
  }
}
