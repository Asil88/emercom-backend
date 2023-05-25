package by.EMERCOM.controller;

import by.EMERCOM.exception.ResourceNotFoundException;
import by.EMERCOM.mapper.UserToResponseUserMapper;
import by.EMERCOM.model.domain.Team;
import by.EMERCOM.model.domain.User;
import by.EMERCOM.model.response.UserResponse;
import by.EMERCOM.service.TeamService;
import by.EMERCOM.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    @GetMapping("")
    public ResponseEntity<List<Team>> findAllTeams() {
        List<Team> allTeams = teamService.findAllTeams().orElseThrow(() -> new ResourceNotFoundException("USERS_NOT_FOUND"));
        return new ResponseEntity<>(allTeams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable int id) {
        Team teamById = teamService.getTeamById(id).orElseThrow(() -> new ResourceNotFoundException("TEAM_NOT_FOUND"));
        return new ResponseEntity<>(teamById, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateTeamById(@RequestBody @Valid Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        teamService.updateTeamById(team);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTeamById(@PathVariable Integer id) {
        boolean isDeleted = teamService.deleteTeamById(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
