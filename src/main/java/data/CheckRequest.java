package data;

public class CheckRequest {
    private String name;
    private String value;

    public CheckRequest() {
    }

    public CheckRequest(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CheckRequest{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}



