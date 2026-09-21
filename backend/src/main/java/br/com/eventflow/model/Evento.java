package br.com.eventflow.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="eventos") public class Evento {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String nome; @Column(length=2000) public String descricao; public LocalDateTime dataHora; public Integer capacidade;
 @Enumerated(EnumType.STRING) public StatusEvento status=StatusEvento.RASCUNHO;
 @ManyToOne(optional=false) public Categoria categoria;
 @ManyToOne(optional=false) public LocalEvento local;
 @ManyToOne(optional=false) public Usuario organizador;
}
