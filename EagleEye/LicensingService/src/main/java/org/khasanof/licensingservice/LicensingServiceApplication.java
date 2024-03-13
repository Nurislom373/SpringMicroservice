package org.khasanof.licensingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LicensingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicensingServiceApplication.class, args);
    }

}

@RestController
@RequestMapping(value = "/organizations")
class LicenseController {

    @RequestMapping(value = "/{licenseId}", method = RequestMethod.GET)
    public ResponseEntity<License> getLicense(@PathVariable String licenseId) {
        return ResponseEntity.ok(License.builder()
                        .id(licenseId)
                        .organizationId("TestOrg")
                        .productName("Teleco")
                        .licenseType("Seat")
                .build());
    }
}
