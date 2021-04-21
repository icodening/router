package cn.icodening.router.admin.vo;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class Result {

    private boolean success = true;

    private Object data;

    private String message = "success";

    public Result() {

    }

    public Result(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
