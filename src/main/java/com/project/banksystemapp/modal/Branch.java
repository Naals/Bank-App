package com.project.banksystemapp.modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    private String phone;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection
    private List<String> workingDays;

    private LocalDateTime openTime;
    private LocalDateTime closeTime;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    @ManyToOne
    private Store store;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private User manager;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
