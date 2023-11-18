package azaza.lawkick;

import azaza.lawkick.domain.Member;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//초기 테스트를 위해 그냥 초기 멤버 데이터 집어넣었어요
//rds 연결하면 지울게요
@Profile("local")
@Component
@RequiredArgsConstructor
public class InitData {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }
    @Component
    static class InitService {

        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
            Member member1 = Member.builder()
                    .email("kiminha@naver.com")
                    .password("inha1!")
                    .nickName("김인하")
                    .mileage(30L)
                    .build();

            Member member2 = Member.builder()
                    .email("test1@naver.com")
                    .password("test1")
                    .nickName("테스터1")
                    .mileage(1000L)
                    .build();

            Member member3 = Member.builder()
                    .email("test2@naver.com")
                    .password("test2")
                    .nickName("테스터2")
                    .mileage(2000L)
                    .build();

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

        }
    }
}