package tech.na_app.entity.transport;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.na_app.model.enums.TransportStatus;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transport")
public class Transport {

    @Transient
    public static final String SEQUENCE_NAME = "transport_sequence";

    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date update_date;

    private TransportStatus transport_status;

    private TransportCard transport_card;
    private Integer companyId;
}
