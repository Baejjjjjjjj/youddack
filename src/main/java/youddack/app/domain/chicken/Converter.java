package youddack.app.domain.chicken;

import youddack.app.domain.chicken.domain.*;
import youddack.app.domain.chicken.dto.ResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static ResponseDto.ChickenDetailDto toChickenDetailDto(Chicken chicken, List<Category> categoryList, List<Flavor> flavorList){

        return ResponseDto.ChickenDetailDto.builder()
                .chicken_id(chicken.getId())
                .chicken_allergy(chicken.getAllergy())
                .chicken_capacity(chicken.getCapacity().toString()+"호")
                .chicken_name(chicken.getName())
                .chicken_part(chicken.getPart())
                .chicken_price(chicken.getPrice())
                .chicken_description(chicken.getDescription())
                .chicken_image_url(chicken.getImage_url())
                .brand(chicken.getBrand())
                .categoryList(categoryList)
                .flavorList(flavorList)
                .build();
    }


    //public static List<ResponseDto.ChickenDto> toChickenDtoList(List<Ch>)

    public static ResponseDto.ListChickenDetailDto toChickenDetailListDto (List<ResponseDto.ChickenDetailDto> chickenDtos){

        return ResponseDto.ListChickenDetailDto.builder()
                .chickenDto(chickenDtos)
                .build();
    }

    public static ResponseDto.ChickenDto toChickenDto(Chicken chicken, List<Flavor> flavor, Long rank_id){

        return ResponseDto.ChickenDto.builder()
                .chicken_id(chicken.getId())
                .rank_number(rank_id)
                .chicken_price(chicken.getPrice())
                .chicken_name(chicken.getName())
                .chicken_image_url(chicken.getImage_url())
                .flavor(flavor)
                .brand_id(chicken.getBrand().getId())
                .brand_name(chicken.getBrand().getName())
                .build();
    }


    public static ResponseDto.ListChickenDto toChickenListDto(List<ResponseDto.ChickenDto> chickenDtoList){

        return ResponseDto.ListChickenDto.builder()
                .chickenDto(chickenDtoList)
                .build();
    }

    public static ResponseDto.BrandListChickenDto toBrandListChicken(ResponseDto.ListChickenDto listChickenDto, Brand brand){

        return ResponseDto.BrandListChickenDto.builder()
                .brand(brand)
                .chickenDto(listChickenDto)
                .build();
    }

    public static ResponseDto.ChickenRecommendDto toChickenRecommendDto(Question question, Answer answer1, Answer answer2){

        return ResponseDto.ChickenRecommendDto.builder()
                .question_id(question.getId())
                .question_text(question.getQuestion_text())
                .answer1_id(answer1.getId())
                .answer1_text(answer1.getAnswer_text())
                .answer2_id(answer2.getId())
                .answer2_text(answer2.getAnswer_text())
                .build();

    }

    public static ResponseDto.ListChickenRecommendDto toChickenRecommendListDto(List<ResponseDto.ChickenRecommendDto> chickenRecommendDto){

        return ResponseDto.ListChickenRecommendDto.builder()
                .chickenRecommendDto(chickenRecommendDto)
                .build();
    }

    public static ResponseDto.RecommendChickenTypeDto toRecommendChickenTypeDto(Recommendation recommendation, List<RecommendationDescription> recommendationDescriptionList){

        return ResponseDto.RecommendChickenTypeDto.builder()
                .chicken_type_id(recommendation.getId())
                .chicken_type_name(recommendation.getName())
                .chicken_type_image_url(recommendation.getImage_url())
                .recommendationDescriptions(recommendationDescriptionList)
                .recommend_chicken1_name(recommendation.getRecommend_chicken1_name())
                .recommend_chicken1_image_url(recommendation.getRecommend_chicken1_image_url())
                .recommend_chicken2_name(recommendation.getRecommend_chicken2_name())
                .recommend_chicken2_image_url(recommendation.getRecommend_chicken2_image_url())
                .recommend_chicken3_name(recommendation.getRecommend_chicken3_name())
                .recommend_chicken3_image_url(recommendation.getRecommend_chicken3_image_url())
                .build();
    }
}
