package aristo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B7795 {

    // 입력값  받는 함수 세팅
    static int[] setArr(int size, String[] args) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(args[i]);
        }
        return arr;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < caseNum; i++) {
            int result = 0;
            String[] arg = br.readLine().split(" ");
            // nums[] 각 배열의 크기 0:A, 1:B
            int[] nums = setArr(2, arg);
            arg = br.readLine().split(" ");
            int[] A = setArr(nums[0], arg);
            arg = br.readLine().split(" ");
            int[] B = setArr(nums[1], arg);

            Arrays.sort(A);
            Arrays.sort(B);

            // nums[] 각 배열의 크기 0:A, 1:B
            for (int j = 0; j < nums[0]; j++) {
                int left = 0;
                int right = nums[1] - 1;
                int index = -1;
                // 비교하는 포인트 left <= right 가 되는 순간의 상황은 의미가 없으므로 반복문이 멈추도록 설계
                while (left <= right) { // A의 노드 하나하나씩 해당 반복문을 돈다.
                    int mid = (left + right) / 2;
                    // 비교하는 배열의 중간값을 찾아 비교
                    // 중간에 있는 배열이 비교 노드보다 크면 중앙값 뒤에 나열된 노드들은 현재 노드보다 크다.
                    // 중간에 있는 배열이 비교 노드보다 작으면 중앙값 앞에 나열된 노드들은 현재 노드보다 작다.

                    // 구하는 것: A의 크기가 B보다 큰 쌍이 몇 개나 있는지
                    // A 의 인덱스는 0에서부터 올라간다.
                    if (B[mid] < A[j]) {
                        // mid 보다 뒤에 있는 B의 노드는 모두 A보다 작을 때
                        // 비교하는 B의 index 를 중앙값+1 로 잡아 while 반복문을 진행한다.
                        index = mid;
                        left = mid + 1;
                        continue;
                    }
                    // 비교하는 A의 원소보다 B의 원소가 더 크다.
                    // 그렇다면 비교하는 B의 원소값을 조정한다. 더 작은 값을 볼 수 있도록
                    right = mid - 1;
                }
                // 해당 index 의 앞의 값들은 모두 index 위치 노드보다 작은 수이다. 따라서 index 까지 원소의 갯수 index+1 을 더한다.
                result += index + 1;
            }
            // System.out.println코드를 줄이기 위해 StringBuilder 를 사용한다.
            // 테스트 케이스는 하나가 아니다.
            sb.append(result).append("\n");
        }
        System.out.println(sb);

    }
}
