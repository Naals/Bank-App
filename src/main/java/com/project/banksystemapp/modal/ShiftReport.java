package com.project.banksystemapp.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShiftReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


}
