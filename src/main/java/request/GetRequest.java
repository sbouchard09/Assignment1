package request;

import java.io.IOException;
import java.util.Map;

public class GetRequest extends Request {

    public GetRequest(String url, boolean isVerbose) {
        super(url, isVerbose, null);
    }

    public GetRequest(String url, boolean isVerbose, Map<String, String> headers) {
        super(url, isVerbose, headers);
    }

    public GetRequest(String url, boolean isVerbose, Map<String, String> headers, String outputFileName) {
        super(url, isVerbose, headers, outputFileName);
    }

    public void sendRequest() {
        // do stuff
    }
}
