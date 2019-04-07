package danielrichtersz.ActionGroupsOverviewFrame;

import danielrichtersz.HttpClient.HttpClientGateway;

import java.util.HashMap;

public class ActionGroupsOverviewRestGateway {

    private HttpClientGateway httpClientGateway = new HttpClientGateway();

    public ActionGroupsOverviewRestGateway() {

    }

    public void getCharities() {
        httpClientGateway.SendGetRequest("/charities");
    }

    public void createActionGroup(String title, String description, long minimum, String username, String charityName) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("title", title);
        parameters.put("description", description);
        parameters.put("minimum", Long.toString(minimum));
        parameters.put("username", username);
        parameters.put("charityName", charityName);
        httpClientGateway.SendPostRequest("/actiongroups", parameters);
    }
}
