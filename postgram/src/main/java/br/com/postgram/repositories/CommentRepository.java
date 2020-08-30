package br.com.postgram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.postgram.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
