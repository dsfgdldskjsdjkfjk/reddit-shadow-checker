package jar.example.shadowcheck.service;
import org.springframework.stereotype.Controller; // Changed from @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Use @Controller to serve HTML templates
public class ShadowCheckController {

    private final RedditService redditService;

    public ShadowCheckController(RedditService redditService) {
        this.redditService = redditService;
    }

    // This loads the initial page at http://localhost:8080/
    @GetMapping("/")
    public String home() {
        return "index"; // refers to index.html
    }

    // This handles the form submission
    @GetMapping("/check")
    public String checkUser(@RequestParam String username, Model model) {
        String result = redditService.checkUserStatus(username);
        model.addAttribute("username", username);
        model.addAttribute("status", result);
        return "index"; // re-loads index.html with the data
    }
}