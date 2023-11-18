package azaza.lawkick.config.exception.handler;

import azaza.lawkick.config.code.BaseErrorCode;
import azaza.lawkick.config.exception.GeneralException;

public class FileHandler extends GeneralException {
    public FileHandler(BaseErrorCode code) {
        super(code);
    }
}
