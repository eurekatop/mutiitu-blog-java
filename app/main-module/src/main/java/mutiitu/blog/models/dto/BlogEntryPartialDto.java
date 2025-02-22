package mutiitu.blog.models.dto;

public class BlogEntryPartialDto {
    public  int id;
    public  String title;
    public  String resume;
    public  String subtitle;

    public BlogEntryPartialDto() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setResume(String resume) {
        this.resume = resume;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


}
