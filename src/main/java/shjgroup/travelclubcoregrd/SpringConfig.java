package shjgroup.travelclubcoregrd;

import org.springframework.context.annotation.Bean;
import shjgroup.travelclubcoregrd.service.logic.ClubServiceLogic;
import shjgroup.travelclubcoregrd.store.ClubStore;
import shjgroup.travelclubcoregrd.store.mapstore.ClubMapStore;

public class SpringConfig {
    @Bean  // 스프링 빈을 내가 직접 등록할거야 라는 의미이다.
    public ClubServiceLogic clubServiceLogic() {
        return new ClubServiceLogic(clubStore());
    }

    @Bean
    public ClubStore clubStore() {
        return new ClubMapStore();
    }
}
