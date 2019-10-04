import request.GetRequest;
import request.PostRequest;
import request.Request;

public class Httpc {

    public static void main(String[] args) {
        Request request;
        boolean isPost = false;

        // parse

        if(isPost) {
            request = new PostRequest(/*args*/);
        } else { // get request
            request = new GetRequest(/*args*/);
        }

        // response
        // parse response
        // output
    }
}
