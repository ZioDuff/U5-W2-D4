package JacopoDeMaio.ValidationAndUpload.repository;


import JacopoDeMaio.ValidationAndUpload.entities.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, UUID> {
    Optional<Autore> findByEmail(String email);

}
