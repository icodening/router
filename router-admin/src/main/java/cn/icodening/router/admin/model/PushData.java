package cn.icodening.router.admin.model;

import java.util.List;

/**
 * @author icodening
 * @date 2021.04.19
 */
public class PushData {

    private final long timestamp = System.currentTimeMillis();

    private List<String> targetAddress;

    private Object data;

    public PushData() {
    }

    public PushData(Object data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(List<String> targetAddress) {
        this.targetAddress = targetAddress;
    }
}
