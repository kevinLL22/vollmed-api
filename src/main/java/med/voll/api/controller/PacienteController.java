package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository medicoRepository) {
        this.pacienteRepository = medicoRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaPaciente> registrarPacientes(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                                     UriComponentsBuilder builder){

        PacienteEntity paciente = pacienteRepository.save(new PacienteEntity(datosRegistroPaciente));
        DatosRespuestaPaciente respuestaPaciente = new DatosRespuestaPaciente(paciente);

        URI url = builder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(url).body(respuestaPaciente);
    }

    @GetMapping
    public ResponseEntity<Page <DatosListadoPaciente>> listadoPacientes(Pageable pageable){
        return ResponseEntity.ok(pacienteRepository.findAll(pageable).map(DatosListadoPaciente::new) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> retornarDatosMedicos(@PathVariable Long id){
        PacienteEntity paciente = pacienteRepository.getReferenceById(id);
        DatosRespuestaPaciente datosRespuestaMedico = new DatosRespuestaPaciente(paciente);
        return ResponseEntity.ok(datosRespuestaMedico);
    }

    @PutMapping
    @Transactional
    public void actualizarPaciente(@RequestBody@Valid DatosActualizarPaciente datosActualizarPaciente){

        Optional<PacienteEntity> optionalPaciente = pacienteRepository.findById(datosActualizarPaciente.id());
        if (optionalPaciente.isPresent()){
            PacienteEntity paciente = optionalPaciente.get();
            paciente.actualizarPaciente(datosActualizarPaciente);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarPaciente(@PathVariable Long id){
        pacienteRepository.deleteById(id);
    }


}
