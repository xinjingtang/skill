package com.group.integrate.advice;

import com.group.integrate.excepiton.BizException;
import com.group.integrate.excepiton.ExceptionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author xinjing.tang
 * @since 2018/9/27.
 */
@ControllerAdvice
public class ResourceAdvice {

//    @Autowired
//    private Tracer tracer;

    @ExceptionHandler(BizException.class)
    public ResponseEntity<ExceptionDetail> handleBizException(BizException e) {
//        logRequest(ExceptionErrorCode.VALIDATION_ERROR, e, false);
//        String errorMsg = exceptionService.getMessageDetailByCode(e.getCode(), e.getArgs());
//        detail.setMessage(StringUtils.isEmpty(errorMsg) ? e.getMsg() : errorMsg);
        ExceptionDetail detail = new ExceptionDetail();
        detail.setErrorCode(e.getCode());
        detail.setMessage(e.getMessage() == null ? null : e.getMessage());
        detail.setBizErrorCode("BizErrorCode");
        e.printStackTrace();
        return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDetail> handleRuntimeException(BizException e) {
//        logRequest(ExceptionErrorCode.VALIDATION_ERROR, e, false);
        ExceptionDetail detail = new ExceptionDetail();
        detail.setErrorCode(e.getCode());
//        String errorMsg = exceptionService.getMessageDetailByCode(e.getCode(), e.getArgs());
//        detail.setMessage(StringUtils.isEmpty(errorMsg) ? e.getMsg() : errorMsg);
        detail.setMessage(e.getMessage() == null ? null : e.getMessage());
        detail.setBizErrorCode("RuntimeException");
        return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
    }

}
