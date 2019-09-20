package com.siglo21.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.siglo21.springboot.backend.apirest.models.entity.Platillo;

public interface IPlatilloDao extends CrudRepository<Platillo, Integer>{

}
