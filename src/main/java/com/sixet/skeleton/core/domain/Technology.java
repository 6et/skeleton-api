package com.sixet.skeleton.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Technology class provides the mapping of the table.
 * @since 11/06/2019
 * @author <a href="mailto:gtrevisane@gmail.com">Get Trevisan</a>
 */
@Data
@Entity
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

    public boolean hasName() {
        return this.name != null && !StringUtils.isEmpty(this.name);
    }

    public void updateFields(Technology technology){
        this.name = technology.getName();
        this.active = technology.isActive();
    }
}
