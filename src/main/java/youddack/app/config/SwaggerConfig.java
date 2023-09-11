package youddack.app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "딱 너 닭!",
                description = "딱 너 닭! 프로젝트 API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "배재은",
                        email = "rew1212@email.co.kr"
                )
        )
)
@Configuration
public class SwaggerConfig {

}
