package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.MedicoEntity;
import med.voll.api.paciente.DatosListadoPaciente;
import med.voll.api.paciente.DatosRegistroPaciente;
import med.voll.api.paciente.PacienteEntity;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
}
