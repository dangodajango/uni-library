package uni.plovdiv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uni.plovdiv.domain.dto.patron.CreatePatronDto;
import uni.plovdiv.domain.dto.patron.PatronInformationDto;
import uni.plovdiv.service.PatronService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/patron")
@Tag(name = "Patron endpoints")
@RequiredArgsConstructor
public class PatronController {

    private final PatronService patronService;

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all patrons")
    public List<PatronInformationDto> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping(value = "/{username}", produces = APPLICATION_JSON_VALUE)
    public PatronInformationDto getPatronFromUsername(
            @PathVariable String username
    ) {
        return patronService.getPatronFromUsername(username);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new patron")
    public void createPatron(
            @RequestBody CreatePatronDto createPatronDTO
    ) {
        patronService.createPatron(createPatronDTO);
    }

    @PutMapping("/update")
    @Operation(summary = "Update an existing patron")
    public void updatePatron(
            @RequestParam String ucn,
            @RequestBody PatronInformationDto patronInformationDto
    ) {
        patronService.updatePatron(ucn, patronInformationDto);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a patron")
    public void deletePatron(
            @RequestParam String ucn
    ) {
        patronService.deletePatron(ucn);
    }
}
