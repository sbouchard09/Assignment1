package request;

import connection.Connection;

import java.util.Map;
import java.io.IOException;


public class PostRequest extends Request {

    boolean hasInputFile = false;
    boolean hasInlineData = false;
    String option;
    String inputFileName;

    public PostRequest(String url, boolean isVerbose, Map<String, String> headers, Map<String, String> options) throws IllegalArgumentException{
        super(url, isVerbose, headers);
        parseOptions(options);
        super.setConnection(new Connection("POST"));
    }

    private void parseOptions(Map<String, String> options) throws IllegalArgumentException{
        if(options == null) return;

        if(options.containsKey("inline_data")) {
            hasInlineData = true;
            option = options.get("inline_data");
        }

        if(options.containsKey("input_file")) {
            hasInputFile = true;
            option = options.get("input_file");
        }

        if(hasInlineData && hasInputFile) {
            throw new IllegalArgumentException("Can only have one of [-d] or [-f] options in POST request");
        }
    }

    public void sendRequest(){
        try {connection.openConnection(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(headers != null) {
            for (Map.Entry<String, String> header: headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }
        }
        connection.setBody(option);
        try {
            connection.sendRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.viewOutput();
    }
}