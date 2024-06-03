package org.example.proiect.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalSupplyRequest {
    private String wardName;
    private String drugName;
    private Long quantity;
}
