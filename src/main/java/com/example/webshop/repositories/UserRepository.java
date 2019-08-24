package com.example.webshop.repositories;
import com.example.webshop.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findAllByLoginIsNotNull(Pageable pageable);

}
