package it.unicam.coloni.hackhub.shared.domain.models;



import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import java.time.LocalDateTime;


@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime deletedAt;

    @Column
    private LocalDateTime modifiedAt;






}
