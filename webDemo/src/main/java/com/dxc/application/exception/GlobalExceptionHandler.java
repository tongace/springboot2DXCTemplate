package com.dxc.application.exception;

import com.dxc.application.constants.MessagesConstants;
import com.dxc.application.exceptions.ApplicationException;
import com.dxc.application.feature.common.dto.RestJsonData;
import com.dxc.application.utils.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public final class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler({ApplicationException.class})
    @ResponseBody
    public RestJsonData<String> applicationExceptionHandler(HttpServletRequest request, ApplicationException ae) {
        log.warn("Well known Error with message code {}", ae.getMessageCode());
        RestJsonData<String> restJsonData = new RestJsonData<>();
        restJsonData.setMessage(MessageUtil.getErrorMessage(messageSource, ae, request));
        return restJsonData;
    }

    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseBody
    public RestJsonData<String> applicationExceptionHandler(HttpServletRequest request, DuplicateKeyException ae) {
        log.warn("Well known Error with message code insert database duplicated keys");
        RestJsonData<String> restJsonData = new RestJsonData<>();
        restJsonData.setMessage(messageSource.getMessage(MessagesConstants.DATA_DUPLICATED, new String[]{}, RequestContextUtils.getLocale(request)));
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
