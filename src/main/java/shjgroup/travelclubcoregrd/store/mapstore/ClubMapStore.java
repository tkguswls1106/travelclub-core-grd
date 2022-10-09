package shjgroup.travelclubcoregrd.store.mapstore;

import org.springframework.stereotype.Repository;
import shjgroup.travelclubcoregrd.aggregate.club.TravelClub;
import shjgroup.travelclubcoregrd.store.ClubStore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClubMapStore implements ClubStore {  // 엔티티 layer(계층)에서의 TravelClub 클래스 클럽정보를 저장할 저장소 구현 (ClubStore 인터페이스로 구현함)

    private Map<String, TravelClub> clubMap;  // 키 자료형: String, 값 자료형: TravelClub  // 키: 객체의id(clubId), 값: club객체(club)

    public ClubMapStore() {
        this.clubMap = new LinkedHashMap<>();  // LinkedHashMap<> 이라서 put,get 등등 메소드 사용가능.
    }

    @Override
    public String create(TravelClub club) {  // 만들 TravelClub의 club객체를값해쉬맵에 put해서 ClubMapStore 저장소에 저장함.
        clubMap.put(club.getId(), club);  // 키: 객체의id, 값: club객체  // Entity 클래스의 Getter 어노테이션덕에 getId 사용가능.
        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {  // id값으로 하나의 club객체를 찾는(검색하는) 용도이다.
        return clubMap.get(clubId);
    }  // 주의사항: 이 메소드는 clubMap의 키값중 키인 id로 검색하기때문에 람다스트림이나 for문같은 반복문 없이 사용 가능하지만,
       // 만약 clubMap의 키값중 값인 TravelClub의 필드정보로 검색해야하는 메소드라면 람다스트림이나 for문같은 반복문을 사용하여 검색해야한다.

    @Override
    public List<TravelClub> retrieveByName(String name) {  // name값으로, 동일한 이름의 club객체들까지를 모두 찾아 리스트로 묶어 반환해줌.
        return clubMap.values().stream()  // clubMap 키값에서 값인 club객체를 데이터를 반복문처럼 돌려 확인하겠다는 의미이다.
                .filter(club -> club.getName().equals(name))  // 반복문으로 돌린 club 객체의 getName으로 갖고온 name이 retrieveByName(String name)의 name과 동일힐때 해당 객체를 반환해줌.
                .collect(Collectors.toList());  // 반환된 club객체들을 모두 묶어 리스트형태로 만듦. 그렇게 만든 리스트를 최종적으로 반환함.
    }  // 주의사항: 이 메소드는 clubMap의 키값중 값인 TravelClub의 필드정보로 검색해야하는 메소드이므로, 람다스트림이나 for문같은 반복문을 사용해야하며,
       // 메소드 반환자료형이 List<TravelClub> 이므로, 중복허용하여 중복목록을 리스트로 묶어 반환해준다.
       // 만약 중복허용이 아니라면 중복들을 리스트로 묶을필요가 없으므로, 메소드 반환자료형이 List<TravelClub>가 아닌, TravelClub 이었을것이다.

    @Override
    public void update(TravelClub club) {  // 업데이트(수정) 용도인데, 참고로 매개변수 TravelClub club 객체 정보는 업데이트 된 데이터로 넘어오는것이다.
        clubMap.put(club.getId(), club);  // Map<String, TravelClub>
    }

    @Override
    public void delete(String clubId) {  // club객체를 하나 삭제하는 용도이다.
        clubMap.remove(clubId);
    }

    @Override
    public boolean exists(String clubId) {  // store 저장소에 해당 clubId를 가지고있는지 확인하는 용도이다.
        return clubMap.containsKey(clubId);  // 키값 존재여부 확인해서 true나 false로 반환함. 이것과 마찬가지로 밑의 예시도 boolean 형태로 반환함.
        // return Optional.ofNullable(clubMap.get(clubId)).isPresent(); 위대신 이거 사용해도됨.
    }
}