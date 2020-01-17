package com.ricadro.orderserviceapi.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ricadro
 * @Date: 2020/1/17 09:53
 */
@Data
public abstract class PageRequest extends BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pageSize = 10;
    private Integer pageNum = 1;
}
