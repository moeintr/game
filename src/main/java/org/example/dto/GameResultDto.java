package org.example.dto;

import lombok.*;
import org.example.model.GameMove;
import org.example.model.GameResult;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameResultDto implements Serializable {
    private GameMove gameMovePlayeOne;
    private GameMove gameMovePlayeTwo;
    private GameResult gameResult;
}
