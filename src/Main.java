import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        // Mengatur port untuk API
        int port = 8078;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        // Menginisialisasi server dengan port yang telah ditentukan
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);


        server.createContext("/users", new Response.UsersHandler());
        server.createContext("/products", new Response.ProductsHandler());
        server.createContext("/orders", new Response.OrdersHandler());
        server.createContext("/detail", new Response.OrderDetailsHandler());
        server.createContext("/reviews", new Response.ReviewHandler());

        // Add more handlers for other endpoints (/products, /orders, /reviews) if needed
        server.setExecutor(null);
        server.createContext("/api/data", new Server.DataHandler());
        server.start();
        System.out.println("Listening on port: "+port);
    }
}
