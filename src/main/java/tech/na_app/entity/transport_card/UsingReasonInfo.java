package tech.na_app.entity.transport_card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsingReasonInfo {

    private String num_and_name_contract;

    @Temporal(value = TemporalType.DATE)
    private Date date_start;

    private Boolean is_contract_fixed_term;

    @Temporal(value = TemporalType.DATE)
    private Date date_end;

    @Temporal(value = TemporalType.DATE)
    private Date date_next_start;
}
