package com.example.back.pojo;

import lombok.Data;

/**
 * @author lala
 */
@Data
public class Result {
    //其中msg是你想要返回的信息，code就是自定义的状态码了,data是要返回的数据，这里我采用了Object类型。
    private Integer code;
    private String msg;
    private Object data;
}

