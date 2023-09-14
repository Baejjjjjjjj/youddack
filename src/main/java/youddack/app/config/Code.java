package youddack.app.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Code {

    /**
     * 200 : 요청 성공
     */
    SUCCESS(HttpStatus.OK, true, 2000, "요청에 성공하였습니다."),

    ALREADY_IN_COMPARISON(HttpStatus.OK, false, 3000, "요청에 실패했습니다. 이미 비교함에 들어 있습니다."),

    OVER_IN_COMPARISON(HttpStatus.OK, false, 3001, "요청에 실패했습니다. 비교함에 4개 이상의 치킨이 들어있습니다."),

    NOT_IN_COMPARISON(HttpStatus.OK, false, 3002, "요청에 실패했습니다. 비교함에 해당하는 치킨이 존재하지 않습니다. 치킨 id를 재 확인해주세요");



    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final int code;
    private final String message;

    Code(HttpStatus httpStatus, boolean isSuccess, int code, String message) {
        this.httpStatus = httpStatus;
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
