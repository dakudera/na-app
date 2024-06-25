package tech.na_app.entity.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.na_app.model.enums.InternshipAndInstructionType;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "internship_instruction")
public class InternshipAndInstruction {

    @Transient
    public static final String SEQUENCE_NAME = "internship_instruction_sequence";

    @Id
    private Integer id;

    private String doc_number;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private InternshipAndInstructionType type;

    private ObjectId userId;

}
