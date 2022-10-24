package shjgroup.travelclubcoregrd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shjgroup.travelclubcoregrd.service.logic.ClubServiceLogic;
import shjgroup.travelclubcoregrd.service.logic.MemberServiceLogic;
import shjgroup.travelclubcoregrd.store.ClubStore;
import shjgroup.travelclubcoregrd.store.MemberStore;
import shjgroup.travelclubcoregrd.store.mapstore.ClubMapStore;
import shjgroup.travelclubcoregrd.store.mapstore.MemberMapStore;

@Configuration
public class SpringConfig {
//    @Bean  // 스프링 빈을 내가 직접 등록할거야 라는 의미이다.
//    public ClubServiceLogic clubServiceLogic() {
//        return new ClubServiceLogic(clubStore());
//    }
//
//    @Bean
//    public ClubStore clubStore() {
//        return new ClubMapStore();
//    }
//
//
//    @Bean  // 스프링 빈을 내가 직접 등록할거야 라는 의미이다.
//    public MemberServiceLogic memberServiceLogic() {
//        return new MemberServiceLogic(memberStore());
//    }
//
//    @Bean
//    public MemberStore memberStore() {
//        return new MemberMapStore();
//    }


//    // ClubRepository 덕분에 알아서 구현체가 빈에 등록되어있어 @Autowired로 의존관계 형성 가능해져서 인젝션 받음.
//    // 단, 이는 ClubJpaStore 클래스에 @Repository를 떼어버려서 빈등록이 안되었을경우, 그리고 동시에 ClubRepository 인터페이스에 JpaRepository<TravelClubJpo, String>, ClubStore 라고 extends를 적었을 경우, 이 두가지를 모두 만족할때 쓰일수 있는 예시를 보충 설명으로 말하는 것이다.
//    @Autowired  // @Autowired 생략 가능하긴함.
//    public SpringConfig(ClubStore clubStore) {
//        this.clubstore = clubStore;
//    }
}
