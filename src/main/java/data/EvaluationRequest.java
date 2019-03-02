package data;

public class EvaluationRequest {
    private String authId;
    private String sessionId;
    private String email;
    private String trueIP;

    public EvaluationRequest() {
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTrueIP(String trueIP) {
        this.trueIP = trueIP;
    }

    public String getAuthId() {
        return authId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getEmail() {
        return email;
    }

    public String getTrueIP() {
        return trueIP;
    }
}
