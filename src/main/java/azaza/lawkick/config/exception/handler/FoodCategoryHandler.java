package azaza.lawkick.config.exception.handler;


import azaza.lawkick.config.code.BaseErrorCode;
import azaza.lawkick.config.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
