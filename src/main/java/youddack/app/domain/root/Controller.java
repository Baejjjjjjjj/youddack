package youddack.app.domain.root;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youddack.app.config.BaseResponse;
import youddack.app.config.Code;
import youddack.app.domain.chicken.repository.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Tag(name = "root")
@RestController("rootController")
@RequiredArgsConstructor
public class Controller {

    private final Repository repository;
    @GetMapping("/")
    public BaseResponse<String> GetHealthCheck(){

        System.out.println("health check success");
        return new BaseResponse<>(Code.SUCCESS);
    }

    @GetMapping("/health")
    public BaseResponse<String> GetHealth(){

        System.out.println("health check success");
        return new BaseResponse<>(Code.SUCCESS);
    }

    @GetMapping("/Query")
    public BaseResponse<List<String>> GetChickenQuery(){

        List<String> st = new ArrayList<>();
        for(int i = 291; i< 492; i++){

            String name = repository.findById(Long.valueOf(i)).get().getName();
            st.add("update chicken set image_url = \"\" where name = \""+name +"\"");

        }

        return new BaseResponse<>(st);
    }

}
