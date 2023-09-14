package youddack.app.domain.chicken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import youddack.app.config.BaseResponse;
import youddack.app.domain.chicken.dto.ResponseDto;

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
    public BaseResponse<String> GetChickenList(){

        return null;

    }

    /**
     * 치킨 비교하기 API
     * [GET] /chicken/comparison
     * @return BaseResponse<String>
     * */

    @Operation(summary = "치킨 비교 ", description = "치킨 비교 API")
    @GetMapping("/comparison")
    public BaseResponse<String> GetChickenComparison(){

        return null;

    }

    /**
     * 치킨 상세 정보 API
     * [GET] /chicken/{chicken_id}
     * @return BaseResponse<String>
     * */

    @Operation(summary = "치킨 상세 정보", description = "치킨 상세 정보 API")
    @GetMapping("/")
    public BaseResponse<ResponseDto.ChickenDto> GetChickenDetail(@NotEmpty @RequestParam Long chickenId){

        return new BaseResponse<>(provider.findChickenDetail(chickenId));

    }

}
