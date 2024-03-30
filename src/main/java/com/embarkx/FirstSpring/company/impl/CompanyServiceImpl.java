package com.embarkx.FirstSpring.company.impl;

import com.embarkx.FirstSpring.company.Company;
import com.embarkx.FirstSpring.company.CompanyRepository;
import com.embarkx.FirstSpring.company.CompanyService;
import com.embarkx.FirstSpring.job.Job;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id,Company company) {
        Optional<Company> companyOptional=companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company companyToUpdate=companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);

    }

    @Override
    public boolean deleteCompanyById(Long id) {
//        companyRepository.deleteById(id);
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return  true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {

        return companyRepository.findById(id).orElse(null);
    }
}
