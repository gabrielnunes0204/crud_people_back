package br.com.pamcary.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.pamcary.entities.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

	@Query("SELECT p FROM PersonEntity p")
	Optional<List<PersonEntity>> getAll();
	
	@Query("SELECT p FROM PersonEntity p WHERE p.id = :id")
	Optional<PersonEntity> getById(@Param("id") Long id);
	
	@Query("SELECT p FROM PersonEntity p WHERE p.cpf = :cpf")
	Optional<PersonEntity> getByCpf(@Param("cpf") String cpf);
}
