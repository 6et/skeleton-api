package com.sixet.skeleton.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Technology class provides the mapping of the table.
 * @since 11/06/2019
 * @author <a href="mailto:gtrevisane@gmail.com">Get Trevisan</a>
 */
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

    public boolean hasName() {
        return this.name != null && !StringUtils.isEmpty(this.name);
    }
}
