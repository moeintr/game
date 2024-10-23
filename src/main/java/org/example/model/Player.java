package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.common.DataTypes;

import java.io.Serializable;

@Entity
@Table(name = "player")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;
    @Column(columnDefinition = DataTypes.NVARCHAR_100)
    private String playerName;
    @Enumerated(EnumType.STRING)
    private GameMove gameMove;
}
