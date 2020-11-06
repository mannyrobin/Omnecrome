package ru.armd.pbk.aspose.core;

import org.apache.commons.pool.impl.GenericKeyedObjectPool;

/**
 * Провайдер процессоров отчётов.
 */
public class ProcessorProvider {

   private ReportProcessorFactory simpleFactory;
   private GenericKeyedObjectPool<IReportType, IReportProcessor> poolFactory; // использует PoolingReportProcessorFactory

   public ProcessorProvider(ReportProcessorFactory simpleFactory, GenericKeyedObjectPool<IReportType, IReportProcessor> poolFactory) {
	  this.simpleFactory = simpleFactory;
	  this.poolFactory = poolFactory;
   }

   /**
	* Запрашивает процессор отчёта из пула по типу отчёта.
	*
	* @param reportType Тип отчёта.
	* @return Процессор.
	* @throws Exception
	*/
   public IReportProcessor getPooledProcessor(IReportType reportType)
	   throws Exception {
	  return poolFactory != null && reportType != null ?
		  poolFactory.borrowObject(reportType) :
		  null;
   }

   /**
	* Возвращает взятый из пула по типу отчёта процессор отчёта.
	*
	* @param reportType Тип отчёта.
	* @param processor  Процессор отчёта, ранее взятый из пула по типу отчёта.
	* @throws Exception
	*/
   public void returnPooledProcessor(IReportType reportType, IReportProcessor processor)
	   throws Exception {
	  if (poolFactory != null) {
		 poolFactory.returnObject(reportType, processor);
	  }
   }

   /**
	* Новый (не из пула) процессор отчёта для предобработанных шаблонов.
	*
	* @param reportType Тип отчёта.
	* @return Процессор отчёта.
	* @throws Exception
	*/
   public IReportProcessor getProcessor(IReportType reportType)
	   throws Exception {
	  return simpleFactory != null ? simpleFactory.getProcessor(reportType) : null;
   }

   /**
	* Новый (не из пула) процессор отчёта, требующий предобработку шаблона.
	*
	* @param reportType Тип отчёта.
	* @return Процессор отчёта.
	* @throws Exception
	*/
   public IReportProcessor getProcessorRaw(IReportType reportType)
	   throws Exception {
	  return simpleFactory != null ? simpleFactory.getProcessorRaw(reportType) : null;
   }

   /**
	* Очищает пул процессоров только для конкретного типа отчёта.
	*
	* @param reportType Тип отчёта.
	*/
   public void clearProcessorPool(IReportType reportType) {
	  if (reportType == null || poolFactory == null) {
		 return;
	  }
	  poolFactory.clear(reportType);
   }

   /**
	* Очищает пул процессоров.
	*/
   public void clearProcessorPool() {
	  if (poolFactory != null) {
		 poolFactory.clear();
	  }
   }
}
