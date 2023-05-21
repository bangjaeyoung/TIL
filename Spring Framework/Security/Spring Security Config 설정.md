# Filter Off
- Spring Security의 특정 Filter를 disable하여 동작하지 않게 한다.
- 사용하지 않을 필터는 명시적으로 disable하는 것이 좋다.

```java
http.xxxx.disable();
// ex) http.httpBasic().disable();
```

</br>

# 로그인 & 로그아웃 페이지 관련

```java
// Login
http.formLogin()
         .loginPage("/login") // 로그인 페이지 url 지정
         .defaultSuccessUrl("/")  // 로그인 성공 시, url 지정
         .permitAll();  // 어떤 사용자든 접근 허용
         
// Logout
http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 페이지 url 지정
        .logoutSuccessUrl("/"); // 로그아웃 성공 시, url 지정
```

</br>

# Url Matchers 관련
- antMatchers

```java
http.authorizeRequests()
        .antMatchers("/signup")
        .permitAll(); // "/signup" 요청을 모두에게 허용
```
- mvcMatchers

```java
http.authorizeRequests()
        .mvcMatchers("/signup")
        .permitAll(); // "/signup"으로 시작하는 모든 요청을 모두에게 허용
                      // ex) "/signup", "/signup.html"
```
- regexMatchers : 정규표현식으로 매칭
- requestMatchers : antMatchers, mvcMatchers, regexMatchers는 결국에 requestMatchers로 이루어져 있다. 주로, 명확하게 요청 대상을 지정하는 경우에 사용

</br>

# 인가 관련
- authorizeRequests() : 인가 설정
    
    ```java
    http.authorizeRequests();
    ```
    
- permitAll() : 모두에게 허용
- hasRole() : 권한 검증
    
    ```java
    http.authorizeRequests()
            .antMatchers(HttpMethod.POST, "/board")
            .hasRole("USER")  // hasRole() 안에 들어가는 인자가 ROLE_ADMIN이 아닌 이유는
                              // 기본적으로 ROLE_을 넣어주기 때문에생략 가능
    ```
    
- authenticated() : 인증되었는지 검증
    
    ```java
    http.authorizeRequests()
            .anyRequest()
            .authenticated()
    ```

</br>

# Ignoring

특정 리소스에 대해 Spring Security 자체를 적용하고 싶지 않을 때, 사용한다.
> 주로, 정적인 리소스에 대해 사용
    
    

**ignoring() vs permitAll()**

- 둘의 결과는 같지만, ignoring()은 아예 Spring Security의 대상에 포함되지도 않고, 어떤 필터도 거치지 않는다.
- css, javascript, images 등 정적인 리소스에 대해 ignoring()을 적용하는 것이 permitAll()보다는 더 성능이 뛰어나다.
