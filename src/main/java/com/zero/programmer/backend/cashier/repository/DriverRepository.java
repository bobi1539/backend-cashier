package com.zero.programmer.backend.cashier.repository;

import com.zero.programmer.backend.cashier.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT * FROM drivers WHERE is_delete = false", nativeQuery = true)
    List<Driver> findAll();

    @Query(value = "SELECT * FROM drivers WHERE is_delete = false AND id = :id", nativeQuery = true)
    Optional<Driver> findById(@PathParam("id") Long id);
}
