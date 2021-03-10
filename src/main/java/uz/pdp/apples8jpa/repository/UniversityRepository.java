package uz.pdp.apples8jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.apples8jpa.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University,Integer> {
}
