package request;

import connection.Connection;

import java.util.Map;

public abstract class Request {

    protected Map<String, String> headers;
    protected boolean isVerbose;
    protected String url;
    protected Connection connection;

    public Request() { }

    public Request(String url, boolean isVerbose, Map<String, String> headers) {
        this.url = url;
        this.isVerbose = isVerbose;
        this.headers = headers;
    }

    abstract void sendRequest();

    public void setConnection(Connection c) {
        this.connection = c;
    }

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

    public void viewOutput() {

        // TODO: Scott will do this

    }
}
