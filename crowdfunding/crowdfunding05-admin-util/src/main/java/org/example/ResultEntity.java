package org.example;

public class ResultEntity<T> {
    public static final String SUCCESS="SUCCESS";
    public static final String FAILED="FAILED";
    //用来封装当前请求结果是成功还是失败
    private String result;
    //请求处理失败时返回的错误信息
    private String message;
    //要返回的数据
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    /**
     * 请求成功且不需要返回数据时使用的工具方法
     * @reture
     */
    public static <T>ResultEntity<T> successWithoutData(){
        return new ResultEntity<T>(SUCCESS,null,null);
    }
    /**
     * 请求成功且需要返回数据时使用的工具方法
     * @param data 要返回的数据
     * @reture
     */
    public static <T>ResultEntity<T> successWithData(T data){
        return new ResultEntity<T>(SUCCESS,null,data);
    }
    /**
     * 请求失败使用的工具方法
     * @param message 要返回的失败的信息
     * @reture
     */
    public static <T>ResultEntity<T> failed(String message){
        return new ResultEntity<T>(FAILED,message,null);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
