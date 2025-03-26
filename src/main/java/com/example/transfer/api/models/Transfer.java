package com.example.transfer.api.models;

import com.example.transfer.api.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "payer_id", referencedColumnName = "id")
    private Person payer;

    @ManyToOne
    @JoinColumn(name = "payee_id", referencedColumnName = "id")
    private Person payee;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransferStatus status;
}
