package uni.plovdiv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uni.plovdiv.dto.user.UserInformationDto;
import uni.plovdiv.service.PatronService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/patrons")
@SessionAttributes("oldPatron")
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

    @PostMapping("/find")
    public String findPatron(
            @RequestParam String ucn,
            RedirectAttributes redirectAttributes
    ) {
        UserInformationDto patron = patronService.findPatronByUcn(ucn);
        redirectAttributes.addFlashAttribute("patron", patron);
        return "redirect:/patrons/edit-page";
    }

    @RequestMapping("/edit/patron-info")
    public String findPatronPage() {
        return "edit-patron-info";
    }

    @RequestMapping("/edit-page")
    public String getEditPage(
            @ModelAttribute("patron") UserInformationDto patron,
            Model model
    ) {
        model.addAttribute("oldPatron", patron);
        return "edit-patron";
    }

    @PostMapping("/edit")
    public String editPatron(
            @ModelAttribute("patronUpdated") UserInformationDto patronUpdated,
            Model model
    ) {
        UserInformationDto oldPatron = (UserInformationDto) Objects.requireNonNull(model.getAttribute("oldPatron"));
        patronService.editPatron(oldPatron.getUcn(), patronUpdated);
        return "redirect:/patrons/";
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
