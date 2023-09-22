package youddack.app.domain.chicken.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class RequestDto {

    @Schema(description = "Chicken 리스트 조회 Dto")
    @Getter
    @Setter
    public static class FindChickenDto{



        @Schema(description = "chicken_id", defaultValue = "0")
        @Builder.Default
        @NotEmpty
        private Long chicken_id =Long.valueOf(0);

        @Schema(description = "sort_id", defaultValue = "1")
        @Builder.Default
        private Integer sort_id = 0;

        @Schema(description = "brand_id", defaultValue = "0")
        @Builder.Default
        private Long brand_id = Long.valueOf(0);

        @Schema(description = "chicken_id", defaultValue = " ")
        @Builder.Default
        private String chicken_name="";

        @Schema(description = "category_id", defaultValue = " ")
        @Builder.Default
        private String category_name="";

        @Schema(description = "part_name", defaultValue = " ")
        @Builder.Default
        private String part_name="";

        @Builder.Default
        @Schema(defaultValue = "", example = "")
        @Size(min=1, max = 3)
        private List<String> flavor= Arrays.asList(new String[]{""});

        @Size(min = 0, max = 20000)
        @Schema(defaultValue = "0",example = "0")
        @Builder.Default
        private Integer start_price=-1;

        @Size(min= 5000, max = 50000)
        @Schema(defaultValue = "40000", example = "40000")
        @Builder.Default
        private Integer end_price=0;

        public void ChickenIdDto(){
            this.chicken_id = Long.valueOf(0);
        }
        public void FindChickenDto(){
            this.sort_id = 0;
        }
    }
}
