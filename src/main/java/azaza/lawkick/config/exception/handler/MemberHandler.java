package azaza.lawkick.config.exception.handler;

import azaza.lawkick.config.code.BaseErrorCode;
import azaza.lawkick.config.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
