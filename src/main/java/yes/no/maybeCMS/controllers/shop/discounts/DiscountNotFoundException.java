package yes.no.maybeCMS.controllers.shop.discounts;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DiscountNotFoundException extends Exception {
}
