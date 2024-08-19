package August;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1629 {
  static long standard= 10000000000L;
  public static void main(String[] args) throws IOException{
    long A, B, C;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    A = Long.parseLong(st.nextToken());
    B = Long.parseLong(st.nextToken());
    C = Long.parseLong(st.nextToken());
    long answer=1L;
    for(long i=0 ; i<B ; i++){
      answer*=A;
      if(answer > standard){
        long sum = answer / C;
        long temp = answer %C;
        if(temp == 0){
          System.out.println(0);
          break;
        }
        answer = sum * C+temp;
      }
    }
    System.out.println(answer%C);
  }





}
