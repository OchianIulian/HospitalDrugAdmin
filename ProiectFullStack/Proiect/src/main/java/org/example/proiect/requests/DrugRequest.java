package org.example.proiect.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DrugRequest {
    public String name;
    public String manufacturer;
    public String description;
    public String sideEffects;
    public String dosage;
    public Long quantity;
}
