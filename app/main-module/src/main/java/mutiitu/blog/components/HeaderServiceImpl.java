package mutiitu.blog.components;

import java.util.UUID;

import com.mutiitu.dao.MigrateDatabaseImpl;
import com.mutiitu.domain.FooterModel;
import com.mutiitu.domain.FooterModel_;
import com.mutiitu.domain.HeaderModel;
import com.mutiitu.domain.TranslateModel;
import com.mutiitu.domain.TranslateModel_;
import com.mutiitu.persistence.PersistenceFactory;
import com.mutiitu.persistence.SQLiteDB;


public class HeaderServiceImpl implements HeaderService {


    @Override
    public String render() {

//        var dbContext = new SQLiteDB();


//        var dao = new HeaderModelDaoImpl(dbContext);

//        dbContext.getTransactionManager().required(
//         () -> {
//            var header = new HeaderModel();
//            var id = (int) Math.random();
//            header.setId(id);    
//            header.setTitle("hola tu");
//
//            dao.insert(header);
//         }
//        );

        
//        dbContext.getTransactionManager().required(
//         () -> {
//
//                Entityql eql = new Entityql(dbContext); 
//                var hh = new HeaderModel_();
//                var l = eql.from(hh)
//                    .where( 
//                        c -> c.gt(hh.id,0)
//                    )
//                    .fetch();
//
//                for (HeaderModel header : l) {
//                    System.out.println ( header );
//                }
//         }
//        );

//        SQLiteDB.singleton().getTransactionManager().required(
//         () -> {
//
//                Entityql eql = new Entityql(SQLiteDB.singleton()); 
//                var hh = new HeaderModel_();
//                var model = new HeaderModel();
//                model.setTitle("JUJU");
//                var l = eql.insert(hh,model).execute();
//                System.out.println(l);
//
//         }
//        );
         

        // var crud = new ModelCrudImpl<HeaderModel, HeaderModel_>( new HeaderModel_() );
        // var kk2 = new HeaderModel();
        // kk2.setId(80);
        // kk2.setTitle("kaka al cubo 11");
        // crud.insert(kk2);


        // var crud2 = new ModelCrudImpl<FooterModel, FooterModel_>( new FooterModel_() );
        // var kk22 = new FooterModel();
        // kk22.setCustomId(80);
        // kk22.setTitle("pipi al cubo 11");
        // crud2.insert(kk22);



        // var kk = new ModelCrudImpl<HeaderModel, HeaderModel_>( new HeaderModel_() );
        // var model2 = kk.getById(5);
        // System.out.println ( model2 );

        //var kk222 = new ModelCrudImpl<FooterModel, FooterModel_>( new FooterModel_() );
        
        var kk222 = new PersistenceFactory<FooterModel>().create(FooterModel.class);

        //var model22 = kk222.getById(5);
        //System.out.println ( model22 );


        // var crudStr = new ModelCrudImpl<TranslateModel, TranslateModel_>( new TranslateModel_() );
        
        var local =new TranslateModel();

        var uuid = UUID.randomUUID().toString();
        //            

        local.setId(uuid);
        local.setLanguage("ES");
        local.setValue("HOLA");

        // crudStr.insert(local);

        /// var model224 = crudStr.getById(uuid);
        /// System.out.println ( model224 );

        // crudStr.delete(uuid);


        //try {
        //    db.prepareEnv();
        //}
        //catch ( Exception ex) {
        //    System.out.println(ex);
        //}

//
//        db.


            // create database
            var migrate = new MigrateDatabaseImpl();
            migrate.create();

            var headerCrud = new PersistenceFactory<HeaderModel>().create(HeaderModel.class);
            var header = new HeaderModel();
            header.setId(1);
            header.setTitle("Header");
            header.setSubtitle("gradacions");

            headerCrud.insert(header);

            var hh = headerCrud.getById(1);
            System.out.println(hh);

            // inserts
            for ( int i=2; i<409; i++){
                var _header = new HeaderModel();
                _header.setTitle("Header");
                _header.setSubtitle("gradacions " + i);
                headerCrud.insertAsync(_header);
            }

            // delete
            // for ( int i=2; i<409; i++){
            //     headerCrud.delete(i);
            // }

            // delete sync
            for ( int i=2; i<409; i++){
                headerCrud.deleteAsync(i);
            }



        return "hola";
    } 
}