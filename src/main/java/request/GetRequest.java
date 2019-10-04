package request;

import connection.Connection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.Map;

public class GetRequest extends Request {

    public GetRequest(String url, boolean isVerbose) {
        super(url, isVerbose, null);
        super.setConnection(new Connection("GET"));
    }

    public GetRequest(String url, boolean isVerbose, Map<String, String> headers) {
        super(url, isVerbose, headers);
        super.setConnection(new Connection("GET"));
    }

    public void sendRequest() {
        /*
        connection.setHeaders(....)
        super.viewOutput();
         */
    }
}
