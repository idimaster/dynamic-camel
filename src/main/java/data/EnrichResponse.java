package data;

public class EnrichResponse {
    private String sessionId;
    private String deviceId;
    private String proxyIP;
    private String recomendation;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getProxyIP() {
        return proxyIP;
    }

    public void setProxyIP(String proxyIP) {
        this.proxyIP = proxyIP;
    }

    public String getRecomendation() {
        return recomendation;
    }

    public void setRecomendation(String recomendation) {
        this.recomendation = recomendation;
    }

    @Override
    public String toString() {
        return "EnrichResponse{" +
                "sessionId='" + sessionId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", proxyIP='" + proxyIP + '\'' +
                ", recomendation='" + recomendation + '\'' +
                '}';
    }
}
