package my.home.tgbot;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HiController {
// Logger log = org.slf4j.LoggerFactory.getLogger(HiController.class);
    @RequestMapping("/h")
    public String hiEndpoint(){
        log.info("Hi Endpoint");
        System.out.println("Hi Endpoint");
        return "hi";
    }
}
