package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.MedicoEntity;
import med.voll.api.paciente.DatosRegistroPaciente;
import med.voll.api.paciente.PacienteEntity;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository medicoRepository) {
        this.pacienteRepository = medicoRepository;
    }

    @PostMapping
    public void registrarMedicos(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){
        pacienteRepository.save(new PacienteEntity(datosRegistroPaciente));
    }
}
