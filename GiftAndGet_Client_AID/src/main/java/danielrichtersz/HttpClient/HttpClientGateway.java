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

    public String RegisterNewUser(String email, String username, String password) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("password", password);
        parameters.put("username", username);
        return SendPostRequest("/users", parameters);
        /*try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost post = new HttpPost(baseURL + "/users");

            List<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("email", email));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            nameValuePairs.add(new BasicNameValuePair("username", username));

            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuilder stringBuilder = new StringBuilder();

            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);

                if (line.startsWith("Auth=")) {
                    String key = line.substring(5);
                    // do something with the key
                }
                else {
                    stringBuilder.append(line);
                }
            }

            return stringBuilder.toString();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        return null;*/
    }

    public String LoginUser(String username) {
        return SendGetRequest( "/users/" + username);
    }

    public String SendPostRequest(String endpoint, HashMap<String, String> parameters) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(baseURL + endpoint);

            List<NameValuePair> nameValuePairs = new ArrayList<>(1);

            // Using loop to add parameters
            for (Map.Entry<String,String> entry : parameters.entrySet()){

                System.out.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());

                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

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

        StringBuilder stringBuilder = new StringBuilder();

        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }


}
