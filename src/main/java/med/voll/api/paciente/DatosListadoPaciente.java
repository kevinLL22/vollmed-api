package med.voll.api.paciente;

public record DatosListadoPaciente(
        String nombre,
        String email,
        String documento_identidad) {

    public DatosListadoPaciente(PacienteEntity pacienteEntity){
        this(pacienteEntity.getNombre(), pacienteEntity.getEmail(), pacienteEntity.getDocumentoIdentidad());
    }

}
