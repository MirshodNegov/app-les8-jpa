package uz.pdp.apples8jpa.payLoad;

import lombok.Data;
import uz.pdp.apples8jpa.entity.University;

@Data
public class FacultyDto {
    private String name;
    private Integer universityId;
}
