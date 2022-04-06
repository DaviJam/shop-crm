package eu.ensup.shopcrm.domain;

import java.io.Serializable;

public class CustomResponse implements Serializable {

    private int status;
    private String reason;
    private Object result;


    public CustomResponse(int status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public CustomResponse(int status, String reason, Object result) {
        this.status = status;
        this.reason = reason;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CustomResponse{" +
                "status=" + status +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
