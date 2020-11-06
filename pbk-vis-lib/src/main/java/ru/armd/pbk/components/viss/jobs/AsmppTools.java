package ru.armd.pbk.components.viss.jobs;

import armd.lightHttp.client.BaseHttpClientParameters;
import armd.lightHttp.client.HttpClientException;
import org.apache.log4j.Logger;
import ru.armd.pbk.components.viss.core.clients.DefaultRestClient;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.domain.viss.intervals.AsmppPsgAvg;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsmppTools
	extends BaseComponent {

   public static final Logger LOGGER = Logger.getLogger(AsmppTools.class);
   static Pattern patternMarshPsgAvgValue = Pattern.compile("<row marsh_avg_value=\"([0-9]+)[0-9.]+\"\\s{0,}\\/>");
   static Pattern patternStopPsgAvgValue = Pattern.compile("<row stop_avg_in=\"([0-9]+)[0-9.]+\" stop_avg_out=\"([0-9]+)[0-9.]+\" mr_id=\"([0-9]+)\" \\/>");

   public static Integer getMarshPsgAvgValue(BaseHttpClientParameters parameters)
	   throws HttpClientException {
	  DefaultRestClient client = new DefaultRestClient(parameters);
	  client.callRest(null, null);

	  Matcher matcher = patternMarshPsgAvgValue.matcher(new String(client.getRawResponse()));
	  while (matcher.find()) {
		 return Integer.valueOf(matcher.group(1));
	  }

	  return null;
   }

   public static AsmppPsgAvg getStopPsgAvgValue(String routeCode, BaseHttpClientParameters parameters)
	   throws HttpClientException {
	  DefaultRestClient client = new DefaultRestClient(parameters);
	  client.callRest(null, null);

	  Matcher matcher = patternStopPsgAvgValue.matcher(new String(client.getRawResponse()));
	  while (matcher.find()) {
		 if (routeCode.equals(matcher.group(3))) {
			return new AsmppPsgAvg(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
		 }
	  }

	  return null;
   }

   public static BaseHttpClientParameters getMarshPsgAvgValueParameters(VisExchangeConfig visExchangeConfig, Date date, String routeId, Date from, Date to) {
	  BaseHttpClientParameters parameters = getParameters(visExchangeConfig);
	  parameters.setServiceAddress(visExchangeConfig.getUri() + "/getMarshPsgAvgValue.php?mr_id=" + routeId
		  + "&num_dow=" + getDay(date)
		  + "&period_starttime=" + ((from.getTime() - date.getTime()) / 1000)
		  + "&period_endtime=" + ((to.getTime() - date.getTime()) / 1000));

	  return parameters;
   }

   public static BaseHttpClientParameters getStopPsgAvgValueParameters(VisExchangeConfig visExchangeConfig, Date date, Long stopId, Date from, Date to) {
	  BaseHttpClientParameters parameters = getParameters(visExchangeConfig);
	  parameters.setServiceAddress(visExchangeConfig.getUri() + "/getStopPsgAvgValue.php?st_id=" + stopId
		  + "&num_dow=" + getDay(date)
		  + "&period_starttime=" + ((from.getTime() - date.getTime()) / 1000)
		  + "&period_endtime=" + ((to.getTime() - date.getTime()) / 1000));

	  return parameters;
   }

   protected static BaseHttpClientParameters getParameters(VisExchangeConfig visExchangeConfig) {
	  BaseHttpClientParameters parameters = new BaseHttpClientParameters();
	  parameters.setServiceUsername(visExchangeConfig.getLogin());
	  parameters.setServicePassword(visExchangeConfig.getPassword());
	  parameters.setLogExchangeToFiles(false);
	  //parameters.setLogExchangeDir("./http-logs/asmpp");

	  return parameters;
   }

   protected static int getDay(Date date) {
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(date);
	  int day = cal.get(Calendar.DAY_OF_WEEK);
	  if (day == Calendar.SUNDAY) {
		 day = 7;
	  } else {
		 --day;
	  }

	  return day;
   }
}
