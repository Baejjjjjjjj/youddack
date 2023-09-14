package youddack.app.domain.chicken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import youddack.app.config.BaseResponse;
import youddack.app.domain.chicken.dto.RequestDto;
import youddack.app.domain.chicken.dto.ResponseDto;

import java.util.List;
import java.util.Optional;

@Slf4j
@Tag(name = "치킨")
@RequestMapping("/chicken")
@RestController
@RequiredArgsConstructor
public class Controller {

    public final Provider provider;

    /**
     * 치킨추천 API
     * [GET] /chicken/recommendation
     * @return BaseResponse<String>
     * */
    @Operation(summary = "치킨 추천!", description = "치킨 추천 API")
    @GetMapping("/recommendation")
    public BaseResponse<String> GetChickenRecommendation(){

        return null;

    }

    @Operation(summary = "치킨 추천!", description = "치킨 추천 API")
    @GetMapping("/recommendation/list")
    public BaseResponse<String> GetChickenRecommendationList(){


        return null;

    }


    /**
     * 치킨 리스트 API
     * [GET] /chicken/list
     * @return BaseResponse<String>
     * */

    @Operation(summary = "치킨 리스트", description = "치킨 리스트 API")
    @GetMapping("/list")
    public BaseResponse<ResponseDto.ListChickenDto> GetChickenList( RequestDto.FindChickenDto request){


        //Brand별 치킨 리스트조회
        if(request.getBrand_id() != null&&request.getBrand_id()!=0){
            return new BaseResponse<>(provider.findBrandChickenList(request.getChicken_id(), request.getBrand_id()));
        }

        return new BaseResponse<>(provider.findChickenList(request.getChicken_id(), request.getBrand_id(), request.getFlavor() ,
               request.getCategory_name(), request.getPart_name(), request.getStart_price(), request.getEnd_price() , request.getSort_id()));


    }

    /**
     * 치킨 비교하기 API
     * [GET] /chicken/comparison
     * @return BaseResponse<String>
     * */

    @Operation(summary = "치킨 비교 ", description = "치킨 비교 API")
    @GetMapping("/comparison")
    public BaseResponse<ResponseDto.ListChickenDetailDto> GetChickenComparison(@NotEmpty @RequestParam @Size(min=1, max= 4) List<Long> chicken_id){

        return new BaseResponse<>(provider.findChickenComparisonList(chicken_id));



    }

    /**
     * 치킨 상세 정보 API
     * [GET] /chicken/{chicken_id}
     * @return BaseResponse<String>
     * */

    @Operation(summary = "치킨 상세 정보", description = "치킨 상세 정보 API")
    @GetMapping("/")
    public BaseResponse<ResponseDto.ChickenDetailDto> GetChickenDetail(@NotEmpty @PathVariable Long chickenId){

        return new BaseResponse<>(provider.findChickenDetail(chickenId));

    }

}
