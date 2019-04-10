package danielrichtersz.HttpClient;

public class ExtractMethods {
    public static String extractUsernameFromResponse(String response) {

        int start = response.indexOf("\"username\":") + 12;
        int end = response.indexOf("\",", start);

        if (start == -1 || end == -1) {
            return null;
        }
        return response.substring(start, end);
    }
}
