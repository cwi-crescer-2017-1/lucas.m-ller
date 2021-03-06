/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula7.services;

import br.com.crescer.aula7.exceptions.NotFoundException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Lucas
 */
public class GenericService<T extends Object, ID extends Serializable> implements BasicService<T, ID> {
    
    private static final int MIN_LIMIT = 10;
    
    @Autowired
    private PagingAndSortingRepository<T, ID> repo;

    @Override
    public Page<T> findAll(Integer page, Integer limit) {
        return repo.findAll(new PageRequest(
                page == null ? 0 : page, 
                limit == null || limit == 0 ? MIN_LIMIT : limit
        ));
    }

    @Override
    public T findByID(ID id) {
        T et = repo.findOne(id);
        if(et != null)
            return et;
        else
            throw new NotFoundException();
    }

    @Override
    public T save(T et) {
        return repo.save(et);
    }

    @Override
    public T update(ID id, T et) {
        if(repo.exists(id))
            return repo.save(et);
        else
            throw new NotFoundException();
    }

    @Override
    public void delete(ID id) {
        if(repo.exists(id))
            repo.delete(id);
        else
            throw new NotFoundException();
    }

}
