# Spring_FrameWork_Study
Study Spring FrameWork

---
## SpringProject
**스프링 MVC 구동 순서**
스프링 프레임워크는 하나의 기능을 위해서만 만들어진 프레임워크가 아닌 '코어'라고 할 수 있는 여러 서브 프로젝트들을 결합해서 다양한 상황에 대처할 수 있도록 개발하였다. 그중 하나가 MVC 구조이다.

스프링 MVC  
![스프링실행순서](./image/spring.png)
+ [Spring MVC]  -  WEB 관련 영역
+ [Spring Core]  -  일반 JAVA 영역(POJO)
+ [Spring-MyBatis]
+ [DB]

- 프로젝트 구동은 web.xml 에서 시작한다. web.xml 상단에는 가장 먼저 구동되는 Context Listener가 등록되어 있다.  

> 1. ContextLoaderListener는 해당 웹 어플리케이션을 구동하게 되면 같이 작동이 시작되므로 해당 프로젝트를 실행하면 가장 먼저 로그를 출력하면서 실행한다. --> 여기서 root-context를 부름  
> 2. root-context.xml이 처리되면 파일에 있는(설정해 놓은) Bean들이 작동한다.   
> 3. root-context.xml이 처리된 후에는 DispatcherServlet이라는 서블릿과 관련되어 설정이 작동한다. MVC 구조에서 가장 핵심적인 역할을 하는 클래스이며 내부적으로 웹 관련 처리의 준비 작업을 진행한다. 
>    내부적으로 웹 관련 처리의 준비 작업을 진행하기 위해 사용하는 파일이 있고 servlet-context.xml이다.  
> 4. DispatcherServlet에서 XmlWebApplicationContext를 이용해서 servlet-context.xml을 로딩하고 해석한다. 이 과정에서 등록된 객체(Bean)들을 기존에 만들어진 객체(Bean)들과 같이 연동하게 된다.  
 
### pom.xml
Maven 프로젝트에서 사용되는 파일로, 프로젝트의 설정과 의존성 관리를 위해 사용된다. Maven은 Java 프로젝트의 빌드, 패키징, 테스트, 배포 등을 자동화하는 프로젝트 관리 도구이다. pom.xml 파일은 Maven 프로젝트의 루트 디렉토리에 위치하며,  
XML 형식으로 작성된다.  

**pom.xml구조**   
1. 프로젝트에 대한 정보  
groupId : 2번째 레벨까지의 패키지 구조  
artifactId : 3번째 레벨의 패키지명(프로그램명)  
packaging : 배포형식  
```jsp
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.kh</groupId>
	<artifactId>spring</artifactId>
	<name>springProject</name>
	<packaging>war</packaging>
	<version>1.0.0-BUILD-SNAPSHOT</version>
```

2. Properties
properties : 이 문서에서 사용할 환경 설정 내용들을 담은 변수 (태그명이 변수명이 된다)  
${변수명}으로 해당 값을 불러올 수 있음.
```jsp
	<properties>
		<java-version>11</java-version>
		<org.springframework-version>5.2.24.RELEASE</org.springframework-version>
		<org.aspectj-version>1.6.10</org.aspectj-version>
		<org.slf4j-version>1.6.6</org.slf4j-version>
	</properties>
```

3. Repositories
기본 저장소에서 다운받지 못하는 경우 직접 다운받고자 하는 저장소 경로 등록
```jsp
	 <repositories>
	 	<repository>
	 		<id>Datanucleus</id>
	 		<url>http://www.datanucleus.org/downloads/maven2/</url>
	 	</repository>
	 </repositories>
```

4. 의존성 (Dependency)
프로젝트가 사용하는 외부 라이브러리나 모듈의 정보를 정의한다. Maven은 이 정보를 바탕으로 필요한 의존성을 자동으로 다운로드하고 프로젝트에 추가한다.
```jsp
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.3.8</version>
    </dependency>
    <!-- 다른 의존성들 -->
</dependencies>
```

5. 플러그인 (Plugin)
빌드 과정에서 수행되는 작업을 정의하는 플러그인을 설정한다. 예를 들어, 테스트 실행, 패키징, 리소스 복사 등을 위한 플러그인을 설정할 수 있다.  
```jsp
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
        <!-- 다른 플러그인들 -->
    </plugins>
</build>
```

6. 빌드 설정
Maven 빌드의 세부 사항을 설정한다. 빌드 결과물이 생성되는 디렉토리, 리소스 파일의 위치 등을 지정할 수 있다.
```jsp
<build>
    <sourceDirectory>src/main/java</sourceDirectory>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
        </resource>
    </resources>
    <outputDirectory>target/classes</outputDirectory>
    <!-- 더 많은 빌드 설정들 -->
</build>
```
