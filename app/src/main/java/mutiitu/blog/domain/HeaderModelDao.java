package mutiitu.blog.domain;

import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Sql;

@Dao
public interface HeaderModelDao {
  @Sql("""
  select * from header where id = /*id*/0
  """)
  @Select
  HeaderModel selectById(Integer id);

  @Insert
  int insert(HeaderModel headerModel);    
}
