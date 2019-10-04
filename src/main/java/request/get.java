package request;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class get {

    private HttpURLConnection connection;
    private URL url;

    private void sendGetRequest() throws IOException, MalformedURLException, ProtocolException {
        String requestUrl = "";

        url = new URL(requestUrl);
        connection = (HttpURLConnection)url.openConnection();

        connection.setRequestMethod("GET");

    }
}
