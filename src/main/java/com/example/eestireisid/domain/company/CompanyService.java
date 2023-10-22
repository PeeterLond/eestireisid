package com.example.eestireisid.domain.company;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Resource
    private CompanyRepository companyRepository;

    public Optional<Company> findCompanyBy(String companyName) {
        return companyRepository.findCompanyBy(companyName);
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }
}
