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

public class RequestDto {

    @Schema(description = "Chicken 리스트 조회 Dto")
    @Getter
    @Setter
    public static class FindChickenDto{



        @Builder.Default
        @NotEmpty
        private Long chicken_id =Long.valueOf(0);

        @Builder.Default
        private Integer sort_id = 0;

        @Builder.Default
        private Long brand_id = Long.valueOf(0);


        @Schema(defaultValue = " ")
        @Builder.Default
        private String category_name="";

        @Schema(defaultValue = " ")
        @Builder.Default
        private String part_name="";

        @Builder.Default
        @Schema(defaultValue = "")
        @Size(min=1, max = 3)
        private List<String> flavor= Arrays.asList(new String[]{""});

        @Size(min = 0, max = 20000)
        @Schema(defaultValue = "0")
        @Builder.Default
        private Integer start_price=-1;

        @Size(min= 5000, max = 50000)
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
