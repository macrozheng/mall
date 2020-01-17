package com.ricadro.orderserviceapi.response;

import com.ricadro.orderserviceapi.enums.ResCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: ricadro
 * @Date: 2020/1/17 09:32
 */
@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String resCode;

    private String resMsg;

    private T data;

    public Boolean isSuccess(){
        return this.resCode.equals(ResCodeEnum.SUCCESS.getCode());
    }

}
