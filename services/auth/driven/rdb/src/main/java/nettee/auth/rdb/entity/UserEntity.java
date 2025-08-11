package nettee.auth.rdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

@Entity
@Table(schema = "auth", name = "user")
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends SnowflakeBaseTimeEntity {

    @Column(unique = true)
    private String loginId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String email;

    @Column(name = "encoded_password")
    private String encodedPassword;

    @Column(name = "login_retry_count")
    private Integer loginRetryCount = 0;

    @Column(name = "locked_until")
    private Instant lockedUntil;

    @Column(name = "status")
    private UserEntityStatus status;
}