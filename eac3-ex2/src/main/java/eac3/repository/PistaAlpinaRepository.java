package eac3.repository;

import eac3.model.PistaAlpina;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositori per manegar els objectes de la classe PistaAlpina a la base de
 * dades
 */
@Repository
public interface PistaAlpinaRepository extends JpaRepository<PistaAlpina, String> {

    /**
     * Consulta les pistes alpines segons el seu color [cite: 165]
     *
     * @param color el color de la pista alpina
     * @return la llista de pistes alpines
     */
    @Query("SELECT p FROM PistaAlpina p WHERE p.color = :color")
    public List<PistaAlpina> getByColor(@Param(value = "color") String color);

}