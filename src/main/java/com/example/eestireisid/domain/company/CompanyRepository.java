package com.example.eestireisid.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("select c from Company c where c.name = ?1")
    Optional<Company> findCompanyBy(String name);

}