package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    public void registrarMedicos(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){
        medicoRepository.save(new MedicoEntity(datosRegistroMedico));
    }

    @GetMapping                                 //Pageable viene del frondend, pero ya tiene datos por default
    public Page<DatosListadoMedico> listadoMedicos(Pageable pageable){
        //return medicoRepository.findAll(pageable).map(DatosListadoMedico::new);  //spring devuelve automaticamente un json
        return medicoRepository.findByActivoTrue(pageable).map(DatosListadoMedico::new);
    }

    //todo modificar método del put con save
    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        MedicoEntity medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
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
    public void eliminar(@PathVariable Long id){
        MedicoEntity medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }}
