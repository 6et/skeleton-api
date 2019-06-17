package com.sixet.skeleton.core.exception.handler;

import com.sixet.skeleton.core.exception.BusinessException;
import com.sixet.skeleton.core.exception.NoContentException;
import com.sixet.skeleton.core.exception.NotFoundException;
import com.sixet.skeleton.utils.MessageResourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 *
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GlobalExceptionHandler {

    private MessageResourceUtil messageResource;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardErrorHandler> processNotFoundException(NotFoundException e,
                                                                         HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(
                this.createStandardErrorHandler(System.currentTimeMillis(), NOT_FOUND.value(), e.getMessage(),
                        request.getRequestURI())
        );
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardErrorHandler> processBusinessException(NotFoundException e,
                                                                         HttpServletRequest request) {
        return ResponseEntity.status(CONFLICT).body(
                this.createStandardErrorHandler(System.currentTimeMillis(), CONFLICT.value(), e.getMessage(),
                        request.getRequestURI()));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoContentException.class)
    public void processNoContentException(NoContentException ex) {
        log.error(ex.getMessage(), ex);
    }

    private StandardErrorHandler createStandardErrorHandler(Long date, Integer statusCode, String errorMessage,
                                                            String path) {
        return new StandardErrorHandler(date, statusCode, errorMessage, path);
    }



//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ValidationErrorResponse> processValidationError(MethodArgumentNotValidException ex) {
//        log.error(ex.getMessage(), ex);
//        BindingResult result = ex.getBindingResult();
//        List<FieldError> fieldErrors = result.getFieldErrors();
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(processFieldErrors(fieldErrors));
//    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<ValidationErrorResponse> processValidationError(ValidationException ex) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getErrors());
//    }



    /**
     * Process field errors from exception.
     *
     * @param errors Errors list
     * @return Validation error response
     */
//    private ValidationErrorResponse processFieldErrors(final List<FieldError> errors) {
//        ValidationErrorResponse response = new ValidationErrorResponse();
//        for (final FieldError fieldError : errors) {
//            response.addFieldError(fieldError.getField(), this.messageResource.get(fieldError.getDefaultMessage()));
//        }
//        return response;
//    }
}
