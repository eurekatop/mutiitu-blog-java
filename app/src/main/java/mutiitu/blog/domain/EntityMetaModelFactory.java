package mutiitu.blog.domain;


public class EntityMetaModelFactory {

    // TODO: change to switch patterns java 17
    static Object get(Class<?> model) {
        if ( model.equals(HeaderModel.class))   {
            return new HeaderModel_();
        }
        
        return null;
    }

}
