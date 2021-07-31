package com.luiztictac.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luiztictac.os.domain.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer>{

}
