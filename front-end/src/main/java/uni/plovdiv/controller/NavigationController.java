package uni.plovdiv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class NavigationController {


    @RequestMapping("/update")
    public String updateBook() {
        return "edit-book";
    }

    @RequestMapping("/delete")
    public String deleteBook() {
        return "delete-book";
    }
}
