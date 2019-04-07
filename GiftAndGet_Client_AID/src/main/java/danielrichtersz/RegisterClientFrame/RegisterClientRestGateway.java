package danielrichtersz.RegisterClientFrame;

import danielrichtersz.HttpClient.HttpClientGateway;

import java.util.HashMap;

public class RegisterClientRestGateway {

    private HttpClientGateway httpClientGateway = new HttpClientGateway();

    public RegisterClientRestGateway() {

    }

    public String RegisterNewUser(String email, String username, String password) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("email", email);
        parameters.put("password", password);
        parameters.put("username", username);
        return httpClientGateway.SendPostRequest("/users", parameters);
    }
}
