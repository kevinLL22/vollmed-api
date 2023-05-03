package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository medicoRepository) {
        this.pacienteRepository = medicoRepository;
    }

    @PostMapping
    public void registrarPacientes(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){
        pacienteRepository.save(new PacienteEntity(datosRegistroPaciente));
    }

    @GetMapping
    public Page <DatosListadoPaciente> listadoPacientes(Pageable pageable){
        return pacienteRepository.findAll(pageable).map(DatosListadoPaciente::new);
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
