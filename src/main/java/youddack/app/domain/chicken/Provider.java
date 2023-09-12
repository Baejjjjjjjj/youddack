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

import java.util.List;
import java.util.Optional;

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

        return Converter.toShowChickenDto(chicken.get(), categoryList, flavorList);

    }

}
