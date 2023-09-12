package youddack.app.domain.chicken;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import youddack.app.config.BaseResponse;
import youddack.app.domain.chicken.domain.Brand;
import youddack.app.domain.chicken.domain.Category;
import youddack.app.domain.chicken.domain.Chicken;
import youddack.app.domain.chicken.domain.Flavor;
import youddack.app.domain.chicken.dto.ResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static youddack.app.domain.chicken.Converter.toChickenDto;
import static youddack.app.domain.chicken.Converter.toChickenListDto;

@Service
@RequiredArgsConstructor
public class Provider {

    final Repository repository;
    /*
    * 치킨 상세 정보 조회
    * */
    public ResponseDto.ChickenDto findChickenDetail(Long chickenId){

        Optional<Chicken> chicken = repository.findById(chickenId);

        List<Category> categoryList = repository.findByChickenIdJoin(chickenId);

        List<Flavor> flavorList = repository.findByChickenIdJoinFlavor(chickenId);

        System.out.println("chicken:"+ flavorList);

        return toChickenDto(chicken.get(), categoryList, flavorList);

    }

    public ResponseDto.ListChickenDto findChickenComparisonList(List<Long> chicken_id){

        System.out.println(chicken_id.toArray()[0].getClass().getName());

        List<ResponseDto.ChickenDto> chickenDtoList = new ArrayList<>();

        for(int i = 0; i < chicken_id.toArray().length; i++ ){

            Optional<Chicken> chicken = repository.findById(Long.valueOf(chicken_id.toArray()[i].toString()));

            List<Category> categoryList = repository.findByChickenIdJoin(Long.valueOf(chicken_id.toArray()[i].toString()));

            List<Flavor> flavorList = repository.findByChickenIdJoinFlavor(Long.valueOf(chicken_id.toArray()[i].toString()));

            chickenDtoList.add(toChickenDto(chicken.get(), categoryList, flavorList));

        }

        return toChickenListDto(chickenDtoList);

    }

}
