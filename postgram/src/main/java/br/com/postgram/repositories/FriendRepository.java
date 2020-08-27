package br.com.postgram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.postgram.models.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

	
	
}
