package youddack.app.domain.chicken.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "recommendationDescription")
public class RecommendationDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    private String description;

    @ManyToOne
    @JoinColumn(name = "recommendation_id")
    private Recommendation recommendation;
}
