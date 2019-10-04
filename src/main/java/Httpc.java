import request.GetRequest;
import request.PostRequest;
import request.Request;

public class Httpc {

    public static void main(String[] args) {
        Request request;
        String requestType = ""; // GET, POST, empty (from args)
        boolean needsHelp = false;
        boolean isVerbose = false;

        if(needsHelp) {
            Help help = new Help(requestType);
            System.out.println(help.printHelp());
        }
    }
}
