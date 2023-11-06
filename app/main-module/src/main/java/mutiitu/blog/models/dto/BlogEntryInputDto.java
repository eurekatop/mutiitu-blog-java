package mutiitu.blog.models.dto;

import com.google.gson.annotations.Expose;

public class BlogEntryInputDto {
    @Expose
    public Integer id;
    @Expose
    public String title;
    @Expose
    public String subtitle;
    @Expose
    public String content;
    @Expose
    public Integer authorId;
}
