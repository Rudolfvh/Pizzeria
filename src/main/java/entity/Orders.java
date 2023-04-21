package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    private Integer orderid;
    private Integer customerId;
    private Integer pizzaNameId;
    private LocalDateTime dateGet;
}
