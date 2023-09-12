package youddack.app.domain.chicken.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import youddack.app.domain.chicken.domain.Brand;
import youddack.app.domain.chicken.domain.Category;
import youddack.app.domain.chicken.domain.Flavor;

import java.util.List;

public class ResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ChickenDto {
        private Long id;

        private String image_url;

        private String name;

        private String description;

        private String allergy;

        private String capacity;

        private String part;

        private Integer price;

        private Brand brand;

        private List<Category> categoryList;

        private List<Flavor> flavorList;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ListChickenDto{

        private List<ChickenDto> chickenDto;

    }
}
