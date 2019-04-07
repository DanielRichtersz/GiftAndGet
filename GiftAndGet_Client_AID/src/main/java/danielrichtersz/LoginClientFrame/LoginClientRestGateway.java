package danielrichtersz.LoginClientFrame;

import danielrichtersz.HttpClient.HttpClientGateway;

import java.util.HashMap;

public class LoginClientRestGateway {

    HttpClientGateway httpClientGateway = new HttpClientGateway();

    public LoginClientRestGateway() {

    }

    public String LoginUser(String username) {
        return httpClientGateway.SendGetRequest( "/users/" + username);
    }
}
