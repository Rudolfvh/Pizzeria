package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Long orderid;
    private Long customerId;
    private Long pizzaNameId;

    private Date dateGet;
}
