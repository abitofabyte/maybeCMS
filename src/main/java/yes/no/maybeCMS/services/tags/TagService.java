package yes.no.maybeCMS.services.tags;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.controllers.shop.tags.TagNotFoundException;
import yes.no.maybeCMS.controllers.shop.tags.TagRepository;
import yes.no.maybeCMS.entities.shop.Tag;

import java.util.List;
import java.util.UUID;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public Tag getById(UUID id) throws TagNotFoundException {
        return tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
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
