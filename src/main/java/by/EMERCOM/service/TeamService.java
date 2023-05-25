package by.EMERCOM.service;

import by.EMERCOM.model.domain.Team;
import by.EMERCOM.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface TeamService {
        Optional<List<Team>> findAllTeams();

        Optional<Team> getTeamById(int id);

        void updateTeamById(Team team);

        boolean deleteTeamById(Integer id);
}
