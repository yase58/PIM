package tr.com.yavuzduran.pim.blog.personal.dto;

import lombok.Builder;

public class BlogPersonalDto extends BlogPersonalSimpleDto {
    private String text;

    @Builder
    public BlogPersonalDto(String title, String summary, String text) {
        super(title, summary);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
