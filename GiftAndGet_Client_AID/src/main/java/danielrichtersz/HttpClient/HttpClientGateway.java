package danielrichtersz.HttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpClientGateway {

    final String baseURL = "http://localhost:8080/api";

    public void SendGetRequest() {


    }

    public void SendPostRequest() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost post = new HttpPost(baseURL + "/users");

            List<NameValuePair> nameValuePairs = new ArrayList<>(1);
            nameValuePairs.add(new BasicNameValuePair("email", "runtimeEmail@mail.com"));
            nameValuePairs.add(new BasicNameValuePair("password", "runtimePassword"));
            nameValuePairs.add(new BasicNameValuePair("username", "runtimeUsername"));

            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("Auth=")) {
                    String key = line.substring(5);
                    // do something with the key
                }

            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public String LoginUser(String username) {
        if (username != null) {
            try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

                HttpGet request = new HttpGet(baseURL + "/users/" + username);
                HttpResponse response = httpClient.execute(request);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((
                        response.getEntity().getContent()
                )));

                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    System.out.println(line);
                }

                return stringBuilder.toString();
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }

        return null;
    }
}
