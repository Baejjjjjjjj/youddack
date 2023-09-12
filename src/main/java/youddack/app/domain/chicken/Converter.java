package youddack.app.domain.chicken;

import youddack.app.domain.chicken.domain.Brand;
import youddack.app.domain.chicken.domain.Category;
import youddack.app.domain.chicken.domain.Chicken;
import youddack.app.domain.chicken.domain.Flavor;
import youddack.app.domain.chicken.dto.ResponseDto;

import java.util.List;

public class Converter {

    public static ResponseDto.ChickenDto toShowChickenDto(Chicken chicken, List<Category> categoryList, List<Flavor> flavorList){

        return ResponseDto.ChickenDto.builder()
                .id(chicken.getId())
                .allergy(chicken.getAllergy())
                .capacity(chicken.getCapacity())
                .name(chicken.getName())
                .part(chicken.getPart())
                .price(chicken.getPrice())
                .description(chicken.getDescription())
                .image_url(chicken.getImage_url())
                .brand(chicken.getBrand())
                .categoryList(categoryList)
                .flavorList(flavorList)
                .build();
    }



}
