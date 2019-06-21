package com.sixet.skeleton.core.exception.handler;

import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NoContentException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.utils.MessageResourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;

/**
 *  Provides handling for exceptions throughout this service.
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({
            NotFoundException.class,
            BusinessException.class,
            NoContentException.class,
            Exception.class
    })
    @Nullable
    public final ResponseEntity<StandardErrorHandler> handleException(Exception ex, HttpServletRequest request) {

        log.error("Handling " + ex.getClass().getSimpleName() + " due to " + ex.getMessage());

        if (ex instanceof NotFoundException) {
            return ResponseEntity.status(NOT_FOUND).body(
                    this.createStandardErrorHandler(ex.getClass().getName(), System.currentTimeMillis(),
                            NOT_FOUND.value(), ex.getMessage(),
                            request.getRequestURI())
            );
        } else if (ex instanceof BusinessException) {
            return ResponseEntity.status(CONFLICT).body(
                    this.createStandardErrorHandler(ex.getClass().getName(), System.currentTimeMillis(),
                            CONFLICT.value(), ex.getMessage(),
                            request.getRequestURI())
            );
        } else if (ex instanceof NoContentException) {
            return ResponseEntity.status(NO_CONTENT).body(
                    this.createStandardErrorHandler(ex.getClass().getName(), System.currentTimeMillis(),
                            NO_CONTENT.value(), ex.getMessage(),
                            request.getRequestURI())
            );
        } else {
            if (log.isWarnEnabled()) {
                log.warn("Exception type: " + ex.getClass().getName());
                log.warn(ex.getMessage());
            }
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                    this.createStandardErrorHandler(ex.getClass().getName(), System.currentTimeMillis(),
                            INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
                            request.getRequestURI())
            );
        }
    }

    /**
     * Create an StandardErrorHandler
     * @param exception
     * @param date
     * @param statusCode
     * @param errorMessage
     * @param path
     * @return
     */
    private StandardErrorHandler createStandardErrorHandler(String exception,
                                                            Long date,
                                                            Integer statusCode,
                                                            String errorMessage,
                                                            String path) {
        return new StandardErrorHandler(exception, date, statusCode, errorMessage, path);
    }
}
