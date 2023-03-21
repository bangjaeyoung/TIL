# 그리디(Greedy) 알고리즘
현재 상태에서의 선택지 중 최선의 선택지가 전체 선택지 중 최선의 선택지라고 가정하는 알고리즘

<br>

## 그리디 알고리즘 수행 과정
1. 해 선택 : 현재 상태에서 가장 최선이라고 생각되는 해를 선택
2. 적절성 검사 : 현재 선택한 해가 전체 문제의 제약 조건에서 벗어나지 않는지 검사
3. 해 검사 : 현재까지 선택한 해 집합이 전체 문제를 해결할 수 있는지 검사   
만약 해결하지 못한다면, 다시 과정 1로 돌아가서 같은 과정 반복

## 관련 문제
- [동전 0](https://www.acmicpc.net/problem/11047)
- [카드 정렬하기](https://www.acmicpc.net/problem/1715)
- [수 묶기](https://www.acmicpc.net/problem/1744)
- [회의실 배정](https://www.acmicpc.net/problem/1931)
- [잃어버린 괄호](https://www.acmicpc.net/problem/1541)


<br>

***[Reference](https://www.baeldung.com/java-greedy-algorithms)***