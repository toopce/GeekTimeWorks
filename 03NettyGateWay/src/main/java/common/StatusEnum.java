package common;

public enum StatusEnum {
    //通过登录
    PASS("YES","1"),
    //没通过登录
    REJECTED("FALSE","0");

    private String status;
    private String num;

    StatusEnum(String status, String num) {
        this.status = status;
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
