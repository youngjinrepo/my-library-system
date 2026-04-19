## 📚 my-library-system

DDD(Domain-Driven Design)를 적용한 도서관 관리 시스템

실무에서 쌓은 도서관 도메인 지식을 바탕으로, DDD의 핵심 개념(Aggregate, Bounded Context, Value Object, Domain Event)을 직접 설계하고 구현한 포트폴리오 프로젝트입니다.

---

![Java](https://img.shields.io/badge/Java_17-007396?style=flat&logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=springboot&logoColor=white) ![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat&logo=spring&logoColor=white) ![H2](https://img.shields.io/badge/H2-004088?style=flat&logoColor=white)

---

## 목차

1. [주요 기능](#주요-기능)
2. [아키텍처](#아키텍처)
3. [핵심 설계 결정](#핵심-설계-결정)
4. [실행 방법](#실행-방법)
5. [테스트](#테스트)
---

## 주요 기능

- **도서 관리** : 수서 → 편목 → 배가 단계별 상태 전환
- **회원 관리** : 준회원 등록 → CI 인증 후 정회원 승격
- **대출 / 반납** : 도서관 정책 기반 대출 건수 제한, 반납 연장
- **연체 처리** : 반납 기한 초과 시 연체일수 계산 및 대출 정지 처리


## 아키텍처

헥사고날 아키텍처(Ports & Adapters)를 적용했습니다.
도메인이 외부 기술(JPA, Spring)에 의존하지 않도록 의존성 방향을 안쪽으로 유지했습니다.

```
┌─────────────────────────────────────────┐
│              adapter/in/web             │  ← HTTP 요청 (Controller)
├─────────────────────────────────────────┤
│              application                │  ← 유스케이스 (Service)
├─────────────────────────────────────────┤
│               domain                    │  ← 엔티티, 포트(Repository 인터페이스)
├─────────────────────────────────────────┤
│           adapter/out/persistence       │  ← JPA 구현체
└─────────────────────────────────────────┘
```

### 패키지 구조

```
src/main/java/me/my_library_system
├── adapter
│   └── out
│       └── persistence          # JPA Repository 구현체 (PersistenceAdapter)
│           ├── jpa              # JpaRepository 인터페이스
│           └── sequence         # 도서 코드 채번 전략
├── application
│   ├── book                     # 편목 서비스
│   ├── loan                     # 대출 서비스
│   ├── member                   # 회원 서비스
│   └── returnBook               # 반납 서비스
└── domain
    ├── book                     # BookInfo, BookItem
    ├── library                  # Library, Policy
    ├── loan                     # Loan
    ├── member                   # Member, MemberGrade
    ├── reservation              # Reservation (예정)
    └── returnBook               # ReturnBook
```

---



---

## 테스트

단위 테스트와 JPA 슬라이스 테스트로 구성되어 있습니다.

| 종류 | 대상 | 도구 |
|---|---|---|
| 도메인 단위 테스트 | Member, Loan, BookInfo, Policy 등 | JUnit5, AssertJ |
| 서비스 단위 테스트 | LoanService, ReturnBookService 등 | Mockito |
| 슬라이스 테스트 | MemberRepository, LoanJpaRepository | @DataJpaTest |

```bash
# 전체 테스트 실행
./gradlew test
```
