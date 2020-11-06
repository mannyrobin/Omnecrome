package ru.armd.pbk.domain.nsi.bso;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BsoInfo {

    @JsonProperty("person_id")
    private String personId;
    @JsonProperty("act_number")
    private String actNumber;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getActNumber() {
        return actNumber;
    }

    public void setActNumber(String actNumber) {
        this.actNumber = actNumber;
    }
}
