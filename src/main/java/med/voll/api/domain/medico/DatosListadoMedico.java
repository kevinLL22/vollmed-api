package med.voll.api.domain.medico;

public record DatosListadoMedico(Long id,String nombre, String especialidad, String documento, String email) {

    public DatosListadoMedico(MedicoEntity medicoEntity){
        this(medicoEntity.getId(), medicoEntity.getNombre(), medicoEntity.getEspecialidad().toString(),
                medicoEntity.getDocumento(), medicoEntity.getEmail());

    }
}
