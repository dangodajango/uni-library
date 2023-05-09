package uni.plovdiv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.dto.patron.PatronInformationDTO;
import uni.plovdiv.service.PatronService;

@RestController
@RequestMapping("/patron")
@Tag(name = "Patron endpoints")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @PostMapping("/create")
    @Operation(summary = "Create a new patron")
    public void createPatron(
            @RequestBody PatronInformationDTO patronInformationDTO
    ) {
        patronService.createPatron(patronInformationDTO);
    }
}
