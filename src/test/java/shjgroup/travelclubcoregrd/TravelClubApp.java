package shjgroup.travelclubcoregrd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TravelClubApp {

    @Test
    public static void main(String[] args) {

    }



    // 빈 출력
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); //타입이 지정이안되어있어서 오브젝트로 꺼낸다.
            System.out.println("name = " + beanDefinitionName + " Object = "+ bean);
        }
    }
    // 빈 출력
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


}
