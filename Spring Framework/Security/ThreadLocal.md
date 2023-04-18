# ThreadLocal
WebMVC 기반으로 프로젝트를 생성하면, 대부분의 경우에는 요청 1개당 Thread 1개가 생성됨

이때 ThreadLocal을 사용하면, Thread마다 고유한 공간을 만들 수 있고 그 곳에 SecurityContext를 저장할 수 있음

***요청 1개 : Thread 1개 : SecurityContext 1개***

---
<br>

그러나, ThreadLocal만 강제적으로 사용해야 하는 것은 아니며, 상황에 따라 SecurityContext 공유 전략을 바꿀 수도 있음

1. **MODE_THREADLOCAL(기본값)**
 - ThreadLocalSecurityContextHolderStrategy를 사용
 - ThreadLocal을 사용하여 같은 Thread 안에서 SecurityContext를 공유
2. **MODE_INHERITABLETHREADLOCAL**
 - InheritableThreadLocalSecurityContextHolderStrategy를 사용
 - InheritableThreadLocal을 사용하여 자식 Thread까지도 SecurityContext를 공유
3. **MODE_GLOBAL**
 - GlobalSecurityContextHolderStrategy를 사용
 - Global로 설정되어 애플리케이션 전체에서 SecurityContext를 공유

***보통은 ThreadLocal(기본값) 전략을 사용***

<br>

변수는 지역변수나 전역변수와 같은 scope를 가짐

마찬가지로, ThreadLocal은 Thread마다 고유한 영역을 가지고 있는 곳에 저장된 변수로 각각의 Thread 안에서 유효한 변수임

***따라서, ThreadLocal로 SecurityContext를 관리하게 되면, SecurityContext는 요청마다 독립적으로 관리될 수 있음***
> ThreadLocal은 스프링 프레임워크 뿐만 아니라, 다른 라이브러리와 프레임워크에서 널리 통용되는 것이므로 알아두면 좋음
---

<br>

### ThreadLocalSecurityContextHolderStrategy class

<img src = "https://github.com/bangjaeyoung/TIL/blob/main/img/ThreadLocal.png">
