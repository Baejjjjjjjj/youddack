package youddack.app.domain.chicken.domain;

import jakarta.persistence.*;
import lombok.*;
import youddack.app.domain.chicken.domain.common.BaseEntity;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "brand")
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Byte brand_rank;

    @Column(length = 100)
    private String name;

    private String image_url;

    private String logo_image_url;


}
