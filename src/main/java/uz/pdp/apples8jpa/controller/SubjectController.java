package uz.pdp.apples8jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apples8jpa.entity.Subject;
import uz.pdp.apples8jpa.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody Subject subject){
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName){
            return "This subject already exist";
        }
        subjectRepository.save(subject);
        return "Subject saved";
    }

    @GetMapping
    public List<Subject> getSubjects(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteSubject(@PathVariable Integer id){
        boolean existsById = subjectRepository.existsById(id);
        if (!existsById){
            return "Subject not found with given id";
        }
        subjectRepository.deleteById(id);
        return "Subject deleted";
    }

}
