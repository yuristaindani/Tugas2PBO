import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

class Server {
    static void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(response.getBytes());
        output.flush();
        output.close();
    }

    static Request getRequest(HttpExchange exchange) throws IOException {
        InputStreamReader reader = new InputStreamReader(exchange.getRequestBody());
        StringBuilder sb = new StringBuilder();
        int data;
        try {
            while ((data = reader.read()) != -1) {
                sb.append((char) data);
            }

            String requestBody = sb.toString();
            String[] requestBodyParts = requestBody.split("&");
            String name = null;
            double price = 0.0;
            for (String part : requestBodyParts) {
                String[] keyValue = part.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    if (key.equals("name")) {
                        name = value;
                    } else if (key.equals("price")) {
                        price = Double.parseDouble(value);
                    }
                }
            }
            return new Request(name, price);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    static int getProductIdFromPath(String path) {
        // Mendapatkan ID produk dari URL path
        String[] pathParts = path.split("/");
        return Integer.parseInt(pathParts[pathParts.length - 1]);
    }
}
