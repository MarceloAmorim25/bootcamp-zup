package br.com.postgram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.postgram.models.PostPhoto;

@Repository
public interface PostPhotoRepository extends JpaRepository<PostPhoto, Long> {

	
	
}
