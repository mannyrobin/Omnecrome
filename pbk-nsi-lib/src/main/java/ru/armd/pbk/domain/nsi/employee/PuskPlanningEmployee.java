package ru.armd.pbk.domain.nsi.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PuskPlanningEmployee {

    @JsonProperty("person_id")
    private String personId;
    private long department;
    private String fio;
    private String skk;
    private String bsk;

    public String getPersonId() {return personId;}

    public void setPersonId(String personId) {this.personId = personId;}

    public long getDepartment() {return department;}

    public void setDepartment(long department) {this.department = department;}

    public String getFio() {return fio;}

    public void setFio(String fio) {this.fio = fio;}

    public String getSkk() {return skk;}

    public void setSkk(String skk) {this.skk = skk;}

    public String getBsk() {return bsk;}

    public void setBsk(String bsk) {this.bsk = bsk;}
}
