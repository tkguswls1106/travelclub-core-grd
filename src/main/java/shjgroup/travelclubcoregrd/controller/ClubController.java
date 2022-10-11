package shjgroup.travelclubcoregrd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shjgroup.travelclubcoregrd.aggregate.club.TravelClub;
import shjgroup.travelclubcoregrd.service.ClubService;
import shjgroup.travelclubcoregrd.service.sdo.TravelClubCdo;
import shjgroup.travelclubcoregrd.shared.NameValueList;

import java.util.List;

@RestController  // 데이터가 담겨오는 방식은 @RequestParam("") 사용해서 url주소에 따라오느냐, 아니면 @RequestBody를 사용해서 http request의 body에 담겨오느냐인데, @RestController 이므로, json 사용으로 @RequestBody를 적어주자.
// html 같은 뷰파일없이 바로 json으로 데이터를 보내 출력할거면, @Controller와 @ResponseBody가 결합된 @RestController 어노테이션을 컨트롤러 클래스 위에 적어두고,
// html 같은 뷰파일 사용시 컨트롤러 클래스 위에 @Controller 어노테이션을 적어둔다.
@RequestMapping("/club")
// 해당 컨트롤러 클래스에 url 시작부분이 공통적으로 시작된다면 (예를들어 해당 컨트롤러 클래스 내의 모든 메소드들 맵핑 url이 "/club" 으로 시작된다면),
// 해당 컨트롤러 클래스에 @RequestMapping 어노테이션을 사용해줌으로써, @RequestMapping("/club")을 사용하면 모든 메소드 url 앞에 모두 "/club" 을 생략시켜주어도 된다.
// 즉, 시작부분 공통 맵핑 url 부분을 선언 해준것이다.
public class ClubController {  // ClubServiceLogic과 연관된 메소드들의 컨트롤러 버전의 메소드를 구현하면 된다.  // TravelClub 클래스내의 샘플데이터와 Gson을 이용하여, Insomnia 프로그램으로 데이터 입력하고 정상작동되는지 실험해보자.

    // ClubController --ClubService 인터페이스를 사이에 두고 느슨한 결합--> ClubServiceLogic --ClubStore 인터페이스를 사이에 두고 느슨한 결합--> ClubMapStore
    private ClubService clubService;
    @Autowired
    public ClubController(ClubService clubService) {  // 인터페이스 사이에 두고 느슨한 결합 생성자 주입 DI
        this.clubService = clubService;
    }

    @PostMapping // 등록하는 컨트롤러 메소드이므로, POST방식으로 데이터를 받아와야 가능한 메소드이다.  // http://localhost:8080/club
    // 컨트롤러 클래스의 어노테이션 @RequestMapping("/club") 덕분에, 메소드에 @PostMapping("/club") 대신 @PostMapping만 적어주어도 된다.
    public String register(@RequestBody TravelClubCdo travelClubCdo) {  // 클라이언트로부터 요청이 올때, TravelClubCdo 데이터가 오며 가장 먼저 컨트롤러로 위임될것이고,
                                                           // TravelClubCdo의 객체를 파라미터로 데이터로 받는다면
        return clubService.registerClub(travelClubCdo);  // Controller 레이어에서 Service 레이어로 전달
                                                         // (추후에 서비스에서 clubStore.create() 메소드를 호출하며, Store 레이어로 전달하면, 최종적으로 DB에 등록 완료.)
                                                         // 즉, 레이어 전달 과정이 클라이언트요청->Controller->Service->Store 인것이다.
    }

    @GetMapping("/all")  // http://localhost:8080/club/all
    // 컨트롤러 클래스의 어노테이션 @RequestMapping("/club") 덕분에, 메소드에 @GetMapping("/club/all") 대신 @GetMapping("/all")만 적어주어도 된다.
    public List<TravelClub> findAll() {  // 전체 클럽 조회
        return clubService.findAll();
    }

    @GetMapping("/{clubId}")  // http://localhost:8080/club/{clubId}
    // 컨트롤러 클래스의 어노테이션 @RequestMapping("/club") 덕분에, 메소드에 @GetMapping("/club/{clubId}") 대신 @GetMapping("/{clubId}")만 적어주어도 된다.
    public TravelClub find(@PathVariable String clubId) {  // @RequestParam은 url이 /club?name=hihihi 이런식인 반면에, @PathVariable은 /club/1234 이런식이다.
        return clubService.findClubById(clubId);
    }

    @GetMapping  // http://localhost:8080/club?name=JavaClub
    // 컨트롤러 클래스의 어노테이션 @RequestMapping("/club") 덕분에, 메소드에 @GetMapping("/club") 대신 @GetMapping만 적어주어도 된다.
    public List<TravelClub> findByName(@RequestParam String name) {
        // System.out.println(name);
        return clubService.findClubsByName(name);
    }

    @PutMapping("/{clubId}")  // 수정 용도  // http://localhost:8080/club/{clubId}
    // 컨트롤러 클래스의 어노테이션 @RequestMapping("/club") 덕분에, 메소드에 @PutMapping("/club/{clubId}") 대신 @PutMapping("/{clubId}")만 적어주어도 된다.
    public void modify(@PathVariable String clubId, @RequestBody NameValueList nameValueList) {  // nameValueList는 @PutMapping 어노테이션이 http body에다가 데이터를 받아 넣어준다.
                                                                                                 // 그러므로 PostMapping 방식떼처럼 @RequestBody를 사용해주면 된다.
        clubService.modify(clubId, nameValueList);
    }

    @DeleteMapping("/{clubId}")  // http://localhost:8080/club/{clubId}
    // 컨트롤러 클래스의 어노테이션 @RequestMapping("/club") 덕분에, 메소드에 @DeleteMapping("/club/{clubId}") 대신 @DeleteMapping("/{clubId}")만 적어주어도 된다.
    public void delete(@PathVariable String clubId) {
        clubService.remove(clubId);
    }
}