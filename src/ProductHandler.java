import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

class ProductHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                // Mengambil daftar produk
                JSONArray productList = new JSONArray();
                List<Product> products = Database.getAllProducts();
                for (Product product : products) {
                    JSONObject productJson = new JSONObject();
                    productJson.put("id", product.getId());
                    productJson.put("name", product.getName());
                    productJson.put("price", product.getPrice());
                    productList.put(productJson);
                }
                Server.sendResponse(exchange, productList.toString(), 200);
            } else if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                // Menambahkan produk baru
                Request request = Server.getRequest(exchange);
                Product product = new Product(request.getId(), request.getName(), request.getPrice());
                Database.addProduct(product);
                JSONObject productJson = new JSONObject();
                productJson.put("id", product.getId());
                productJson.put("name", product.getName());
                productJson.put("price", product.getPrice());
                Server.sendResponse(exchange, productJson.toString(), 201);
            } else {
                // Method not allowed
                String response = "Method not allowed.";
                Server.sendResponse(exchange, response, 405);
            }
        }
    }
