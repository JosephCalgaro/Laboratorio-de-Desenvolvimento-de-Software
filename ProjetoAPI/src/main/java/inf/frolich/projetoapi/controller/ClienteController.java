package inf.frolich.projetoapi.controller;

import inf.frolich.projetoapi.model.Cliente;
import inf.frolich.projetoapi.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import inf.frolich.projetoapi.model.Cliente;
import java.util.List;

@RestController
@RequestMapping("/clientes")

public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        if(!clienteRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtt){
        if(!clienteRepository.existsById(id)){ return ResponseEntity.notFound().build(); }

        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if(cliente != null){
            cliente.setNome(clienteAtt.getNome());
            Cliente clienteAttBD = clienteRepository.save(cliente);
            return ResponseEntity.ok(clienteAttBD);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
