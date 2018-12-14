package ua.training.cashmachine.controller.dto;

public class Alert {

    private Type type;
    private String attentionText;
    private String message;

    public enum Type {
        INFO, WARN, DANGER, SUCCESS;
    }

    public Alert(String attentionText, String message, Type type) {
        this.attentionText = attentionText;
        this.message = message;
        this.type = type;
    }

    public String getType() {
        return type.name().toLowerCase();
    }

    public String getAttentionText() {
        return attentionText;
    }

    public String getMessage() {
        return message;
    }
}
