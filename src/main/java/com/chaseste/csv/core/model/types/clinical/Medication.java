package com.chaseste.csv.core.model.types.clinical;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.chaseste.csv.core.model.types.common.Code;
import com.chaseste.csv.core.model.types.common.Physician;
import com.chaseste.csv.core.reader.Fields;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ 
    "unique_order_number",
    "order_Status",
    "original_order_dt_tm",
    "medication",
    "give_amount_min",
    "give_amount_max",
    "units",
    "give_dosage_form",
    "instructions",
    "dispense_as_written",
    "dispense_ammount",
    "dispense_units",
    "physician",
    "route",
    "admin_method",
    "frequency",
    "number_of_refills",
    "start_dt_tm",
    "end_dt_tm",
    "prn_ind",
    "prn_reason",
    "comments",
    "order_status_mod_descrip",
})
public class Medication {
    
    public static List<Medication> requiredMedications(Fields fields) {
        Medication m = new Medication();
        m.uniqueOrderNumber = fields.requiredString(6);
        m.orderStatus = Code.optionalCode(fields.optionalField(7));
        m.originalOrderDate = fields.optionalString(8);
        m.medication = Code.requiredCode(fields.requiredField(9));
        m.giveAmountMin = fields.optionalString(10);
        m.giveAmountMax = fields.optionalString(11);
        m.units = Code.requiredCode(fields.requiredField(12));
        m.giveDosageForm = Code.optionalCode(fields.optionalString(13));
        m.instructions = fields.optionalRepeatField(14).stream().map(f -> {
            try {
                return f.get(0);
            } catch (IndexOutOfBoundsException e) { 
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        m.dispenseAsWritten = fields.optionalString(15);
        m.dispensAmmount = fields.optionalString(16);
        m.dispensUnits = Code.optionalCode(fields.optionalField(17));
        m.physician = Physician.optionalPhysician(fields.optionalField(18));
        m.route = Code.optionalCode(fields.optionalField(19));
        m.adminMethod = Code.optionalCode(fields.optionalField(20));
        m.frequency = Code.optionalCode(fields.optionalField(21));
        m.numberOfRefills = fields.optionalRepeatField(22).stream().map(f -> {
            try {
                return f.get(0);
            } catch (IndexOutOfBoundsException e) { 
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        m.startDate = fields.optionalString(23);
        m.endDate = fields.optionalString(24);
        m.prnIndicator = fields.optionalString(25);
        m.prnReason = fields.optionalString(26);
        m.comments = Comment.optionalComments(fields.optionalRepeatField(27));
        m.orderStatusModDescrip = fields.optionalString(28);
        return Arrays.asList(m);
    }

    private String uniqueOrderNumber;
    private Code orderStatus;
    private String originalOrderDate;
    private Code medication;
    private String giveAmountMin;
    private String giveAmountMax;
    private Code units;
    private Code giveDosageForm;
    private List<String> instructions;
    private String dispenseAsWritten;
    private String dispensAmmount;
    private Code dispensUnits;
    private Physician physician;
    private Code route;
    private Code adminMethod;
    private Code frequency;
    private List<String> numberOfRefills;
    private String startDate;
    private String endDate;
    private String prnIndicator;
    private String prnReason;
    private List<Comment> comments;
    private String orderStatusModDescrip;

    @JsonProperty("unique_order_number")
    public String getUniqueOrderNumber() {
        return uniqueOrderNumber;
    }

    public void setUniqueOrderNumber(String uniqueOrderNumber) {
        this.uniqueOrderNumber = uniqueOrderNumber;
    }

    @JsonProperty("order_status")
    public Code getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Code orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonProperty("original_order_dt_tm")
    public String getOriginalOrderDate() {
        return originalOrderDate;
    }

    public void setOriginalOrderDate(String originalOrderDate) {
        this.originalOrderDate = originalOrderDate;
    }

    public Code getMedication() {
        return medication;
    }

    public void setMedication(Code medication) {
        this.medication = medication;
    }

    @JsonProperty("give_amount_min")
    public String getGiveAmountMin() {
        return giveAmountMin;
    }

    public void setGiveAmountMin(String giveAmountMin) {
        this.giveAmountMin = giveAmountMin;
    }

    @JsonProperty("give_amount_max")
    public String getGiveAmountMax() {
        return giveAmountMax;
    }

    public void setGiveAmountMax(String giveAmountMax) {
        this.giveAmountMax = giveAmountMax;
    }

    public Code getUnits() {
        return units;
    }

    public void setUnits(Code units) {
        this.units = units;
    }

    @JsonProperty("give_dosage_form")
    public Code getGiveDosageForm() {
        return giveDosageForm;
    }

    public void setGiveDosageForm(Code giveDosageForm) {
        this.giveDosageForm = giveDosageForm;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    @JsonProperty("dispense_as_written")
    public String getDispenseAsWritten() {
        return dispenseAsWritten;
    }

    public void setDispenseAsWritten(String dispenseAsWritten) {
        this.dispenseAsWritten = dispenseAsWritten;
    }

    @JsonProperty("dispense_ammount")
    public String getDispensAmmount() {
        return dispensAmmount;
    }

    public void setDispensAmmount(String dispensAmmount) {
        this.dispensAmmount = dispensAmmount;
    }

    @JsonProperty("dispense_units")
    public Code getDispensUnits() {
        return dispensUnits;
    }

    public void setDispensUnits(Code dispensUnits) {
        this.dispensUnits = dispensUnits;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    public Code getRoute() {
        return route;
    }

    public void setRoute(Code route) {
        this.route = route;
    }

    @JsonProperty("admin_method")
    public Code getAdminMethod() {
        return adminMethod;
    }

    public void setAdminMethod(Code adminMethod) {
        this.adminMethod = adminMethod;
    }

    public Code getFrequency() {
        return frequency;
    }

    public void setFrequency(Code frequency) {
        this.frequency = frequency;
    }

    @JsonProperty("number_of_refills")
    public List<String> getNumberOfRefills() {
        return numberOfRefills;
    }

    public void setNumberOfRefills(List<String> numberOfRefills) {
        this.numberOfRefills = numberOfRefills;
    }

    @JsonProperty("start_dt_tm")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("end_dt_tm")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("prn_ind")
    public String getPrnIndicator() {
        return prnIndicator;
    }

    public void setPrnIndicator(String prnIndicator) {
        this.prnIndicator = prnIndicator;
    }

    @JsonProperty("prn_reason")
    public String getPrnReason() {
        return prnReason;
    }

    public void setPrnReason(String prnReason) {
        this.prnReason = prnReason;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("order_status_mod_descrip")
    public String getOrderStatusModDescrip() {
        return orderStatusModDescrip;
    }

    public void setOrderStatusModDescrip(String orderStatusModDescrip) {
        this.orderStatusModDescrip = orderStatusModDescrip;
    }

    @JsonPropertyOrder({ "text", "source" })
    public static class Comment {
    
        public static List<Comment> optionalComments(List<List<String>> commentFields) {
            List<Comment> comments = new LinkedList<Comment>();
            for (List<String> fields : commentFields) {
                if (!fields.isEmpty()) {
                    Comment comment = new Comment();
                    comment.text = Fields.optionalString(fields, 0);
                    comment.source = Fields.optionalString(fields, 1);
                    comments.add(comment);
                }    
            }
            return comments;
        }
    
        private String text;
        private String source;
    
        public String getText() {
            return text;
        }
    
        public void setText(String text) {
            this.text = text;
        }
    
        public String getSource() {
            return source;
        }
    
        public void setSource(String source) {
            this.source = source;
        }
    }
}
