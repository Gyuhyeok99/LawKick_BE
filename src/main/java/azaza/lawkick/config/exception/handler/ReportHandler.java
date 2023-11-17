package azaza.lawkick.config.exception.handler;

import azaza.lawkick.config.code.BaseErrorCode;
import azaza.lawkick.config.exception.GeneralException;

public class ReportHandler extends GeneralException {
    public ReportHandler(BaseErrorCode code) {
        super(code);
    }
}
