package br.com.postgram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.postgram.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
