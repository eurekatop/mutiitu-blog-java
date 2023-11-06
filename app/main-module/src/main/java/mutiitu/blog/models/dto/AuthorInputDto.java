package mutiitu.blog.models.dto;

import com.google.gson.annotations.Expose;

public class AuthorInputDto {
    @Expose
    public Integer id;
    @Expose
    public String name;
    @Expose
    public String surname;
    @Expose
    public String password;
}
