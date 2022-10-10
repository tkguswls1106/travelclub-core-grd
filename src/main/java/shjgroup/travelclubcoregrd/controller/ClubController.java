package shjgroup.travelclubcoregrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shjgroup.travelclubcoregrd.service.ClubService;
import shjgroup.travelclubcoregrd.service.sdo.TravelClubCdo;

@RestController  // 데이터가 담겨오는 방식은 @RequestParam("") 사용해서 url주소에 따라오느냐, 아니면 @RequestBody를 사용해서 http request의 body에 담겨오느냐인데, @RestController 이므로, json 사용으로 @RequestBody를 적어주자.
// html 같은 뷰파일없이 바로 json으로 데이터를 보내 출력할거면, @Controller와 @ResponseBody가 결합된 @RestController 어노테이션을 컨트롤러 클래스 위에 적어두고,
// html 같은 뷰파일 사용시 컨트롤러 클래스 위에 @Controller 어노테이션을 적어둔다.
public class ClubController {

    // ClubController --ClubService 인터페이스를 사이에 두고 느슨한 결합--> ClubServiceLogic --ClubStore 인터페이스를 사이에 두고 느슨한 결합--> ClubMapStore
    private ClubService clubService;
    @Autowired
    public ClubController(ClubService clubService) {  // 인터페이스 사이에 두고 느슨한 결합 생성자 주입 DI
        this.clubService = clubService;
    }

    @PostMapping("/club") // 등록하는 컨트롤러 메소드이므로, POST방식으로 데이터를 받아와야 가능한 메소드이다.
    public String register(@RequestBody TravelClubCdo travelClubCdo) {  // 클라이언트로부터 요청이 올때, TravelClubCdo 데이터가 오며 가장 먼저 컨트롤러로 위임될것이고,
                                                           // TravelClubCdo의 객체를 파라미터로 데이터로 받는다면
        return clubService.registerClub(travelClubCdo);  // Controller 레이어에서 Service 레이어로 전달
                                                         // (추후에 서비스에서 clubStore.create() 메소드를 호출하며, Store 레이어로 전달하면, 최종적으로 DB에 등록 완료.)
                                                         // 즉, 레이어 전달 과정이 클라이언트요청->Controller->Service->Store 인것이다.
    }

}