package br.com.eventflow.controller;
import br.com.eventflow.model.*;
import br.com.eventflow.repository.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins={"http://localhost:5173","http://127.0.0.1:5173"})
public class ApiController {
  private final UsuarioRepository usuarios;
  private final EventoRepository eventos;
  private final LoteIngressoRepository lotes;
  private final PedidoRepository pedidos;
  private final IngressoRepository ingressos;
  private final BCryptPasswordEncoder encoder;

  public ApiController(UsuarioRepository usuarios, EventoRepository eventos, LoteIngressoRepository lotes, PedidoRepository pedidos, IngressoRepository ingressos, BCryptPasswordEncoder encoder){
    this.usuarios=usuarios; this.eventos=eventos; this.lotes=lotes; this.pedidos=pedidos; this.ingressos=ingressos; this.encoder=encoder;
  }

  record Auth(String nome,String email,String senha,TipoUsuario tipoUsuario){}
  record EventoReq(String nome,String descricao,String dataHora,Integer capacidade,Long organizadorId,String categoria,String localNome,String endereco,String status){}
  record LoteReq(String nome,java.math.BigDecimal preco,Integer quantidadeTotal,Long eventoId){}
  record CompraReq(Long participanteId,Long loteId,Integer quantidade){}

  @PostMapping("/auth/cadastro")
  public Usuario cadastro(@RequestBody Auth r){
    if(usuarios.findByEmail(r.email()).isPresent()) throw new RuntimeException("E-mail já cadastrado");
    Usuario x=new Usuario();
    x.nome=r.nome(); x.email=r.email(); x.senhaHash=encoder.encode(r.senha());
    x.tipoUsuario=r.tipoUsuario()==null?TipoUsuario.PARTICIPANTE:r.tipoUsuario();
    return usuarios.save(x);
  }

  @PostMapping("/auth/login")
  public Usuario login(@RequestBody Auth r){
    Usuario x=usuarios.findByEmail(r.email()).orElseThrow();
    if(!encoder.matches(r.senha(),x.senhaHash)) throw new RuntimeException("Login inválido");
    return x;
  }

  @GetMapping("/eventos")
  public List<Evento> eventos(){ return eventos.findByStatus(StatusEvento.PUBLICADO); }

  @GetMapping("/eventos/{id}")
  public Evento evento(@PathVariable Long id){ return eventos.findById(id).orElseThrow(); }

  @GetMapping("/eventos/{id}/lotes")
  public List<LoteIngresso> lotes(@PathVariable Long id){ return lotes.findByEventoId(id); }

  @GetMapping("/organizador/{id}/eventos")
  public List<Evento> orgEventos(@PathVariable Long id){ return eventos.findByOrganizadorId(id); }

  @GetMapping("/organizador/{id}/vendas")
  public List<Pedido> vendas(@PathVariable Long id){ return pedidos.findByLoteIngressoEventoOrganizadorIdOrderByDataDesc(id); }

  @GetMapping("/usuarios/{id}/historico")
  public List<Pedido> historico(@PathVariable Long id){ return pedidos.findByParticipanteIdOrderByDataDesc(id); }

  @GetMapping("/usuarios/{id}/ingressos")
  public List<Ingresso> ingressos(@PathVariable Long id){ return ingressos.findByPedidoParticipanteId(id); }

  @PostMapping("/eventos")
  public Evento novoEvento(@RequestBody EventoReq r){
    Usuario org=usuarios.findById(r.organizadorId()).orElseThrow();
    if(org.tipoUsuario!=TipoUsuario.ORGANIZADOR) throw new RuntimeException("Usuário não é organizador");
    Evento x=new Evento();
    x.nome=r.nome(); x.descricao=r.descricao(); x.dataHora=java.time.LocalDateTime.parse(r.dataHora());
    x.capacidade=r.capacidade(); x.status=StatusEvento.valueOf(r.status()==null?"RASCUNHO":r.status());
    x.organizador=org; x.categoria=r.categoria(); x.localNome=r.localNome(); x.endereco=r.endereco();
    return eventos.save(x);
  }

  @PostMapping("/lotes")
  public LoteIngresso novoLote(@RequestBody LoteReq r){
    Evento ev=eventos.findById(r.eventoId()).orElseThrow();
    if(r.quantidadeTotal()>ev.capacidade) throw new RuntimeException("Quantidade excede capacidade do evento");
    LoteIngresso x=new LoteIngresso();
    x.nome=r.nome(); x.preco=r.preco(); x.quantidadeTotal=r.quantidadeTotal(); x.quantidadeDisponivel=r.quantidadeTotal(); x.evento=ev;
    return lotes.save(x);
  }

  @PostMapping("/pedidos/comprar")
  public Map<String,Object> comprar(@RequestBody CompraReq r){
    Usuario part=usuarios.findById(r.participanteId()).orElseThrow();
    if(part.tipoUsuario!=TipoUsuario.PARTICIPANTE) throw new RuntimeException("Apenas participantes podem adquirir ingressos");
    LoteIngresso lote=lotes.findById(r.loteId()).orElseThrow();
    if(r.quantidade()==null||r.quantidade()<1) throw new RuntimeException("Quantidade inválida");
    if(!Boolean.TRUE.equals(lote.ativo)||r.quantidade()>lote.quantidadeDisponivel) throw new RuntimeException("Quantidade indisponível");
    lote.quantidadeDisponivel-=r.quantidade();
    if(lote.quantidadeDisponivel==0) lote.ativo=false;
    lotes.save(lote);
    Pedido ped=new Pedido();
    ped.participante=part; ped.loteIngresso=lote; ped.quantidade=r.quantidade();
    ped.valorTotal=lote.preco.multiply(java.math.BigDecimal.valueOf(r.quantidade())); ped.status="CONFIRMADO";
    pedidos.save(ped);
    List<Ingresso> emitidos=new ArrayList<>();
    for(int n=0;n<r.quantidade();n++){ Ingresso ing=new Ingresso(); ing.pedido=ped; ing.evento=lote.evento; emitidos.add(ingressos.save(ing)); }
    return Map.of("pedido",ped,"ingressos",emitidos,"quantidadeDisponivel",lote.quantidadeDisponivel);
  }
}