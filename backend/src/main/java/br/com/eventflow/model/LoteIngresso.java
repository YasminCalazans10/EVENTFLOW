package br.com.eventflow.model;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity @Table(name="lotes_ingresso") public class LoteIngresso {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public String nome; public BigDecimal preco; public Integer quantidadeTotal; public Integer quantidadeDisponivel;
 @Enumerated(EnumType.STRING) public StatusLote status=StatusLote.ATIVO;
 @ManyToOne(optional=false) public Evento evento;
 @ManyToOne(optional=false) public TipoIngresso tipoIngresso;
}
