package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;


public record DatosRegistroPaciente(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "^[0-9]{1,20}$")
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documentoIdentidad,
        @NotNull // es un objeto, así que usamos notNull
        @Valid
        DatosDireccion direccion
) {
}
