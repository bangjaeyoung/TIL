## LIS(Longest Increasing Subsequence) - 최장 증가 부분 수열
- 가장 긴 증가하는 부분 수열을 구하는 것
  - ex. **5** 3 **7** **8** 6 2 **9** 4  ->  4 (5789 이외에도 가능한 수열이 존재)

- 문제   
첫째 줄은 입력되는 데이터의 수 n이고, 둘째 줄은 n개의 입력 데이터들이 주어진다. 부분증가수열의 최대 길이를 출력하라.

- 풀이 코드
```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.print(LIS(arr));
    }

    private static int LIS(int[] arr) {
        int answer = 0;
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int max = 0;

            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j] && dp[j] > max) {
                    max = dp[j];
                }

                dp[i] = max + 1;
                answer = Math.max(answer, dp[i]);
            }
        }

        return answer;
    }
}
```

<br>

## LCS
- LCS는 주로 **최장 공통 부분수열(Longest Common Subsequence)** 를 나타냄
  - ex. **ban**ana, f**ban**kk  ->  **ban**
- 하지만, 최장 공통 문자열(Longest Common Substring)을 말하기도 함
  - ex. **b**fd**ann**, **ba**k**n**i**n**  ->  **bann**

- 최장 공통 부분수열 문제   
첫째 줄과 둘째 줄에 두 문자열이 주어진다. LCS(Longest Common Subsequence)를 출력하라.

- 풀이 코드   
```java
import java.io.*;

public class Main {
    static int[][] dp;
    static int length_1, length_2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();

        length_1 = str1.length;
        length_2 = str2.length;

        dp = new int[length_1 + 1][length_2 + 1];

        System.out.print(LCS(str1, str2));
    }

    private static int LCS(char[] str1, char[] str2) {
        for (int i = 1; i <= length_1; i++) {
            for (int j = 1; j <= length_2; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[length_1][length_2];
    }
}
```

- [ref](https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence#%EA%B5%AC%ED%98%84%EA%B3%BC%EC%A0%95).
- [ref](https://loosie.tistory.com/379).
- [ref](https://st-lab.tistory.com/139).
