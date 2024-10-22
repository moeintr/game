package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.common.DataTypes;

@Entity
@Table(name = "Game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = DataTypes.nvarchar, nullable = false)
    private String playerOneName;
    @Column(columnDefinition = DataTypes.nvarchar)
    private String playerTwoName;
    @Enumerated(EnumType.STRING)
    private GameResult gameResult;
    private boolean isVsMachine;
}
