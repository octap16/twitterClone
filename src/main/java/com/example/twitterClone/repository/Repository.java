package com.example.twitterClone.repository;

import com.example.twitterClone.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<Message, Long> {

    Page<Message> findByTag(String tag, Pageable pageable);
    Page<Message> findAll(Pageable pageable);

}
