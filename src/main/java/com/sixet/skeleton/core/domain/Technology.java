package com.sixet.skeleton.core.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Technology implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The 'name' is mandatory")
    @Column(nullable = false)
    private String name;

    @Column
    private boolean active;

    public void updateFields(Technology technology){
        this.id = technology.id;
        this.name = technology.name;
        this.active = technology.active;
    }
}
