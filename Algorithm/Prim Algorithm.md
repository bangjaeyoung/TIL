> 이 글은 다음 링크를 참고해서 작성한 글이며, [링크](https://maetdori.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Minimum-Spanning-Tree-MST-%EC%B5%9C%EC%86%8C-%EC%8B%A0%EC%9E%A5-%ED%8A%B8%EB%A6%AC?category=857970)를 참고하면 쉽게 이해할 수 있습니다.
# Prim Algorithm
> 양수 가중치 무방향 그래프에서 **Minimum Spanning Tree(MST)**를 찾는 알고리즘   
> **정점을 기준으로** 트리 형성 = **가중치가 작은 간선부터 선택**

## Prim Algorithm 동작 과정
1. 노드 하나를 골라 시작 ex) 2번 노드 시작
2. 2번 노드가 가진 간선들 중 가중치가 가장 작은 간선을 선택하고 간선을 따라 노드 이동
3. 이미 선택된 간선은 제외하고 사이클이 발생하지 않도록 2번 동작을 반복

**사이클이 발생하는 경우, MST를 찾을 수 없음**
