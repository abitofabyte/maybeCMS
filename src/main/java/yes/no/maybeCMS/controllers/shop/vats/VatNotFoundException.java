package yes.no.maybeCMS.controllers.shop.vats;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VatNotFoundException extends Exception {
}
