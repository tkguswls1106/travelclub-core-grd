package shjgroup.travelclubcoregrd.service.logic;

import shjgroup.travelclubcoregrd.aggregate.club.TravelClub;
import shjgroup.travelclubcoregrd.service.ClubService;
import shjgroup.travelclubcoregrd.service.sdo.TravelClubCdo;
import shjgroup.travelclubcoregrd.shared.NameValueList;
import shjgroup.travelclubcoregrd.store.ClubStore;

import java.util.List;

public class ClubServiceLogic implements ClubService {

    private ClubStore clubStore;  // ClubStore 인터페이스 타입의 필드(변수) 선언.

    public ClubServiceLogic(ClubStore clubStore) {  // ClubServiceLogic 은 ClubMapStore 을 알아야하는 관계이기때문에 이렇게 적어준다.
        this.clubStore = clubStore;
        // 아마 이렇게 적은 이유는, ClubServiceLogic과 ClubMapStore은 ClubStore 인터페이스를 사이에 두고 느슨한 결합을 유지해야하기때문에, DI에 ClubStore 인터페이스를 사용한것같다.
        // 이렇게하면, 장점은 ClubStore 인터페이스를 사이에 두고있어, ClubServiceLogic 입장에서는 ClubStore 인터페이스를 참조하고있는 객체가 ClubMapStore던지 아니면 다른 추후에 변경된 DB든지간에 알아서 DI주입이 될테니 상관없어진다는 것이다.
        // 즉, 나중에 DB 종류를 변경할때 다른 코드는 건드리지않고 그대로 써도 되기때문에 편해진다.
    }

    @Override
    public String registerClub(TravelClubCdo club) {  // Club 등록 용도이다.
//        clubStore.create();  // clubStore 객체는 ClubMapStore의 인스턴스이때문에 이렇게 create를 호출해서 데이터를 넘겨줘서 사용할것이다.
        return null;
    }

    @Override
    public TravelClub findClubById(String id) {
        return null;
    }

    @Override
    public List<TravelClub> findClubsByName(String name) {
        return null;
    }

    @Override
    public void modify(String clubId, NameValueList nameValues) {

    }

    @Override
    public void remove(String clubId) {

    }
}