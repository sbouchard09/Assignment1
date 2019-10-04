package request;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.Map;

public class GetRequest extends Request {

    public GetRequest(String url, boolean isVerbose) {
        super(url, isVerbose, null);
    }

    public GetRequest(String url, boolean isVerbose, Map<String, String> headers) {
        super(url, isVerbose, headers);
    }

    public void sendRequest() {
    }
}
