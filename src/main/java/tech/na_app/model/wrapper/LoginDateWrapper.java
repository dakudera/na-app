package tech.na_app.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoginDateWrapper {

    private Date issueAt;
    private Date expDate;
}
