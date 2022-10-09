package shjgroup.travelclubcoregrd;

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


//    @Bean  // 스프링 빈을 내가 직접 등록할거야 라는 의미이다.
//    public MemberServiceLogic memberServiceLogic() {
//        return new MemberServiceLogic(memberStore());
//    }
//
//    @Bean
//    public MemberStore memberStore() {
//        return new MemberMapStore();
//    }
}
