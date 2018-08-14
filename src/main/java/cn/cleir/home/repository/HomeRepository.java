package cn.cleir.home.repository;

import cn.cleir.home.domain.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<Bus, Integer> {
}
