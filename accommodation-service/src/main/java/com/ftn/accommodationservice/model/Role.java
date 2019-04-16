package com.ftn.accommodationservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Role {

    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }

}
