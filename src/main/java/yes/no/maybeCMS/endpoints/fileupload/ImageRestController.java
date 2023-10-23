package yes.no.maybeCMS.endpoints.fileupload;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
public class ImageRestController {

    @PostMapping
    public String upload(@RequestParam MultipartFile image, @RequestParam String test) {
        System.out.println(image.getOriginalFilename());
        return test;
    }
}
