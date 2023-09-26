package yes.no.maybeCMS.controllers.shop.tags;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tags")
public class TagRestController {
    TagRepository tagRepository;

    public TagRestController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping
    List<Tag> getAll() {
       return tagRepository.findAll();
    }

    @GetMapping("{id}")
    Tag getById(@PathVariable UUID id) throws TagNotFoundException {
        return tagRepository.findById(id).orElseThrow(TagNotFoundException::new);
    }

    @PostMapping
    Tag create(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) throws TagNotFoundException {
        var tag = getById(id);
        tagRepository.delete(tag);
    }

    @PatchMapping
    Tag update(@RequestBody Tag tag) throws TagNotFoundException {
        var dbTag = getById(tag.getId());
        dbTag.setName(tag.getName() != null ? tag.getName() : dbTag.getName());
        dbTag.setDescription(tag.getDescription() != null ? tag.getDescription() : dbTag.getDescription());
        return tagRepository.save(dbTag);
    }
}
