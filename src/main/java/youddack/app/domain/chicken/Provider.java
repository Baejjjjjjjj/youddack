package youddack.app.domain.chicken;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import youddack.app.domain.chicken.domain.*;
import youddack.app.domain.chicken.dto.ResponseDto;
import youddack.app.domain.chicken.repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static youddack.app.domain.chicken.Converter.*;

@Service
@RequiredArgsConstructor
public class Provider {

    final Repository repository;
    final BrandRepository brandRepository;

    final CustomRepository customRepository;

    final QuestionRepository questionRepository;

    final AnswerRepository answerRepository;

    final RecommendationRepository recommendationRepository;

    final RecommendationDescriptionsRepository recommendationDescriptionsRepository;
    /*
    * 치킨 상세 정보 조회
    * */
    public ResponseDto.ChickenDetailDto findChickenDetail(Long chickenId){

        Optional<Chicken> chicken = repository.findById(chickenId);

        List<Category> categoryList = repository.findByChickenIdJoin(chickenId);

        List<Flavor> flavorList = repository.findByChickenIdJoinFlavor(chickenId);

        System.out.println("chicken:"+ flavorList);

        return toChickenDetailDto(chicken.get(), categoryList, flavorList);

    }

    public ResponseDto.ListChickenDetailDto findChickenComparisonList(List<Long> chicken_id){

        System.out.println(chicken_id.toArray()[0].getClass().getName());

        List<ResponseDto.ChickenDetailDto> chickenDtoList = new ArrayList<>();

        for(int i = 0; i < chicken_id.toArray().length; i++ ){

            Optional<Chicken> chicken = repository.findById(Long.valueOf(chicken_id.toArray()[i].toString()));

            List<Category> categoryList = repository.findByChickenIdJoin(Long.valueOf(chicken_id.toArray()[i].toString()));

            List<Flavor> flavorList = repository.findByChickenIdJoinFlavor(Long.valueOf(chicken_id.toArray()[i].toString()));

            chickenDtoList.add(toChickenDetailDto(chicken.get(), categoryList, flavorList));

        }

        return toChickenDetailListDto(chickenDtoList);

    }

    public ResponseDto.ListChickenDto findBrandChickenList(Long chicken_id, Long brand_id){

        Pageable pageable =  PageRequest.of(0,10);

        //Optional<Brand> brand = brandRepository.findById(brand_id);

        List<Chicken> chickenList = repository.findChickenListLimit(chicken_id,pageable);

        List<ResponseDto.ChickenDto> chickenDtoList = new ArrayList<>();
        for(int i = 0; i < chickenList.size(); i++){

            Long chicken_ids = chickenList.get(i).getId();
            List<Flavor> flavorList = repository.findByChickenIdJoinFlavor(chicken_ids);
            chickenDtoList.add(toChickenDto(chickenList.get(i), flavorList));
        }

        return toChickenListDto(chickenDtoList);

    }

    public ResponseDto.ListChickenDto findChickenList(Long chicken_id, Long brand_id, List<String> flavorList,String category_name, String part_name, Integer start_price, Integer end_price, Integer sort_id){

        if(sort_id==0&&chicken_id==0){

            List<ResponseDto.ChickenDto> RecommendDtoList = new ArrayList<>();
            List<String> RecommendChickenNameList = Arrays.asList(new String[]{"고추바사삭 윙","황금올리브치킨","간장마늘치킨","허니콤보","파채소이살살","알싸한마늘치킨","엄청큰후라이드","XO양념치킨","타코마요치킨"});
            for(int i = 0; i < 10; i++){

                Chicken chicken = repository.findByChickenName(RecommendChickenNameList.get(i));
                List<Flavor> recommendFlavorList = repository.findByChickenIdJoinFlavor(chicken.getId());
                RecommendDtoList.add(toChickenDto(chicken, recommendFlavorList));
            }
            return toChickenListDto(RecommendDtoList);
        }


        System.out.println("정렬 순: "+ sort_id);
        String sort = "";

        Pageable pageable = PageRequest.of(0,10);
        List<Chicken> chickenList = customRepository.selectChickenList(chicken_id,category_name,part_name,start_price,end_price,sort_id);


        List<ResponseDto.ChickenDto> chickenDtoList = new ArrayList<>();
        for(int i = 0; i < chickenList.size();i++){
            Long chicken_ids = chickenList.get(i).getId();
            System.out.println(chicken_ids);
            List<Flavor> flavorLists = customRepository.SelectFlavorList(chicken_ids, flavorList);
            chickenDtoList.add(toChickenDto(chickenList.get(i), flavorLists));

        }

        return toChickenListDto(chickenDtoList);
    }

    public ResponseDto.ListChickenRecommendDto findChickenRecommendList(){

        List<ResponseDto.ChickenRecommendDto> chickenRecommendDtos = new ArrayList<>();
        for(int i = 1; i < 6; i++) {

            Optional<Question> question = questionRepository.findById(Long.valueOf(i));


            List<Answer> answerList = answerRepository.findByQuestionId(question.get().getId());

            chickenRecommendDtos.add(toChickenRecommendDto(question.get(), answerList.get(0), answerList.get(1)));


        }

        return toChickenRecommendListDto(chickenRecommendDtos);
    }

    public ResponseDto.RecommendChickenTypeDto findChickenRecommendType(List<Long> answerList){

       String code = (Long.toString(answerList.get(0)) + Long.toString(answerList.get(1)) + Long.toString(answerList.get(2)) + Long.toString(answerList.get(3)) + Long.toString(answerList.get(4)));

       System.out.println(code);
       var id = 0;

       if(code == "10001"||code=="10101"||code == "10111"||code == "11001"){
           id = 1;
       }
       else if(code == "00100"||code=="01000"||code == "01010"||code == "01100"){
           id = 2;
       }
       else if(code == "00010"||code=="00110"||code == "01110"){
           id = 3;
       }
       else if(code == "00000"||code=="00001"||code == "00011"||code == "00101"||code == "00111"||
               code == "01001"||code == "01011"||code == "01101"||code == "01111"){
           id = 4;
       }
       else if(code == "10100"||code=="10110"||code == "11100"){
           id = 5;
       }
       else if(code == "10000"||code=="10010"||code == "11000"||code == "11010"||code == "11110"){
           id = 6;
       }else{
            id = 7;
       }

       Optional<Recommendation> recommendation = recommendationRepository.findById(Long.valueOf(id));
       List<RecommendationDescription> recommendationDescriptions = recommendationDescriptionsRepository.findByRecommendationId(recommendation.get().getId());

       return toRecommendChickenTypeDto(recommendation.get(), recommendationDescriptions);

    }
}
