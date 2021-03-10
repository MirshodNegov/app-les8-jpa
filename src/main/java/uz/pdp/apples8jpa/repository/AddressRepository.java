package uz.pdp.apples8jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apples8jpa.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
