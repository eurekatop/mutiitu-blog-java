package mutiitu.blog.services;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mutiitu.dao.BlogEntryDao;
import com.mutiitu.domain.BlogEntryModel;
import com.mutiitu.domain.BlogEntryModel_;

import mutiitu.blog.models.dto.BlogEntryPartialDto;

//TODO: this is a repository
public class BlogEntryService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    BlogEntryDao BlogEntryDao;


    public void DeleteBydId(int id) {
        BlogEntryDao.delete(id);
    }

    public BlogEntryModel GetBydId(int id) {
        return BlogEntryDao.getWithAuthorsById(id);
    }

    public List<Integer> GetAllBlogIds() {
        return BlogEntryDao.getIds();
    }

    public List<BlogEntryPartialDto> GetAllBlogPartial() {
        //return BlogEntryDao.getBlogsPartial(200);
        return null;
    }

    public List<BlogEntryModel> GetAllBlogJooq() {
        return BlogEntryDao.getBlogsJooq(200);
    }

    public List<BlogEntryPartialDto> GetAllBlogPartialJooq() {
        //return BlogEntryDao.getBlogsJooqPartial(200);
        return null;
    }

    public List<BlogEntryPartialDto> GetAllBlogPartialByDto()  {
        var entity = new BlogEntryModel_();
        List<String> fieldsToSelect = Arrays.asList(
            entity.id.getName(),
            entity.title.getName(),
            entity.resume.getName()
            //entity.subtitle.getName()
        );


        //3
        var daat3 = BlogEntryDao.getPartialDynamic(200, fieldsToSelect, BlogEntryPartialDto.class);
        return daat3;
        // 2
        //var data2  =  BlogEntryDao.getBlogsJooqPartialDynamicDto(200,fieldsToSelect, BlogEntryPartialDto.class);
        //return data2;

    }




    public List<BlogEntryModel> GetBlogs(int count) {
        return BlogEntryDao.getBlogs(count);
    }

    public List<Integer> SelectAllIds() {
        var entity = new BlogEntryModel_();

        List<String> fieldsToSelect = Arrays.asList(
            entity.id.getName()
        );
        
        return BlogEntryDao.getPartialDynamic(Integer.MAX_VALUE, fieldsToSelect, Integer.class);
    }


    public void AddBlog(BlogEntryModel blogEntryModel) {
        var id = blogEntryModel.getId();
        if ( id != null  &&  id > 0 ) {
            BlogEntryDao.update(blogEntryModel);
        }
        else {
            blogEntryModel.setId(-1);
            BlogEntryDao.insert(blogEntryModel);
        }
    }

}
