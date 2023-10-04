package yes.no.maybeCMS.controllers.shop.tags;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Tag;
import yes.no.maybeCMS.services.tags.TagService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tags")
public class TagRestController {
    private final TagService tagService;

    public TagRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    List<Tag> getAll() {
        return tagService.getAll();
    }

    @GetMapping("{id}")
    Tag getById(@PathVariable UUID id) throws TagNotFoundException {
        return tagService.getById(id);
    }

    @PostMapping
    Tag create(@RequestBody Tag tag) {
        return tagService.create(tag);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) throws TagNotFoundException {
        tagService.delete(id);
    }

    @PatchMapping
    Tag update(@RequestBody Tag tag) throws TagNotFoundException {
        return tagService.update(tag);
    }
}
