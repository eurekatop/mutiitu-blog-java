package mutiitu.blog.components;

import com.mutiitu.dao.MigrateDatabaseImpl;


public class HeaderServiceImpl implements HeaderService {


    @Override
    public String render() {

        // create database
        var migrate = new MigrateDatabaseImpl();
        migrate.create();

        return "hola";
    } 
}