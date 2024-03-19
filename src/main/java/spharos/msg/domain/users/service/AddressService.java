package spharos.msg.domain.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spharos.msg.domain.users.dto.NewAddressRequestDto;
import spharos.msg.domain.users.entity.Address;
import spharos.msg.domain.users.repository.AddressRepository;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public void createNewAddress(NewAddressRequestDto newAddressRequestDto){
        addressRepository.save(Address
            .builder()
            .addressName(newAddressRequestDto.getAddressName())
            .recipient(newAddressRequestDto.getRecipient())
            .mobileNumber(newAddressRequestDto.getMobileNumber())
            .addressPhoneNumber(newAddressRequestDto.getAddressPhoneNumber())
            .address(newAddressRequestDto.getAddress())
            .users(newAddressRequestDto.getUsers())
            .build());
    }
}
