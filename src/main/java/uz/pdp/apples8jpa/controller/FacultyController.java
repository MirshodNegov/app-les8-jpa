package uz.pdp.apples8jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apples8jpa.entity.Faculty;
import uz.pdp.apples8jpa.entity.University;
import uz.pdp.apples8jpa.payLoad.FacultyDto;
import uz.pdp.apples8jpa.repository.FacultyRepository;
import uz.pdp.apples8jpa.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto){

        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists)
            return "This faculty and university already exist";

        Faculty faculty=new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent()){
            return "University not found";
        }
        University university = optionalUniversity.get();
        faculty.setUniversity(university);
        facultyRepository.save(faculty);
        return "Faculty added";
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId){
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        try {
            facultyRepository.deleteById(id);
            return "Faculty deleted ! ";
        }catch (Exception e){
            return "Faculty not found !";
        }
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable Integer id,@RequestBody FacultyDto facultyDto){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent())
                return "University not found !";
            University university = optionalUniversity.get();
            faculty.setUniversity(university);
            facultyRepository.save(faculty);
            return "Faculty edited !";
        }
        return "Faculty not found !";
    }

}
