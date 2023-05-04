package med.voll.api.domain.paciente;

public record DatosListadoPaciente(
        String nombre,
        String email,
        String documento_identidad) {

    public DatosListadoPaciente(PacienteEntity pacienteEntity){
        this(pacienteEntity.getNombre(), pacienteEntity.getEmail(), pacienteEntity.getDocumentoIdentidad());
    }

}
