package yes.no.maybeCMS.services.shop.tags;

import org.springframework.data.jpa.repository.JpaRepository;
import yes.no.maybeCMS.entities.shop.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    List<Tag> findByNameIgnoreCaseContaining(String name);
    List<Tag> findAllByNameIgnoreCaseIn(List<String> strings);
    List<Tag> findAllByNameIgnoreCaseIn(Optional<List<String>> tagNames);
}
