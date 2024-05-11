package utilities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ApiRequest {

    public static double conversor(String primaryCoin, String secondaryCoin) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        try {
            URI apiUrl = URI.create("https://v6.exchangerate-api.com/v6/432b64534090a19a64b42cf7/latest/" + primaryCoin);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.valueOf(apiUrl)))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            JsonObject jSon = gson.fromJson(json, JsonObject.class);
            JsonObject conversionRates = jSon.getAsJsonObject("conversion_rates");
            return conversionRates.get(secondaryCoin).getAsDouble();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
