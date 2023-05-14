package uni.plovdiv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.domain.dto.patron.CreatePatronDto;
import uni.plovdiv.domain.dto.patron.PatronInformationDto;
import uni.plovdiv.service.PatronService;

import java.util.List;

@RestController
@RequestMapping("/patron")
@Tag(name = "Patron endpoints")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @GetMapping("/all")
    @Operation(summary = "Get all patrons")
    public List<PatronInformationDto> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new patron")
    public void createPatron(
            @RequestBody CreatePatronDto createPatronDTO
    ) {
        patronService.createPatron(createPatronDTO);
    }
}
