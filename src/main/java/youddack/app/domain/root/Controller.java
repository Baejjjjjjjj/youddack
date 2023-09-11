package youddack.app.domain.root;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import youddack.app.config.BaseResponse;
import youddack.app.config.Code;

@Slf4j
@Tag(name = "root")
@RestController("rootController")
@RequiredArgsConstructor
public class Controller {

    @GetMapping("/")
    public BaseResponse<String> GetHealthCheck(){

        System.out.println("health check success");
        return new BaseResponse<>(Code.SUCCESS);
    }
}
