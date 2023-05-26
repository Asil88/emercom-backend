package by.EMERCOM.repository;

import by.EMERCOM.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByLogin(String login);

    @Query(nativeQuery = true, value = "SELECT role FROM roles WHERE user_id=:id")
    String getRole(int id);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO roles (user_id, role) VALUES (:userId, 'USER')")
    void setUserRole(Integer userId);

    List<User> findAllByName(String name);

    List<User> findAllBySurname(String name);

    List<User> findAllByCountry(String name);

}
