package jpabook.start;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    /**
     * 양방향 연관관계 매핑
     * 팀과 회원은 일대다 관계다. 따라서 팀 엔티티에 컬렉션인 List<Member> members를 추가했다.
     * 그리고 일대다 관계를 매핑하기 위해 @OneToMany 매핑 정보를 사용했다.
     * mappedBy 속성은 양방향 매핑일 때 사용하는데 반대쪽 매핑의 필드 이름을 값으로 주면 된다.
     * 반대쪽 매핑이 Member.team이므로 team을 값으로 주었다.
     */
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();

    public Team() {
    }

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter, Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
