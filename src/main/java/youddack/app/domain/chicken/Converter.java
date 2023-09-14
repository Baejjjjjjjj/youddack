package youddack.app.domain.chicken;

import youddack.app.domain.chicken.domain.Brand;
import youddack.app.domain.chicken.domain.Category;
import youddack.app.domain.chicken.domain.Chicken;
import youddack.app.domain.chicken.domain.Flavor;
import youddack.app.domain.chicken.dto.ResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static ResponseDto.ChickenDetailDto toChickenDetailDto(Chicken chicken, List<Category> categoryList, List<Flavor> flavorList){

        return ResponseDto.ChickenDetailDto.builder()
                .chicken_id(chicken.getId())
                .chicken_allergy(chicken.getAllergy())
                .chicken_capacity(chicken.getCapacity())
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

    public static ResponseDto.ChickenDto toChickenDto(Chicken chicken, List<Flavor> flavor){

        return ResponseDto.ChickenDto.builder()
                .chicken_id(chicken.getId())
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

}
