package aristo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/*
https://www.acmicpc.net/problem/2178
미로탐색(실버1)
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.*/
public class B2178 {
    static List<String> todoes = new ArrayList<>(); //현재 주기에서 방문할 노드 목록
    static List<String> nextDo = new ArrayList<>(); // 다음 주기에서 방문할 노드 목록
    static List<String> isVisited = new ArrayList<>(); // 방문 여부 확인
    static char[][] table; // 미로 판
    static int result = 0; // 거쳐온 블럭 수
    static int N, M; // 미로의 크기


    public static void main(String[] args) {
        setValues(); // static 으로 선언한 변수 초기화
        todoes.add("0 0"); // 처음 시작 지점 설정

        while (todoes.size() > 0){
            for (String todo : todoes) {//현재 주기에서 방문할 수 있는 노드 모두 방문
                if(control(todo) == true) continue;
                break;// BFS 로 이어지는 메서드
            }
            nextGeneration();// todo를 nextDo로 바꾸고 nextDo는 비운다.
        }
    }

    public static void nextGeneration() {
        todoes = nextDo;
        nextDo = new ArrayList<>();
        result += 1; // 주기가 바뀌면서 지나온 블럭 수 +1
    }

    public static void endGeneration(){
        todoes = new ArrayList<>();
        nextDo = new ArrayList<>();
        result += 1; // 주기가 바뀌면서 지나온 블럭 수 +1
    }

    public static boolean control(String todo) {
        //String 방식으로 받은 입력값에서 가로와 세로 좌표를 구한다.
        // ex. todo={"2 5"} x:2, y:5
        int x = Integer.parseInt(todo.split(" ")[0]);
        int y = Integer.parseInt(todo.split(" ")[1]);
        // 조건분기 발생!
        // 1. 좌표가 원하는 목표 지점에 도착할 경우
        if (x == N - 1 && y == M - 1) {
            endGeneration(); // 메서드를 나가자마자 for 문에서 빠져나오도록 todo 를 비운다.
            System.out.println(result); // 결과 출력
            return false;
        }
        // 2. 좌표가 원하는 목표 지점에 도착하지 못할 경우 BFS 를 돈다.
        BFS(x, y);
        return true;
    }

    public static void BFS(int i, int j) {
        StringBuilder sb = new StringBuilder();
        sb.append(i).append(" ").append(j);

        isVisited.add(sb.toString());//현재 노드를 방문 상태로 전환
        // 상하좌우 노드를 nextDo에 추가하도록
        addTodoes(i, j - 1);
        addTodoes(i, j + 1);
        addTodoes(i - 1, j);
        addTodoes(i + 1, j);
    }

    public static void addTodoes(int i, int j) {
        // 추가하려는 노드 좌표가 유효한 좌표인지 확인 후 삽입
        if (i < 0 || j < 0) return;
        if (i >= N || j >= M) return;
        StringBuilder sb = new StringBuilder();
        sb.append(i).append(" ").append(j);
        if(canVisit(sb.toString()) == false) return;

        if (table[i][j] == '1') {
            nextDo.add(sb.toString());
        }
    }

    public static boolean canVisit(String commands){
        //중복 방문 방지를 위함
        if (isVisited.contains(commands)) return false;
        if (todoes.contains(commands)) return false;
        if (nextDo.contains(commands)) return false;
        return true;
    }

    public static void setValues() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] nums = br.readLine().split(" ");
            N = Integer.parseInt(nums[0]);
            M = Integer.parseInt(nums[1]);

            table = new char[N + 1][M + 1];
            for (int i = 0; i < N; i++) {
                table[i] = br.readLine().toCharArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
