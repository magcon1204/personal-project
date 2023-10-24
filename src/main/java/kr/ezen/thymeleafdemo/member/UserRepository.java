package kr.ezen.thymeleafdemo.member;

import kr.ezen.thymeleafdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    // SQL => JPQL => 쿼리메서드
    // find + 엔티티명 + By + 변수명 ==> findUserByUsername
    // 엔티티명은 생략가능 : findByUsername

    // SELECT * FROM users WHERE username = ?1;
    Optional<User> findById(String id);


    User findByUsername(String userName);
}
