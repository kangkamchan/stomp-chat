package net.stompchat.dto;

public enum MessageType {
    ENTER("ENTER"),
    CHAT("CHAT"),
    EXIT("EXIT");

    private final String type;
    MessageType(String type) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
}
