package pl.wsis.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@Table(name = "permissions")
@NoArgsConstructor
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissions_seq_gen")
    @SequenceGenerator(name = "permissions_seq_gen", sequenceName = "global_sequence", allocationSize = 1)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;
}
