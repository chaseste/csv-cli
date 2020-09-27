package com.chaseste.csv.core.model.types.common;

import static com.chaseste.csv.core.model.types.common.Physician.genericPhysician;
import static com.chaseste.csv.core.reader.Fields.optionalString;
import static com.chaseste.csv.core.reader.Fields.subFields;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "text", "comment_dt_tm", "physician" })
public class Comment {

    public static List<Comment> optionalComments(List<List<String>> commentFields) {
        List<Comment> comments = new LinkedList<Comment>();
        for (List<String> fields : commentFields) {
            if (!fields.isEmpty()) {
                Comment comment = new Comment();
                comment.text = optionalString(fields, 0);
                comment.commentDtTm = optionalString(fields, 1);
                comment.physician = genericPhysician(subFields(fields, 2));
                comments.add(comment);
            }    
        }
        return comments;
    }

    private String text;
    private String commentDtTm;
    private Physician physician;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("comment_dt_tm")
    public String getCommentDtTm() {
        return commentDtTm;
    }

    public void setCommentDtTm(String commentDtTm) {
        this.commentDtTm = commentDtTm;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }
}
