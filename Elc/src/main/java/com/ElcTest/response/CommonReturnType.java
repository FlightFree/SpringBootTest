package com.ElcTest.response;

public class CommonReturnType {
    //表明请求返回的状态
    //成功success失败fail
    private String status;
    //请求返回的json
    private Object date;

    public static CommonReturnType create( Object result){
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create( Object result,String status){
        CommonReturnType tCommonReturnType=new CommonReturnType();
        tCommonReturnType.setStatus(status);
        tCommonReturnType.setDate(result);
        return tCommonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
