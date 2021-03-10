package uz.pdp.apples8jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apples8jpa.entity.Faculty;
import uz.pdp.apples8jpa.entity.Group;
import uz.pdp.apples8jpa.payLoad.GroupDto;
import uz.pdp.apples8jpa.repository.FacultyRepository;
import uz.pdp.apples8jpa.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping
    public List<Group> get(){
        List<Group> groupList = groupRepository.findAll();
        return groupList;
    }

    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsUniversity(@PathVariable Integer universityId){
        groupRepository.getGroupByUniversityId(universityId);
        groupRepository.getGroupByUniversityIdNative(universityId);
        return groupRepository.findAllByFaculty_University_Id(universityId);
    }

    @PostMapping
    public String add(@RequestBody GroupDto groupDto){
        Group group=new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent())
            return "Such faculty not found";
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group saved";
    }

}
