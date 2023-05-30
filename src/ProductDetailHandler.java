import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;

class ProductDetailHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            // Mendapatkan detail produk berdasarkan ID
            int productId = Server.getProductIdFromPath(exchange.getRequestURI().getPath());
            Product product = Database.getProductById(productId);
            if (product != null) {
                JSONObject productJson = new JSONObject();
                productJson.put("id", product.getId());
                productJson.put("name", product.getName());
                productJson.put("price", product.getPrice());
                Server.sendResponse(exchange, productJson.toString(), 200);
            } else {
                String response = "Product not found.";
                Server.sendResponse(exchange, response, 404);
            }
        } else if (exchange.getRequestMethod().equalsIgnoreCase("PUT")) {
            // Mengubah data produk berdasarkan ID
            int productId = Server.getProductIdFromPath(exchange.getRequestURI().getPath());
            Product product = Database.getProductById(productId);
            if (product != null) {
                Request request = Server.getRequest(exchange);
                product.setName(request.getName());
                product.setPrice(request.getPrice());
                Database.updateProduct(product);
                JSONObject productJson = new JSONObject();
                productJson.put("id", product.getId());
                productJson.put("name", product.getName());
                productJson.put("price", product.getPrice());
                Server.sendResponse(exchange, productJson.toString(), 200);
            } else {
                String response = "Product not found.";
                Server.sendResponse(exchange, response, 404);
            }
        } else if (exchange.getRequestMethod().equalsIgnoreCase("DELETE")) {
            // Menghapus produk berdasarkan ID
            int productId = Server.getProductIdFromPath(exchange.getRequestURI().getPath());
            Product product = Database.getProductById(productId);
            if (product != null) {
                Database.removeProduct(product);
                String response = "Product deleted successfully.";
                Server.sendResponse(exchange, response, 200);
            } else {
                String response = "Product not found.";
                Server.sendResponse(exchange, response, 404);
            }
        } else {
            // Method not allowed
            String response = "Method not allowed.";
            Server.sendResponse(exchange, response, 405);
        }
    }
}
