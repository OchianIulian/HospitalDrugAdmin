package org.example.proiect.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.proiect.enums.Roles;

@RequiredArgsConstructor
@Data
public class UserRequest {
    public String fullName;
    public String email;
    public String cnp;
    public Roles role;
}
