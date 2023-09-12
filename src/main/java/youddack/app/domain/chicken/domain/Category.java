package youddack.app.domain.chicken.domain;

import jakarta.persistence.*;
import lombok.*;
import youddack.app.domain.chicken.domain.common.BaseEntity;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "category")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

}
