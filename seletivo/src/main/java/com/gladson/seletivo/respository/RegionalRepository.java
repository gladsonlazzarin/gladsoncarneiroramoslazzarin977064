package com.gladson.seletivo.respository;

import com.gladson.seletivo.model.Regional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionalRepository extends JpaRepository<Regional, Integer> {

    List<Regional> findByAtivoTrue();
}
