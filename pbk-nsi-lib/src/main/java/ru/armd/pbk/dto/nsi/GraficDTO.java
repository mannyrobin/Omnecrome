package ru.armd.pbk.dto.nsi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.json.DotSepratedDateDeserializer;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

public class GraficDTO
  extends BaseDTO
{
  private String name;
  private String code;
  @JsonSerialize(using=DotSepratedDateSerializer.class)
  @JsonDeserialize(using=DotSepratedDateDeserializer.class)
  private Date firstDate;
  @JsonSerialize(using=DotSepratedDateSerializer.class)
  @JsonDeserialize(using=DotSepratedDateDeserializer.class)
  private Date secondDate;
  @JsonSerialize(using=DotSepratedDateSerializer.class)
  @JsonDeserialize(using=DotSepratedDateDeserializer.class)
  private Date thirdDate;
  @JsonSerialize(using=DotSepratedDateSerializer.class)
  @JsonDeserialize(using=DotSepratedDateDeserializer.class)
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
}
