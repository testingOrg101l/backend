package com.project.demo.repositories.FiliereRepository;

import com.project.demo.models.ConfigurationModel;
import com.project.demo.models.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<ConfigurationModel, Long> {
    int countAll();
}
