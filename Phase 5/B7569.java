package may.lastWeek.June;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//https://www.acmicpc.net/problem/7569
//- `푼 문제 문제의 링크, 문제 레벨, 해당 알고리즘으로 문제를 푼 이유, 코드에 대한 상세한 주석 작성`
// 골드5,
// 그래프 이번 페이즈에서는 그래프 중심으로 문제를 풀었습니다.
// 이 전까지 그래프 문제는 주로 2차원 배열에서 생각하여 답을 도출하는 게 많았는데, 이 문제는 3차원인 상태를 생각해야 했기에 선택했습니다.
// 이 문제를 BFS로 접근하는 이유는 시작 노드와 연결된 특정 조건의 노드를 찾아가고, 걸린 시간을 계산해야 했기 때문입니다.

public class B7569 {

    public static class Point {
        // 토마토 위치 정보 저장
        int height;
        int row;
        int col;
        public Point(int h, int r, int c) {
            this.height = h;
            this.row = r;
            this.col = c;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 인접한 토마토 위치 탐색을 위한 방향
    static int rowArr[] = {0, 0, 0, 0, 1, -1};
    static int colArr[] = {0, 0, 1, -1, 0, 0};
    static int heightArr[] = {1, -1, 0, 0, 0, 0};
    static int m, n, h; // 가로, 세로, 높이
//    토카토가 저장될 상자
    static int arr[][][];
    static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        arr = new int[h + 1][n + 1][m + 1];

        // 초기화
        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= m; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                    if (arr[i][j][k] == 1) queue.add(new Point(i, j, k));
                }
            }
        }

        // 탐색 결과 출력
        System.out.println(bfs());
    }

    public static int bfs() {
        // queue: 확인된 인접한 토마토
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int height = point.height;
            int col = point.col;
            int row = point.row;

            for (int i = 0; i < 6; i++) {
                int nh = height + heightArr[i];
                int ncol = col + colArr[i];
                int nrow = row + rowArr[i];

                // 유효한 좌표인지 확인 (상자 길이보다 작고 1보다 커야 한다)
                if (checkPoint(nh, nrow, ncol)) {
                    queue.add(new Point(nh, nrow, ncol));
//                    기존에 저장된 값 +1 을 하여 익은 날짜를 표시한다.
                    arr[nh][nrow][ncol] = arr[height][row][col] + 1;
                }
            }
        }
//        최댓값을 구하기 때문에 Integer 에서 가장 작은 정수로 초기화한다
        int result = Integer.MIN_VALUE;

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= m; k++) {
//                    하나라도 익지 않았을 경우
                    if (arr[i][j][k] == 0) return -1;
                    result = Math.max(result, arr[i][j][k]);
                }
            }
        }
        if (result == 1) return 0;
        else return (result - 1);
    }

    // 유효한 좌표인지 확인 (상자 길이보다 작고 1보다 커야 한다)
    private static boolean checkPoint(int height, int row, int col) {
        if (height < 1 || height > h || row < 1 || row > n || col < 1 || col > m) return false;
        if (arr[height][row][col] == 0) return true;
        return false;
    }
}
