import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class Handler implements URLHandler {
    ArrayList<String> fruitList = new ArrayList<String>();

    public String handleRequest(URI url) {

        if (url.getPath().equals("/")) {
            return String.format("List of Fruits:" + fruitList);
        } else {
            System.out.println("Path: " + url.getPath());

            if (url.getPath().contains("/add")) {
                fruitList.add(url.getQuery().split("s=")[1]);
                return String.format("Object added!");
            }

            if (url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                ArrayList<String> search_results = new ArrayList<>();
                if (parameters[0].equals("s")) {
                    for (String fruit: fruitList) {
                        if (fruit.contains(parameters[1])) {
                            search_results.add(fruit);
                            return search_results.toString();
                        }
                    }
                }
                //return String.format("Object added!", parameters[1], search_results);
                return fruitList.toString();
                


            }
            return "404 Not Found!";
        }
    }
}

class FruitServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
