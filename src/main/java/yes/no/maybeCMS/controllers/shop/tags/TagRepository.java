package yes.no.maybeCMS.controllers.shop.tags;

import org.springframework.data.jpa.repository.JpaRepository;
import yes.no.maybeCMS.entities.shop.Tag;

import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}
