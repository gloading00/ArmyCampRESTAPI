package gr.marag.army.springboot.entity;


import jakarta.persistence.*;

import lombok.*;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "camps", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Camp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;


    @Column(name = "description", nullable = false)
    private String description;


    @OneToMany(mappedBy = "camp", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Soldier> soldiers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campType_id")
    private CampType campType;
}

