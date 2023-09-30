package youddack.app.domain.chicken;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import youddack.app.domain.chicken.domain.*;
import youddack.app.domain.chicken.dto.ResponseDto;
import youddack.app.domain.chicken.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static youddack.app.domain.chicken.Converter.*;

@Service
@RequiredArgsConstructor
public class Provider {

    final FlavorRepository flavorRepository;
    final Repository repository;

    final BrandRepository brandRepository;

    final CustomRepository customRepository;

    final QuestionRepository questionRepository;

    final AnswerRepository answerRepository;

    final RecommendationRepository recommendationRepository;

    final RecommendationDescriptionsRepository recommendationDescriptionsRepository;

    public String findS3ImageUrl() {

        for(int i = 0; i < 2; i++){

            Optional<Chicken> chicken = repository.findById(Long.valueOf(i));
            String name = chicken.get().getName();
            String brand = chicken.get().getBrand().getName();
            //String url =
            System.out.println(name);
        }

        return null;
    }

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

        System.out.println("brand");
        Pageable pageable =  PageRequest.of(0,10);

        List<Chicken> chickenList = repository.findChickenListLimitAndBrand(chicken_id,brand_id,pageable);

        List<ResponseDto.ChickenDto> chickenDtoList = new ArrayList<>();
        for(int i = 0; i < chickenList.size(); i++){

            Long chicken_ids = chickenList.get(i).getId();
            System.out.println(chicken_ids);
            List<Flavor> flavorList = repository.findByChickenIdJoinFlavor(chicken_ids);
            chickenDtoList.add(toChickenDto(chickenList.get(i), flavorList,null));
        }

        return toChickenListDto(chickenDtoList);

    }

    public ResponseDto.ListChickenDto findChickenList(Long chicken_id, Long brand_id, List<String> flavorList,String category_name, String part_name, Integer start_price, Integer end_price, Integer sort_id, String chicken_name, Long rank_id){

        if(sort_id==0&&chicken_id==0){
            System.out.println("추천순 초기 정렬입니다.");
            List<ResponseDto.ChickenDto> RecommendDtoList = new ArrayList<>();
            List<Long> RecommendChickenNameList = new ArrayList<Long>();//
            RecommendChickenNameList.add(Long.valueOf(16));
            RecommendChickenNameList.add(Long.valueOf(97));
            RecommendChickenNameList.add(Long.valueOf(261));
            RecommendChickenNameList.add(Long.valueOf(308));
            RecommendChickenNameList.add(Long.valueOf(320));
            RecommendChickenNameList.add(Long.valueOf(357));
            RecommendChickenNameList.add(Long.valueOf(359));
            RecommendChickenNameList.add(Long.valueOf(400));
            RecommendChickenNameList.add(Long.valueOf(454));
            RecommendChickenNameList.add(Long.valueOf(420));

            for(int i = 0; i < 10; i++){

                Optional<Chicken> chickens = repository.findById(RecommendChickenNameList.get(i));
                Chicken chicken = chickens.get();
                List<Flavor> recommendFlavorList = repository.findByChickenIdJoinFlavor(chicken.getId());
                RecommendDtoList.add(toChickenDto(chicken, recommendFlavorList,null));
            }
            return toChickenListDto(RecommendDtoList);
        }


        List<Chicken> chickenList = customRepository.selectChickenList(chicken_id,category_name,part_name,start_price,end_price,sort_id,flavorList,rank_id);


        List<Long> rank = new ArrayList<>();
        if(sort_id==1){
            System.out.println(sort_id);
            rank = repository.findRankOrderByPriceAsc(rank_id);
        }else if(sort_id==2){
            System.out.println(sort_id);
            rank = repository.findRankOrderByCapacityAsc(rank_id);

        }else if(sort_id==3){
            System.out.println(sort_id);
            rank = repository.findRankOrderByPriceDesc(rank_id);
        }

        List<ResponseDto.ChickenDto> chickenDtoList = new ArrayList<>();
        for(int i = 0; i < chickenList.size();i++) {
            Long chicken_ids = chickenList.get(i).getId();
            System.out.println(chicken_ids);
            List<Flavor> flavorLists = customRepository.SelectFlavorList(chicken_ids, flavorList);
            if (sort_id == 0) {
                chickenDtoList.add(toChickenDto(chickenList.get(i), flavorLists, null));
            } else {
                chickenDtoList.add(toChickenDto(chickenList.get(i), flavorLists, rank.get(i)));
            }
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

       var id = 0;

       String color_code = "";
       //string은 == 로 비교하지 말기!
       if(code.equals("10001")||code.equals("10101")||code.equals("10111")||code.equals("11001")){
           id = 1;
           code = "#EA2AD7";
       }
       else if(code.equals("00100")||code.equals("01000")||code.equals("01010")||code.equals("01100")){
           id = 2;
           code ="#28C1BC";
       }
       else if(code.equals("00010")||code.equals("00110")||code.equals("01110")){
           id = 3;
           code = "#007851";
       }
       else if(code.equals("00000")||code.equals("00001")||code.equals("00011")||code.equals("00101")||code.equals("00111")||
               code.equals("01001")||code.equals("01011")||code.equals("01101")||code.equals("01111")){
           id = 4;
           code ="#EA412A";
       }
       else if(code.equals("10100")||code.equals("10110")||code.equals("11100")){
           id = 5;
           code = "#EEBB38";
       }
       else if(code.equals("10000")||code.equals("10010")||code.equals("11000")||code.equals("11010")||code.equals( "11110")){
           id = 6;
           code = "#EA642A";
       }else{
            id = 7;
            code = "#9747FF";
       }

       System.out.println(id);

       Optional<Recommendation> recommendation = recommendationRepository.findById(Long.valueOf(id));
       List<RecommendationDescription> recommendationDescriptions = recommendationDescriptionsRepository.findByRecommendationId(recommendation.get().getId());
       return toRecommendChickenTypeDto(recommendation.get(), recommendationDescriptions,code,recommendation.get().getRecommend_brand());

    }

    public ResponseDto.ListChickenDto findChickenListWithName(Long chicken_id, String chicken_name) {

        Pageable pageable = PageRequest.of(0,10);
        List<Chicken> chickenList= repository.findAllByIdAndByChickenName(chicken_id,chicken_name,pageable);

        List<ResponseDto.ChickenDto> chickenDtoList = new ArrayList<>();

        for(int i = 0; i<chickenList.size();i++){

            Long id = chickenList.get(i).getId();
            List<Flavor> flavorList = flavorRepository.findFlavorWithChickenFlavorById(id);
            chickenDtoList.add(toChickenDto(chickenList.get(i),flavorList,null));

        }

        return Converter.toChickenListDto(chickenDtoList);
    }
}
