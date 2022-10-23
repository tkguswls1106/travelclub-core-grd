package shjgroup.travelclubcoregrd.store.jpastore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shjgroup.travelclubcoregrd.store.jpastore.jpo.TravelClubJpo;

public interface ClubRepository extends JpaRepository<TravelClubJpo, String> {  // 인터페이스가 인터페이스를 상속받을때에는 implements가 아닌, extends를 사용한다. 그리고 콤마(,)로 구분하여 다중 상속이 가능하다.
                                                                                // <TravelClubJpo, String>는 <@Entity가 적혀있는 TravelClubJpo 클래스의 DB 엔티티, TravelClubJpo 엔티티의 pk id의 자료형인 String> 이다.  // 자료형 <class T,ID 식별자 pk 자료형>
}
