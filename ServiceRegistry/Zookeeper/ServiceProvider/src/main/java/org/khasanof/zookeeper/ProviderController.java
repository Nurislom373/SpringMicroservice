package org.khasanof.zookeeper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nurislom
 * @see org.khasanof.zookeeper
 * @since 4/12/2024 9:44 AM
 */
@RestController
public class ProviderController {

    /**
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> greeting(@PathVariable String name) {
        return ResponseEntity.ok(getConcat(name));
    }

    private String getConcat(String name) {
        return "Hello ".concat(name);
    }
}
