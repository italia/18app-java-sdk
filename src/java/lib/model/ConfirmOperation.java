package lib.model;


public enum ConfirmOperation {

    CONFIRM("1");

    String type;

    ConfirmOperation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
