package br.com.postgram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.postgram.models.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
