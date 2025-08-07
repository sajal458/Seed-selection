package CodeCrafter.example.Seed_Selection.repository;

import CodeCrafter.example.Seed_Selection.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}