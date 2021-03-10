package uz.pdp.apples8jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.apples8jpa.entity.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Integer> {

    List<Group> findAllByFaculty_University_Id(Integer faculty_university_id);

    @Query("select gr from groups gr where gr.faculty.university.id=:universityId")
    List<Group> getGroupByUniversityId(Integer universityId);

    @Query(value = "select * from groups gr join faculty f on f.id=gr.faculty_id " +
            "join university u on u.id=f.university_id" +
            " where u.id=:universityId" , nativeQuery = true)
    List<Group> getGroupByUniversityIdNative(Integer universityId   );
}
