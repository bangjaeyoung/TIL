> 이 글은 다음 링크를 참고해서 작성한 글이며, [링크](https://maetdori.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Kruskal-Algorithm-Union-Find-%ED%81%AC%EB%A3%A8%EC%8A%A4%EC%B9%BC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%9C%A0%EB%8B%88%EC%98%A8-%ED%8C%8C%EC%9D%B8%EB%93%9C?category=857970)를 참고하면 쉽게 이해할 수 있습니다.

# Kruskal Algorithm
> 양수 가중치 무방향 그래프에서 Minimum Spanning Tree(MST)를 찾는 알고리즘   
> **간선을 기준으로** 트리 형성 = **가중치가 작은 간선부터 선택**
<br>

사이클이 발생하는 경우, MST를 찾을 수 없음   
-> 사이클의 발생 여부는 **Union Find** 개념을 이용해 확인 가능

## Union Find(합집합 찾기)
> Disjoint-Set Algorithm   
> 임의의 두 노드를 선택했을 때, 두 노드가 하나의 그래프에 속하는지 판별하는 방법

### Union Find 동작 과정
1. 각 노드들의 부모를 저장할 공간을 만들고, 각 노드 자기 자신을 부모로 초기화 ex) 노드 0 -> 부모 0, 노드 1 -> 부모 1
2. 0번, 1번 노드가 서로 연결되어 있다고 가정하면, 부모는 통상적으로 값이 작은 노드 번호를 따르기 때문에, 1번 노드의 부모를 0으로 바꿈
3. 0번, 3번, 4번 노드가 차례로 연결되어 있다고 가정하면, 4번 노드의 부모는 3이 됨
4. 3번 노드의 부모가 자기 자신(3)이 아니고, 0을 가르키고 있기 때문에, 총 대표인 0이 4번 노드의 부모가 됨

> Union Find에서 부모를 갱신할 때, 자기 자신의 노드 번호를 부모로 가지는 노드를 찾을 때까지 깊이 탐색을 하게 됨   
> 모든 과정을 마치면, 최종적으로 문어발 형식의 그래프 집합들이 생겨남

</br>

## Union Find 메서드
```Java
    public int find(int v) {
        if (unf[v] == v)
            return v;
        else
            return unf[v] = find(unf[v]);
    }

    public void union(int v1, int v2) {
       int fv1 = find(v1);
        int fv2 = find(v2);

        if (fv1 != fv2)
            unf[fv2] = fv1;
    }
```

</br>

## Kruskal Algorithm 구현
> 알고리즘 문제 - [프로그래머스 Lv.3 섬 연결하기](https://school.programmers.co.kr/learn/courses/30/lessons/42861)   
> [문제 풀이 코드](https://github.com/bangjaeyoung/codingtest/commit/629bec419eb31ce4dc5db738917b1b8e130fde29)

