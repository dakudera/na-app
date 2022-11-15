package tech.na_app.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;


@Service
@RequiredArgsConstructor
public class SequenceGeneratorService {

    private final MongoOperations mongoOperations;


    public Object getSequenceNumber(String sequenceNumber, Class<?> classes) {
        Query query = new Query(Criteria.where("id").is(sequenceNumber));
        //update the sequence no
        Update update = new Update().inc("seq", 1);
        //modify in document

        return mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), classes);
    }


}
