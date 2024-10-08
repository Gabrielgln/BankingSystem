package com.accenture.academico.bankingsystem.services;
import com.accenture.academico.bankingsystem.domain.agency.Agency;
import com.accenture.academico.bankingsystem.dtos.agency.AgencyRequestDTO;
import com.accenture.academico.bankingsystem.dtos.agency.AgencyResponseDTO;
import com.accenture.academico.bankingsystem.exceptions.ConflictException;
import com.accenture.academico.bankingsystem.exceptions.NotFoundException;
import com.accenture.academico.bankingsystem.mappers.agency.AgencyMapper;
import com.accenture.academico.bankingsystem.repositories.AgencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgencyService {

    private final AgencyRepository agencyRepository;
    private final AddressService addressService;

    public List<AgencyResponseDTO> getAllAgencies() {
        List<Agency> agencyList = this.agencyRepository.findAll();
        return AgencyMapper.convertToAgencyResponseDTOList(agencyList);
    }

    public AgencyResponseDTO getAgencyById(UUID id) {
        Agency agency = this.findById(id);
        return AgencyMapper.convertToAgencyResponseDTO(agency);
    }

    public Agency getAgencyInternalById(UUID id) {
        return this.findById(id);
    }

    public AgencyResponseDTO createAgency(AgencyRequestDTO agencyDTO) {
        if (agencyRepository.existsAgencyByNumberAndName(agencyDTO.number(), agencyDTO.name()))
            throw new ConflictException("Agency already exists ");

        Agency agency = new Agency();
        agency.setName(agencyDTO.name());
        agency.setTelephone(agencyDTO.telephone());
        agency.setNumber(agencyDTO.number());

        agency.setAddress(addressService.getAddressById(agencyDTO.addressId()));

        this.agencyRepository.save(agency);

        return AgencyMapper.convertToAgencyResponseDTO(agency);
    }

    public AgencyResponseDTO updateAgency(UUID id, AgencyResponseDTO agencyDTO) {
        Agency agency = this.findById(id);

        if (agencyDTO.name() != null) agency.setName(agencyDTO.name());

        if (agencyDTO.telephone() != null) agency.setTelephone(agencyDTO.telephone());

        if (agencyDTO.number() != null) agency.setNumber(agencyDTO.number());

        if (agencyDTO.address_id() != null) agency.setAddress(addressService.getAddressById(agencyDTO.address_id()));

        this.agencyRepository.save(agency);
        return AgencyMapper.convertToAgencyResponseDTO(agency);
    }

    public void deleteAgency(UUID id) {
        this.agencyRepository.delete(this.findById(id));
    }

    public Agency findById(UUID id) {
        return this.agencyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Agency not found with ID: " + id));
    }
}