package br.com.fiap.contatos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_contatos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Contato {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_CONTATOS"
    )
    @SequenceGenerator(
            name = "SEQ_CONTATOS",
            sequenceName = "SEQ_CONTATOS",
            allocationSize = 1
    )
    @Column(name = "contato_id")
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

}
