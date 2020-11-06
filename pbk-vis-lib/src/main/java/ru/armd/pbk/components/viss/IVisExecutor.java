package ru.armd.pbk.components.viss;

/**
 * Интерфейс, в котором определены все Executor модуля ВИС,
 * необходимых для запуска асинхронных задач.
 */
public interface IVisExecutor {

   String ASDU_EXECUTOR = "asduExecutor";
   String GISMGT_EXECUTOR = "gismgtExecutor";
   String EASUFHD_EXECUTOR = "easufhdExecutor";
   String DVR_EXECUTOR = "dvrExecutor";
   String GKUOP_EXECUTOR = "gkuopExecutor";
   String ASKP_EXECUTOR = "askpExecutor";
   String PASSENGERS_EXECUTOR = "passengersExecutor";
   String GTFS_EXECUTOR = "gtfsExecutor";
   String ASMPP_EXECUTOR = "asmppExecutor";

}
