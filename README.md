# ⚒ Spring Class

### 제어 역전(Inversion of Control, IoC)

- 객체의 생성과 제어(생명주기, 의존성 주입 등)를 개발자가 하지 않고,
  프레임워크나 컨테이너가 대신 담당하는 설계 원칙


- "개발자가 직접 객체를 생성하고 관리하는 것이 아니라, 외부에서 객체를 주입받는다.”


- 전통적인 방식에서는 개발자가 객체를 직접 생성하고 사용하는 흐름을 제어했었음.


- 하지만 IoC에서는 **객체의 제어권이 프레임워크**(예: 스프링 컨테이너)로 넘어감.
  그래서 제어의 흐름이 '개발자 → 프레임워크'로 역전되었기 때문에 "제어 역전"이라 함


- IoC를 구현하는 가장 일반적인 방법은 DI(Dependency Injection)
  - 스프링에서는 **생성자 주입**, 세터 주입, 필드 주입 방법으로 DI 지원
  - 스프링에서는 **@Component, @Autowired, @Configuration, @Bean** 등을 통해 IoC를 구성
  - 스프링이 객체들을 스캔하고 자동으로 주입 → 개발자가 제어하지 않음 → 제어 역전


```java
// MemberService가 MemberRepository를 직접 생성하고 사용
// 이 구조는 결합도가 높고 테스트나 변경이 어려움.

public class MemberService {
    private MemberRepository repository;

    public MemberService() {
        this.repository = new MemberRepository(); // 직접 생성
    }

    public void joinMember(String name) {
        repository.save(name);
    }
}
```

```java
// 객체 생성은 외부(컨테이너)가 담당
// MemberService는 자신이 사용할 MemberRepository를 주입 받음
// 결합도 낮고 테스트 용이

public class MemberService {
    private final MemberRepository repository;

    // 생성자 주입 (의존성 주입 - DI)
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public void joinMember(String name) {
        repository.save(name);
    }
}
```

```java
@Component
public class MemoryMemberRepository implements MemberRepository {
    @Override
    public void save(String name) {
        System.out.println("회원 저장: " + name);
    }
}

@Service
public class MemberService {
    private final MemberRepository repository;

    @Autowired
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }
}
```

**제어 역전의 장점**

| **장점** | **설명** |
| --- | --- |
| 낮은 결합도 | 객체 간 의존성이 줄어들어 유지보수 용이 |
| 테스트 용이 | 테스트 시 Mock 객체를 쉽게 주입 가능 |
| 재사용성 향상 | 다양한 구현체를 쉽게 교체 가능 |
| 유연한 구조 | OCP 원칙(개방-폐쇄 원칙)을 지키기 쉬움 |

### AOP

- 관점 지향 프로그래밍
- 공통 관심 사항을 핵심 비즈니스 로직과 분리하여 모듈화하는 프로그래밍 패러다임. 대표적으로
  **로깅**, **보안**, **트랜잭션**, **성능 측정**등의 기능이 이에 해당
- 핵심 로직과는 **다른 부가적인 로직**을 관점으로 분리하여 코드 중복을 줄이고 유지보수를 쉽게 함


### 스프링 프레임워크 모듈

- 자바 기반의 엔터프라이즈 애플리케이션을 개발하기 위한 경량 애플리케이션 프레임워크
- 스프링은 모듈화된 구조를 갖고 있으며, 각 모듈은 특정 기능을 담당. 필요한 모듈만 선택해서
  사용할 수 있으며, 유연하고 확장성이 뛰어남

**Core Container (핵심 컨테이너)**

| 모듈 | 설명 |
| --- | --- |
| **Core** | IoC(Inversion of Control), DI(Dependency Injection) 기능의 기반을 제공 |
| **Beans** | 스프링 빈 설정, 생성, 주입, 생명주기 관리 |
| **Context** | BeanFactory를 확장한 ApplicationContext 제공 (국제화, 이벤트 전달 등 지원) |
| **SpEL (Spring Expression Language)** | 런타임에 객체 그래프를 조회하거나 조작할 수 있는 표현식 언어 |

**Data Access / Integration** : 데이터 접근과 관련된 모듈

**Web (Spring Web) :** DispatcherServlet 중심의 요청 처리, RESTful API, Thymeleaf, JSP 등 다양한 뷰 기술과 연동 가능

### Spring Boot Starter

- Starter는 Spring Boot에서 제공하는 기능별 의존성 패키지 모음
- 즉, 어떤 기능을 사용하고 싶을 때 관련된 라이브러리들을 일일이 설정할 필요 없이,
  하나의 starter만 추가하면 자동으로 필요한 라이브러리가 함께 추가됨

| **Starter 이름** | **설명** |
| --- | --- |
| `spring-boot-starter` | 기본적인 의존성 (로깅, 코어 등) |
| `spring-boot-starter-web` | 웹 애플리케이션 (Spring MVC + 내장 Tomcat) |
| `spring-boot-starter-data-jpa` | JPA, Hibernate 기반 ORM |
| `spring-boot-starter-security` | Spring Security 기반 인증/인가 |
| `spring-boot-starter-thymeleaf` | Thymeleaf 템플릿 엔진 |
| `spring-boot-starter-test` | JUnit, Mockito 등 테스트 도구 모음 |
| `spring-boot-starter-aop` | AOP 관련 기능 (AspectJ 등) |
| `spring-boot-starter-actuator` | 애플리케이션 모니터링 및 헬스 체크 |
| `spring-boot-starter-validation` | Bean Validation (JSR 380) 지원 |

### 빈약한 도메인 모델 (Anemic Domain Model)

- **데이터베이스 중심 설계 경향**
  - 많은 개발자들이 DB 테이블을 먼저 설계한 다음,
  - 그 구조에 맞춰 Entity 클래스를 만들고,
  - 그 Entity를 중심으로 로직을 작성하다 보니...
  - 비즈니스 로직이 점점 DB 구조에 종속되고,
  - 도메인 모델(Entity) 안에는 데이터만 있고, 행동(로직)은 없음

- **핵심 도메인 객체인데도, 자기 일을 못하고 그냥 값만 들고 있는 상태**
  - **Order** 는 그저 데이터를 담는 **DTO 같은 존재** 일 뿐


```java
// Entity (데이터만 있음)
public class Order {
    private Long id;
    private int quantity;
    private int price;

    // getter/setter만 있음
}
```

```java
// Service에서 모든 로직 처리
public class OrderService {
    public void placeOrder(Order order) {
        // 수량 계산, 가격 계산 등 모든 로직이 여기 있음
    }
}
```

- **이 문제를 해결하려는 아키텍처는?**
  - → **도메인 주도 설계 (DDD: Domain-Driven Design)**
  - DDD는 도메인 모델에 로직을 집중시켜 풍부한 도메인 모델을 만들도록 유도

- **풍부한 도메인 모델 (Rich Domain Model)**

```java
// Order 객체가 스스로 자신의 역할(자기 일) 수행
public class Order {
	private int quantity;
	private int price;
	
	public int calculateTotalPrice() {
	    return quantity * price;
	}
	
	public void cancel() {
	    // 주문 취소 로직
	}
}
```

## 🧱 Layered Architecture

> 소프트웨어 시스템을 **논리적인 계층(Layer)** 으로 분리하여 구성하는 가장 일반적이고 전통적인 아키텍처 패턴 중 하나

---

- 🔹 **각 계층은 특정 관심사(concern)에 집중**하며,  
  응집도를 높이고 결합도를 낮추는 것을 목표로 함


- 🔹 **각 계층이 서로 독립적으로 구성**되어 있어서  
  한 계층의 변경이 다른 계층에 영향을 주지 않게 설계


- 🔹 **외부의 요구사항이나 세부적인 구현이 변화하더라도**  
  도메인의 로직을 변경하지 않도록 보호하기 위해 계층화를 하게 됨


- 🔹 **관심사의 분리 (Separation of Concerns)**:  
  각 계층은 **특정 역할과 책임**만 수행


- 🔹 **유지보수성 향상**:  
  특정 계층의 수정이 다른 계층에 미치는 영향을 **최소화**


- 🔹 **재사용성 증가**:  
  특정 계층의 컴포넌트를 **다른 시스템에서도 재사용**하기 용이


- 🔹 **테스트 용이성**:  
  각 계층을 **독립적으로 테스트**하기 쉬움


## 🖥️ 프레젠테이션 계층 (Presentation Layer / UI Layer)

- 🔹 **사용자 인터페이스(UI)** 를 담당하고, 사용자와 시스템 간의 **상호작용** 처리


- 🔹 **사용자로부터 입력을 받아 애플리케이션 계층으로 전달**하고,  
  애플리케이션 계층의 처리 결과를 사용자에게 **표시**


- 🔹 사용 예시:
    - 웹 브라우저 (HTML, CSS, JavaScript)
    - 모바일 앱 UI
    - 데스크톱 애플리케이션 UI
    - REST API 엔드포인트

## ⚙️ 애플리케이션 계층 (Application Layer / Business Logic Layer / Service Layer)

- 🔹 시스템의 **핵심 비즈니스 로직**을 구현하고,  
  프레젠테이션 계층과 데이터 접근 계층 사이의 **흐름을 제어**


- 🔹 프레젠테이션 계층으로부터 요청을 받아 **비즈니스 규칙을 적용**하고,  
  필요한 경우 데이터 접근 계층을 통해 데이터를 조작하며  
  그 결과를 다시 **프레젠테이션 계층으로 반환**


- 🔹 **트랜잭션 관리**, **도메인 서비스 조합** 등의 작업이 이루어짐


- 🔹 구성 예시:
    - `@Service` 어노테이션이 붙은 클래스들
    - 유스케이스(Use Cases)를 구현하는 클래스

## 🧩 도메인 계층 (Domain Layer / Model Layer) - *선택적이지만 중요*

- 🔹 **비즈니스 도메인의 핵심 개념, 상태, 규칙**을 표현하는 객체  
  (예: 엔티티, 값 객체 등)를 포함


- 🔹 애플리케이션의 **핵심 비즈니스 로직과 데이터 구조 정의**


- 🔹 이 계층은 **다른 계층에 의존하지 않아야 이상적**  
  (특히 **인프라스트럭처 계층**에 의존하지 않아야 함)


- 🔹 구성 요소 예시:
    - `@Entity` 클래스
    - 값 객체 (Value Objects)
    - 도메인 서비스
    - 도메인 이벤트

## 🗄️ 데이터 접근 계층 (Data Access Layer / Persistence Layer / Infrastructure Layer 일부)

- 🔹 **데이터베이스, 파일 시스템 등 영구 저장소와의 데이터 CRUD**  
  (생성, 읽기, 갱신, 삭제) 작업 담당


- 🔹 **애플리케이션 계층의 요청에 따라 데이터를 저장하고 조회**


- 🔹 **데이터베이스 연결, SQL 실행, ORM(Object-Relational Mapping)** 등을 처리


- 🔹 구성 요소 예시:
    - DAO (Data Access Object)
    - Repository 패턴 구현체 (`@Repository` 어노테이션 사용)
    - JPA, MyBatis

## 📊 N-Tier Architecture의 장단점

### ✅ 장점

- 🔹 **단순성**:  
  구조가 비교적 단순하고 **이해하기 쉬움**


- 🔹 **개발 용이성**:  
  각 계층별로 **개발을 분담**하기 좋음


- 🔹 **표준화**:  
  널리 사용되는 **패턴**이라 많은 개발자가 **익숙함**

---

### ⚠️ 단점

- 🔺 **데이터베이스 중심 설계 경향**:  
  비즈니스 로직이 **데이터 구조에 종속되기 쉬움**,  
  **도메인 모델은 빈약**해질 수 있음


- 🔺 **계층 간 결합도 증가 가능성**:  
  엄격한 규칙을 지키지 않으면 **결합도가 높아지고**,  
  변경에 **취약**


- 🔺 **불필요한 계층**:  
  간단한 애플리케이션의 경우,  
  **과도한 계층 분리가 오히려 복잡성을 증가**시킬 수 있음


- 🔺 **비즈니스 로직 분산**:  
  비즈니스 로직이 **서비스 계층에만 집중**되어  
  **도메인 객체의 역할이 축소**될 수 있음

## 🧱 계층 기반 패키지 구조 (Layer-Based Package Structure / Package by Layer)

- 🔹 **아키텍처 계층(Layer)을 기준**으로 패키지를 구성하는 방식


- 🔹 일반적으로 웹 애플리케이션에서 다음과 같은 계층별로 구성:
    - **프레젠테이션** (Controller)
    - **서비스** (Service)
    - **도메인** (Domain / Model)
    - **데이터 접근** (Repository / DAO)
    - **유틸리티** (Util)


- 🔹 각 계층별로 **최상위 패키지를 만들고**,  
  그 하위에 **관련된 클래스들을 배치**


- 🔹 핵심 기준: **기술적 관심사(Technical Concern)** 에 따라 코드 그룹화

## ✅ 계층 기반 패키지 구조의 장점

- 🔹 **단순하고 직관적**:  
  아키텍처 계층에 대한 이해가 있다면 **구조 파악이 쉬움**


- 🔹 **기술적 역할 명확**:  
  각 패키지가 **어떤 기술적 역할**을 하는지 **명확하게 드러남**


- 🔹 **일관성 유지 용이**:  
  새로운 기능을 추가할 때 **어떤 패키지에 위치시켜야 할지**  
  비교적 **명확한 가이드라인 제공**


- 🔹 **작은 프로젝트에 적합**:  
  프로젝트 규모가 작거나 기능이 단순한 경우 **관리하기 용이**

## ⚠️ 계층 기반 패키지 구조의 단점

- 🔺 **낮은 응집도**:  
  하나의 기능을 구현하기 위해 **여러 패키지를 넘나들며 파일을 찾아야 함**


- 🔺 **높은 결합도 가능성**:  
  특정 계층의 변경이 **다른 계층에 영향을 줄 가능성**이 큼


- 🔺 **탐색의 어려움**:  
  프로젝트가 커질수록 각 계층 패키지에 **파일이 무한정 쌓이며**  
  _“패키지 스크롤 지옥”_ 발생


- 🔺 **모듈화의 어려움**:  
  특정 기능을 **독립적인 모듈로 분리하거나 재사용**하기 어려움


- 🔺 **컨텍스트 스위칭 비용 증가**:  
  하나의 기능을 작업할 때 **여러 패키지를 오가며 작업**


- 🔺 **캡슐화 부족**:  
  특정 도메인/기능과 관련된 클래스들이 **여러 패키지에 흩어져 있어**  
  캡슐화가 **부실**

## 🧩 기능 기반 패키지 구조 (Feature-Based Package Structure / Package by Feature)

- 🔹 애플리케이션의 **기능(Feature)** 또는 **도메인(Domain)** 을 기준으로  
  최상위 패키지를 구성하는 방식


- 🔹 각 기능 패키지 내부에 해당 기능과 관련된  
  **컨트롤러, 서비스, 도메인 객체, 리포지토리** 등을 **모두 포함**


- 🔹 **"비즈니스 관심사"** 또는 **"기능적 관심사"** 를 기준으로 코드를 그룹화

## ✅ 기능 기반 패키지 구조의 장점

- 🔹 **높은 응집도**:  
  특정 기능과 관련된 **모든 코드가 한 패키지**에 모여 있어  
  해당 기능을 **이해하고 수정하기 용이**


- 🔹 **낮은 결합도**:  
  특정 기능의 변경이 **다른 기능에 영향을 거의 미치지 않음**


- 🔹 **탐색의 용이성**:  
  특정 기능을 작업할 때 **해당 패키지 안에서만 작업**하면 되어  
  **파일 찾기 쉬움**


- 🔹 **모듈화 용이**:  
  각 기능 패키지를 **독립적인 모듈**로 간주 가능 →  
  마이크로서비스화에도 **유리한 구조**


- 🔹 **캡슐화 강화**:  
  기능과 관련된 모든 요소가 **하나의 패키지에 캡슐화**되어  
  외부 접근을 최소화


- 🔹 **코드 이해도 향상**:  
  새로운 개발자가 프로젝트에 참여했을 때  
  **특정 기능의 코드 흐름을 빠르게 이해 가능**


- 🔹 **컨텍스트 스위칭 비용 감소**:  
  하나의 기능을 작업할 때 **동일한 패키지 내에서 대부분 작업이 완료됨**

## ⚠️ 기능 기반 패키지 구조의 단점

- 🔺 **초기 설계의 중요성**:  
  기능을 어떻게 정의하고 분리할지에 대한 **초기 설계가 매우 중요**


- 🔺 **기능 간 공통 코드 처리**:  
  여러 기능에서 **공통으로 사용하는 유틸리티/기반 클래스**들을  
  **어떻게 처리할지 고민 필요**


- 🔺 **작은 프로젝트에서는 과도할 수 있음**:  
  기능이 매우 적거나 단순한 프로젝트에서는  
  오히려 **구조가 복잡해질 수 있음**

## 🧩 계층 기반 패키지 vs 기능 기반 패키지 비교

| 비교 항목                        | 계층 기반 패키지 구조가 적합한 경우                            | 기능 기반 패키지 구조가 적합한 경우                                 |
|----------------------------------|-------------------------------------------------------------------|----------------------------------------------------------------------|
| 📦 프로젝트 규모                | 작고 단순한 프로젝트                                              | 크고 복잡하며 많은 기능 포함                                         |
| 👥 팀의 익숙함                 | 팀원들이 계층 구조에 익숙하며 학습 비용을 줄이고 싶을 때         | 도메인/비즈니스 중심으로 구조화가 필요한 팀 또는 마이크로서비스 지향 |
| 🏃‍♂️ 개발 속도                 | 빠르게 프로토타입을 개발해야 하는 경우                           | 장기적인 유지보수 및 확장성이 중요한 프로젝트                       |
| 🔧 코드 품질 목표              | 명확하지 않음                                                    | 높은 응집도 + 낮은 결합도를 통한 코드 품질 향상 목표               |
| 🧩 모듈화/마이크로서비스 적용 | 고려하지 않음                                                    | 모듈 단위 개발 혹은 마이크로서비스 지향일 때 유리                   |
| 📐 아키텍처 관점               | 기술적 관심사(Controller, Service 등) 기준                       | 도메인 또는 기능 중심 관심사 기준                                   |
| 🧠 DDD 적용 여부              | 적용하기 어려움                                                  | 도메인 주도 설계(DDD) 적용 시 자연스럽게 기능/도메인별 구성 가능     |

---

## 🎨 디자인 패턴 (Design Pattern)

- 🔹 **소프트웨어 개발에서 자주 등장하는 문제들을 해결하기 위한 재사용 가능한 해결책**


- 🔹 마치 건축에서 반복적으로 사용되는 설계 방식이 있듯이,  
  **소프트웨어에서도 반복되는 설계 문제를 효율적으로 해결하기 위한 표준화된 방식**


- 🔹 소프트웨어 설계에서 자주 발생하는 **특정 문제에 대한 일반적이고 재사용 가능한 해결책**


- 🔹 **특정 언어나 코드에 종속되지 않고**,  
  **설계 구조와 관계**에 대한 설명으로 제공됨


- 🔹 코드의 **유지보수성**, **확장성**, **재사용성**을 높이고,  
  **개발자 간 의사소통을 원활하게 함**


## 💡 디자인 패턴 사용 이유

- 🔹 **효율적인 문제 해결**:  
  이미 **검증된 해결책**을 사용함으로써  
  문제를 더 빠르고 안정적으로 해결할 수 있음


- 🔹 **코드 재사용성 향상**:  
  반복되는 설계 문제를 **모듈화**하여  
  다양한 곳에서 **재사용 가능**


- 🔹 **유지보수 용이성**:  
  **구조화된 설계** 덕분에 코드의 변경이나 확장이 쉬움


- 🔹 **개발자 간 소통 향상**:  
  예: “싱글턴 패턴을 쓰자”와 같은 말로  
  **설계 의도를 설명하지 않아도 이해**할 수 있음


## 🌱 스프링에서의 대표적 디자인 패턴

- 🔹 **의존성 주입 (Dependency Injection, DI)**
  - 제어의 역전 (Inversion of Control, IoC) 기반
  - 객체 생성과 의존성 관리를 스프링 컨테이너가 담당


- 🔹 **MVC 패턴 (Model-View-Controller)**
  - 아키텍처 패턴
  - 사용자 인터페이스, 처리 로직, 데이터 분리를 통해 유지보수 용이


- 🔹 **레포지토리 패턴 (Repository Pattern)**
  - 아키텍처 패턴
  - 도메인 계층과 데이터 접근 계층 분리


- 🔹 **싱글톤 패턴 (Singleton Pattern)**
  - 스프링 빈은 기본적으로 싱글톤으로 관리됨


- 🔹 **팩토리 메서드 패턴 (Factory Method Pattern)**
  - 객체 생성 로직을 서브클래스에 위임


- 🔹 **추상 팩토리 패턴 (Abstract Factory Pattern)**
  - 관련 있는 객체를 묶어서 생성


- 🔹 **프록시 패턴 (Proxy Pattern)**
  - AOP, 트랜잭션 처리, Lazy Loading 등에 활용


- 🔹 **템플릿 메서드 패턴 (Template Method Pattern)**
  - 공통 로직은 상위 클래스에, 세부 로직은 하위 클래스에 위임


- 🔹 **스트래티지 패턴 (Strategy Pattern)**
  - 런타임 시점에 알고리즘/행동을 선택적으로 교체 가능


- 🔹 **빌더 패턴 (Builder Pattern)**
  - 복잡한 객체 생성을 유연하게 처리 (ex. `Lombok @Builder`)

---

## 🌐 REST (Representational State Transfer)

- 🔹 **웹 서비스 개발에서 가장 널리 사용되는 아키텍처 스타일 중 하나**


- 🔹 REST는 **REpresentational State Transfer**의 약자로,  
  **웹의 기존 기술과 HTTP 프로토콜을 기반으로 자원을 처리하는 아키텍처 스타일**


- 🔹 REST는 2000년, **로이 필딩 (Roy Fielding)** 박사의 **박사학위 논문**에서 처음 소개됨
  > (HTTP/1.1, URI, HTML 등의 웹 표준 공동 설계자)


- 🔹 REST를 따르는 API를 **RESTful API** 라고 부름


## 📌 REST 등장 배경

- 🔹 **비표준적인 API 설계**
  - 각 시스템이 제멋대로 API를 만들고 사용 → **일관성 부족**


- 🔹 **상태 유지(Session)**
  - 많은 시스템이 **클라이언트 상태를 서버에 저장**
  - → 시스템 규모가 커질수록 **복잡도 증가**


- 🔹 **확장성과 유지보수 문제**
  - 기능이 추가될수록 **시스템의 복잡도가 증가**


- 🔹 **HTTP 기능의 미활용**
  - 단순히 **데이터 전송 수단**으로만 사용 (ex. GET, POST만 사용)
  - HTTP의 다양한 기능 활용 부족


- 🔹 **분산 시스템 설계 원칙 부재**
  - 웹 기반 분산 시스템을 설계할 **명확한 기준이 없었음**


## ✅ REST 필수 조건 (REST 아키텍처 제약조건)

- 🔹 **클라이언트-서버 구조**
  - 클라이언트와 서버의 역할을 분리
  - UI와 데이터 처리를 나누어 **관심사 분리**를 구현


- 🔹 **무상태성 (Stateless)**
  - 각 요청은 **독립적**이며,
  - 서버는 **클라이언트의 상태를 저장하지 않음**


- 🔹 **캐시 처리 가능**
  - HTTP의 **캐시 기능**을 활용하여
  - 응답 성능을 **향상**시킬 수 있음


- 🔹 **계층화 구조 (Layered System)**
  - 클라이언트는 **중간 서버(프록시, 로드 밸런서 등)** 가 있는지 알지 못함
  - 보안, 로드 밸런싱 등에서 유리


- 🔹 **인터페이스 일관성 (Uniform Interface)**
  - 자원의 URI, HTTP 메서드 등 **일관된 인터페이스** 사용 필요


- 🔹 **코드 온 디맨드 (Code on Demand)** *(선택)*
  - 클라이언트가 **서버로부터 코드(JavaScript 등)** 를 받아 실행 가능


## 🔮 REST의 미래

- 🔹 **웹의 성장과 함께 REST는 자연스럽게 많은 개발자에게 채택됨**


- 🔹 **SOAP, RPC** 등 무거운 프로토콜에 비해  
  REST는 **간단하고 직관적**


- 🔹 **JSON, HTTP와의 궁합이 뛰어나**  
  모바일·웹 API의 **사실상 표준으로 자리 잡음**


- 🔹 **Google, Amazon, Twitter, GitHub** 등  
  대형 플랫폼들이 **REST API를 공식적으로 제공**


## 🛠️ REST URI 설계 규칙

- 🔹 **URI(Uniform Resource Identifier)** 는  
  REST 아키텍처에서 **리소스를 고유하게 식별**하는 핵심 요소


- 🔹 잘 설계된 URI는 **API의 가독성, 사용성, 유지보수성**을 크게 향상시킴

---

### 🎯 설계 원칙

- **자원 중심**
  - URI는 ‘무엇(What)’을 식별해야 함
  - ‘어떻게(How)’는 HTTP 메서드로 표현
  - ❌ `/getUser` → ✅ `/users/{id}`


- **행위는 HTTP 메서드로 구분**
  - GET, POST, PUT, PATCH, DELETE 등


- **직관성 및 예측 가능성**
  - 사용자가 **쉽게 이해하고 예측할 수 있는 URI**로 설계


- **일관성**
  - API 전체에 **일관된 명명 규칙**과 **구조**를 적용


- **계층 구조 표현**
  - 리소스 간의 관계는 `/` 슬래시를 이용한 **계층 구조**로 표현
  - 예: `/users/{userId}/orders/{orderId}`


## 📌 REST URI 설계 규칙 - 명사 사용 (Use Nouns, Not Verbs)


- 🔹 **URI는 자원을 식별하기 위한 것**이므로,  
  **행위(동사)** 가 아닌 **대상(명사)** 를 사용해야 함


- 🔹 **동작(행위)은 HTTP 메서드** (GET, POST, PUT, DELETE 등)로 표현

---

### ✅ 예시

| URI 경로         | 설명                        |
|------------------|-----------------------------|
| `/users`         | 모든 사용자 목록 조회       |
| `/users/123`     | ID가 123인 사용자 조회      |
| `/products`      | 상품 목록 조회              |
| `/orders`        | 주문 목록 조회              |

> ❌ 잘못된 예: `/getUsers`, `/createOrder`  
> ✅ 올바른 예: `/users`, `/orders`

## 📌 REST URI 설계 규칙 - 복수 명사 사용 (Use Plural Nouns for Collections)

- 🔹 **특정 타입의 자원들(모음, 컬렉션)을 나타낼 때는 복수 명사를 사용하는 것이 일반적**

- 🔹 이는 해당 URI가 **여러 개의 자원(리소스)을 포함할 수 있음**을 명확하게 표현함

---

### ✅ 예시

| URI 경로                    | 설명                               |
|-----------------------------|------------------------------------|
| `/users`                    | 여러 사용자 (Users)                |
| `/products`                 | 여러 상품 (Products)               |
| `/users/123/orders`         | ID가 123인 사용자의 여러 주문      |

> 📌 단수 명사 사용보다 **복수 형태 사용**이 RESTful한 표현으로 권장됨


## 📌 REST URI 설계 규칙 - 계층 구조 표현 (Use Slashes for Hierarchy)

- 🔹 자원 간의 **포함 관계, 종속 관계**를 나타낼 때는  
  **슬래시(/)** 를 사용하여 **계층적으로 표현**

---

### ✅ 예시

| URI 경로                                                       | 설명                               |
|----------------------------------------------------------------|------------------------------------|
| `/users/{userId}/orders`                                       | 특정 사용자의 모든 주문            |
| `/products/{productId}/reviews`                                | 특정 상품의 모든 리뷰              |
| `/products/{productId}/reviews/{reviewId}`                     | 특정 상품의 특정 리뷰              |

> 📌 쿼리스트링은 필터링 등에 사용하고, 자원 식별 및 계층 표현은 URI 경로에 포함시키는 것이 RESTful한 설계입니다.


## 📌 REST URI 설계 규칙 - 하이픈(-) 사용 (Use Hyphens for Word Separation)

- 🔹 URI의 **가독성을 높이기 위해**, 여러 단어로 이루어진 경로는 **하이픈(-)** 으로 구분

- 🔹 **밑줄(_)은 가독성이 떨어지거나**, 일부 환경에서 잘못 해석될 수 있으므로 사용을 **지양**

---

### ✅ 예시

| URI 경로                   | 설명                         |
|----------------------------|------------------------------|
| `/product-categories`      | 상품 카테고리 컬렉션         |
| `/user-settings`           | 사용자 설정 리소스           |

> 📌 하이픈은 웹 브라우저, 크롤러, 검색 엔진에서도 자연스럽게 처리되는 구분자로 REST URI에서 널리 사용됩니다.


## 📌 REST URI 설계 규칙 - 쿼리 파라미터 활용
### (Use Query Parameters for Filtering, Sorting, Pagination)


- 🔹 컬렉션 자원에 대한 **필터링, 정렬, 페이징, 검색** 등의 부가적인 조건은  
  쿼리 파라미터 `(?key=value&key2=value2)`를 사용하여 표현



- 🔹 **URI 경로 자체를 변경하지 않고**, 자원의 표현을 조작하는 방식 제공

---

### ✅ 예시

| 목적     | URI 예시 |
|----------|----------|
| 필터링   | `/users?status=active&department=sales` |
| 정렬     | `/products?sortBy=price&order=desc` |
| 페이징   | `/articles?page=2&size=10` |
| 검색     | `/books?query=rest-api-design` |

> 📌 **쿼리 파라미터는 리소스 자체가 아닌 리소스의 표현을 조작할 때** 사용하는 것이 REST의 철학에 부합합니다.

## 📌 REST URI 설계 규칙 - 행위(동사) 대신 HTTP 메서드 사용
### (Use HTTP Methods for Actions)

REST에서는 자원에 대한 **CRUD(Create, Read, Update, Delete)** 작업 및 기타 행위를  
**URI에 동사를 쓰지 않고, HTTP 메서드로 명확히 표현**해야 함

---

### ✅ 메서드별 사용 예시

| 메서드 | 의미         | 예시 URI              |
|--------|--------------|------------------------|
| POST   | 자원 생성    | `POST /users`          |
| GET    | 자원 조회    | `GET /users`, `GET /users/123` |
| PUT    | 자원 전체 수정 또는 생성 (멱등성) | `PUT /users/123` |
| PATCH  | 자원 부분 수정 | `PATCH /users/123`     |
| DELETE | 자원 삭제    | `DELETE /users/123`    |

---

### 🔁 규칙 1 반복 강조
> URI는 자원을 식별하고,  
> CRUD 및 기타 행위는 HTTP 메서드로 표현한다.


## 📌 REST URI 설계 규칙 - API 버전 관리 (API Versioning)

### 📍 API 버전이 필요한 경우
- API가 **변경**되거나 **새로운 기능이 추가**될 경우에도
- 기존 클라이언트와의 **호환성을 유지**하기 위해 버전 관리가 필요함

---

### ✅ 일반적인 방법: URI에 버전 포함
- 가장 흔히 쓰이는 방식
- 버전 정보가 URI 경로에 직접 포함됨

```http
/v1/users
/v2/products
```

>⚠️ URI에 버전을 포함하면 버전 간 명확한 구분이 가능해 유지보수에 용이함


## 🔧 빌드 관리 도구 (Build Tool)

### 📌 정의
- 소프트웨어 개발 과정에서 **반복적이고 중요한 작업들을 자동화**하는 도구

---

### 🛠️ 주요 기능

- **의존성 관리**  
  외부 라이브러리(JAR 등)를 자동으로 다운로드 및 관리


- **소스 코드 컴파일**  
  작성된 소스 코드를 JVM에서 실행 가능한 **바이트 코드**로 변환


- **테스트 실행**  
  단위 테스트, 통합 테스트 등을 자동으로 실행하고 **결과 보고**


- **패키징**  
  컴파일된 코드와 리소스, 의존성들을 묶어 JAR, WAR 등 **배포 가능한 형태**로 생성


- **배포 (_선택적_)**  
  특정 환경으로의 **애플리케이션 배포 지원**


- **문서 생성 및 보고**  
  프로젝트 관련 문서나 테스트 결과 보고서 **자동 생성**


- **일관된 빌드 환경 제공**  
  팀원 모두 동일한 방식으로 프로젝트를 빌드할 수 있도록 **환경 표준화**

## ☕ 메이븐 (Maven)

### 📌 개요
- **Apache 소프트웨어 재단**에서 개발한 **Java 기반 프로젝트의 라이프사이클 관리용 빌드 도구**
- `settings.xml`, `pom.xml`에 라이브러리만 정의하면 Maven이 자동으로 **다운로드 및 설치** 수행

---

### ✅ 장점
- **방대한 문서 및 커뮤니티** 덕분에 정보 접근 용이
- **CoC(Convention over Configuration)** 철학 적용 → 단순한 프로젝트는 설정이 매우 쉬움
- 주요 IDE(IntelliJ, Eclipse 등)와 **높은 호환성**

---

### ⚠️ 단점
- XML 기반 설정은 **가독성 저하 및 장황함**
- 복잡한 빌드 로직 구현이 어려워 **유연성 부족**
- **빌드 속도가 느린 편** (Gradle에 비해)

---

### ✍️ 예시 파일
```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
</dependencies>
```

## 🐘 그레이들 (Gradle)

### 📌 개요
- **Groovy 기반** 빌드 도구이며 **Android Studio의 공식 빌드 도구**
- Maven의 CoC(Convention over Configuration) 철학을 계승하면서도 **유연성과 성능**에 중점
- `build.gradle` 파일에서 사용할 **라이브러리 직접 지정 가능**

---

### ✅ 장점
- **빌드 스크립트 간결**, 높은 가독성 (Groovy/Kotlin DSL 기반)
- **복잡한 빌드 로직 구현이 쉬움**
- **점진적 빌드 및 캐시** 기능으로 **빌드 속도 매우 빠름**
- **멀티 프로젝트 관리에 용이**

---

### ⚠️ 단점
- **학습 곡선이 높음** (특히 Groovy/Kotlin DSL 미숙한 경우)
- 메이븐보다 **초기 진입장벽 존재**

---

### ✍️ 예시 (build.gradle.kts)

```kotlin
dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
}
```


## 🌐 스프링 부트 API 설계하기

### 🔹 API란?
- **API (Application Programming Interface)**:
  - 애플리케이션 간의 **상호작용을 위한 규약**
  - 내부 구현을 몰라도 **요청만 하면 결과를 받을 수 있게 해주는 창구/인터페이스**

---

### ✅ API가 제공해야 할 정보

1. **요청 방법 (어떻게 요청할지)**
  - 어떤 함수 또는 **엔드포인트(Endpoint)** 를 호출할지 명확히 지정

2. **요청 데이터 (무엇을 요청할지)**
  - 어떤 정보를 서버에 보낼 것인지 정의 (예: JSON, Query Params 등)

3. **응답 포맷 (무엇을 받을지)**
  - 어떤 결과를 어떤 **형식**(ex: JSON, XML)으로 받을지를 정의

---

### 🧠 예시
```http
POST /api/users
Content-Type: application/json

{
  "name": "홍길동",
  "email": "gildong@example.com"
}
```
응답

```json
{
"id": 1,
"name": "홍길동",
"email": "gildong@example.com"
}
```

## 🛠️ 스프링 부트 API 설계하기 (REST 기반 설계)

### 🔹 REST API 개요
- 웹 환경에서는 **HTTP 프로토콜 기반의 REST API**가 가장 널리 사용됨


- REST API는 다음과 같은 **HTTP 메서드**로 동작:
  - `GET`, `POST`, `PUT`, `PATCH`, `DELETE`


- 이 메서드들을 통해 **리소스(자원)** 에 대한 **행위(Action)** 를 정의함

---

### 🧩 REST API 설계 핵심 원칙
- **자원을 올바르게 URI로 표현**  
  → `명사 중심`, `계층 구조`, `컬렉션 명 복수형` 등 REST URI 설계 규칙 준수


- **HTTP 메서드를 의미에 맞게 사용**  
  → CRUD에 맞는 메서드를 정확히 매핑


- **상태 코드와 응답 형식을 표준적으로 사용**
  - 상태 코드: `200`, `201`, `400`, `404`, `500` 등
  - 응답 형식: 대부분의 경우 `JSON` 사용

---

### ✅ 좋은 REST API 설계 예시

```http
GET /users/123
→ 사용자 123번의 정보를 조회

POST /products
→ 상품을 새로 등록

PATCH /orders/456
→ 주문 456번의 일부 정보를 수정

DELETE /reviews/789
→ 리뷰 789번 삭제
```

## 📡 GET API 만들기

### ✅ GET 요청이란?
- **주로 리소스를 조회**할 때 사용됨 (서버에서 데이터 가져오기)
- 예시:
  - 사용자 목록 조회
  - 특정 사용자 상세 정보 조회

---

### 🔧 주요 어노테이션

#### 1. `@RestController`
- 해당 클래스가 **REST 컨트롤러**임을 명시
- `@Controller` + `@ResponseBody` 조합과 동일
- 메서드 반환 값은 HTTP 응답 본문(Body)에 직접 작성되어 전달됨 (주로 JSON)

#### 2. `@GetMapping`
- `@RequestMapping(method = RequestMethod.GET)`의 **축약 어노테이션**
- 특정 **GET 요청 URL 경로**와 매핑되는 메서드를 선언

---

### 📤 응답 처리
- **조회 성공**: `200 OK` 상태 코드와 함께 사용자 정보 반환
- **조회 실패**: `404 Not Found` 상태 코드 반환

---

### 🌐 예시 URL

```html
GET /users → 사용자 목록
GET /users/123 → ID가 123인 사용자 상세정보
```

## 📮 POST API 만들기

POST API는 **서버에 새로운 리소스를 생성할 때** 사용됩니다.  
>예: ➕ 새로운 사용자 등록, ✏️ 새 게시글 작성 등

---

### ✅ `@PostMapping`

- 📥 **요청 본문 (Request Body)**
  - 생성할 리소스의 정보는 **보통 JSON 형식**으로 HTTP 요청 본문에 담아 전달합니다
  - **`@RequestBody`** 어노테이션을 사용해, 해당 JSON 데이터를 자바 객체로 변환합니다


- 📤 **응답 (Response)**
  - 성공적으로 리소스가 생성되면 → `HTTP 201 Created` 상태 코드와 함께  
    생성된 리소스의 정보(또는 URI)를 반환하는 것이 일반적입니다

## 🛠️ PUT API 만들기

### ✅ PUT 요청이란?

- 기존 리소스의 전체 정보를 수정(교체)할 때 사용
- **멱등성(Idempotent)**: 동일한 요청을 여러 번 보내도 결과는 항상 동일

---

### 🔧 주요 어노테이션

#### `@PutMapping`
- 요청 본문(Request Body): 수정할 리소스의 **전체 정보**를 담아 전달
- `@RequestBody`를 통해 수신

---

### 📍 경로 설정
- **경로 변수**: 어떤 리소스를 수정할 것인지 식별하기 위해 URL에 ID 포함  
  예시: `/api/v1/users/{id}`

---

### 📤 응답 처리
- **성공 시**:
  - `200 OK` 상태 코드와 함께 **수정된 리소스 정보 반환**
  - 또는 **내용 없이** `204 No Content` 반환

## 🩹 PATCH API 만들기

### ✅ PATCH 요청이란?
- 기존 리소스의 **일부 정보만 수정**할 때 사용
- `PUT`과 달리 전체 리소스를 보내지 않고, **변경하려는 필드만 전송**

---

### 🔧 주요 어노테이션

#### `@PatchMapping`
- 요청 본문(Request Body): 수정할 필드와 값을 담아 전달  
  → `@RequestBody`로 수신

---

### 📍 경로 설정
- **경로 변수**: 어떤 리소스를 수정할지 명시하기 위해 ID 포함  
  예시: `/api/v1/users/{id}`

---

### 📤 응답 처리
- `PUT`과 동일하게 다음 중 하나를 반환:
  - `200 OK`: 수정된 리소스 정보 반환
  - `204 No Content`: 본문 없이 상태만 반환

## ❌ DELETE API 만들기

### ✅ DELETE 요청이란?
- 특정 리소스를 **삭제**할 때 사용됨

---

### 🔧 주요 어노테이션

#### `@DeleteMapping`
- 메서드를 **HTTP DELETE 요청**과 매핑
- URL 경로에 **삭제 대상 리소스의 ID 포함**

---

### 📍 경로 설정
- 예시: `/api/v1/users/{id}`  
  → 해당 ID에 해당하는 사용자를 삭제

---

### 📤 응답 처리
- 삭제 성공 시:
  - `200 OK`: 삭제된 리소스 정보를 반환하는 경우
  - `204 No Content`: 본문 없이 상태 코드만 반환 (일반적)

___

<br>
<br>
<br>

# 📘 Lombok(롬복)

- 자바 개발 시 반복적으로 작성해야 하는 코드  
  (Getter, Setter, 생성자, toString(), equals(), hashCode() 등)을  
  어노테이션 프로세싱을 통해 컴파일 시점에 자동으로 생성해주는 라이브러리


- 이를 통해 코드의 가독성을 높이고, 개발 생산성을 향상시킬 수 있음


- `pom.xml`에서 scope를 `provided`로 설정해서  
  Lombok이 컴파일 타임에 코드를 생성하고,  
  생성된 바이트코드에는 Lombok 관련 코드가 포함되지 않도록 함

🔹 **코드 간결성 향상**: 반복적인 보일러플레이트 코드를 대폭 줄여 코드가 훨씬 깔끔해지고 가독성이 높아짐

🔹 **개발 생산성 증대**: 단순 반복 작업을 줄여 개발자가 핵심 로직에 더 집중할 수 있게 함

🔹 **유지보수 용이성**: 필드 추가/삭제 시 관련 getter/setter 등이 자동으로 추가되므로 유지보수가 용이

🔹 **버그 감소 가능성**: 단순 반복 코드 작성 시 발생할 수 있는 실수를 줄여줌

***

## 📘 주요 Lombok 어노테이션

### 🔹 **`@Getter`**  
→ *모든 필드에 대한* `public getter` *메서드를 자동으로 생성*

### 🔹 **`@Setter`**  
→ *`final`이 아닌 모든 필드에 대한* `public setter` *메서드를 자동으로 생성*

### 🔹 **`@NoArgsConstructor`**  
→ *파라미터가 없는 기본 생성자를 생성*

### 🔹 **`@RequiredArgsConstructor`**  
→ *`final` 또는* `@NonNull` *어노테이션이 붙은 필드만을 파라미터로 받는 생성자를 생성*

### 🔹 **`@AllArgsConstructor`**  
→ *모든 필드를 파라미터로 받는 생성자를 생성*

### 🔹 **`@ToString`**  
→ *`toString()` 메서드를 자동으로 생성*

### 🔹 **`@Data`**  
→ `@Getter`, `@Setter`, `@RequiredArgsConstructor`, `@ToString`, `@EqualsAndHashCode` 어노테이션을  
모두 합쳐놓은 *강력한 어노테이션*

### 🔹 **`@Value`**  
→ *불변 (`immutable`) 데이터 클래스*를 만들 때 유용  
→ `@Getter` + 모든 필드를 `private final`로 선언  
→ `@AllArgsConstructor`, `@ToString`, `@EqualsAndHashCode`를 합쳐놓은 것과 유사

### 🔹 **`@Builder`**  
→ *빌더 패턴 코드를 자동 생성*  
→ 객체 생성 시 *가독성 향상*, 선택적 파라미터 처리에 유용

### 🔹 **`@Log` (및 다양한 로그 변형)**  
→ `SLF4J`, `Log4j`, `Log4j2`, `Commons Logging`, `Java Util Logging` 등  
→ 다양한 로깅 프레임워크에 대한 *로거 (`Logger`) 객체*를 자동 생성  
→ `log`라는 이름의 `static final` 필드로 주입

### 🔹 **`@Data`**  
→ `@Getter`, `@Setter`, `@RequiredArgsConstructor`, `@ToString`, `@EqualsAndHashCode` 어노테이션을  
모두 합쳐놓은 *강력한 어노테이션*

### 🔹 **`@Value`**  
→ *불변 (`immutable`) 데이터 클래스*를 만들 때 유용  
→ `@Getter` + 모든 필드를 `private final`로 선언  
→ `@AllArgsConstructor`, `@ToString`, `@EqualsAndHashCode`를 합쳐놓은 것과 유사

### 🔹 **`@Builder`**  
→ *빌더 패턴 코드를 자동 생성*  
→ 객체 생성 시 *가독성 향상*, 선택적 파라미터 처리에 유용

### 🔹 **`@Log` (및 다양한 로그 변형)**  
→ `SLF4J`, `Log4j`, `Log4j2`, `Commons Logging`, `Java Util Logging` 등  
→ 다양한 로깅 프레임워크에 대한 *로거 (`Logger`) 객체*를 자동 생성  
→ `log`라는 이름의 `static final` 필드로 주입

***

## 📘 도메인 객체 (Entity)

🔹 **비즈니스 도메인의 핵심 개념**을 표현하는 객체 – *실제 데이터를 담는 역할*

🔹 *고유한 식별자*를 가지며, 이 식별자를 통해
애플리케이션의 생명주기 동안 **지속적으로 추적되고 변경**됨

🔹 단순한 데이터 그 이상  
→ 해당 데이터와 관련된 **비즈니스 로직(`메서드`)**도 함께 포함

🔹 **DB(데이터베이스)와 연결되어, 진짜 정보를 담고 저장하는 역할** 🗃️

🔹 `DDD(Domain-Driven Design)`에서는 이를 **엔티티(`Entity`)**라고도 부름

🔹 🎯 **주요 목적:**  
도메인의 **핵심 비즈니스 로직과 규칙을 캡슐화**하고 표현

🔹 **식별성**  
→ 각 인스턴스는 *고유한 ID*로 구분됨  
→ 속성 값이 같아도 ID가 다르면 **서로 다른 객체로 취급**

🔹 **가변성**  
→ 상태가 변경될 수 있음  
→ 예: *사용자의 주소가 변경되거나*, *주문의 상태가 변경됨*

🔹 **비즈니스 로직 포함**  
→ 단순한 데이터 컨테이너가 아님  
→ 도메인의 규칙과 관련된 **행위(메서드)**를 스스로 수행  
→ 예: ``Order`` 객체가 ``cancel()`` 메서드를 통해 주문 취소 수행

🔹 **생명주기 관리**  
→ 생성, 조회, 수정, 삭제(`CRUD`)의 대상이 되며  
→ 일반적으로 **데이터베이스의 테이블과 매핑**

🔹 📦 **대표 예시**  
→ `User` (사용자), `Product` (상품), `Order` (주문), `Account` (계좌)

> Entity는 시스템의 핵심 비즈니스 개념을 **프로그래밍적으로 표현**하는 데 사용,
다음과 같은 경우에 Entity로 모델링

>🔹 **시스템의 핵심 비즈니스 개념**을 모델링할 때  
→ 예: 사용자(User), 주문(Order), 상품(Product) 등
>
>🔹 **고유하게 식별 가능**해야 하며,  
→ 시간이 지남에 따라 *상태가 변할 수 있는 대상*일 때  
→ 예: 주소 변경, 주문 상태 변경 등
>
>🔹 **데이터베이스에 영속화**되어야 하는 객체일 때  
→ 즉, **실제 데이터를 담고 저장할 필요가 있는 경우**

***

## 📘 데이터 전송 객체 (DTO)

🔹 **계층 간 데이터 전송을 위해 사용되는 객체**

🔹 프레젠테이션 계층(`Controller`)과 서비스 계층(`Service`),  
또는 외부 시스템과의 데이터 송수신 시 활용

🔹 로직 없이 **순수한 데이터 컨테이너(Data Container)** 역할

🔹 계층 간 데이터를 **효율적이고 안전하게 전달**하며,  
각 계층의 책임을 **명확히 분리**하는 데 도움을 줌

### 🧩 핵심 특징

- **📤 데이터 전달 목적**  
  → 오직 *데이터 전달*을 위한 목적


- **🚫 행위(로직) 없음**  
  → *비즈니스 로직 또는 도메인 규칙 없음*  
  → `getter`, `setter` 메서드만 가짐


- **🧱 계층 간 의존성 분리**  
  → 도메인 객체 직접 노출 X  
  → DTO를 사용하여 **계층 간 결합도 감소**


- **🔁 가변성 / 불변성**  
  → 요청 DTO: *일반적으로 불변 객체*  
  → 응답 DTO: *필요에 따라 가변 또는 불변으로 설계*

---

## 📦 예시

```java
UserRegistrationRequestDto // 사용자 등록 요청 정보
ProductResponseDto         // 상품 정보 응답
OrderSummaryDto            // 주문 요약 정보
```

---

### 📘 데이터 전송 객체 (DTO)를 사용하는 경우

🔹 **클라이언트와 서버 간**, 또는 **서버 내부의 다른 계층 간에 데이터를 주고받을 때**

🔹 **API 요청/응답의 스펙을 명확히 정의하고 싶을 때**

🔹 도메인 모델을 외부에 직접 노출하지 않고,  
필요한 데이터만 *필터링*하거나 *가공하여 전달*하고 싶을 때

🔹 여러 도메인 객체의 정보를 *조합하여*,  
특정 뷰에 필요한 형태로 *전달하고 싶을 때*

---

# 📘 값 객체 (VO: Value Object)

**VO(Value Object)**는 도메인에서 **특정 값**을 나타내는 객체다.  

식별자 없이 **속성 값 자체로 동일성 판단**하며, **불변성**이 핵심이다.

---

## 🧩 주요 특징

- **의미 있는 값 표현**  
  → 도메인에서 특정 값을 나타냄


- **식별자 없음**  
  → 오직 *속성 값*으로 동일 여부 판단  
  → 속성이 모두 같으면 같은 객체로 취급


- **🔒 불변성 (Immutability)**  
  → **핵심 특징**  
  → 생성 이후 내부 상태 변경 ❌


- **예측 가능한 코드 유도**  
  → 도메인 개념 중 *값의 의미를 명확히 표현*  
  → 불변성을 통해 *안정적이고 예측 가능한 코드* 작성 가능

---

### 💡 예시

```java
Money amount = new Money(1000, "KRW");
Address home = new Address("서울시 강남구", "123-45");
```

`amount`나 `home`은 속성 값만 같다면 같은 VO로 간주된다.

---

### 🧩 핵심 속성

- **🧠 동등성**  
  → 모든 속성 값이 같으면 *논리적으로 동일한 객체*로 간주  
  → `equals()`, `hashCode()` 오버라이딩 필수


- **🔒 불변성 (Immutability)**  
  → 생성 후 상태 변경 불가  
  → 상태가 바뀌면 **새로운 VO 생성** 필요


- **✅ 자체 유효성 검사**  
  → 생성 시점에 유효성을 검사하여  
  → **잘못된 값 생성을 방지**


- **⚙️ 행위 포함 가능**  
  → 값 자체와 관련된 *단순 계산 로직* 포함 가능  
  → 예: `Money.plus()`, `Period.isWithinRange()`

---

## 📦 예시 VO 객체

```text
- Money   (금액 + 통화 단위)
- Address (주소: 시, 도, 우편번호 등)
- Color   (RGB 값)
- Period  (기간: 시작일, 종료일)
```

> 📌 VO는 단순한 데이터 컨테이너가 아니라, **값의 의미와 제약을 캡슐화**하여 **안정성과 표현력**을 높이는 데에 쓰인다.


---

# 📘 데이터베이스 연동 핵심 개념

Spring Boot는 다양한 데이터베이스와의 연동을 **간편하게 지원**하며,  
자동 설정과 스타터 의존성을 통해 **최소한의 설정으로 관계형 데이터베이스 사용 가능**.

---

## 🔑 핵심 개념 정리

- **JDBC (Java Database Connectivity)**  
  → 자바 애플리케이션이 데이터베이스와 통신하기 위한 *표준 API*


- **DataSource**  
  → 데이터베이스 커넥션을 관리하는 객체  
  → 커넥션 풀링 기능 제공, *성능 향상* 및 *리소스 효율적 관리*


- **Spring Boot와 커넥션 풀**  
  → 클래스패스에 HikariCP, Tomcat CP, Commons DBCP2 등이 있으면 자동 설정  
  → 기본값: `HikariCP`


- **JDBC 드라이버**  
  → 각 데이터베이스 벤더(MySQL, Oracle, PostgreSQL, H2 등)가 제공하는 *JDBC API 구현체*


- **Spring JDBC**  
  → 반복적인 JDBC 코드 (커넥션 열고 닫기, 예외 처리 등)를 줄여줌  
  → 개발자는 `SQL 쿼리 작성과 결과 처리`에 집중 가능

---

## 🔧 주요 기술 비교

### 🧩 **MyBatis (iBatis)**
- SQL 쿼리를 XML 파일 또는 어노테이션에 정의
- 자바 객체와 명시적으로 매핑
- **직접 SQL을 제어**하고 싶을 때 유용

---

### 🧩 **ORM (Object-Relational Mapping)**
- 객체 지향 언어와 관계형 DB 간의 *불일치 해결*을 위한 기술
- 객체를 DB 테이블에 **자동 매핑**
- SQL 없이도 객체 조작으로 데이터 접근 가능

---

### 🧩 **JPA (Java Persistence API)**
- 자바 ORM 기술에 대한 **표준 명세 (API)**

---

### 🧩 **Hibernate**
- 가장 널리 사용되는 JPA 구현체 중 하나

---

### 🧩 **Spring Data JPA**
- 스프링 생태계 기반의 하위 프로젝트
- `JPA`를 더 쉽게 사용하기 위한 **추상화 계층** 제공
- `Repository` 인터페이스만 정의하면:
  - 기본 `CRUD`
  - 페이징
  - 정렬 기능 등 자동 구현

> 📌 위 기술들은 각기 다른 수준의 추상화와 제어권을 제공하며, **요구사항에 따라 선택적으로 사용**할 수 있음.

---

## 📘 데이터베이스 연동 방식

---

### 🧩 Starter 의존성 추가

- **`spring-boot-starter-jdbc`**  
  → `Spring JDBC (JdbcTemplate)` 사용 시 추가


- **`spring-boot-starter-data-jpa`**  
  → `JPA (Hibernate 포함)` 및 `Spring Data JPA` 사용 시 추가  
  → 이미 `spring-boot-starter-jdbc` 포함하고 있어 **별도 추가 불필요**


- **`mybatis-spring-boot-starter`**  
  → `MyBatis` 사용 시 추가

---

### 🔧 기타 설정 요소

- **DB 드라이버 의존성**  
  → 사용 DB(MySQL, PostgreSQL 등)에 맞는 **JDBC 드라이버 의존성 추가** 필요


- **자동 설정 조건**  
  → 스타터 의존성이 클래스패스에 추가되면  
  → Spring Boot의 **자동 설정 기능 활성화**


- **DB 연결 설정 파일 필요**  
  → `application.properties` 또는 `application.yml`에 설정

---

# 📘 H2 Database

Java로 작성된 **오픈 소스 SQL 데이터베이스**  
매우 가볍고 빠르며, **개발 및 테스트 단계**에서 자주 사용됨  
주로 **임베디드(Embedded)** 또는 **인메모리(In-Memory)** 모드 활용

---

## 🧠 인메모리 (In-Memory) 모드

- 데이터를 **메모리에만 저장**, 애플리케이션 종료 시 데이터 소멸
- 매우 빠른 성능
- **단위 테스트 / 통합 테스트에 적합**
- 예시:
  ```
  jdbc:h2:mem:testdb
  ```

---

## 💾 임베디드 (Embedded, 파일 기반) 모드

- 애플리케이션에 **내장 실행**, 데이터는 **로컬 파일에 저장**
- 애플리케이션 종료 후에도 데이터 **유지됨**
- 예시:
  ```
  jdbc:h2:~/sample
  ```
---

# 📘 H2 Database 특징

---

## 🌐 웹 기반 콘솔 제공
- DB 스키마 확인, 데이터 조회, SQL 실행이 가능한 **관리 도구 제공**
- 브라우저 기반 접근 (`http://localhost:8080/h2-console`)

---

## 📚 표준 SQL 및 JDBC API 지원
- 대부분의 표준 SQL 문법 지원
- JDBC 드라이버를 통해 Java와 쉽게 연동 가능

---

## 🔄 다른 데이터베이스 호환성 모드
- 호환 대상: `MySQL`, `PostgreSQL`, `Oracle`, `MS SQL Server` 등
- 타 DB의 SQL 문법 및 동작 일부 모방 가능
- 예시:
  ```text
  jdbc:h2:mem:testdb;MODE=MySQL
  ```

---

## ⚙️ Spring Boot와의 통합
- Spring Boot가 H2 Database를 감지하면
- 별도의 복잡한 설정 없이 자동으로 `DataSource` 구성

---

# 🐍 MyBatis

MyBatis는 자바 퍼시스턴스 프레임워크 중 하나로, **SQL 매퍼(SQL Mapper)** 프레임워크에 해당한다.  
**개발자가 직접 작성한 SQL**을 바탕으로 자바 객체와 결과를 **명시적으로 매핑**하는 방식이다.

---

## 🧩 주요 특징

- **직접 SQL 작성**  
  → XML 파일 또는 어노테이션에 SQL 쿼리 명시


- **명시적 매핑 방식**  
  → SQL 실행 결과를 자바 객체(`DTO`, `도메인 객체`)에 직접 매핑


- **ORM보다 SQL 제어권이 높음**  
  → 복잡한 쿼리 최적화나 DB 특화 로직에 유리

---

## 📦 예시 구조

```xml
<!-- mapper XML 예시 -->
<select id="findUserById" resultType="UserDto">
  SELECT * FROM users WHERE id = #{id}
</select>
```

```java
// 인터페이스 매퍼 예시
UserDto findUserById(int id);
```

---

## 🐍 MyBatis 주요 특징

---

### 🧩 핵심 장점

- **🧠 SQL 중심**  
  → 개발자가 직접 SQL을 작성하고 완전한 제어 가능

- **🔗 유연성**  
  → 단순 쿼리부터 **복잡한 조인**, **동적 SQL**, **저장 프로시저 호출**까지 지원

- **⚙️ 생산성**  
  → 반복적인 JDBC 코드를 줄이고  
  → SQL 실행 결과를 자바 객체로 자동 매핑

- **📉 낮은 학습 곡선**  
  → SQL에 대한 기본 지식만 있다면 **쉽게 습득 가능**

---

### ✨ 고급 기능

- **🔄 동적 SQL 지원**  
  → `<if>`, `<choose>` 등의 태그로 조건에 따라 SQL 생성 가능

- **🧼 매핑의 분리**  
  → SQL과 자바 코드를 **명확히 분리**해 유지보수 용이 (ex: XML 매퍼)

- **♻️ SQL 재사용**  
  → `<sql>` 태그로 공통 SQL 조각 정의 후 재사용 가능

```xml
<sql id="BaseColumnList">
  id, name, email, created_at
</sql>
```

---

## 🐍 MyBatis 주요 구성 요소

---

### 🔨 `SqlSessionFactoryBuilder`
- MyBatis 설정 파일 또는 자바 코드 기반으로 `SqlSessionFactory` 생성

### 🏗️ `SqlSessionFactory`
- `SqlSession` 인스턴스를 생성하는 팩토리 역할
- (스프링 부트에서는 빈으로 자동 등록됨)

### ⚙️ `SqlSession`
- SQL 실행 및 트랜잭션 제어를 위한 핵심 인터페이스
- SQL 실행 및 매퍼 인터페이스 메서드 호출 담당

### 🧩 매퍼 인터페이스
- 개발자가 SQL 문장을 호출하기 위해 정의한 **자바 인터페이스**

### 📄 매퍼 XML 파일
- SQL 문장, 파라미터 매핑, 결과 매핑을 정의하는 XML 파일

---

## 🗄️ 데이터베이스 초기화

개발 또는 테스트 시, **DB 스키마 생성 및 초기 데이터 적재**가 필요할 때 사용하는 방법

---

### ⚙️ JPA ddl-auto

- 속성: `spring.jpa.hibernate.ddl-auto`
- 기능: JPA가 시작 시 **DDL 자동 실행 전략**을 설정 (예: `create`, `update`, `validate` 등)

---

### 📄 `schema.sql` 및 `data.sql`

- `src/main/resources/schema.sql`  
  → 앱 시작 시 실행되는 DDL 스크립트 (예: 테이블 생성)

- `src/main/resources/data.sql`  
  → 앱 시작 시 실행되는 DML 스크립트 (예: 초기 데이터 삽입)

- 실행 제어 옵션:
  ```properties
  spring.sql.init.mode=always
  ```

---

### 🧭 Flyway 또는 Liquibase

- **정교하고 버전 관리 가능한 마이그레이션 도구**

#### Flyway
- 의존성 추가: `flyway-core`
- 마이그레이션 예시:
  ```text
  src/main/resources/db/migration/V1__Init.sql
  ```

#### Liquibase
- 의존성 추가: `liquibase-core`
- 유사한 방식으로 마이그레이션 관리

>📌 운영 환경에서는 Flyway/Liquibase를 **표준 방식으로 추천**


