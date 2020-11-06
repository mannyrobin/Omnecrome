package ru.armd.pbk.domain.nsi;

import java.util.Date;
import ru.armd.pbk.core.domain.BaseDomain;

public class Grafic
  extends BaseDomain
{
  private String name;
  private String code;
  private Date firstDate;
  private Date secondDate;
  private Date thirdDate;
  private Date fourthDate;
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public Date getFirstDate()
  {
    return this.firstDate;
  }
  
  public void setFirstDate(Date firstDate)
  {
    this.firstDate = firstDate;
  }
  
  public Date getSecondDate()
  {
    return this.secondDate;
  }
  
  public void setSecondDate(Date secondDate)
  {
    this.secondDate = secondDate;
  }
  
  public Date getThirdDate()
  {
    return this.thirdDate;
  }
  
  public void setThirdDate(Date thirdDate)
  {
    this.thirdDate = thirdDate;
  }
  
  public Date getFourthDate()
  {
    return this.fourthDate;
  }
  
  public void setFourthDate(Date fourthDate)
  {
    this.fourthDate = fourthDate;
  }
  
  public String toString()
  {
    return "Grafic{, name='" + this.name + '\'' + ", code='" + this.code + '\'' + ", firstDate=" + this.firstDate + ", secondDate=" + this.secondDate + ", thirdDate=" + this.thirdDate + ", fourthDate=" + this.fourthDate + '}';
  }
}
