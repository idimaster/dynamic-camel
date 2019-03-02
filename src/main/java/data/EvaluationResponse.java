package data;

import java.util.UUID;

public class EvaluationResponse {
    private final UUID id;
    private String action;

    public EvaluationResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
