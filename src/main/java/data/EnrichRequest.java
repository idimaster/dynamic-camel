package data;

public class EnrichRequest {
    private String sessionId;

    public EnrichRequest() {
    }

    public EnrichRequest(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "EnrichRequest{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
