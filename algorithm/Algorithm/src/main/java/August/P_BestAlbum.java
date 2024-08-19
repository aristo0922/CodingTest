package August;

import java.util.*;

public class P_BestAlbum {

  static String[] genres = {"classic", "pop", "classic", "classic"};
  static int[] plays = {500, 600, 150, 800};
  static HashMap<String, ArrayList<Integer>> infoPlays = new HashMap<>();
  static int[] playNums;
  static int[] answer;
  static Deque<Integer> selected;

  public static void main(String[] args) {
    solution(genres, plays);
  }

  public static int[] solution(String[] genres, int[] plays) {
    playNums = plays;

    ArrayList<String> sortedGenre = initGenres(genres, playNums);
    answer = new int[sortedGenre.size() * 2];
    matchGenre(genres);
    int index = 0;
    for (String genre : sortedGenre) {
      int[] sub = findSongFromGenres(genre);
      for (int i = 0; i < 2; i++) {
        if(sub.length<2 && i >=1){
          return answer;
        }
        answer[index++] = sub[i];
      }
    }

    for(int node: answer){
      System.out.println(node);
    }
    return answer;
  }

  public static int[] findSongFromGenres(String genre) {
    ArrayList<Integer> songList = infoPlays.get(genre);
    selected = new ArrayDeque<>(songList.size());
    int size = songList.size();

    for (int i = 0; i < size; i++) {
      locateNode(songList.get(i));
    }

    int[] subAns = new int[songList.size()];

    for (int i = 0; i < songList.size(); i++) {
      subAns[i] = selected.pollFirst();
    }
    return subAns;
  }

  public static void locateNode(int index) {
    if (selected.size() == 0) {
      selected.add(index);
      return;
    }
    int rankedNode = playNums[selected.peekFirst()];
    int node = playNums[index];

    if (rankedNode < node) {
      selected.addFirst(index);
      return;
    }
    if (selected.size() == 1) {
      selected.addLast(index);
      return;
    }
    rankedNode = playNums[selected.peekLast()];
    if (rankedNode < node) {
      selected.poll();
      selected.addLast(index);
    }

  }

  public static Deque<Integer> insertFirst(Deque<Integer> que, int index) {
    if (que.size() == 0) {
      que.addFirst(index);
      return que;
    }
    int n = que.peekFirst();
    int compareA = playNums[n];
    int compareB = playNums[index];
    if (compareA < compareB) {
      que.addFirst(index);
      return que;
    }
    if (compareA == compareB && n > index) {
      que.addFirst(index);
      return que;
    }
    return que;
  }

  public static Deque<Integer> insertLast(Deque<Integer> que, int index) {
    int compare = que.peekLast();
    if (compare == index) {
      return que;
    }
    int compareA = playNums[compare];
    int compareB = playNums[index];
    if (compareA < compareB) {
      que.pollLast();
      que.addLast(index);
      return que;
    }
    if (playNums[compare] == playNums[index] && compare > index) {
      que.pollLast();
      que.addLast(index);
      return que;
    }
    return que;
  }

  public static void matchGenre(String[] genres) {// genres: songs index list
    int size = genres.length;
    ArrayList<Integer> songs;
    for (int i = 0; i < size; i++) {
      if (infoPlays.containsKey(genres[i])) {
        songs = infoPlays.get(genres[i]);
      } else {
        songs = new ArrayList<>();
      }

      songs.add(i);
      infoPlays.put(genres[i], songs);
    }
  }

  public static ArrayList<String> initGenres(String[] genres, int[] plays) {
    Map<String, Integer> counts = new HashMap<>();

    for (int index = 0; index < genres.length; index++) {
      int score = counts.getOrDefault(genres[index], 0) + plays[index];
      counts.put(genres[index], score);
    }
    ArrayList<String> sortedGenres = ranks(counts);
    return sortedGenres;
  }

  public static ArrayList<String> ranks(Map<String, Integer> counts) {
    ArrayList<String> rank = new ArrayList<>();
    Iterator<String> keySet = counts.keySet().iterator();
    int index = 0;
    String rankedKey = keySet.next();
    rank.add(rankedKey);
    while (keySet.hasNext()) {
      String key = keySet.next();
      while (index <= rank.size() - 1) {
        rankedKey = rank.get(index);
        if (counts.get(rankedKey) < counts.get(key)) {
          rank.add(index, key);
          index = 0;
          break;
        }
        index++;
      }
      if (index >= rank.size()) {
        rank.add(key);
      }
    }

    return rank;
  }
}
