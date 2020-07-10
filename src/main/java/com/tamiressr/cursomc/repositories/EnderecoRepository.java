package com.tamiressr.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tamiressr.cursomc.domain.Endereco;
@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Integer>{

}
