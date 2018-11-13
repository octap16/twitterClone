package com.example.twitterClone.repository;

import com.example.twitterClone.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Repository extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);

}
