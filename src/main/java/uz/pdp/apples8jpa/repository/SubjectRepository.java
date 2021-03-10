package uz.pdp.apples8jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apples8jpa.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    boolean existsByName(String name);

}
