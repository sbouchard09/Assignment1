package request;

import connection.Connection;

import java.util.Map;

public class PostRequest extends Request {

    boolean hasInputFile = false;
    boolean hasInlineData = false;
    String inlineData;
    String inputFileName;

    public PostRequest(String url, boolean isVerbose, Map<String, String> headers, Map<String, String> options) throws IllegalArgumentException{
        super(url, isVerbose, headers);
        parseOptions(options);
        super.setConnection(new Connection("GET"));
    }

    private void parseOptions(Map<String, String> options) throws IllegalArgumentException{
        if(options.containsKey("inline_data")) {
            hasInlineData = true;
            inlineData = options.get("inline_data");
        }

        if(options.containsKey("input_file")) {
            hasInputFile = true;
            inlineData = options.get("input_file");
        }

        if(hasInlineData && hasInputFile) {
            throw new IllegalArgumentException("Can only have one of [-d] or [-f] options in POST request");
        }
    }

    public void sendRequest() {
        // do stuff
    }
}
