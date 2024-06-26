package jpabook.start;

import javax.persistence.*;

/**
 * 대상 테이블에 외래 키
 * ------------------------------------------------------------------------
 * 양방향
 * 일대일 매핑에서 대상 테이블에 외래 키를 두고 싶으면 이렇게 양방향으로 매핑한다.
 * 주 엔티티인 Member 엔티티 대신에 대상 엔티티인 Locker를 연관관계의
 * 주인으로 만들어서 LOCKER 테이블의 외래 키를 관리하도록 했다.
 */
@Entity
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // Getter, Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
