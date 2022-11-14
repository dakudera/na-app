package tech.na_app.entity.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "company")
public class Company {

    @Transient
    public static final String SEQUENCE_NAME = "company_sequence";

    @Id
    private Integer id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_date;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_date;

    private CompanyName ukr_name;
    private CompanyName eng_name;
    private String address;
    private String postal_address;
    private Communication communication;
    private BankingDetails banking_details;
    private IdentificationDetails identification_details;
    private String licence_info;

}
