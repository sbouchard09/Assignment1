import request.GetRequest;
import request.PostRequest;
import request.Request;

public class Httpc {

    public static void main(String[] args) {
        boolean needsHelp = false;
        String requestType = "";
        Request request;

        /*
        parse input
         */

        if(needsHelp) {
            Help help = new Help(requestType);
            System.out.println(help.printHelp());
        }

        /*
        if(GET) {
           request = new GetRequest(String url, boolean isVerbose, headers);
           request.sendRequest();
        }
        else if(POST) {
           request = new PostRequest(String url, boolean, ....);
           request.sendRequest();
        }
         */

    }
}
