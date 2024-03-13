package org.khasanof.licensingservice;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.licensingservice
 * @since 3/3/2024 12:52 PM
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class License {

    private String id;
    private String organizationId;
    private String productName;
    private String licenseType;

}
