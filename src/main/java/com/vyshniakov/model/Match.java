package com.vyshniakov.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "player1", nullable = false)
    private Player player1;

    @ToString.Include
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "player2", nullable = false)
    private Player player2;

    @ToString.Include
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "winner", nullable = false)
    private Player winner;
}
