package br.com.eventflow.repository;
import br.com.eventflow.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface EventoRepository extends JpaRepository<Evento,Long>{
  List<Evento> findByStatus(StatusEvento status);
  List<Evento> findByOrganizadorId(Long id);
}