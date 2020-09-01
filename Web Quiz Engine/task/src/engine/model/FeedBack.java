package engine.model;

public class FeedBack {

    private static final String SUCCESS_STRING = "That was an easy one, ha? :)";
    private static final String FAIL_STRING = "Our mistakes are just small steps on the long road of Experience. Please, try again.";

    private boolean success;
    private String feedback;

    public FeedBack(boolean success) {
        this.success = success;
        this.feedback = success ? SUCCESS_STRING : FAIL_STRING;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }

}
