package yes.no.maybeCMS.endpoints.shop;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Tag;
import yes.no.maybeCMS.services.shop.tags.TagNotFoundException;
import yes.no.maybeCMS.services.shop.tags.TagService;
import yes.no.maybeCMS.services.validation.Uuid;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("tags")
public class TagRestController {
    private final TagService tagService;

    @GetMapping
    private List<Tag> getAll(@RequestParam Optional<String> name) {
        if(name.isEmpty()) {
            return tagService.getAll();
        }
        return tagService.getAllByName(name.get());
    }

    @GetMapping("{id}")
    private Tag getById(@Uuid @PathVariable UUID id) throws TagNotFoundException {
        return tagService.getById(id);
    }

    @GetMapping("test")
    private Set<Tag> getByNames(@RequestParam Optional<List<String>> names) {
        return tagService.getAllByName(names);
    }

    @PostMapping
    private Tag create(@Valid @RequestBody Tag tag) {
        return tagService.create(tag);
    }

    @DeleteMapping("{id}")
    private void delete(@Uuid @PathVariable UUID id) throws TagNotFoundException {
        tagService.delete(id);
    }

    @PatchMapping
    private Tag update(@Valid @RequestBody Tag tag) throws TagNotFoundException {
        return tagService.update(tag);
    }
}
