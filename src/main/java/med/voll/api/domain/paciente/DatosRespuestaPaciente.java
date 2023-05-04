package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(Long id, String nombre, String email,
                                     String telefono, String documento, DatosDireccion direccion) {
    public DatosRespuestaPaciente(PacienteEntity paciente){

        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(),
                paciente.getTelefono(), paciente.getDocumentoIdentidad(), new DatosDireccion(paciente.getDireccion()) );

    }
}
