# Spring Resource
> java.net.URL의 한계(classpath 내부 접근 등)을 해결하기 위해 추가적으로 구현된 것 - [Reference](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#resources)

## Resource Interface
```java
public interface Resource extends InputStreamSource {

    boolean exists();

    boolean isReadable();

    boolean isOpen();

    boolean isFile();

    URL getURL() throws IOException;

    URI getURI() throws IOException;

    File getFile() throws IOException;

    ReadableByteChannel readableChannel() throws IOException;

    long contentLength() throws IOException;

    long lastModified() throws IOException;

    Resource createRelative(String relativePath) throws IOException;

    String getFilename();

    String getDescription();
}
```

## Resource interface의 대표적인 구현체들

- **UrlResource**
  - java.net.URL을 래핑한 버전
  - ftp:, file:, http: 등의 Resource에 접근하기 위한 구현체 - 기본적으로 http(s)로 원격 접근
- **ClassPathResource**
  - classpath 하위의 Resource 접근하기 위한 구현체 
- **FileSystemResource**
  - file을 다루기 위한 구현체 
- **ServletContextResource, InputStreamResource, ByteArrayResource**
- Servlet 애플리케이션 루트 하위 파일, InputStream, ByteArrayInput 스트림을 가져오기 위한 구현체

---

## Spring ResourceLoader

- 스프링 프로젝트 내의 Resource(주로 classpath 하위 파일)에 접근할 때 사용하는 기능
- 기본적으로 ApplicationContext에서 구현이 되어 있음
- 대부분의 사전 정의된 파일들은 자동으로 로딩되도록 되어 있지만, 추가로 필요한 파일이 있을 때 이 부분 활용 가능

```java
@Service
public class ResourceService {
	@Autowired
	ApplicationContext ctx;

	public void setResource() {
		Resource myTemplate = 
			ctx.getResource("classpath:some/resource/path/xxx.txt");
			// ctx.getResource("file:/some/resource/path/xxx.txt");
			// ctx.getResource("http://myhost.com/resource/path/xxx.txt");
	}
}
```
---
## ResourcePatternResolver

- ApplicationContext에서 ResourceLoader를 불러올 때 사용하는 interface   
- 위치 지정자 패턴("classpath:", "file:", "http:")에 따라 자동으로 ResourceLoader 구현체를 선택해줌

```java
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory,
        HierarchicalBeanFactory, MessageSource,
        ApplicationEventPublisher, **ResourcePatternResolver** {
    
    // ...
        
}
```
**ApplicationContext interface는 기본적으로 ResourcePatternResolver를 상속**

---

## ApplicationContext & Resource Paths

ApplicationContext(스프링의 핵심설정)을 이루는 설정값을 가져오는 방법들

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/appContext.xml");

ApplicationContext ctx = new FileSystemXmlApplicationContext("conf/appContext.xml");

ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:conf/appContext.xml");


// 설정된 ApplicationContext를 사용하는 예시
// 특정 Bean을 가져와야 하는 상황 
Car car = (Car) ctx.getBean("car");
```