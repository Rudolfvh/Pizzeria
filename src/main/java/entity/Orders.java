package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    private Integer orderid;
    private Integer customerId;
    private Integer pizzaNameId;
    private Date dateGet;
}
