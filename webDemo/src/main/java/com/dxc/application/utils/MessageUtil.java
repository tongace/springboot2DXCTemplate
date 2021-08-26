package com.dxc.application.utils;

import com.dxc.application.constants.MessagesConstants;
import com.dxc.application.exceptions.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;

public final class MessageUtil {
    // protected new Instant
    private MessageUtil() {
    }

    private static String getCauseErrorMessage(Exception e) {
        Throwable cause = e.getCause();

        return (cause == null ? e.getMessage() : cause.getMessage());
    }

    public static String getErrorMessage(MessageSource messageSource, Exception e, HttpServletRequest request) {

        return getErrorMessage(messageSource, e, null, request);
    }

    public static String getErrorMessage(MessageSource messageSource, Exception e, Object[] messageParam, HttpServletRequest request) {
        String error = getCauseErrorMessage(e);

        if (e instanceof ApplicationException) {
            ApplicationException ae = (ApplicationException) e;
            return messageSource.getMessage(ae.getMessageCode(), ae.getParam(), RequestContextUtils.getLocale(request));

        } else if (e instanceof DuplicateKeyException) {
            return messageSource.getMessage(MessagesConstants.DATA_DUPLICATED, new String[]{}, RequestContextUtils.getLocale(request));
        } else {
            return messageSource.getMessage(MessagesConstants.ERROR_UNDEFINED_ERROR, new String[]{error}, RequestContextUtils.getLocale(request));
        }
    }

}
