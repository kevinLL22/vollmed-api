package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedicos(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                           UriComponentsBuilder uriComponentsBuilder){
        MedicoEntity medico = medicoRepository.save(new MedicoEntity(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico);

        //creando uri
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping                                 //Pageable viene del frondend, pero ya tiene datos por default
    public Page<DatosListadoMedico> listadoMedicos(Pageable pageable){
        //return medicoRepository.findAll(pageable).map(DatosListadoMedico::new);  //spring devuelve automaticamente un json
        return medicoRepository.findByActivoTrue(pageable).map(DatosListadoMedico::new);
    }

    //todo modificar método del put con save
    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        MedicoEntity medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico);
        return ResponseEntity.ok(datosRespuestaMedico);
    }

    /* delete total:
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id){
        MedicoEntity medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    } */

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        MedicoEntity medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }
}
