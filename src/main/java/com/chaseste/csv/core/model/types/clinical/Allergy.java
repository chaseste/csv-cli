package com.chaseste.csv.core.model.types.clinical;

import java.util.List;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.model.types.common.Comment;
import com.chaseste.csv.core.model.types.common.Physician;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Arrays;

@JsonPropertyOrder({ "allergen_type", "allergen", "severity", "onset", "reaction_status", "reaction_class",
    "source_of_info", "source_of_info_ft", "cancel_dt_tm", "reviewed_dt_tm", "reactions", "physician", 
    "comments" })
public class Allergy {

    public static List<Allergy> requiredAllergys(Fields fields) {
        Allergy allergy = new Allergy();
        allergy.allergenType = Code.requiredCode(fields.optionalField(6));
        allergy.allergen = Code.requiredCode(fields.optionalField(7));
        allergy.severity = Code.optionalCode(fields.optionalField(8));
        allergy.onset = fields.optionalString(9);
        allergy.reactionStatus = Code.requiredCode(fields.optionalField(10));
        allergy.reactionClass = Code.optionalCode(fields.optionalField(11));
        allergy.sourceOfInfo = Code.optionalCode(fields.optionalField(12));
        allergy.sourceOfInfoFt = fields.optionalString(13);
        allergy.cancelDtTm = fields.optionalString(14);
        allergy.reviewedDtTm = fields.optionalString(15);
        allergy.reactions = Reaction.optionalReactions(fields.optionalRepeatField(16));
        allergy.physician = Physician.optionalPhysician(fields.optionalField(17));
        allergy.comments = Comment.optionalComments(fields.optionalRepeatField(18));
        return Arrays.asList(allergy);
    }

    private Code allergenType;
    private Code allergen;
    private Code severity;
    private String onset;
    private Code reactionStatus;
    private Code reactionClass;
    private Code sourceOfInfo;
    private String sourceOfInfoFt;
    private String cancelDtTm;
    private String reviewedDtTm;
    private List<Reaction> reactions;
    private Physician physician;
    private List<Comment> comments;

    @JsonProperty("allergen_type")
    public Code getAllergenType() {
        return allergenType;
    }

    public void setAllergenType(Code allergenType) {
        this.allergenType = allergenType;
    }

    public Code getAllergen() {
        return allergen;
    }

    public void setAllergen(Code allergen) {
        this.allergen = allergen;
    }

    public Code getSeverity() {
        return severity;
    }

    public void setSeverity(Code severity) {
        this.severity = severity;
    }

    public String getOnset() {
        return onset;
    }

    public void setOnset(String onset) {
        this.onset = onset;
    }

    @JsonProperty("reaction_status")
    public Code getReactionStatus() {
        return reactionStatus;
    }

    public void setReactionStatus(Code reactionStatus) {
        this.reactionStatus = reactionStatus;
    }

    @JsonProperty("reaction_class")
    public Code getReactionClass() {
        return reactionClass;
    }

    public void setReactionClass(Code reactionClass) {
        this.reactionClass = reactionClass;
    }

    @JsonProperty("source_of_info")
    public Code getSourceOfInfo() {
        return sourceOfInfo;
    }

    public void setSourceOfInfo(Code sourceOfInfo) {
        this.sourceOfInfo = sourceOfInfo;
    }

    @JsonProperty("source_of_info_ft")
    public String getSourceOfInfoFt() {
        return sourceOfInfoFt;
    }

    public void setSourceOfInfoFt(String sourceOfInfoFt) {
        this.sourceOfInfoFt = sourceOfInfoFt;
    }

    @JsonProperty("cancel_dt_tm")
    public String getCancelDtTm() {
        return cancelDtTm;
    }

    public void setCancelDtTm(String cancelDtTm) {
        this.cancelDtTm = cancelDtTm;
    }

    @JsonProperty("reviewed_dt_tm")
    public String getReviewedDtTm() {
        return reviewedDtTm;
    }

    public void setReviewedDtTm(String reviewedDtTm) {
        this.reviewedDtTm = reviewedDtTm;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
