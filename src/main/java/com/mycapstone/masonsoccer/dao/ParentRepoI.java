package com.mycapstone.masonsoccer.dao;

import com.mycapstone.masonsoccer.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepoI extends JpaRepository<Parent, Integer> {
}
