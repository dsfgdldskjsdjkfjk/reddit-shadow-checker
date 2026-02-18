package jar.example.shadowcheck.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service //
public class RedditService {

    private final RestClient restClient;

    public RedditService() {
        // We build the client with a custom User-Agent to avoid 429/403 errors from Reddit
        this.restClient = RestClient.builder()
                .baseUrl("https://www.reddit.com")
                .defaultHeader("User-Agent", "SpringBoot:ShadowChecker:v1.0")
                .build();
    }

    public String checkUserStatus(String username) {
        try {
            // We check the .json endpoint because it's lightweight
            restClient.get()
                    .uri("/user/{username}/about.json", username)
                    .retrieve()
                    .toBodilessEntity();

            return "User is ACTIVE";
        } catch (HttpClientErrorException.NotFound e) {
            // Reddit returns 404 for shadowbanned or non-existent users
            return "User is SHADOWBANNED or DOES NOT EXIST";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}