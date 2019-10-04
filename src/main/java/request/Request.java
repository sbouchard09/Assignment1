package request;

import java.util.Map;

public abstract class Request {

    protected Map<String, String> headers;
    protected boolean isVerbose;
    protected String url;

    public Request() { }

    public Request(String url, boolean isVerbose, Map<String, String> headers) {
        this.url = url;
        this.isVerbose = isVerbose;
        this.headers = headers;
    }

    abstract void sendRequest();

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean isVerbose() {
        return isVerbose;
    }

    public void setVerbose(boolean verbose) {
        isVerbose = verbose;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
