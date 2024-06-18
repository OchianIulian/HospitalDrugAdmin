package org.example.proiect.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proiect.enums.Roles;

import javax.management.relation.Role;

/**
 * This class offers a body for signing up
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String fullName;
    private String email;
    private String cnp;
    private Roles role;
}
