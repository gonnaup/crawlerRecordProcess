package cn.gonnaup.crawlerrecordprocess.util;

import java.util.StringJoiner;

/**
 * @author gonnaup
 * @version created at 2022/4/8 12:35
 */
public class ResultTemplate<T> {

    public ResultTemplate() {
    }

    public ResultTemplate(T data) {
        this.data = data;
    }

    public ResultTemplate(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }


    private T data;

    private String code;

    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private ResultTemplate<T> withCode(ResultCode code) {
        this.setCode(code.code());
        return this;
    }

    public ResultTemplate<T> success() {
        return withCode(ResultCode.SUCCESS);
    }

    public ResultTemplate<T> fail() {
        return withCode(ResultCode.FAIL);
    }

    public static <T> ResultTemplate<T> data(T data) {
        return new ResultTemplate<>(data);
    }

    public ResultTemplate<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ResultTemplate.class.getSimpleName() + "[", "]")
                .add("data=" + data)
                .add("code='" + code + "'")
                .add("message='" + message + "'")
                .toString();
    }

    public enum ResultCode {
        SUCCESS("200"),
        FAIL("400");

        private final String code;

        ResultCode(String code) {
            this.code = code;
        }

        public String code() {
            return code;
        }
    }
}
