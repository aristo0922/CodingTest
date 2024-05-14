//// 백준 골드 5 https://www.acmicpc.net/problem/7576
//// BFS
//
///*
//토마토 창고의 토마토가 며칠 후에 다 익게 되는지를 구하는 문제이다.
//주변의 토마토를 익힌다는 점과 최소의 값을 구하는 문제로 bfs 알고리즘을 떠올렸다.
//
//dfs로 풀 경우에는 풀 필요한 계산이 너무나 많아진다.
//
//---
//
//상자의 전체를 돌면서 현재 익어있는 토마토의 주변을 모두 방문하며 익히고, 해당 위치의 포인터를 큐에 넣는다.
//1일에 익힐 수 있는 모든 토마토를 익힌 후 (-1, -1)을 넣어서 날짜를 구별할 수 있도록 한다.
//이를 반복하며 (-1, -1)값이 나오면 data에 1을 더하면서 토마토를 익혀나간다.
//
//마지막으로 check함수를 통하여 전체를 체크하여 익지 않는 토마토가 있다면 -1, 없다면 걸린 날을 print한다.
//
// */
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.StringTokenizer;
//
//public class Main {
//    static boolean[][] visited;
//    static int[][] arr;
//    static int N, M;
//    static Queue<Point> queue = new LinkedList<>();
//    static int[] x_pos = {0, 1, 0, -1};
//    static int[] y_pos = {1, 0, -1, 0};
//    // 상하좌우를 이동하기 위한 좌표값
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        M = Integer.parseInt(st.nextToken());   // 상자의 가로
//        N = Integer.parseInt(st.nextToken());   // 상자의 세로
//        arr = new int[N][M];
//        visited = new boolean[N][M]; // 방문기록
//
//        for(int i = 0; i < N; i++){     // y
//            st = new StringTokenizer(br.readLine(), " ");
//            for(int j = 0; j < M; j++) {    // x
//                arr[i][j] =  Integer.parseInt(st.nextToken());
//                if(arr[i][j] == 1){
//                    queue.add(new Point(i, j));
//                    visited[i][j] = true;
//                } // 이미 익어있는 토마토의 경우 1의 값을 삽입하고 방문한 것으로 친다.
//                else if (arr[i][j] == -1) {
//                    visited[i][j] = true;
//                } // 토마토가 없는 경우 방문할 필요가 없기 때문에 방문한 것으로 친다.
//            }
//        } // 초기의 값을 받기 위한 코드다.
//        queue.add(new Point(-1, -1));   // 날짜 변경을 체크하기 위해서 (-1, -1)값을 큐에 삽입한다.
//        int date = bfs();
//        if (check()) {  // 모든 토마토가 다 있었는지 확인한다.
//            System.out.println(date);
//        } else {
//            System.out.println(-1);
//        }
//
//    }
//    static class Point {    // 좌표를 편하게 계산하기 위한 class
//        public int x;
//        public int y;
//        public Point(int x, int y){
//            this.x = x;
//            this.y = y;
//        }
//    }
//    public static int bfs(){
//        int date = -1; // 이미 다 익었을 경우를 대비하여 -1일부터 시작한다.
//        while(!queue.isEmpty()){    // 큐가 빌 때까지 반복한다. 마지막에는 항상 (-1, -1)이다.
//            Point point = queue.poll(); // 큐에서 포인터 poll
//            if(point.x < 0){ // (-1, -1)값이 들어왔을 경우
//                date++;
//                if(!queue.isEmpty())
//                    queue.add(new Point(-1, -1));   // 날짜 변경 flag 삽입
//            }   // 날짜 변경의 경우 실행
//            else {
//                for (int i = 0; i < 4; i++) {
//                    int nx = point.x + x_pos[i];
//                    int ny = point.y + y_pos[i];    // 상하좌우 4방향을 방문하여 토마토를 익힌다.
//                    if (nx > -1 && nx < N && ny >-1 && ny < M && !visited[nx][ny]){
//                        queue.add(new Point(nx, ny));   // 방문한 곳을 큐에 넣어 추후 상하좌우를 방문한다.
//                        visited[nx][ny] = true;
//                    }
//                }
//            }
//        }
//        return date;
//    }
//
//    public static boolean check(){
//        for(int i = 0; i < N; i++) {     // y
//            for(int j = 0; j < M; j++) {    // x
//                if(!visited[i][j])
//                    return false;
//            }
//        }
//        return true;
//    }
//}