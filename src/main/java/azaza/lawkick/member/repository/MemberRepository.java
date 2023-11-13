package azaza.lawkick.member.repository;

import azaza.lawkick.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
