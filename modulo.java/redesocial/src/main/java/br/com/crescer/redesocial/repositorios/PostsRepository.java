/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.redesocial.repositorios;

import br.com.crescer.redesocial.entidades.Post;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author lucas.muller
 */
public interface PostsRepository extends PagingAndSortingRepository<Post, BigDecimal>  {
    
    Page<Post> findAllByOrderByDataDesc(Pageable pgbl);
    
    Page<Post> findAllByTextoContainingIgnoreCaseOrderByDataDesc(Pageable pgbl, String texto);

    public Page<Post> findByIdusuario_idInOrderByDataDesc(Pageable pgbl, Set<BigDecimal> amigos);
    
}
