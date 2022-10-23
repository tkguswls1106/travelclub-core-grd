package shjgroup.travelclubcoregrd.store.jpastore;

import org.springframework.stereotype.Repository;
import shjgroup.travelclubcoregrd.aggregate.club.TravelClub;
import shjgroup.travelclubcoregrd.store.ClubStore;
import shjgroup.travelclubcoregrd.store.jpastore.jpo.TravelClubJpo;
import shjgroup.travelclubcoregrd.store.jpastore.repository.ClubRepository;

import java.util.List;

@Repository
// ClupMapStore 대신 ClubJpaStore 로 저장소를 변경할거라,
// ClupMapStore 클래스의 @Repository 어노테이션 제거해서 빈등록 안되게 막고,
// 대신 ClubJpaStore 클래스에 @Repository 어노테이션 붙여줘서 빈등록하게함.
public class ClubJpaStore implements ClubStore {
    // 참고로 엔터티 매니저(EntityManager)는 엔터티를 저장하는 메모리상의 데이터베이스라고 생각하면 된다. 엔터티를 저장하고 수정하고 삭제하고 조회하는 등 엔터티와 관련된 모든일을 한다.
    // 원래는 EntityManager라는 객체(예를들어 private final EntityManager em;)를 이용한
    // em.persist() 이나 em.find() 와 같은 영속성 메소드를 사용하여, 순수 jpa를 통해 데이터를 넣고 쓰고 사용할 수 있지만,
    // 순수 jpa에서 EntityManager를 사용하던 이러한 작업들을, 이제는 더욱 추상화하여 더 쉽게 쓸 수 있도록 해주는 방법인 spring data jpa 가 나와서 전자의 방법 대신 이걸 사용하면 된다.
    // 즉, 일일이 jpa에서 제공하는 EntityManager를 이용해서 데이터를 읽고 쓰는게 아니라, EntityManager에 대한 관리조차도 spring data 에게 맡기면 되게 되었다.

    // ClubController --ClubService 인터페이스를 사이에 두고 느슨한 결합--> ClubServiceLogic --ClubStore 인터페이스를 사이에 두고 느슨한 결합--> ClubJpaStore --Jpa에서 제공하는 JpaRepository 인터페이스를 상속하는 ClubRepository 인터페이스를 사이에 두고 느슨한 결합--> 결국은 Jpa를 통해 Spring Data Jpa 의 DB에 접근 성공
    private ClubRepository clubRepository;

    public ClubJpaStore(ClubRepository clubRepository) {  // 마치 ClubServiceLogic 클래스가 ClubStore 인터페이스를 사이에두고 느슨한결합으로 서비스 레이어와 스토어 레이어를 의존관계로 연결시킨것처럼 비슷하다.
        this.clubRepository = clubRepository;
    }

    @Override
    public String create(TravelClub club) {
        clubRepository.save(new TravelClubJpo(club));
        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        return null;
    }

    @Override
    public List<TravelClub> retrieveByName(String name) {
        return null;
    }

    @Override
    public List<TravelClub> retrieveAll() {
        return null;
    }

    @Override
    public void update(TravelClub club) {

    }

    @Override
    public void delete(String clubId) {

    }

    @Override
    public boolean exists(String clubId) {
        return false;
    }
}
