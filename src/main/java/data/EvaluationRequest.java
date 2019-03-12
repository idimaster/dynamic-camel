package data;

public class EvaluationRequest {
    private String domain;
    private String policy;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    @Override
    public String toString() {
        return "EvaluationRequest{" +
                "domain='" + domain + '\'' +
                ", policy='" + policy + '\'' +
                ", authId='" + authId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", email='" + email + '\'' +
                ", trueIP='" + trueIP + '\'' +
                '}';
    }
}
