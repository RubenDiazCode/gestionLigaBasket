package talent.campus.examenRubenDiaz.utils;

import talent.campus.examenRubenDiaz.exception.BadRequestException;
import talent.campus.examenRubenDiaz.exception.InternalErrorException;
import talent.campus.examenRubenDiaz.exception.NotFoundException;

public class ExceptionFactoryUtils {

    private ExceptionFactoryUtils() {}

    public static final InternalErrorException internalErrorException(String message) {
        return new InternalErrorException(message);
    }

    public static final NotFoundException resourceNotFoundException(String message) {
        return new NotFoundException(message);
    }

    public static final BadRequestException badRequestException(String message) {
        return new BadRequestException(message);
    }
}
