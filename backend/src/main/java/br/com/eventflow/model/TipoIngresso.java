package br.com.eventflow.model;
import jakarta.persistence.*;
@Entity @Table(name="tipos_ingresso")
public class TipoIngresso {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String nome;
 @Column(length=800) public String descricao;
 public Boolean ativo=true;
 @ManyToOne(optional=false) public Evento evento;
}
