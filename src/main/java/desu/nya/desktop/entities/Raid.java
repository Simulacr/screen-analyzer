package desu.nya.desktop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Raid {
    private String name;
    private boolean nightRaid;
    private LocalDateTime time;
    private int memberCount;
}
