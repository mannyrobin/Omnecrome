package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.views.tasks.TaskDateSerializer;

import java.util.Date;

import static ru.armd.pbk.utils.StringUtils.binaryToMarkSymbol;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Cписок маршрутов АСМ-ПП".
 */
public class So25View
	extends SoView {

    @JsonSerialize(using = TaskDateSerializer.class)
    private Date workDate;
    private String routeName;
    private String typeTs;
    private int stops;

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getTypeTs() {
        return typeTs;
    }

    public void setTypeTs(String typeTs) {
        this.typeTs = typeTs;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }


}
