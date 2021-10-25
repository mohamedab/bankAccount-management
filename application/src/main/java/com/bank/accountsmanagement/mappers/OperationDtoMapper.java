package com.bank.accountsmanagement.mappers;

import com.bank.accountsmanagement.dto.OperationDto;
import com.bank.accountsmanagement.models.Operation;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OperationDtoMapper {

    OperationDto toDto(Operation operation);

    Operation fromDto(OperationDto operationDto);

    List<OperationDto> toListDto(List<Operation> operations);
}
