package giadatonni.CONSEGNA_S18L4.repositories;

import giadatonni.CONSEGNA_S18L4.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlogsRepository extends JpaRepository<Blog, UUID> {

    @Query("SELECT b FROM Blog b WHERE b.utente.userId = :utenteId")
    List<Blog> findAllBlogByUtenteId(UUID utenteId);
}
