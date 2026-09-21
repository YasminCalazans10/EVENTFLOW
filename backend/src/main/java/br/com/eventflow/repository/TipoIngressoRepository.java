package br.com.eventflow.repository;
import br.com.eventflow.model.TipoIngresso; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface TipoIngressoRepository extends JpaRepository<TipoIngresso,Long>{ List<TipoIngresso> findByEventoId(Long id); }
