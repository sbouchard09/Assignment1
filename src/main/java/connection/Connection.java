package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Connection {

    private String requestMethod; // GET | POST
    private String request;
    private String response;
    private String body;
    private InetAddress address;
    private Socket socket;
    private PrintWriter requestWriter;
    private BufferedReader requestReader;
    private Map<String, String> headers;
    private URL url; // used to parse the URL

    private final int PORT = 80;

    public Connection(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void openConnection(String host) throws IOException {
        this.address = InetAddress.getByName(host);
        socket = new Socket(address, PORT);
        requestWriter = new PrintWriter(socket.getOutputStream(), true);
        requestReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.headers = new HashMap<String, String>();
        this.url = new URL(host);
    }

    public void setRequestProperty(String key, String value) {
        headers.put(key, value);
    }

    public void setBody(String body) {
        format(body);
    }

    // format: '{"key": value, "key": value, ...}' to key=value&key=value&..
    private void format(String body) {
        String[] arguments = body.split(":");
        StringBuilder bodyBuilder = new StringBuilder();
        for(int i = 0; i < arguments.length; i+=2) {
            bodyBuilder.append(arguments[i].replaceAll("[^a-zA-Z0-9]", "") + "=" + arguments[i + 1]);
            if(i + 2 < arguments.length) {
                bodyBuilder.append("&");
            }
        }
        this.body = bodyBuilder.toString();
    }

    public void sendRequest() throws IOException {
        prepareRequest();
        requestWriter.println(request);
        readResponse();
    }

    private void prepareRequest() {
        String host = url.getHost();
        String path = url.getPath();
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(requestMethod.toUpperCase() + " " + path + " HTTP/1.0\r\n");
        requestBuilder.append("Host: " + host + "\r\n");
        if(requestMethod.equalsIgnoreCase("POST")) {
            if(!headers.containsKey("Content-Length")) {
                headers.put("Content-Length", "" + body.length());
            }
        }
        requestBuilder.append(addHeaders());
        if(requestMethod.equalsIgnoreCase("POST")) {
            requestBuilder.append(body);
        }

        request = requestBuilder.toString();
    }

    private String addHeaders() {
        StringBuilder headerBuilder = new StringBuilder();

        headerBuilder.append("Content-Type: application/json\r\n");

        for(Map.Entry<String, String> header : headers.entrySet()) {
            headerBuilder.append(header.getKey() + ": " + header.getValue() + "\r\n");
        }
        headerBuilder.append("\r\n");

        return headerBuilder.toString();
    }

    private void readResponse() throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean isReading = true;

        while(isReading) {
            if(requestReader.ready()) {
                int i = 0;
                while(i != -1) {
                    i = requestReader.read();
                    sb.append((char) i);
                }
                isReading = false;
            }
        }

        response = sb.toString();
        socket.close();
    }

    public String getResponse() {
        return response;
    }
}
