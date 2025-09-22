package com.example.demo.model;

public class Content {
    private Long contendId;
    private String content;

    //Constructor
    public Content(Long contentId, String content){
        this.contendId = contentId;
        this.content = content;
    }

    //Getter and Setter
    public Long getContentId(){
        return contendId;
    }
    public void setContentId(Long contentId){
        this.contendId = contentId;
    }

    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
}
