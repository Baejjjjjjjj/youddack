package youddack.app.domain.chicken.domain;

import jakarta.persistence.*;
import lombok.*;
import youddack.app.domain.chicken.domain.common.BaseEntity;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Chicken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image_url;

    @Column(length = 200)
    private String name;

    private String description;

    private String allergy;

    private String capacity;

    private String part;

    private Integer price;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;


}
