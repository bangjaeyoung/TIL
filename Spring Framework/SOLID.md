# 좋은 객체지향 설계를 위한 5가지 원칙 - SOLID
- **SRP : 단일 책임 원칙** (Single Responsibility Principle)
    - **변경**이 있을 때, 수정 범위가 적다면 단일 책임 원칙을 잘 따른 것


- **OCP : 개방-폐쇄 원칙** (Open/Closed Principle)
    - **다향성**을 활용해 **개방에는 열려**있으나 **변경에는 닫혀**있어야 함
    - 그러나, 코드의 변경이 일절 없이 OCP를 지킬 수는 없음
    - 다형성뿐만이 아니라, **DI**와 **IOC 컨테이너**가 필요함


- **LSP : 리스코프 치환 원칙** (Liskov Substitution Principle)
    - 인터페이스를 구현한 객체는 인터페이스의 기본 특성과 어긋나서는 안됨


- **ISP : 인터페이스 분리 원칙** (Interface Segregation Principle)
    - 여러 핵심 기능들이 혼합된 인터페이스는 좋지 않음
    - 즉, 단일 핵심 기능들을 분리해서 인터페이스를 설계해야 함


- **DIP : 의존관계 역전 원칙** (Dependency Inversion Principle)
    - 구체화에 의존하는 것이 아닌, **추상화**에 의존해야 함
    - 클라이언트 코드는 구현 객체가 아닌 인터페이스에 의존해야 함
    - OCP와 마찬가지로, **DI**와 **IOC 컨테이너**가 필요함