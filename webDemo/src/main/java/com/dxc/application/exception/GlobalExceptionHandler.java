package com.dxc.application.exception;

import com.dxc.application.exceptions.ApplicationException;
import com.dxc.application.feature.common.dto.RestJsonData;
import com.dxc.application.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public final class GlobalExceptionHandler {
    private MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({ApplicationException.class})
    @ResponseBody
    public RestJsonData<String> applicationExceptionHandler(HttpServletRequest request, ApplicationException ae) {
        log.warn("Well known Error with message code {}", ae.getMessageCode());
        RestJsonData<String> restJsonData = new RestJsonData<>();
        restJsonData.setMessage(MessageUtil.getErrorMessage(messageSource, ae, request));
        return restJsonData;
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public RestJsonData<String> exceptionHandler(HttpServletRequest request, Exception ex) {
        if (ex instanceof ApplicationException) {
            ApplicationException ae = (ApplicationException) ex;
            log.warn("Well known Error with message code {}", ae.getMessageCode());
            RestJsonData<String> restJsonData = new RestJsonData<>();
            restJsonData.setMessage(MessageUtil.getErrorMessage(messageSource, ae, request));
            return restJsonData;
        } else {
            log.error(ex.getMessage(), ex);
            RestJsonData<String> restJsonData = new RestJsonData<>();
            restJsonData.setMessage(MessageUtil.getErrorMessage(messageSource, ex, request));
            return restJsonData;
        }
    }
}
