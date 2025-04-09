package com.example.demo.Repository;

import com.example.demo.Model.itemMagico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface itemMagicoRepository extends JpaRepository<itemMagico, Integer> {
}
