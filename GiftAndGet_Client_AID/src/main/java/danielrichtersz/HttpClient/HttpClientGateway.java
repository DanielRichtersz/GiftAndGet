package danielrichtersz.HttpClient;

import danielrichtersz.models.UserAccount;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientGateway {

    final String baseURL = "http://localhost:8080/api";

    public String SendPostRequest(String endpoint, HashMap<String, String> parameters) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(baseURL + endpoint);

            List<NameValuePair> nameValuePairs = new ArrayList<>(1);

            // Using loop to add parameters
            for (Map.Entry<String,String> entry : parameters.entrySet()){

               // System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            System.out.println("Sending POST request to: " + baseURL + endpoint);
            System.out.println("Parameters: " + nameValuePairs.toString());

            return getResponse(httpClient.execute(request), httpClient, request);
        }
        catch (IOException e) {
            System.out.println(e);
            return e.toString();
        }
    }

    public String SendGetRequest(String endpoint) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(baseURL + endpoint);

            System.out.println("Sending GET request to: " + baseURL + endpoint);
            return getResponse(httpClient.execute(request), httpClient, request);
        }
        catch (IOException e) {
            System.out.println(e);
            return e.toString();
        }
    }

    private String getResponse(CloseableHttpResponse execute, CloseableHttpClient httpClient, HttpRequestBase request) throws IOException {
        HttpResponse response = execute;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent()));

        System.out.println("Reading response...");

        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }
}
