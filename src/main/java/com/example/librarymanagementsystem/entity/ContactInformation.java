package com.example.librarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "contact_information")
@Validated
@Setter
@Getter
@JsonIgnoreProperties({"patron"})
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Min(value = 0, message = "Age must be a positive number")
    @Column(name = "age")
    private Integer age;

    @NonNull
//    @Pattern(regexp = "\\\\+\\\\d{1,3}\\\\s\\\\d{3,}", message = "Invalid phone number format")
    @Column(name = "phone", nullable = false)
    private String phoneNumber;

    @NonNull
    @Min(value = 1, message = "Country code must be a positive number")
    @Column(name = "country_code", nullable = false)
    private Integer countryCode;

    @NonNull
    @NotBlank(message = "Nationality must not be blank")
    @Column(name = "nationality")
    private String nationality;

    @NonNull
    @NotBlank(message = "the language must not be blank")
    @Column(name = "language")
    private String language;

    @NonNull
    @NotBlank(message = "the address must not be blank")
    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "contactInformation")
    private Patron patron;
}
