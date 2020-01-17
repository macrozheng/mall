package com.ricadro.orderserviceapi.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ricadro
 * @Date: 2020/1/17 09:28
 */
@Data
public abstract class BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date reqTime;

    private String reqId;

    private String appId;

}
