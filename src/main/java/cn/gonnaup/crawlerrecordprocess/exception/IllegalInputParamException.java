package cn.gonnaup.crawlerrecordprocess.exception;

import java.io.Serial;

/**
 * @author gonnaup
 * @version created at 2022/5/22 12:41
 */
public class IllegalInputParamException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1831042851277589752L;

    public IllegalInputParamException(String message) {
        super(message);
    }
}
