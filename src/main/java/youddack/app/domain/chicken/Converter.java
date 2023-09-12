package youddack.app.domain.chicken;

import youddack.app.domain.chicken.domain.Brand;
import youddack.app.domain.chicken.domain.Category;
import youddack.app.domain.chicken.domain.Chicken;
import youddack.app.domain.chicken.domain.Flavor;
import youddack.app.domain.chicken.dto.ResponseDto;

import java.util.List;

public class Converter {

    public static ResponseDto.ChickenDto toChickenDto(Chicken chicken, List<Category> categoryList, List<Flavor> flavorList){

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


    //public static List<ResponseDto.ChickenDto> toChickenDtoList(List<Ch>)

    public static ResponseDto.ListChickenDto toChickenListDto (List<ResponseDto.ChickenDto> chickenDtos){

        return ResponseDto.ListChickenDto.builder()
                .chickenDto(chickenDtos)
                .build();
    }



}
