package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.common.DataTypes;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL/**, fetch = FetchType.LAZY**/)
    @JoinColumn(name = "player_one")
    private Player playerOne;

    @OneToOne(cascade = CascadeType.ALL/**, fetch = FetchType.LAZY**/)
    @JoinColumn(name = "player_two")
    private Player playerTwo;

    @Enumerated(EnumType.STRING)
    private GameResult gameResult;

    @Column(name = "game_date")
    private Date gameDate;
}
