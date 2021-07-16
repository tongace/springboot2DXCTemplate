package com.dxc.application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApplicationException extends Exception {
    private final String messageCode;
    private final Object param[];
}
