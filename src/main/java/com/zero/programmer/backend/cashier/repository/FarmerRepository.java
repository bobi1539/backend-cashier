package com.zero.programmer.backend.cashier.repository;

import com.zero.programmer.backend.cashier.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    @Query(value = "SELECT * FROM farmers WHERE is_delete = false", nativeQuery = true)
    List<Farmer> findAll();

    @Query(value = "SELECT * FROM farmers WHERE is_delete = false AND id = :id", nativeQuery = true)
    Optional<Farmer> findById(Long id);
}
