package mutiitu.blog.components;

import mutiitu.blog.core.persistence.SQLiteDB;
import mutiitu.blog.domain.HeaderModel;
import mutiitu.blog.domain.impl.HeaderModelDaoImpl;

public class HeaderServiceImpl implements HeaderService {


    @Override
    public String render() {

        var dbContext = new SQLiteDB();

        var dao = new HeaderModelDaoImpl(dbContext);

        dbContext.getTransactionManager().required(
         () -> {
            var header = new HeaderModel();
            var id = (int) Math.random();
            header.setId(id);    
            header.setTitle("hola tu");

            dao.insert(header);
         }
        );

        //try {
        //    db.prepareEnv();
        //}
        //catch ( Exception ex) {
        //    System.out.println(ex);
        //}

//
//        db.

        return "hola";
    } 
}