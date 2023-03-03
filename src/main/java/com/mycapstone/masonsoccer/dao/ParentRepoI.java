package com.mycapstone.masonsoccer.dao;

import com.mycapstone.masonsoccer.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepoI extends JpaRepository<Parent, Integer> {
}
