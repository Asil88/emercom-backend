package by.EMERCOM.service.impl;

import by.EMERCOM.model.domain.Team;
import by.EMERCOM.repository.TeamRepository;
import by.EMERCOM.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Optional<List<Team>> findAllTeams() {
        return Optional.of((ArrayList<Team>) teamRepository.findAll());
    }

    @Override
    public Optional<Team> getTeamById(int id) {
        return teamRepository.findById(id);
    }

    @Override
    public void updateTeamById(Team team) {
        teamRepository.saveAndFlush(team);
    }

    @Override
    public boolean deleteTeamById(Integer id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        try {
            if (optionalTeam.isPresent()) {
                teamRepository.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
