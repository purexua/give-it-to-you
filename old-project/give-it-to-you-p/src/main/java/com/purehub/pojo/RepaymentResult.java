package com.purehub.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentResult {
    private Integer status;
    private String message;
    private Object data;

    public RepaymentResult success(Object data, String message){
        return new RepaymentResult(0,message,data);
    }
    public RepaymentResult error(String message){
        return new RepaymentResult(1,message,null);
    }
}
