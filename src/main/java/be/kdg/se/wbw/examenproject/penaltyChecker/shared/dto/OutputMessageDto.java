package be.kdg.se.wbw.examenproject.penaltyChecker.shared.dto;

public class OutputMessageDto {
    private final String message;

    public OutputMessageDto(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
