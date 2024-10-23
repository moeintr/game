package org.example.dto;

import lombok.*;
import org.example.model.GameResult;
import org.example.model.Player;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameResultDto implements Serializable {
    private Player playerOne;
    private Player playerTwo;
    private GameResult gameResult;
    private Date gameDate;
}
