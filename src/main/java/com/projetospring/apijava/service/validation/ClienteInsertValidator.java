package com.projetospring.apijava.service.validation;

import com.projetospring.apijava.domain.enums.TipoCliente;
import com.projetospring.apijava.dto.ClienteNewDTO;
import com.projetospring.apijava.resource.exception.FieldMessage;
import com.projetospring.apijava.service.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// ConstraintValidator<NomeDaAnotacao, TipoDaAnotacao>
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CPF inválido"));
        }

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}