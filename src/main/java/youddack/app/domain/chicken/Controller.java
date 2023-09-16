package youddack.app.domain.chicken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import youddack.app.config.BaseResponse;
import youddack.app.domain.chicken.domain.Answer;
import youddack.app.domain.chicken.dto.RequestDto;
import youddack.app.domain.chicken.dto.ResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static youddack.app.config.Code.*;

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
    public BaseResponse<ResponseDto.ListChickenDetailDto> GetChickenComparison(@RequestParam @Size(min = 0, max = 4) List<Long> chicken_id_list){

        return new BaseResponse<>(provider.findChickenComparisonList(chicken_id_list));

    }

    /*
    @Operation(summary = "비교함 추가 ", description = "치킨 비교함에 추가")

    @PostMapping("/comparison")
    public BaseResponse<String> PostChickenComparison(HttpServletRequest request, Long arg1, HttpServletResponse response){

        Cookie[] cookie = request.getCookies();


        if(cookie==null||cookie.length<4){

            Cookie comparisonCookie = new Cookie("chicken_id"+String.valueOf(arg1),arg1.toString());
            comparisonCookie.setPath("/");
            comparisonCookie.setMaxAge(60*60);
            response.addCookie(comparisonCookie);

        }
        else if(cookie.length<4){

            if(cookie.length!=0) {
                for (int i = 0; i < cookie.length; i++) {
                    if (cookie[i].getValue() == String.valueOf(arg1)) {
                        return new BaseResponse<>(ALREADY_IN_COMPARISON);
                    }
                }
            }
            Cookie comparisonCookie = new Cookie("chicken_id"+String.valueOf(arg1),arg1.toString());
            comparisonCookie.setPath("/");
            comparisonCookie.setMaxAge(60*60);
            response.addCookie(comparisonCookie);
        }
        else if(cookie.length>=4){
            return new BaseResponse<>(OVER_IN_COMPARISON);
        }

        return new BaseResponse<>("쿠키에 chicken_id 저장 완료\n 치킨 id = "+ arg1);
    }

    @Operation(summary = "비교함 삭제", description = "비교함에서 치킨 정보를 삭제한다. ")
    @DeleteMapping("/comparison")
    public BaseResponse<String> DeleteChickenComparison(HttpServletRequest request, @RequestParam(required = true) Long chicken_id, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();


        if(cookies!=null) {
            boolean here = false;
            for (Cookie c : cookies) {
                if (c.getValue().equals(String.valueOf(chicken_id))) {
                    here = true;
                }
            }

            if (here == false) {
                return new BaseResponse<>(NOT_IN_COMPARISON);
            }

        }

        System.out.println("chicken_id"+String.valueOf(chicken_id));
        Cookie cookie = new Cookie("chicken_id"+String.valueOf(chicken_id),null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);


        return new BaseResponse<>("cookie 삭제 성공");
    }
           */
    /**
     * 치킨 상세 정보 API
     * [GET] /chicken/{chicken_id}
     * @return BaseResponse<String>
     * */

    @Operation(summary = "치킨 상세 정보", description = "치킨 상세 정보 API")
    @GetMapping("/")
    public BaseResponse<ResponseDto.ChickenDetailDto> GetChickenDetail(Long arg0){

        System.out.println(arg0);
        return new BaseResponse<>(provider.findChickenDetail(arg0));

    }

}
