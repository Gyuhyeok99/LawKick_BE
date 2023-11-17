package azaza.lawkick.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;


@NoArgsConstructor
@Data
public class MyPageRes {

    Map<String, Object> myPage;

    public MyPageRes(Map<String, Object> myPage) {
        this.myPage = myPage;
    }



}
