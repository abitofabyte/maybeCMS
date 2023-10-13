package yes.no.maybeCMS.entities.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true, nullable = false)
    @NotNull
    @Size(min = 3, max = 255)
    private String handle;
    @Column(unique = true, nullable = false)
    @NotNull
    @Email
    private String email;
    @Column(nullable = false)
    private LocalDateTime lastLogin;
    private boolean isDisabled = false;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}