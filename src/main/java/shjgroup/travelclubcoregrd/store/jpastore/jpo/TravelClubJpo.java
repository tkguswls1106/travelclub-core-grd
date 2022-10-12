package shjgroup.travelclubcoregrd.store.jpastore.jpo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import shjgroup.travelclubcoregrd.aggregate.club.TravelClub;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 도메인 객체를 바로 Entity로 매핑할 수 있지만, 임피던스 불일치(기존 관계형 데이터베이스의 SQL과 프로그래밍 언어 사이에 데이터 구조, 기능 등의 차이로 발생하는 충돌) 등의 이유로, 별도의 매핑 클래스 (Jpo)를 둔다.
// 이 @Entity를 선언해주어야, 여기 클래스 테이블을 h2데이터베이스 같은 DB에서 JPA로 매핑을 해주고 DB 테이블을 자동으로 만들어줄수있다.
@Entity  // 이것은 JPA가 관리하는 엔티티이다 라는 것이다. 매핑클래스를 관계형 데이터베이스 테이블로 매핑할때 사용한다.
@Getter
@Setter
@NoArgsConstructor  // @NoArgsConstructor 어노테이션은 파라미터(매개변수)가 없는 기본 생성자 메소드를 생성해준다. TravelClubJpo(){} 메소드 말이다.
                    // 보통 엔티티에 @NoArgsConstructor를 같이 사용하는데, 그 이유는 @Entity는 JPA가 관리하는 엔티티라는 뜻인데, java의 ORM 기술인 JPA는 기본 스펙상 기본 생성자를 요구하기때문이다.
                    // 하지만 사실 @Entity 어노테이션이 내부적으로 기본 생성자를 자동으로 만들어주기때문에, @NoArgsConstructor( access = AccessLevel.PROTECTED) 이런식처럼 조건을 추가로 붙이지 않는한, 보통 @Entity 붙어있으면 @NoArgsConstructor을 생략하여도된다.
                    // 참고로 단, 초기 값이 필요한 final 필드가 있을 경우, 컴파일 에러이다. 그럴때는 @NoArgsConstructor(force=true)를 하면, 컴파일 에러를 내지 않고 0/false/null로 초기화해준다.
@Table(name="TRAVEL_CLUB")  // @Table(~)없이 @Entity 어노테이션만 선언했을때 테이블 이름은 클래스 이름인 'TRAVEL_CLUB_JPO'가 된다. 그러므로 @Table 어노테이션으로 직접 테이블 이름을 지어주면 그 테이블명으로 테이블이 생성된다.
public class TravelClubJpo {  // 이 클래스는 데이터베이스 엔티티 객체라고 생각하면 된다고 한다.

    @Id  // 해당 컬럼을 PK로 설정한다.
    private String id;
    private String name;
    private String intro;
    private long foundationTime;

    public TravelClubJpo(TravelClub travelClub) {  // TravelClub 객체인 domain객체를 가져와서 TravelClubJpo 객체인 Jpo객체로 바꿔줌으로써, 데이터베이스 엔티티 객체로 사용할수있게하는 용도이다.
//        this.id = travelClub.getId();  // this.~ 이것은 TravelClubJpo 클래스의 필드들이다.
//        this.name = travelClub.getName();  // this.~ 이것은 TravelClubJpo 클래스의 필드들이다.
//        this.intro = travelClub.getIntro();  // this.~ 이것은 TravelClubJpo 클래스의 필드들이다.
//        this.foundationTime = travelClub.getFoundationTime();  // this.~ 이것은 TravelClubJpo 클래스의 필드들이다.

        BeanUtils.copyProperties(travelClub, this);  // 위의 this코드 4줄을 이렇개 한줄로 압축시킬수있다. travelClub 객체의 프로퍼티 복사해서 this인 TravelClubJpo 객체로 전달.
    }

    public TravelClub toDomain() {  // 위의 TravelClubJpoJpo 메소드와는 반대로, 객체를 domain객체로 바꿔주는 메소드이다.
        TravelClub travelClub = new TravelClub(this.name, this.intro);  // TravelClub 클래스의 TravelClub(String name, String intro) 메소드를 사용하였다.  // this.~ 이것은 TravelClubJpo 클래스의 필드들이다.
        travelClub.setId(this.id);  // TravelClub 클래스의 부모인 Entity 클래스의 Getter 어노테이션덕에 setId 사용가능.  // this.~ 이것은 TravelClubJpo 클래스의 필드들이다.
        travelClub.setFoundationTime(this.foundationTime);  // TravelClub 클래스의 Setter 어노테이션덕에 setFoundationTime 메소드를 사용가능.  // this.~ 이것은 TravelClubJpo 클래스의 필드들이다.
        return travelClub;
    }
}  // 여기까지만 만든것이면, 아직 spring data jpa를 적용한것이 아니라, 단순히 jpa hibernate 기술만 가지고 사용해본것이다.
