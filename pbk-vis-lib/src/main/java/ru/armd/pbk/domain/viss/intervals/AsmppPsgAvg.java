package ru.armd.pbk.domain.viss.intervals;


/**
 * Класс AsmppPsgAvg.
 */
public class AsmppPsgAvg {

   private Integer in;
   private Integer out;

   public AsmppPsgAvg(Integer in, Integer out) {
	  this.in = in;
	  this.out = out;
   }

   public Integer getIn() {
	  return in;
   }

   public void setIn(Integer in) {
	  this.in = in;
   }

   public Integer getOut() {
	  return out;
   }

   public void setOut(Integer out) {
	  this.out = out;
   }
}
