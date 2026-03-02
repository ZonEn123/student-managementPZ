package com.example.studentmanagement.common;


import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    // 成功
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMsg("成功");
        r.setData(data);
        return r;
    }

    // 失败
    public static <T> Result<T> fail(String msg) {
        Result<T> r = new Result<>();
        r.setCode(500);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

    // 【新增】为了兼容JWT代码：自定义状态码+提示信息
    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

    // 【新增】为了兼容JWT代码：仅传提示信息（别名方法，和fail功能一样）
    public static <T> Result<T> error(String msg) {
        return fail(msg);
    }
}
