package nettee.auth.rdb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nettee.jpa.support.SnowflakeBaseTimeEntity;

@Entity
@Table(schema = "auth", name = "user")
@Builder
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
    private Integer loginRetryCount;

    @Column(name = "locked_until")
    private Instant lockedUntil;
}