package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.repositories.So19Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So19View;

import java.util.List;

/**
 * Сервис стандартного отчёта "Количество совместных бригад с ГКУ ОП".
 */
@Service
public class So19Service
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So19Service.class);

   @Autowired
   private So19Repository so19Repository;

   @Autowired
   So19Service(So19Repository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

    @Override
    public  JsonGridData getGridViews(BaseGridViewParams params) {
        List<So19View> views = so19Repository.getGridViews(params);
        int sumBrKind0 = 0;
        int sumBrKind11 = 0;
        int sumBrKind12 = 0;
        int sumBrKind13 = 0;
        int sumBrKind21 = 0;
        int sumBrKind22 = 0;
        int sumBrKind23 = 0;
        int sumDopMGT = 0;
        int sumDopGKU= 0;
        int sumDopMGTnoGKU = 0;
        int sumMgtCountInDay = 0;
        int sumBrCommon = 0;
        int sumMgtCountInCommonBr = 0;
        int sumGkuCountInCommonBr = 0;
        for (So19View view : views) {
            String mgtEmployees = view.getMgtEmployees();
            String gkuEmployees = view.getGkuEmployees();
            int mgtCount = mgtEmployees.split(", ").length;
            sumMgtCountInDay += mgtCount;
            view.setMgtCountInDay(mgtCount);
            if (gkuEmployees == null) {
                view.setMgtCountInCommonBr(0);
                view.setGkuCountInCommonBr(0);
                view.setBrKind0("1", true);
                sumBrKind0++;
                if (mgtCount > 2) {
                    sumDopMGTnoGKU++;
                    view.setDopMGTnoGKU(mgtCount - 2);
                }
            } else {
                view.setMgtCountInCommonBr(mgtCount);
                int gkuCount = gkuEmployees.split(", ").length;
                view.setGkuCountInCommonBr(gkuCount);
                sumGkuCountInCommonBr += gkuCount;
                sumMgtCountInCommonBr += mgtCount;
                sumBrCommon++;
                view.setBrCommon(1);
                int gkuCountMinus3 = gkuCount - 3;
                int mgtCountMinus2 = mgtCount - 2;
                if (gkuCountMinus3 > 0) {
                    sumDopGKU += gkuCountMinus3;
                    view.setDopGKU(gkuCountMinus3);
                    gkuCount -= gkuCountMinus3;
                } else view.setDopGKU(0);

                if (mgtCountMinus2 > 0) {
                    sumDopMGT += mgtCountMinus2;
                    view.setDopMGT(mgtCountMinus2);
                    mgtCount -= mgtCountMinus2;
                } else view.setDopMGT(0);

                switch (mgtCount) {
                    case 1:
                        if (gkuCount == 1) {
                            sumBrKind11++;
                            view.setBrKind11("1", true);
                        }
                        if (gkuCount == 2) {
                            sumBrKind12++;
                            view.setBrKind12("1", true);
                        }
                        if (gkuCount == 3) {
                            sumBrKind13++;
                            view.setBrKind13("1", true);
                        }
                        break;
                    case 2:
                        if (gkuCount == 1){
                            sumBrKind21++;
                            view.setBrKind21("1", true);
                        }
                        if (gkuCount == 2) {
                            sumBrKind22++;
                            view.setBrKind22("1", true);
                        }
                        if (gkuCount == 3) {
                            sumBrKind23++;
                            view.setBrKind23("1", true);
                        }
                        break;
                }
            }
        }
        if (views != null && views.size() > 0) {
            views.add(new So19View(String.valueOf(sumBrKind0), String.valueOf(sumBrKind11), String.valueOf(sumBrKind12), String.valueOf(sumBrKind13),
                    String.valueOf(sumBrKind21), String.valueOf(sumBrKind22), String.valueOf(sumBrKind23), sumDopMGT, sumDopGKU, sumDopMGTnoGKU, sumMgtCountInDay, sumBrCommon, sumMgtCountInCommonBr, sumGkuCountInCommonBr));
        }
        return createJsonGridData(views, params.getPage(), params.getCount());
    }


}
