package shjgroup.travelclubcoregrd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import shjgroup.travelclubcoregrd.service.ClubService;
import shjgroup.travelclubcoregrd.service.logic.ClubServiceLogic;
import shjgroup.travelclubcoregrd.service.sdo.TravelClubCdo;
import shjgroup.travelclubcoregrd.store.ClubStore;
import shjgroup.travelclubcoregrd.store.mapstore.ClubMapStore;

public class TravelClubApp {

    @Test
    public static void main(String[] args) {

    }


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);


    // SpringConfig에 직접 Bean 등록 방법 사용시, 이미 등록되어있는 Bean들 목록 출력 코드
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); //타입이 지정이안되어있어서 오브젝트로 꺼낸다.
            System.out.println("name = " + beanDefinitionName + " Object = "+ bean);
        }
    }
    // SpringConfig에 직접 Bean 등록 방법 사용시, 이미 등록되어있는 Bean들 목록 출력 코드
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =  ac.getBeanDefinition(beanDefinitionName);

            //등록한 애플리케이션만 출력할 수 있도록 해준다.
            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName); //타입이 지정이안되어있어서 오브젝트로 꺼낸다.
                System.out.println("name = " + beanDefinitionName + " Object = "+ bean);
            }
        }
    }


    @Test
    void NewClubTest_springconfigDI() {  // SpringConfig 클래스에 직접 빈 등록방법으로 Bean 등록하여 DI 형성하였을때, 사용가능한 새로운 Club 등록 테스트 코드
        TravelClubCdo clubCdo = new TravelClubCdo("FirstTravelClub", "Test TravelClub");
        // 이제 밑의코드줄부터 ClubService를 통해서 객체를 넘겨주어야한다.
        // ClubServiceLogic 클래스의 registerClub 메소드를 호출하기위해 Service 관련 Bean을 먼저 스프링컨테이너에서 찾아와야한다.
        ClubService clubService = ac.getBean("clubServiceLogic", ClubService.class);  // getBean의 매개변수: ("등록해둔 Bean 이름", 찾아오는 타입)
        // 보다시피 ClubServiceLogic 클래스를 직접사용하지않고, ClubService 인터페이스를 사용함으로써 느슨한 결합을 유지해준다. 즉, 인터페이스를 사이에 두고 의존관계를 형성한것이다.
        String clubId = clubService.registerClub(clubCdo);
        // 과정: TravelCdo객체 생성하여 값넣어서, 새로 등록될 순수 필드값정보만 담고있는 Cdo 데이터 전달
        // -> 스프링컨테이너에 이미 등록되어있는 Service 빈을 갖고와서 Store 과의 의존관계 확인
        // -> ClubServiceLogic 클래스의 인스턴스 객체인 clubService로 registerClub 메소드를 실행하여, Cdo객체에 등록된 순수한 필드값 정보를 넘겨준다
        // -> registerClub 메소드 안에서 TravelClub 클래스의 TravelClub(String name, String intro)로 객체생성할때 Cdo 필드정보가 넘어가고
        // -> 결국 TravelClubCdo 순수 필드 정보가 새로운 TravelClub을 생성하는 과정에 필드정보가 넘어가서, TravelClub 클래스로 새로운 Club 등록 역할을 하게됨.
        // 여기서 주의할점은 create 될때 필요한 데이터(필드)들을 별도의 domain object로 나누어 적어둔것이 Cdo라는 점을 잊지말아야한다.
        // 즉 전체적인 과정은, TravelCdo의 순수 멤버필드정보가 TravelClub으로 새로운 Club의 정보를 생성하는데 기여하고, 그렇게 생성된 정보객체가 Store에 create 메소드로 저장을 요청한다.
        // 과정 정리: TravelCdo의 순수 멤버필드정보 --registerClub 메소드에 대입해서 서비스 실행--> TravelClub으로 Club 정보 생성 -> Store의 create 메소드로 Map에 저장후 해당 Club 정보를 데이터베이스에 저장하길 요청
        // 위 과정 속의 구조 정리:
        // [일반 과정] TravelCdo의 순수 멤버필드정보
        // [서비스 구조 과정] --registerClub 메소드에 대입해서 서비스 실행--> TravelClub으로 Club 정보 생성 ->
        // [스토어 구조로 데이터베이스접근 과정] Store의 create 메소드로 Map에 저장후 해당 Club 정보를 데이터베이스에 저장하길 요청
        // 마지막 과정으로, Store의 create 메소드가 실행되고 id를 리턴return해줌. 그러면 그 id값을 clubId 변수에 할당함.
        System.out.println("Test에서 등록될 새로운 TravelClub의 ID : " + clubId);
    }


    @Test
    void NewClubTest_annotationDI() {  // Gradle에서 SpringConfig 클래스 없이 @Repository, @Service, @Autowired 로 Bean 등록하여 DI 형성하였을때, 사용가능한 새로운 Club 등록 테스트 코드
                                       // 근데 이건 내가 직접 만든 코드라 약간 불확실함. 테스트 메소드 정상실행되긴함.
        TravelClubCdo clubCdo = new TravelClubCdo("FirstTravelClub", "Test TravelClub");
        ClubStore clubStore = new ClubMapStore();
        ClubService clubService = new ClubServiceLogic(clubStore);
        String clubId = clubService.registerClub(clubCdo);
        System.out.println("Test에서 등록될 새로운 TravelClub의 ID : " + clubId);
    }
}
