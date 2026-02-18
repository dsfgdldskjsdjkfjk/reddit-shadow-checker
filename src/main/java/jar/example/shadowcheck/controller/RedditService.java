package jar.example.shadowcheck.controller;

public class RedditService {

    public String checkUserStatus(String username) {
        // Check the status of a Reddit user
        if (username == null || username.isEmpty()) {
            return "Invalid username";
        }
        return "User status for: " + username;
    }

}
