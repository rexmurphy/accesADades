package eac3.repository;
import eac3.model.Estacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacioRepository extends JpaRepository<Estacio, String> {
}