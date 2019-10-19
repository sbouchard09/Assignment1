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

    private int port = 80;

    public Connection(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void openConnection(String host) throws IOException {
        url = new URL(host);
        this.address = InetAddress.getByName(url.getHost().trim());
        if(url.getPort() != -1) {
            this.port = url.getPort();
        }
        socket = new Socket(address, port);
        requestWriter = new PrintWriter(socket.getOutputStream(), true);
        requestReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.headers = new HashMap<String, String>();
    }

    public void setRequestProperty(String key, String value) {
        headers.put(key, value);
    }

    public void setBody(String body) {
        format(body);
    }

    // format: '{"key": value, "key": value, ...}' to key=value&key=value&..
    private void format(String body) {
        if(body == null) {
            this.body = "";
            return;
        }

        try {
            String[] arguments = body.split(":");
            StringBuilder bodyBuilder = new StringBuilder();
            for(int i = 0; i < arguments.length; i+=2) {
                bodyBuilder.append(arguments[i].replaceAll("[^a-zA-Z0-9]", "") + "=" +
                        arguments[i + 1].replaceAll("[^a-zA-Z0-9]", ""));
                if(i + 2 < arguments.length) {
                    bodyBuilder.append("&");
                }
            }
            this.body = bodyBuilder.toString().trim();
        } catch(Exception e) { // wrong body format
            this.body = body.trim();
        }
    }

    public void sendRequest() throws IOException {
        prepareRequest();
        requestWriter.println(request);
        requestWriter.flush();
        socket.shutdownOutput();
        readResponse();
    }

    private void prepareRequest() {
        String host = url.getHost();
        String path = url.getPath();
        if(path == null || path.length() == 0) {
            path = "/";
        }
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(requestMethod.toUpperCase() + " " + path + " HTTP/1.0\r\n");
        requestBuilder.append("Host: " + host + "\r\n");
        if(requestMethod.equalsIgnoreCase("POST")) {
            if(!headers.containsKey("Content-Length") && body.length() > 1) {
                headers.put("Content-Length", "" + body.length());
            }
        }
        requestBuilder.append(addHeaders());
        if(requestMethod.equalsIgnoreCase("POST")) {
            requestBuilder.append(body);
        }

        request = requestBuilder.toString().trim();
    }

    private String addHeaders() {
        StringBuilder headerBuilder = new StringBuilder();

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
                String message = "";
                while((message = requestReader.readLine()) != null) {
                    sb.append(message + "\r\n");
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
