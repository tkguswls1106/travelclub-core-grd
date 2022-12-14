package shjgroup.travelclubcoregrd.aggregate.club;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shjgroup.travelclubcoregrd.aggregate.Entity;
import shjgroup.travelclubcoregrd.shared.NameValue;
import shjgroup.travelclubcoregrd.shared.NameValueList;

@Getter
@Setter
@NoArgsConstructor  // @NoArgsConstructor 어노테이션은 파라미터(매개변수)가 없는 기본 생성자 메소드를 생성해준다. TravelClubJpo(){} 메소드 말이다.
public class TravelClub extends Entity {
    //
    private static final int MINIMUM_NAME_LENGTH =  3;
    private static final int MINIMUM_INTRO_LENGTH =  10;

    private String name;
    private String intro;
    private long foundationTime;

    public TravelClub(String id) {
        //
        super(id);
    }

    public TravelClub(String name, String intro) {  // 서비스 클래스에서 register할때 TravelClub()안의 매개변수로는 name과 intro 관련만 넣어주면 된다.
        //
        super();
        this.name = name;
        this.intro = intro;
        this.foundationTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();

        builder.append("Travel Club Id:").append(id);
        builder.append(", name:").append(name);
        builder.append(", intro:").append(intro);
        builder.append(", foundation day:").append(foundationTime);

        return builder.toString();
    }

    public void checkValidation() {
        //
        checkNameValidation(name);
        checkIntroValidation(intro);
    }

    private void checkNameValidation(String name) {
        //
        if (name.length() < TravelClub.MINIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException("\t > Name should be longer than " + TravelClub.MINIMUM_NAME_LENGTH);
        }
    }

    private void checkIntroValidation(String intro) {
        //
        if (intro.length() < TravelClub.MINIMUM_INTRO_LENGTH) {
            throw new IllegalArgumentException("\t > Intro should be longer than " + TravelClub.MINIMUM_INTRO_LENGTH);
        }
    }

    public void modifyValues(NameValueList nameValues) {  // 객체 필드 내부값 수정 메소드를 호출하여, 데이터베이스가 아닌 먼저 객체의 정보를 수정해준다.
        //
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    checkNameValidation(value);
                    this.name = value;
                    break;
                case "intro":
                    checkIntroValidation(value);
                    this.intro = value;
                    break;
            }
        }
    }

    public static TravelClub sample() {
        //
        String name = "JavaTravelClub";
        String intro = "Travel club to the Java island.";

        return new TravelClub(name, intro);
    }

    public static void main(String[] args) {
        //
        // System.out.println(sample().toString());
        System.out.println(new Gson().toJson(sample()));

        NameValueList list = new NameValueList();
        list.addNameValue("name", "Change Club name~~");  // name="name", value="Change Club name~~"
        list.addNameValue("intro", "Change Club intro~~");  // name="intro", value="Change Club intro~~"
        System.out.println(new Gson().toJson(list));
    }
}