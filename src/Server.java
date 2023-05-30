import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
    private static final String API_KEY_ENV_VARIABLE = "API_KEY";
    //    private static final String DATABASE_URL = "jdbc:sqlite:path-to-your-sqlite-database";

    static class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("PUT")) {
                String apiKey = System.getenv(API_KEY_ENV_VARIABLE);
                String requestApiKey = exchange.getRequestHeaders().getFirst("Authorization");

                if (requestApiKey == null || !requestApiKey.equals(apiKey)) {
                    sendResponse(exchange, 401, "Unauthorized");
                    return;
                }

                // Proses permintaan dan manipulasi database
                // ...
                // Contoh: Menambahkan data ke database
                // String jsonData = requestBodyToString(exchange);
                // Data data = parseJsonData(jsonData);
                // insertDataToDatabase(data);
                // sendResponse(exchange, 200, "Data added to database.");

                sendResponse(exchange, 200, "Request authorized. Data added to database.");
            } else {
                sendResponse(exchange, 404, "Not Found");
            }
        }
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

}
