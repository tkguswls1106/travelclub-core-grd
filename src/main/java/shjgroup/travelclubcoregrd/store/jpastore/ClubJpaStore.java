package shjgroup.travelclubcoregrd.store.jpastore;

import org.springframework.stereotype.Repository;
import shjgroup.travelclubcoregrd.aggregate.club.TravelClub;
import shjgroup.travelclubcoregrd.store.ClubStore;
import shjgroup.travelclubcoregrd.store.jpastore.jpo.TravelClubJpo;
import shjgroup.travelclubcoregrd.store.jpastore.repository.ClubRepository;
import shjgroup.travelclubcoregrd.util.exception.NoSuchClubException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        clubRepository.save(new TravelClubJpo(club));  // save메소드는 ClubRepository 인터페이스의 부모 인터페이스인, Jpa에서 제공하는 JpaRepository 인터페이스가 가지고있는 연결된 여러 메소드들중 하나이다.

        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {  // id값으로 하나의 club객체를 찾는(검색하는) 용도이다.

        Optional<TravelClubJpo> clubJpo = clubRepository.findById(clubId);  // findById메소드는 ClubRepository 인터페이스의 부모 인터페이스인, Jpa에서 제공하는 JpaRepository 인터페이스가 가지고있는 연결된 여러 메소드들중 하나이다.
                                                                            // findById메소드를 command+마우스클릭으로 살펴보면, 리턴자료형타입이 Optional인것을 알 수 있다.
        if(!clubJpo.isPresent()) {  // clubId를 가진 TravelClubJpo 클래스 객체가 DB에 존재하지않는다면
            throw new NoSuchClubException(String.format("TravelClub(%s) is not found.", clubId));
        }

        return clubJpo.get().toDomain();  // return 할때는 Jpo객체를 도메인형식으로 바꾸어서 반환한다.
    }

    @Override
    public List<TravelClub> retrieveAll() {  // 전부 검색
        List<TravelClubJpo> clubJpos = clubRepository.findAll();
        return clubJpos.stream().map(clupJpo -> clupJpo.toDomain()).collect(Collectors.toList());  // map()은 인자로 함수를 받으며, Stream의 요소를 다른 형태로 변경한다.
                                                                                                   // 즉, clubJpos 리스트에 담긴 Jpo 객체 요소들을, 전부 도메인형태로 바꾸어, 리스트로 모아, List<TravelClub> 형식을 만들게 되는 것이다.

        // return clubJpos.stream().map(TravelClubJpo::toDomain).collect(Collectors.toList());
        // 위의 return 코드 말고, 이 코드로도 대신 사용이 가능하다.
        // ::는, :: 기준으로 왼쪽 객체의 오른쪽 메소드를 사용한다는 의미이다.
        // 즉, clubJpos 리스트에 담긴 Jpo 객체 요소들을, TravelClubJpo 클래스 객체의 toDomain() 메소드를 사용하여 변환시켜준것이다.
    }

    @Override
    public List<TravelClub> retrieveByName(String name) {
        List<TravelClubJpo> clubJpos = clubRepository.findAllByName(name);  // ClubRepository 인터페이스에 따로 정의해준 Jpa 메소드인 findAllByName 을 사용함.
        return clubJpos.stream().map(TravelClubJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(TravelClub club) {
        clubRepository.save(new TravelClubJpo(club));  // save메소드는 ClubRepository 인터페이스의 부모 인터페이스인, Jpa에서 제공하는 JpaRepository 인터페이스가 가지고있는 연결된 여러 메소드들중 하나인데, create 용도만 있는게 아닌, update 용도로도 사용할 수 있다.
        // save메소드로 인해서, select를 통해서 데이터가 존재하는지 확인을 먼저 하고, 데이터가 존재한다면 update를, 존재하지 않는다면 쿼리로 insert하여 create를 하게 된다.
    }

    @Override
    public void delete(String clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public boolean exists(String clubId) {
        return clubRepository.existsById(clubId);
    }
}
