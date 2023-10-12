package yes.no.maybeCMS.endpoints.shop.tags;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Tag;
import yes.no.maybeCMS.services.shop.tags.TagNotFoundException;
import yes.no.maybeCMS.services.shop.tags.TagService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("tags")
public class TagRestController {
    private final TagService tagService;

    public TagRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    private List<Tag> getAll(@RequestParam Optional<String> name) {
        if(name.isEmpty()) {
            return tagService.getAll();
        }
        return tagService.getAllByName(name.get());
    }

    @GetMapping("{id}")
    private Tag getById(@PathVariable UUID id) throws TagNotFoundException {
        return tagService.getById(id);
    }

    @PostMapping
    private Tag create(@RequestBody Tag tag) {
        return tagService.create(tag);
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable UUID id) throws TagNotFoundException {
        tagService.delete(id);
    }

    @PatchMapping
    private Tag update(@RequestBody Tag tag) throws TagNotFoundException {
        return tagService.update(tag);
    }
}
