package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.utils.StringUtils;

import java.util.Date;

public class GmRouteVariant
	extends GmBaseDomain {

   private Long routeMuid;
   private Long typeMuid;
   private Long orderMuid;
   private Date startDate;
   private Date endDate;
   private String comment;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("routeMuid: ").append(StringUtils.nvl(getRouteMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("typeMuid: ").append(StringUtils.nvl(getTypeMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("orderMuid: ").append(StringUtils.nvl(getOrderMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("startDate: ").append(StringUtils.nvl(getStartDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("endDate: ").append(StringUtils.nvl(getEndDate(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("comment: ").append(StringUtils.nvl(getComment(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public Long getRouteMuid() {
	  return routeMuid;
   }

   public void setRouteMuid(Long routeMuid) {
	  this.routeMuid = routeMuid;
   }


   public Long getTypeMuid() {
	  return typeMuid;
   }

   public void setTypeMuid(Long typeMuid) {
	  this.typeMuid = typeMuid;
   }

   public Long getOrderMuid() {
	  return orderMuid;
   }

   public void setOrderMuid(Long orderMuid) {
	  this.orderMuid = orderMuid;
   }

   public Date getStartDate() {
	  return startDate;
   }

   public void setStartDate(Date startDate) {
	  this.startDate = startDate;
   }

   public Date getEndDate() {
	  return endDate;
   }

   public void setEndDate(Date endDate) {
	  this.endDate = endDate;
   }

   public String getComment() {
	  return comment;
   }

   public void setComment(String comment) {
	  this.comment = comment;
   }

}
