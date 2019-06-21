package com.sixet.skeleton.core.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Class to determine the Standard Error Handler.
 */
@Data
@AllArgsConstructor
public class StandardErrorHandler implements Serializable {
    private static final long serialVersionUID = 1L;
    private String exception;
    private Long timestamp;
    private Integer status;
    private String errorMessage;
    private String path;
}
