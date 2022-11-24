package com.prova.apirestfull.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class EntidadeAbstrata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, length = 100)
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @LastModifiedDate
    @Column(nullable = false, length = 100)
    private LocalDateTime dataAtualizacao = LocalDateTime.now();
}
