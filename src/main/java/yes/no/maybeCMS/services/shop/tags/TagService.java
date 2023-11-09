package yes.no.maybeCMS.services.shop.tags;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.entities.shop.Tag;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public List<Tag> getAllByName(String name) {
        return tagRepository.findByNameIgnoreCaseContaining(name);
    }

    public Tag getById(UUID id) throws TagNotFoundException {
        return tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
    }

    public Set<Tag> getAllByIds(Iterable<UUID> ids) {
        return new HashSet<>(tagRepository.findAllById(ids));
    }

    public Set<Tag> getAllByNames(List<String> names) {
        return new HashSet<>(tagRepository.findAllByNameIgnoreCaseIn(names));
    }

    public Set<Tag> getAllByName(Optional<List<String>> names) {
        return new HashSet<>(tagRepository.findAllByNameIgnoreCaseIn(names));
    }

    @Transactional
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    public void delete(UUID id) throws TagNotFoundException {
        var tag = getById(id);
        tagRepository.delete(tag);
    }

    @Transactional
    public Tag update(Tag tag) throws TagNotFoundException {
        var dbTag = getById(tag.getId());
        dbTag.setName(tag.getName() != null ? tag.getName() : dbTag.getName());
        dbTag.setDescription(tag.getDescription() != null ? tag.getDescription() : dbTag.getDescription());
        return tagRepository.save(dbTag);
    }

}
