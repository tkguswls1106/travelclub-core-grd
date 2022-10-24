package shjgroup.travelclubcoregrd.store.jpastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shjgroup.travelclubcoregrd.store.jpastore.jpo.TravelClubJpo;

import java.util.List;

public interface ClubRepository extends JpaRepository<TravelClubJpo, String> {  // 인터페이스가 인터페이스를 상속받을때에는 implements가 아닌, extends를 사용한다. 그리고 콤마(,)로 구분하여 다중 상속이 가능하다.
                                                                                // <TravelClubJpo, String>는 <@Entity가 적혀있는 TravelClubJpo 클래스의 DB 엔티티, TravelClubJpo 엔티티의 pk id의 자료형인 String> 이다.  // 자료형 <class T,ID 식별자 pk 자료형>

    List<TravelClubJpo> findAllByName(String name);  // 만약 findAllByName가 아닌, findByName 이었다면 반환자료형이 List가 아니었을것이다.
    // name으로 검색해서 찾아서 그 이름이 같은 모든 Club데이터들을 모두 가지고 올것이기 때문에, 메소드의 반환자료형이 List인것이고,
    // TravelClubJpo 가 담긴 리스트를 반환해서 가져와야하기때문에, findAllByName 메소드의 반환자료형은 List<TravelClubJpo> 가 되는것이다.

    // JpaRepository안에 매우 기본적이고 공통적인 CRUD등이 전부 구현되어 있기 때문에,
    // retrieveAll() 안에 쓰인 findAll(), create()와 update() 안에 쓰인 save(), retrieve() 안에 쓰인 findById()는
    // 이미 JpaRepository 인터페이스에 있는 Spring Data Jpa 관련 CRUD 메소드 안에 이미 들어있어서 따로 구현해줄 필요없이 그저 Jpa 메소드를 갖다쓰면 되는데,
    // 하지만, retrieveByName() 안에 쓰일 name값으로 데이터를 찾는 메소드처럼 JpaRepository에 없는 특별한 경우에 대해서는 구현되어 있기 어렵다. (모든 시스템이 다르기 때문에)
    // 이유는 find의 검색조건이 여러가지가 될 수 있기에 우리가 지정한 변수인 name에 대해서는 JpaRepository에 따로 구현되어있지 않는것이다.
    // 그래서 findAllByName()은 직접 구현해주어야한다. 참고로 이 findAllByName() 메소드는 우리가 메소드명을 직접 지어준것이다.
    // 결국 JpaRepository 인터페이스에 구현되어있지 않은 CRUD 메소드를, 여기 ClubRepository 인터페이스에서 따로 만들어주고 이 메소드를, ClubJpaStore 클래스에서 Jpa DB 접근 메소드로 불러내어 직접 사용할 수 있게 되는 것이다.

    // 참고로 이처럼 직접 JPA 메소드를 정의하여 만들어줄때는, JPA(Java Persistence API) 자동 생성 쿼리 메소드의 명명 규칙에 따라서 만들어주면 된다.
}
