package br.com.eventflow.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="eventos")
public class Evento {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
  public String nome;
  @Column(length=2000) public String descricao;
  public LocalDateTime dataHora;
  public Integer capacidade;
  @Enumerated(EnumType.STRING) public StatusEvento status=StatusEvento.RASCUNHO;
  public String categoria;
  public String localNome;
  public String endereco;
  @ManyToOne public Usuario organizador;
}