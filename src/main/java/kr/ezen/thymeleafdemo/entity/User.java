package kr.ezen.thymeleafdemo.entity;



import kr.ezen.thymeleafdemo.type.member.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data // @Getter. @Setter, @ToString, @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    private String id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp // 현재 시간이 기본값으로 등록되도록 설정
    private Timestamp createDate;

    @Column(name = "user_uuid", nullable = false)
    private String userUUID;



    @PrePersist
    public void generateUUID() {
        if (userUUID == null) {
            userUUID = UUID.randomUUID().toString();
        }
    }


}
