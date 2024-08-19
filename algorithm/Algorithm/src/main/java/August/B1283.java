package August;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class B1283 {

  private static Map<Character, String> registered = new HashMap<>();
  private static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int size = Integer.parseInt(br.readLine());
    String[] arr = new String[size];
    for (int i = 0; i < size; i++) {
      arr[i] = br.readLine();
    }

    for (String node : arr) {
      control(node);
//      sb.append(result + "\n");
    }

    System.out.println(sb.toString());
  }

  public static void control(String command) throws IOException{
    StringTokenizer st = new StringTokenizer(command);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    while(st.hasMoreTokens()){
      bw.write(register(st.nextToken()));
    }
    bw.write("\n");
    bw.flush();
  }

  public static String register(String command) {
    char[] nodes = command.toCharArray();
    StringBuilder builder = new StringBuilder();

    if(registered.containsValue(command.toLowerCase())){
      builder.append(command+" ");
      return builder.toString();
    }

    boolean flag=false;

    for (char node : nodes) {
      if(registered.containsKey(Character.toLowerCase(node))){
        builder.append(node);
        continue;
      }
      if(node == ' ' || flag == true){
        builder.append(node);
        continue;
      }
      registered.put(Character.toLowerCase(node), command.toLowerCase());
      builder.append("[" + node + "]");
      flag = true;
    }
    builder.append(" ");
    return builder.toString();
  }

}
