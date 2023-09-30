package youddack.app.domain.chicken.domain;

import jakarta.persistence.*;
import lombok.*;
import youddack.app.config.BaseResponse;
import youddack.app.domain.chicken.domain.common.BaseEntity;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "recommendation")
public class Recommendation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 1000)
    private String image_url;

    @OneToOne
    @JoinColumn(name = "brand_id")
    private Brand recommend_brand;

    private String recommend_chicken1_name;
    @Column(length = 1000)
    private String recommend_chicken1_image_url;

    private String recommend_chicken2_name;
    @Column(length = 1000)
    private String recommend_chicken2_image_url;

    private String recommend_chicken3_name;
    @Column(length = 1000)
    private String recommend_chicken3_image_url;




}
