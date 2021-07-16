package com.dxc.application.exception;

import com.dxc.application.exceptions.ApplicationException;
import com.dxc.application.model.RestJsonData;
import com.dxc.application.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    MessageSource messageSource;

    @ExceptionHandler({ApplicationException.class})
    public final RestJsonData<String> applicationExceptionHandler(HttpServletRequest request, ApplicationException ae){
        log.warn(ae.getMessageCode(),ae);
        RestJsonData<String> restJsonData = new RestJsonData<>();
        restJsonData.setMessage(MessageUtil.getErrorMessage(messageSource, ae, request));
        return restJsonData;
    }
    @ExceptionHandler({Exception.class})
    public final  RestJsonData<String> exceptionHandler(HttpServletRequest request,Exception ex){
        if(ex instanceof ApplicationException){
            ApplicationException ae = (ApplicationException)ex;
            log.warn(ae.getMessageCode(),ae);
            RestJsonData<String> restJsonData = new RestJsonData<>();
            restJsonData.setMessage(MessageUtil.getErrorMessage(messageSource, ae, request));
            return restJsonData;
        }else{
            log.error(ex.getMessage(),ex);
            RestJsonData<String> restJsonData = new RestJsonData<>();
            restJsonData.setMessage(MessageUtil.getErrorMessage(messageSource, ex, request));
            return restJsonData;
        }
    }
}
