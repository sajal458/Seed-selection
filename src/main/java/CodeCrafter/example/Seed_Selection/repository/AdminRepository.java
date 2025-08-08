package CodeCrafter.example.Seed_Selection.repository;

import CodeCrafter.example.Seed_Selection.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsernameAndPassword(String username, String password);
}
