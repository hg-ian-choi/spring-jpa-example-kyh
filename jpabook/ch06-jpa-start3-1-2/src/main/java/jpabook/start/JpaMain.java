package jpabook.start;

import javax.persistence.*;

/**
 * 일대일 [1:1]
 * 일대일 관계는 양쪽이 서로 하나의 관계만 가진다.
 * 예를 들어 회원은 하나의 사물함만 사용하고 사물함도 하나의 회원에 의해서만 사용된다.
 * ------------------------------------------------------------------------
 * 일대일 관계는 다음과 같은 특징이 있다:
 * 1. 일대일 관계는 그 반대도 일대일 관계다.
 * 2. 테이블 관계에서 일대다, 다대일은 항상 다(N)쪽이 외래 키를 가진다.
 * 반면에 일대일 관계는 주 테이블이나 대상 테이블 둘 중 어느 곳이나 외래 키를 가질 수 있다.
 * ------------------------------------------------------------------------
 * 테이블은 주 테이블이든 대상 테이블이든 외래 키 하나만 있으면 양쪽으로 조회할 수 있다.
 * 그리고 일대일 관계는 그 반대쪽도 일대일 관계다.
 * 따라서 일대일 관계는 주 테이블이나 대상 테이블 중에 누가 외래 키를 가질지 선택해야 한다.
 * ------------------------------------------------------------------------
 * 주 테이블에 외래 키:
 * 주 객체가 대상 객체를 참조하는 것처럼 주 테이블에 외래 키를 두고 대상 테이블을 참조한다.
 * 외래 키를 객체 참조와 비슷하게 사용할 수 있어서 객체지향 개발자들이 선호한다.
 * 이 방법의 장점은 주 테이블이 외래 키를 가지고 있으므로 주 테이블만 확인해도 대상 테이블과 연관관계가 있는지 알 수 있다.
 * ------------------------------------------------------------------------
 * 대상 테이블에 외래 키:
 * 전통적인 데이터베이스 개발자들은 보통 대상 테이블에 외래 키를 두는 것을 선호한다.
 * 이 방법의 장점은 테이블 관계를 일대일에서 일대다로 변경할 때 테이블 구조를 그대로 유지할 수 있다.
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