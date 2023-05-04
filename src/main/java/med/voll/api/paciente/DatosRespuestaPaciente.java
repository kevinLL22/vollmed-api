package med.voll.api.paciente;

import med.voll.api.direccion.DatosDireccion;

public record DatosRespuestaPaciente(Long id, String nombre, String email,
                                     String telefono, String documento, DatosDireccion direccion) {
    public DatosRespuestaPaciente(PacienteEntity paciente){

        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(),
                paciente.getTelefono(), paciente.getDocumentoIdentidad(), new DatosDireccion(paciente.getDireccion()) );

    }
}
