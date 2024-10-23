package org.example.dto;

import lombok.*;
import org.example.model.GameMove;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPlayerDto implements Serializable {
    private String name;
    private GameMove gameMove;
}
