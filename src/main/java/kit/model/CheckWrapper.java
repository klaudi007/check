package kit.model;

import java.util.List;

/**
 * Created by musa on 7/25/17.
 */
public class CheckWrapper {

    private int status;
    private List<Check> check;

    public CheckWrapper(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Check> getCheck() {
        return check;
    }

    public void setCheck(List<Check> check) {
        this.check = check;
    }
}
