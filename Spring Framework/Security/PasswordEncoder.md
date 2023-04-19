# PasswordEncoder
password를 관리할 때는 다음 2가지를 만족해야 함

1. 회원가입할 때, password를 입력받으면, 그 값을 암호화해서 저장해야 함
2. 로그인할 때, 입력받은 password와 회원가입할 때의 password를 비교할 수 있어야 함

<br>

***이 2가지를 만족하기 위해서는 보통 해시 함수라는 알고리즘 방식을 이용***
> 해시 함수는 암호화는 비교적 쉽지만 복호화가 거의 불가능한 방식의 알고리즘

<br>

**해시 함수**를 사용하면 다음과 같은 방식으로 password를 관리할 수 있음
1. 회원가입할 때, password를 해시 함수로 암호화해서 저장
2. 로그인할 때, password가 들어오면 같은 해시 함수로 암호화
3. 저장된 값을 불러와서 2번 과정에서의 암호화된 값과 비교
4. 동일하다면 같은 암호로 인지

<br>

### PasswordEncoder Interface
<img src = "https://github.com/bangjaeyoung/TIL/blob/main/img/Spring%20Framework/Security/PasswordEncoder.png">

***위 PasswordEncoder Interface에서 볼 수 있듯이, 암호화하는 메서드만 존재할 뿐 복호화하는 메서드는 존재하지 않음***

<br>

## PasswordEncoder 구현체 종류
### NoOpPasswordEncoder
- 암호화하지 않고 평문으로 사용하는 방식
- password가 그대로 노출되기 때문에, 현재는 deprecated 되었고, 사용하지 않기를 권장하고 있음
### BcryptPasswordEncoder
- Bcrypt 해시 함수를 사용하는 방식
- Bcrypt는 애초부터 패스워드 저장을 목적으로 설계되었음
- password를 무작위로 여러 번 시도하여 맞추는 해킹(브루트 포스 공격)을 방지하기 위해 암호를 확인할 때, 의도적으로 느리게 설정되어 있음
- BcryptPasswordEncoder는 강도를 설정할 수 있는데, 강도가 높을수록 오랜 시간이 걸림(보통 일주일 이상)
### Pbkdf2PasswordEncoder
- Pbkdf2는 NIST(National Institute of Standards and Technology, 미국표준기술연구소)에서 승인된 알고리즘으로, 미국 정부 시스템에서도 사용
### ScryptPasswordEncoder
- Scrypt는 Pbkdf2와 유사
- 해커가 무작위로 password를 맞추려고 시도할 때, 메모리 사용량을 늘리거나 줄여서 느린 공격을 실행할 수밖에 없도록 하는 방식
- 공격이 매우 어렵고 Pbkdf2보다 안전하다고 평가받음
- 보안에 아주 민감한 상황에서 사용하면 좋음

> 이외에도 Argon2PasswordEncoder 등 여러 구현체가 존재하며, 더이상 안전하지 않은 구현체들도 존재함  
> 그런 구현체들이 그럼에도 deprecated되지 않은 이유는 기존 레거시 시스템을 마이그레이션하기 어렵기 때문이라고 함

<br>
