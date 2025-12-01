package com.app.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PassengerDetails {
    
    //@NotBlank(message = "Passenger name is required")
    private String passenger_name;
    
    @NotBlank(message = "Gender is required")
    private String gender;
    
    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age must be less than 120")
    private Integer age;
    
    private String mealType;
    
    @NotBlank(message = "Seat number is required")
    private String seatNo;

    public String getMealPreference() {
        return mealType;
    }
}
