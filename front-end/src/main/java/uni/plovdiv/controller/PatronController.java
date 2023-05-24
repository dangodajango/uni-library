package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uni.plovdiv.dto.user.UserInformationDto;
import uni.plovdiv.service.PatronService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @GetMapping("/")
    public String getAllPatrons(
            @RequestParam(required = false) String displayName,
            @RequestParam(required = false) String ucn,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
            Model model
    ) {
        List<UserInformationDto> patrons = patronService.getAllPatronsInformation(displayName, ucn, birthDate);
        model.addAttribute("patrons", patrons);
        return "patrons";
    }

    @RequestMapping("/delete-page")
    public String getPatronDeletePage() {
        return "delete-patron";
    }

    @PostMapping("/delete")
    public String deletePatron(
            @RequestParam String ucn
    ) {
        patronService.deletePatron(ucn);
        return "redirect:/patrons/";
    }
}
