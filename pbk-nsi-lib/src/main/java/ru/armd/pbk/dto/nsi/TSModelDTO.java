package ru.armd.pbk.dto.nsi;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.utils.StringUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Дто моделей ТС.
 */
public class TSModelDTO
	extends BaseDTO {

   @NotBlank(message = "Поле \"Марка\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Марка\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String make;

   @NotBlank(message = "Поле \"Модель\" должно быть заполнено.")
   @Length(max = StringUtils.X_SHORT_INPUT_SIZE, message = "Число символов в поле \"Модель\" не должно превышать "
	   + StringUtils.X_SHORT_INPUT_SIZE + " символов.")
   private String model;

   @NotNull(message = "Поле \"Вместимость ТС\" должно быть заполнено.")
   private Long tsCapacityId;

   @NotNull(message = "Поле \"Максимальное количество мест\" должно быть заполнено.")
   @Min(value = 0, message = "Зачение поля \"Максимальное количество мест\" не должно быть отрицательным.")
   private Integer passengerCountMax;

   @NotNull(message = "Поле \"Максимальное количество сидячих мест\" должно быть заполнено.")
   @Min(value = 0, message = "Зачение поля \"Максимальное количество сидячих мест\" не должно быть отрицательным.")
   private Integer seatCount;

   @NotNull(message = "Поле \"Полезная площадь салона\" должно быть заполнено.")
   @Min(value = 0, message = "Зачение поля \"Полезная площадь салона\" не должно быть отрицательным.")
   private Double square;

   @NotNull(message = "Поле \"Масса\" должно быть заполнено.")
   @Min(value = 0, message = "Зачение поля \"Масса\" не должно быть отрицательным.")
   private Double mass;

   @NotNull(message = "Поле \"Длина\" должно быть заполнено.")
   @Min(value = 0, message = "Зачение поля \"Длина\" не должно быть отрицательным.")
   private Double length;

   @NotNull(message = "Поле \"Ширина\" должно быть заполнено.")
   @Min(value = 0, message = "Зачение поля \"Ширина\" не должно быть отрицательным.")
   private Double width;

   @NotNull(message = "Поле \"Высота\" должно быть заполнено.")
   @Min(value = 0, message = "Зачение поля \"Высота\" не должно быть отрицательным.")
   private Double height;

   @NotNull(message = "Поле \"Максимальная скорость\" должно быть заполнено.")
   @Min(value = 0, message = "Зачение поля \"Максимальная скорость\" не должно быть отрицательным.")
   private Double speedMax;

   public String getMake() {
	  return make;
   }

   public void setMake(String make) {
	  this.make = make;
   }

   public String getModel() {
	  return model;
   }

   public void setModel(String model) {
	  this.model = model;
   }

   public Long getTsCapacityId() {
	  return tsCapacityId;
   }

   public void setTsCapacityId(Long tsCapacityId) {
	  this.tsCapacityId = tsCapacityId;
   }

   public Integer getPassengerCountMax() {
	  return passengerCountMax;
   }

   public void setPassengerCountMax(Integer passengerCountMax) {
	  this.passengerCountMax = passengerCountMax;
   }

   public Integer getSeatCount() {
	  return seatCount;
   }

   public void setSeatCount(Integer seatCount) {
	  this.seatCount = seatCount;
   }

   public Double getSquare() {
	  return square;
   }

   public void setSquare(Double square) {
	  this.square = square;
   }

   public Double getMass() {
	  return mass;
   }

   public void setMass(Double mass) {
	  this.mass = mass;
   }

   public Double getLength() {
	  return length;
   }

   public void setLength(Double length) {
	  this.length = length;
   }

   public Double getWidth() {
	  return width;
   }

   public void setWidth(Double width) {
	  this.width = width;
   }

   public Double getHeight() {
	  return height;
   }

   public void setHeight(Double height) {
	  this.height = height;
   }

   public Double getSpeedMax() {
	  return speedMax;
   }

   public void setSpeedMax(Double speedMax) {
	  this.speedMax = speedMax;
   }
}
