package youddack.app.domain.chicken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import youddack.app.config.BaseResponse;
import youddack.app.domain.chicken.dto.RequestDto;
import youddack.app.domain.chicken.dto.ResponseDto;
import java.util.List;

@Slf4j
@CrossOrigin
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
    @Operation(summary = "치킨 추천", description = "치킨 추천 API")
    @Parameters({
        @Parameter(name = "arg0", description = "치킨 추천 정답 List", required = true)
    })
    @GetMapping("/recommendation")
    public BaseResponse<ResponseDto.RecommendChickenTypeDto> GetChickenRecommendation(@RequestParam @Size(min =7, max = 7) List<Long> arg0){
        System.out.println("isHere"+arg0.get(0));
        return new BaseResponse<>(provider.findChickenRecommendType(arg0));

    }

    @Operation(summary = "치킨 추천 응답 목록 ", description = "치킨 추천 질문 & 응답 목록 API")
    @GetMapping("/recommendation/list")
    public BaseResponse<ResponseDto.ListChickenRecommendDto> GetChickenRecommendationList(){

        return new BaseResponse<>(provider.findChickenRecommendList());

    }


    /**
     * 치킨 리스트 API
     * [GET] /chicken/list
     * @return BaseResponse<String>
     * */

    @Operation(summary = "치킨 리스트", description = "치킨 리스트 API")
    @Parameters({
            @Parameter(name = "request", description = "chicken_id: chikcen id  "+
                    "\t\nsort_id(0) = 추천순 \t\nsort_id(1) = 가격 낮은 순 \t\nsort_id(2) = 열량 낮은 순\t\nsort_id(3) = 가격 높은순  "
            + "\t\nbrand_id: 브랜드 id  " +"\t\ncategory_id: 카테고리 이름  " + "\t\npart_name: 부위 이름  "

            +"\t\nList<flavor>: 맛 필터링 정렬  (필터링이 없을 시 \"\"" +
                    " 다음과 같이 empty string을 입력하세요" + "\t\nstart_price: 시작 금액, 최소 0\t\nend_price: 최대 금액")

    })
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
    @Parameter(name = "chicken_id_list", description = "치킨 id 리스트", example = "[1,2,3,4]")
    @GetMapping("/comparison")
    public BaseResponse<ResponseDto.ListChickenDetailDto> GetChickenComparison(@RequestParam @Size(min = 0, max = 4) List<Long> chicken_id_list){

        return new BaseResponse<>(provider.findChickenComparisonList(chicken_id_list));

    }

    /**
     * 치킨 상세 정보 API
     * [GET] /chicken/{chicken_id}
     * @return BaseResponse<String>
     * */

    @Operation(summary = "치킨 상세 정보", description = "치킨 상세 정보 API")
    @Parameter(name="arg0", description = "chicken_id",example = "1")
    @GetMapping("/")
    public BaseResponse<ResponseDto.ChickenDetailDto> GetChickenDetail(Long arg0){

        return new BaseResponse<>(provider.findChickenDetail(arg0));

    }


}
