package lib;

public class CheckRequestException extends Exception {
    private String id;
    private String message;

    @Override
    public String toString() {
        return "CheckRequestException{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public CheckRequestException(String id, String message) {
        super(message);
        this.id = id;
        this.message = message;
    }
}
