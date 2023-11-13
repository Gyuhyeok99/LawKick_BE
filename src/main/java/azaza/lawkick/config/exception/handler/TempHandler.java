package azaza.lawkick.config.exception.handler;


import azaza.lawkick.config.code.BaseErrorCode;
import azaza.lawkick.config.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
