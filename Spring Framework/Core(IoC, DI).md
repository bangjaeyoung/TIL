# IoC(Inversion of Control), DI(Dependency Injection)

## 스프링에서의 Bean
- 스프링 IoC 컨테이너에 의해 생성되고 관리되는 객체
- 스프링 IoC 컨테이너는 Spring Application Context 기반으로 동작함
- Spring Application Context는 Bean 객체의 생성 및 여러 설정값들을 적용하고 관리해줌

### Bean 등록
> annotation 사용 - **@Bean, @Controller, @Service**

위 annotation이 달린 것들을 스캔하면서 IoC 컨테이너에 Bean으로 등록시킴 - Component Scan

### Bean 이름
원래의 Class 이름의 첫 문자만 소문자로 변경되어서 등록됨 ex) boardController, userService

### Bean 생성 규칙(Scope)
- **singleton** : IoC 컨테이너에 단일로 생성(한 번 new로 인스턴스로 생성해놓고 재활용, 서버가 꺼질 때까지 계속 존재함)
- **prototype** : 작업 시마다 새롭게 Bean을 생성(주로 Spring Batch 때 사용)
- **request** : http 요청마다 새롭게 Bean을 생성

### Bean LifeCycle callback
- **lifecycle callback** : Bean의 생성, 초기화, 파괴 등 특정 시점에 호출되는 메서드
> callback : 특정 이벤트 발생 시, 호출되는 메서드
- **대표적인 callback 메서드**
  - **@PostConstruct** : Bean 생성 시점에 작동할 메서드에 추가 ex) Connection이나 자원의 소모가 큰 작동
  - **@PreDestroy** : Bean 파괴, 어플리케이션 종료 시점에 작동할 메서드에 추가 ex) Connection 끊어줄 때