package jpabook.start;

import javax.persistence.*;

/*
    식별, 비식별 관계의 장단점
    ------------------------------------------------------------------------------------------------------------
    데이터베이스 설계 관점에서 보면 다음과 같은 이유로 식별 관계보다는 비식별 관계를 선호한다.
    - 식별 관계는 부모 테이블의 기본 키를 자식 테이블로 전파하면서 자식 테이블의 기본 키 컬럼이 점점 늘어난다.
      예를 들어 부모 테이블은 기본 키 컬럼이 하나였지만 자식 테이블은 기본 키 컬럼이 2개, 손자 테이블은
      기본 키 컬럼이 3개로 점점 늘어난다. 결국 조인할 때 SQL이 복잡해지고 기본 키 인덱스가 불필요하게
      커질 수 있다.
    - 식별 관계는 2개 이상의 컬럼을 합해서 복합 기본 키를 만들어야 하는 경우가 많다.
    - 식별 관계를 사용할 때 기본 키로 비즈니스 의미가 있는 자연 키 컬럼을 조합하는 경우가 많다.
      반면에 비식별 관계의 기본 키는 비즈니스와 전혀 관계없는 대리 키를 주로 사용한다.
      비즈니스 요구사항은 시간이 지남에 따라 언젠가는 변한다. 식별 관계의 자연 키 컬럼들이
      자식에 손자까지 전파되면 변경하기 힘들다.
    - 식별 관계는 부모 테이블의 기본 키를 자식 테이블의 기본 키로 사용하므로 비식별 관계보다 테이블 구조가 유연하지 못하다.

    객체 관계 매핑의 관점에서 보면 다음과 같은 이유로 비식별 관계를 선호한다.
    - 일대일 관계를 제외하고 식별 관계는 2개 이상의 컬럼을 묶은 복합 기본 키를 사용한다.
      JPA에서 복합 키는 별도의 복합 키 클래스를 만들어서 사용해야 한다.
      따라서 컬럼이 하나인 기본 키를 매핑하는 것보다 많은 노력이 필요하다.
    - 비식별 관계의 기본 키는 주로 대리 키를 사용하는데 JPA는 @GenerateValue 처럼 대리 키를 생성하기 위한 편리한 방법을 제공한다.

    ------------------------------------------------------------------------------------------------------------

    물론 식별 관계가 가지는 장점도 있다. 기본 키 인덱스를 활용하기 좋고, 상위 테이블들의 기본 키 컬럼을 자식, 손자 테이블들이 가지고
    있으므로 특정 상황에 조인 없이 하위 테이블만으로 검색을 완료할 수 있다.

    기본 키 인덱스를 활용하는 예를 보자:
    - 부모 아이디가 A인 모든 자식 조회
      SELECT * FROM CHILD
      WHERE PARENT_ID = 'A';

    - 부모 아이디가 A고 자식 아이디가 B인 자식 조회
      SELECT * FROM CHILD
      WHERE PARENT_ID = 'A' AND CHILD_ID = 'B'

    두경우 모두 CHILD 테이블의 기본 키 인덱스를 PARENT_ID + CHILD_ID로 구성하면 별도의 인덱스를 생성할 필요 없이 기본 키 인덱스만 사용해도 된다.
    이처럼 식별 관계가 가지는 장점도 있으므로 꼭 필요한 곳에는 적절하게 사용하는 것이 데이터베이스 테이블 설계의 묘를 살리는 방법이다.

    ------------------------------------------------------------------------------------------------------------
    정리)
    ORM 신규 프로젝트 진행시 추천하는 방법은 될 수 있으면 비식별 관계를 사용하고 기본 키는 Long 타입의 대리 키를 사용하는 것이다.
    대리 키는 비즈니스와 아무 관련이 없다. 따라서 비즈니스가 변경되어도 유연한 대처가 가능하다는 장점이 있다. JPA는 @GenerateValue를
    통해 간편하게 대리 키를 생성할 수 있다. 그리고 식별자 컬럼이 하나여서 쉽게 매핑할 수 있다. 식별자의 데이터 타입은 Long을 추천하는데,
    자바에서 Integer는 20억 정도면 끝나버리므로 데이터를 많이 저장하면 문제가 발생할 수 있다. 반면에 Long은 아주 커서(약 920경) 안전하다.

    그리고 선택적 비식별 관계보다는 필수적 비식별 관계를 사용하는 것이 좋은데, 선택적인 비식별 관계는 NULL을 허용하므로 조인할 때에 외부 조인을
    사용해야 한다. 반면에 필수적 관계는 NOT NULL로 항상 관계가 있다는 것을 보장하므로 내부 조인만 사용해도 된다.
 */
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook"); // 엔티티 매니저 팩토리 생성
        EntityManager em = emf.createEntityManager(); // 엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); // 트랜잭션 기능 획득

        try {
            tx.begin(); // 트랜잭션 시작
            tx.commit(); // 트랜잭션 커밋
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); // 트랜잭션 롤백
        } finally {
            em.close(); // 엔티티 매니저 종료
        }

        emf.close(); // 엔티티 매니저 팩토리 종료
    }

}