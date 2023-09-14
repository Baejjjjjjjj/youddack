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
    public static class ChickenDetailDto {
        private Long chicken_id;

        private String chicken_image_url;

        private String chicken_name;

        private String chicken_description;

        private String chicken_allergy;

        private String chicken_capacity;

        private String chicken_part;

        private Integer chicken_price;

        private Brand brand;

        private List<Category> categoryList;

        private List<Flavor> flavorList;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ListChickenDetailDto{

        private List<ChickenDetailDto> chickenDto;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ChickenDto{

        private Long chicken_id;

        private Integer chicken_price;

        private String chicken_name;

        private String chicken_image_url;

        private List<Flavor> flavor;

        private Long brand_id;

        private String brand_name;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ListChickenDto{

        private List<ChickenDto> chickenDto;

    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class BrandListChickenDto{

        private ListChickenDto chickenDto;

        private Brand brand;
    }
}
